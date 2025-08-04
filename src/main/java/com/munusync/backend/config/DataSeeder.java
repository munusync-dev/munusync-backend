package com.munusync.backend.config;

import com.munusync.backend.entity.Job;
import com.munusync.backend.entity.Company;
import com.munusync.backend.entity.User;
import com.munusync.backend.entity.enums.JobType;
import com.munusync.backend.entity.enums.ExperienceLevel;
import com.munusync.backend.entity.enums.WorkType;
import com.munusync.backend.entity.enums.JobStatus;
import com.munusync.backend.entity.enums.Role;
import com.munusync.backend.repository.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class DataSeeder implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataSeeder.class);
    private final JobRepository jobRepository;
    private final PasswordEncoder passwordEncoder;
    
    @PersistenceContext
    private EntityManager entityManager;

    public DataSeeder(JobRepository jobRepository, PasswordEncoder passwordEncoder) {
        this.jobRepository = jobRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        
        // Check if data already exists
        if (jobRepository.count() > 0) {
            logger.info("Database already contains data. Skipping data seeding.");
            return;
        }

        logger.info("Database is empty. Seeding sample data...");

        // 1. Create and persist sample users first
        User user1 = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .password(passwordEncoder.encode("password123"))
                .role(Role.USER)
                .build();

        User admin1 = User.builder()
                .firstName("Admin")
                .lastName("User")
                .email("admin@munusync.com")
                .password(passwordEncoder.encode("admin123"))
                .role(Role.ADMIN)
                .build();
        
        entityManager.persist(user1);
        entityManager.persist(admin1);
        entityManager.flush(); // Ensure users are saved before creating companies
        
        logger.info("Created 2 users");

        // 2. Create and persist sample companies using builder pattern
        Company company1 = Company.builder()
                .name("TechCorp Solutions")
                .description("Leading technology solutions provider specializing in enterprise software development and cloud infrastructure.")
                .industry("Technology")
                .city("New York")
                .country("USA")
                .contactEmail("contact@techcorp.com")
                .websiteUrl("https://techcorp.com")
                .logoUrl("https://techcorp.com/logo.png")
                .address("123 Tech Street, Manhattan")
                .employees(150L)
                .build();
        
        Company company2 = Company.builder()
                .name("DataFlow Inc")
                .description("Data analytics and machine learning company helping businesses make data-driven decisions.")
                .industry("Data Analytics")
                .city("San Francisco")
                .country("USA")
                .contactEmail("info@dataflow.com")
                .websiteUrl("https://dataflow.com")
                .logoUrl("https://dataflow.com/logo.png")
                .address("456 Data Avenue, Silicon Valley")
                .employees(75L)
                .build();
        
        Company company3 = Company.builder()
                .name("WebCraft Studio")
                .description("Creative web development agency focused on building beautiful and functional web applications.")
                .industry("Web Development")
                .city("Remote")
                .country("USA")
                .contactEmail("hello@webcraft.com")
                .websiteUrl("https://webcraft.com")
                .logoUrl("https://webcraft.com/logo.png")
                .address("Remote-first company")
                .employees(25L)
                .build();

        entityManager.persist(company1);
        entityManager.persist(company2);
        entityManager.persist(company3);
        entityManager.flush(); // Ensure companies are saved before creating jobs
        
        logger.info("Created 3 companies");

        // 3. Now create jobs with proper relationships
        Job job1 = createJob("Senior Java Developer", 
                                "Expert in Spring Boot, microservices, and cloud platforms. Join our team to build scalable enterprise applications using cutting-edge technologies.",
                                "Must have 5+ years of Java experience, Spring Framework expertise, knowledge of microservices architecture, experience with AWS or Azure, Docker containerization skills.",
                                "Health insurance, dental and vision coverage, 401k with company matching, flexible work hours, professional development budget up to $3000/year, stock options.",
                                JobType.FULL_TIME, ExperienceLevel.senior, WorkType.hybrid,
                                "New York, NY", "contact@techcorp.com", JobStatus.open,
                                120000L, 150000L, "USD", "Engineering",
                                company1, user1);

        Job job2 = createJob("Frontend Engineer", 
                                "Skilled in React, TypeScript, and modern CSS frameworks. Create beautiful user experiences that delight our customers and drive business growth.",
                                "3+ years of React experience, TypeScript proficiency, experience with modern CSS frameworks like Tailwind or Styled Components, knowledge of state management (Redux/Zustand), testing experience with Jest/Cypress.",
                                "100% remote work, health insurance, stock options, annual conference budget, home office stipend, unlimited PTO, learning and development allowance.",
                                JobType.FULL_TIME, ExperienceLevel.mid, WorkType.remote,
                                "Remote", "info@dataflow.com", JobStatus.open,
                                100000L, 130000L, "USD", "Engineering",
                                company2, user1);

        Job job3 = createJob("Data Analyst", 
                                "Proficient in SQL, Python (Pandas), and data visualization tools like Tableau. Help transform raw data into actionable business insights.",
                                "2+ years of SQL experience, Python data analysis skills with Pandas/NumPy, experience with Tableau or similar BI tools (PowerBI, Looker), statistical analysis knowledge, Excel proficiency.",
                                "Flexible schedule, learning stipend, health benefits, remote work options, mentorship opportunities, conference attendance support.",
                                JobType.PART_TIME, ExperienceLevel.junior, WorkType.remote,
                                "Remote", "info@dataflow.com", JobStatus.open,
                                35L, 45L, "USD", "Analytics", // Hourly rate for part-time
                                company2, user1);

        Job job4 = createJob("Junior Java Developer", 
                                "Entry-level position focused on building and maintaining Spring applications. Great learning opportunity with mentorship from senior developers.",
                                "Computer Science degree or equivalent, basic Java knowledge, willingness to learn Spring Framework, understanding of OOP principles, basic SQL knowledge, Git version control experience.",
                                "Comprehensive mentorship program, health insurance, paid time off, career development opportunities, training budget, collaborative work environment.",
                                JobType.FULL_TIME, ExperienceLevel.junior, WorkType.onsite,
                                "Chicago, IL", "contact@techcorp.com", JobStatus.open,
                                60000L, 80000L, "USD", "Engineering",
                                company1, admin1);

        Job job5 = createJob("Full Stack Developer", 
                                "Work with modern web technologies including React, Node.js, and cloud services. Build end-to-end solutions for our creative agency clients.",
                                "Experience with both frontend and backend development, React and modern JavaScript, Node.js and Express.js, database design (PostgreSQL/MongoDB), cloud platforms (AWS/Azure), REST API development.",
                                "Unlimited PTO, 100% remote work, home office stipend up to $1500, health and dental insurance, profit sharing, creative freedom, flexible working hours.",
                                JobType.FULL_TIME, ExperienceLevel.mid, WorkType.remote,
                                "Remote", "hello@webcraft.com", JobStatus.open,
                                110000L, 140000L, "USD", "Engineering",
                                company3, admin1);

        // Save all jobs using the repository
        List<Job> jobs = jobRepository.saveAll(List.of(job1, job2, job3, job4, job5));
        entityManager.flush(); // Ensure save is complete before updating timestamps
        logger.info("Successfully seeded {} jobs into the database.", jobs.size());

        // --- NEW: Update timestamps for deterministic testing ---
        logger.info("Updating job timestamps for cursor pagination testing...");
        LocalDateTime now = LocalDateTime.now();
        
        // These jobs will now have distinct, predictable creation dates
        updateJobCreationTimestamp(jobs.get(0).getId(), now.minusDays(1)); // Yesterday
        updateJobCreationTimestamp(jobs.get(1).getId(), now.minusDays(5)); // 5 days ago
        updateJobCreationTimestamp(jobs.get(2).getId(), now.minusHours(3)); // 3 hours ago
        updateJobCreationTimestamp(jobs.get(3).getId(), now.minusMinutes(10)); // 10 minutes ago
        updateJobCreationTimestamp(jobs.get(4).getId(), now); // Now

        logger.info("Data seeding completed successfully!");
    }
    
    /**
     * NEW HELPER METHOD
     * This method uses a JPQL query to update the createdAt and updatedAt timestamps
     * of a Job, bypassing the Auditing listener for seeding purposes.
     */
    private void updateJobCreationTimestamp(UUID jobId, LocalDateTime newTimestamp) {
        entityManager.createQuery("UPDATE Job j SET j.createdAt = :timestamp, j.updatedAt = :timestamp WHERE j.id = :id")
                .setParameter("timestamp", newTimestamp)
                .setParameter("id", jobId)
                .executeUpdate();
    }
    
    private Job createJob(String title, String description, String requirements, String benefits,
                          JobType jobType, ExperienceLevel experienceLevel, WorkType workType,
                          String location, String contactEmail, JobStatus status,
                          Long salaryMin, Long salaryMax, String currency, String department,
                          Company company, User user) {
        Job job = new Job();
        job.setTitle(title);
        job.setDescription(description);
        job.setRequirements(requirements);
        job.setBenefits(benefits);
        job.setJobType(jobType);
        job.setExperienceLevel(experienceLevel);
        job.setWorkType(workType);
        job.setLocation(location);
        job.setContactEmail(contactEmail);
        job.setStatus(status);
        job.setSalaryMin(salaryMin);
        job.setSalaryMax(salaryMax);
        job.setCurrency(currency);
        job.setDepartment(department);
        job.setCompany(company);
        job.setUser(user);
        job.setApplicants(0L);
        job.setViews(0L);
        job.setIsFeatured(false);
        return job;
    }
}