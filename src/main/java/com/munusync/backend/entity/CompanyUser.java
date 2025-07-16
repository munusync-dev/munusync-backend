package com.munusync.backend.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "company_users", indexes = {
        @Index(name = "idx_company_id", columnList = "companyId"),
        @Index(name = "idx_user_id", columnList = "userId")
})
public class CompanyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long companyId;

    private Long userId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedAt = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }
}
