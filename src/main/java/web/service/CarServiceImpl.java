package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.CarDao;
import web.model.Car;

import java.util.List;

@Service
public class CarServiceImpl implements CarService, ApplicationListener<ContextRefreshedEvent> {

    private final CarDao carDao;

    @Autowired
    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initializeCars();
    }

    private void initializeCars() {
        carDao.addCar(new Car("Toyota", 10, "Black"));
        carDao.addCar(new Car("Honda", 20, "Yellow"));
        carDao.addCar(new Car("Ford", 30, "Red"));
        carDao.addCar(new Car("Kia", 40, "White"));
        carDao.addCar(new Car("Nissan", 50, "Silver"));
    }

    @Override
    public void addCar(Car car) {
        carDao.addCar(car);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Car> getAllCars() {
        return carDao.getCars();
    }

    @Transactional(readOnly = true)
    public List<Car> getCars(int count) {
        List<Car> cars = carDao.getCars();
        if (count >= cars.size() || count <= 0) {
            return cars;
        }
        return cars.subList(0, count);
    }
}
