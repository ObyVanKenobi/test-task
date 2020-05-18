package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirPlaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.service.airplane.AirplaneService;
import com.mcb.creditfactory.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// TODO: reimplement this
@Service
public class CollateralService {
    @Autowired
    private CarService carService;

    @Autowired
    private AirplaneService airplaneService;

    @SuppressWarnings("ConstantConditions")
    public Long saveCollateral(Collateral object) {

        CarDto car;
        AirPlaneDto airPlane;


        /*if (!(object instanceof CarDto) && !(object instanceof AirPlaneDto)) {
            throw new IllegalArgumentException();
        }*/


        if (object instanceof CarDto) {
            car = (CarDto) object;



            boolean approved = carService.approve(car);
            if (!approved) {
                return null;
            }


            return Optional.of(car)
                    .map(carService::fromDto)
                    .map(carService::save)
                    .map(carService::getId)
                    .orElse(null);
        }

        else if (object instanceof AirPlaneDto) {
            airPlane = (AirPlaneDto) object;



            boolean approved = airplaneService.approve(airPlane);
            if (!approved) {
                return null;
            }


            return Optional.of(airPlane)
                    .map(airplaneService::fromDto)
                    .map(airplaneService::save)
                    .map(airplaneService::getId)
                    .orElse(null);
        }


        else  throw new IllegalArgumentException();


    }

        public Collateral getInfo (Collateral object){
            /*if (!(object instanceof CarDto)) {
                throw new IllegalArgumentException();
            }*/

           if (object instanceof CarDto) {


                return Optional.of((CarDto) object)
                        .map(carService::fromDto)
                        .map(carService::getId)
                        .flatMap(carService::load)
                        .map(carService::toDTO)
                        .orElse(null);
            }


            else if (object instanceof AirPlaneDto) {

                return Optional.of((AirPlaneDto) object)
                        .map(airplaneService::fromDto)
                        .map(airplaneService::getId)
                        .flatMap(airplaneService::load)
                        .map(airplaneService::toDTO)
                        .orElse(null);
            }


            else throw new IllegalArgumentException();

            }


}
