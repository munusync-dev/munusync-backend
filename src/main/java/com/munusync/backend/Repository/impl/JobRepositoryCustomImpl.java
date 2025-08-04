package com.munusync.backend.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import com.munusync.backend.dto.request.Job.JobFeedFilter;
import com.munusync.backend.entity.Job;
import com.munusync.backend.repository.JobRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class JobRepositoryCustomImpl implements JobRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Slice<Job> findJobs(UUID cursor, JobFeedFilter filters, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Job> query = cb.createQuery(Job.class);
        Root<Job> job = query.from(Job.class);

        List<Predicate> predicates = buildPredicates(cb, job, cursor, filters, pageable);

        if (!predicates.isEmpty()) {
            query.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        List<Order> orders = buildOrderBy(cb, job, pageable);

        query.orderBy(orders);

        TypedQuery<Job> typedQuery = entityManager.createQuery(query);

        typedQuery.setMaxResults(pageable.getPageSize() + 1);

        List<Job> results = typedQuery.getResultList();

        boolean hasNext = results.size() > pageable.getPageSize();
        if (hasNext) {
            results.remove(results.size() - 1);
        }
        return new SliceImpl<>(results, pageable, hasNext);
    }

    private List<Order> buildOrderBy(CriteriaBuilder cb, Root<Job> job, Pageable pageable) {
        List<Order> orders = new ArrayList<>();

        if (pageable.getSort().isSorted()) {
            for (Sort.Order sortorder : pageable.getSort()) {
                if (sortorder.isAscending()) {
                    orders.add(cb.asc(job.get(sortorder.getProperty())));
                } else {
                    orders.add(cb.desc(job.get(sortorder.getProperty())));
                }
            }
        } else {
            orders.add(cb.desc(job.get("createdAt")));
        }
        return orders;
    }

    private List<Predicate> buildPredicates(CriteriaBuilder cb, Root<Job> job, UUID cursor, JobFeedFilter filters,
            Pageable pageable) {

        List<Predicate> predicates = new ArrayList<>();

        if (cursor != null) {
            addCursorPredicate(cb, job, cursor, pageable, predicates);
        }

        if (filters != null) {
            addFilterPredicates(cb, job, filters, predicates);
        }

        return predicates;
    }

    private void addCursorPredicate(CriteriaBuilder cb, Root<Job> job, UUID cursor, Pageable pageable,
            List<Predicate> predicates) {

        try {
            Sort.Order sortOrder = pageable.getSort().stream().findFirst().orElse(Sort.Order.desc("createdAt"));

            String sortProperty = sortOrder.getProperty();

            CriteriaQuery<Object> cursorQuery = cb.createQuery(Object.class);
            Root<Job> cursorJob = cursorQuery.from(Job.class);
            cursorQuery.select(cursorJob.get(sortProperty)).where(cb.equal(cursorJob.get("id"), cursor));

            Object cursorValue = entityManager.createQuery(cursorQuery).getSingleResult();

            if (sortOrder.isAscending()) {
                predicates.add(cb.greaterThan(job.get(sortProperty), (Comparable) cursorValue));
            } else {
                predicates.add(cb.lessThan(job.get(sortProperty), (Comparable) cursorValue));
            }
        } catch (NoResultException e) {
            predicates.add(cb.disjunction());
        }

    }

    private void addFilterPredicates(CriteriaBuilder cb, Root<Job> job, JobFeedFilter filters,
            List<Predicate> predicates) {

        if (filters.getJobType() != null) {
            predicates.add(cb.equal(job.get("jobType"), filters.getJobType()));
        }
        if (filters.getWorkType() != null) {
            predicates.add(cb.equal(job.get("workType"), filters.getWorkType()));
        }
        if (filters.getExperienceLevel() != null) {
            predicates.add(cb.equal(job.get("experienceLevel"), filters.getExperienceLevel()));
        }
        if (StringUtils.hasText(filters.getLocation())) {
            predicates.add(cb.equal(job.get("location"), filters.getLocation()));
        }
        if (StringUtils.hasText(filters.getDepartment())) {
            String likePattern = "%" + filters.getDepartment().toLowerCase() + "%";
            predicates.add(cb.like(cb.lower(job.get("department")), likePattern));
        }
        if (filters.getMinSalary() != null && filters.getMinSalary() >= 0) {
            predicates.add(cb.greaterThanOrEqualTo(job.get("salary_min"), filters.getMinSalary()));
        }
        if (filters.getMaxSalary() != null && filters.getMaxSalary() < Long.MAX_VALUE) {
            predicates.add(cb.lessThanOrEqualTo(job.get("salary_max"), filters.getMaxSalary()));
        }
        if (filters.getIsFeatured() != null) {
            predicates.add(cb.equal(job.get("isFeatured"), filters.getIsFeatured()));
        }
    }

}
