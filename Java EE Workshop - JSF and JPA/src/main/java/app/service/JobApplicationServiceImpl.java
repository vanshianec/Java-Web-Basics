package app.service;

import app.domain.entities.JobApplication;
import app.domain.models.service.JobApplicationServiceModel;
import app.repository.JobApplicationRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final ModelMapper modelMapper;

    @Inject
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository, ModelMapper modelMapper) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.modelMapper = modelMapper;
    }

    public List<JobApplicationServiceModel> getAllJobs() {
        return this.jobApplicationRepository.findAllJobs()
                .stream()
                .map(j -> this.modelMapper.map(j, JobApplicationServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void save(JobApplicationServiceModel job) {
        this.jobApplicationRepository.save(this.modelMapper.map(job, JobApplication.class));
    }

    @Override
    public JobApplicationServiceModel getById(String id) {
        return this.modelMapper.map(this.jobApplicationRepository.findById(id), JobApplicationServiceModel.class);
    }

    @Override
    public void delete(String id) {
        this.jobApplicationRepository.delete(id);
    }
}
