package app.web.beans;

import app.domain.models.view.JobViewModel;
import app.service.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class DeleteBean extends BaseBean{

    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public DeleteBean(){}

    @Inject
    public DeleteBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    public JobViewModel getJobById(String id){
        return this.modelMapper.map(this.jobApplicationService.getById(id), JobViewModel.class);
    }

    public void delete(){
        String id = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("id");
        this.jobApplicationService.delete(id);
        this.redirect("/home");
    }
}
