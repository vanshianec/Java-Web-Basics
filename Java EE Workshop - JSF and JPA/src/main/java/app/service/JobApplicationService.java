package app.service;

import app.domain.models.service.JobApplicationServiceModel;

import java.util.List;

public interface JobApplicationService {

    List<JobApplicationServiceModel> getAllJobs();

    void save(JobApplicationServiceModel job);

    JobApplicationServiceModel getById(String id);

    void delete(String id);
}
