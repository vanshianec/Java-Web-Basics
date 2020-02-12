package services.base;

import models.entity.Car;
import models.service.CarServiceModel;
import org.modelmapper.ModelMapper;
import services.CarService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {

    private final ModelMapper modelMapper;
    private final EntityManager entityManager;

    @Inject
    public CarServiceImpl(ModelMapper modelMapper, EntityManager entityManager) {
        this.modelMapper = modelMapper;
        this.entityManager = entityManager;
    }

    public List<CarServiceModel> getAll() {
        return entityManager.createQuery("select c FROM Car c", Car.class)
                .getResultList()
                .stream()
                .map(car -> this.modelMapper.map(car, CarServiceModel.class))
                .collect(Collectors.toList());
    }
}
