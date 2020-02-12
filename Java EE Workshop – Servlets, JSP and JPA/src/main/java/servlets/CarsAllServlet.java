package servlets;

import models.view.CarViewModel;
import org.modelmapper.ModelMapper;
import services.CarService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/cars/all")
public class CarsAllServlet extends HttpServlet {

    private final CarService carService;
    private final ModelMapper modelMapper;

    @Inject
    public CarsAllServlet(CarService carService, ModelMapper modelMapper){
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CarViewModel> cars = this.carService.getAll()
                .stream()
                .map(car -> this.modelMapper.map(car, CarViewModel.class))
                .collect(Collectors.toList());
        req.setAttribute("viewModel", cars);
        req.getRequestDispatcher("/cars-all.jsp")
                .forward(req, resp);

    }
}
