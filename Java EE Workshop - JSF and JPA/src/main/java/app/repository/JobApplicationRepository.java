package app.repository;

import app.domain.entities.JobApplication;

import java.util.List;

public interface JobApplicationRepository {
    List<JobApplication> findAllJobs();

    void save(JobApplication job);

    JobApplication findById(String id);

    void delete(String id);
}
