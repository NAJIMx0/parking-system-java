package com.najim.Service;

import com.najim.DAO.CarDAO;
import com.najim.DAO.PaymentDAO;
import com.najim.DAO.SpotDAO;
import com.najim.DAO.TicketDAO;
import com.najim.model.Car;
import com.najim.model.Payment;
import com.najim.model.Spot;
import com.najim.model.Ticket;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ParkingService {

//    park car, exit car, check availability
    public static Ticket parkCar(Car car, String type) throws Exception {

        Car dbCar = CarDAO.getCarByplateNumber(car.getPlateNumber());
        if (dbCar == null) {
            CarDAO.saveCar(car);
        } else {
            car = dbCar;
        }

        Optional<Spot> spotOpt = SpotDAO.getFreeSpots()
                .stream()
                .filter(s -> s.getType().equals(type))
                .findFirst();

        if (spotOpt.isEmpty()) {
            throw new IllegalStateException("No available spot for type: " + type);
        }

        Spot spot = spotOpt.get();

        Ticket ticket = new Ticket(
                LocalDateTime.now(),
                type,
                car,
                spot
        );

        TicketDAO.saveTicket(ticket);
        markOccupied(spot);
        return ticket;
    }

    public static Payment exitCar(String platnumber, String paymentMethod) throws Exception {

        Car dbCar = CarDAO.getCarByplateNumber(platnumber);
        if (dbCar != null) {

            Ticket ticket = TicketDAO.getTicketByCarId(dbCar.getIdCar());
            if (ticket != null) {
                Spot sp = SpotDAO.getSpotById(ticket.getIdSpot());
                if (sp != null) {
                    LocalDateTime entry = ticket.getEntryTime();
                    LocalDateTime exit = LocalDateTime.now();
                    double fee = PaymentService.calculateFee(entry,exit);
//                    private Integer idPayment;
//                    private Double amount;
//                    private LocalDateTime paymentTime;
//                    private String paymentMethod;
//                    private Ticket ticket;
                    Payment py = new Payment(fee , exit ,paymentMethod, ticket.getIdTicket());
                    PaymentDAO.savePayment(py);
                    markFree(sp);
                    return py;
                }

            }
        }
    return null;

    }


    public static List<Spot> findAvailableSpots() throws Exception {
        return SpotDAO.getFreeSpots();
    }
    public static Integer CountAvaibleSpots() throws Exception {
        return findAvailableSpots().stream().mapToInt(s -> s.getIdSpot()).sum();
    }


    public static List<Spot> findAvailableSpotsByType(String type) throws Exception {
        return SpotDAO.getFreeSpotsByType(type);
    }
    public static Integer CountAvaibleSpotsByType(String tp) throws Exception {
        return findAvailableSpotsByType(tp).stream().mapToInt(s -> s.getIdSpot()).sum();
    }

    public static boolean isCarCurrentlyParked(String plateNumber) throws Exception {
        Car cr =CarDAO.getCarByplateNumber(plateNumber);
        if(cr==null) {
            return false;
        }
        Ticket ticket = TicketDAO.getTicketByCarId(cr.getIdCar());
        if(ticket==null) {
            return false;
        }
        Spot spot = SpotDAO.getSpotById(ticket.getIdSpot());

        return spot != null && spot.getStatus().equals("OCCUPIED");


    }

    public static List<Ticket> getCurrentlyParkedCars() throws Exception {
        return TicketDAO.getActiveTickets();
    }
    public static  void markOccupied(Spot spot) throws Exception{
        SpotDAO.updateSpotStatus(spot.getIdSpot(), "OCCUPIED");
    }
    public static  void markFree(Spot spot) throws Exception{
        SpotDAO.updateSpotStatus(spot.getIdSpot(), "FREE");
    }

}
