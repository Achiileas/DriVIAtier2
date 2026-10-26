package sep3.drivia.tier2.service;

import sep3.drivia.tier2.dto.CarResponse;
import sep3.drivia.tier2.grpc.CarDataClient;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

  private final CarDataClient carDataClient;

  public CarService(CarDataClient carDataClient) {
    this.carDataClient = carDataClient;
  }

  public List<CarResponse> getAllCars() {

    return carDataClient.getAllCars()
        .getCarsList()
        .stream()
        .map(car -> new CarResponse(
            car.getId(),
            car.getModel(),
            car.getCategory(),
            car.getLocation()
        ))
        .toList();
  }
}