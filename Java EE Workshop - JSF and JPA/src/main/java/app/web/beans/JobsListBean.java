package app.web.beans;

import app.domain.models.view.JobViewModel;
import app.service.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class JobsListBean {

    private List<JobViewModel> jobs;
    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    JobsListBean() {
    }

    @Inject
    public JobsListBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.setJobs(this.jobApplicationService.getAllJobs().stream()
                .map(j -> this.modelMapper.map(j, JobViewModel.class))
                .collect(Collectors.toList()));
        this.jobs.forEach(j -> j.setSector(j.getSector().toLowerCase()));
    }

    public List<JobViewModel> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobViewModel> jobs) {
        this.jobs = jobs;
    }
}
