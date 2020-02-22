package app.web.beans;

import app.domain.models.view.JobViewModel;
import app.service.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DetailsBean {

    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public DetailsBean(){}

    @Inject
    public DetailsBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    public JobViewModel getJobById(String id){
        return this.modelMapper.map(this.jobApplicationService.getById(id), JobViewModel.class);
    }
}
