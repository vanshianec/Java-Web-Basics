package app.domain.repository;

import app.domain.entities.Car;

import java.util.ArrayList;
import java.util.List;

//add static class instead of object from database just for practice

public final class CarStorage {

    private static List<Car> cars = new ArrayList<>();

    public static void addCar(Car car){
        cars.add(car);
    }

    public static List<Car> getCars(){
        return cars;
    }
}
