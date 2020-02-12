package config;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;

public class ModelMapperConfiguration {

    @Produces
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
