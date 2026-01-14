package com.najim.Service;

import com.najim.DAO.CarDAO;
import com.najim.DAO.SpotDAO;
import com.najim.DAO.TicketDAO;
import com.najim.model.Car;
import com.najim.model.Payment;
import com.najim.model.Spot;
import com.najim.model.Ticket;
import com.najim.synchronization.ParkingQueue;
import com.najim.synchronization.PaymentProcessor;
import com.najim.synchronization.SpotAllocator;

import java.time.LocalDateTime;
import java.util.List;

public class ParkingService {

//    park car, exit car, check availability

    private static final SpotAllocator spotAllocator = new SpotAllocator();
    private static final ParkingQueue parkingQueue = new ParkingQueue(60);
    private static final PaymentProcessor paymentProcessor = new PaymentProcessor();

    public static Ticket parkCar(Car car, String type) throws Exception {

        parkingQueue.waitForSpot();
        try{
            Car dbCar = CarDAO.getCarByplateNumber(car.getPlateNumber());
            if (dbCar == null) {
                CarDAO.saveCar(car);
            } else {
                car = dbCar;
            }
            // check by ticket if the car is alredy parked

            Ticket existingTicket = TicketDAO.getTicketByCarId(car.getIdCar());
            if (existingTicket != null) {
                Spot existingSpot = SpotDAO.getSpotById(existingTicket.getIdSpot());
                if (existingSpot != null && "OCCUPIED".equals(existingSpot.getStatus())) {
                    System.out.println("ERROR: Car already parked at spot " + existingSpot.getSpotNumber());
                    parkingQueue.releasePendingSpot();
                    return null;
                }
            }

            Spot spot = spotAllocator.allocateSpot(type);
//        Optional<Spot> spotOpt = SpotDAO.getFreeSpots()
//                .stream()
//                .filter(s -> s.getType().equals(type))
//                .findFirst();

            if (spot==null) {
                parkingQueue.releasePendingSpot();  // Release the spot we reserved
                return null;
            }

            Ticket ticket = new Ticket(
                    LocalDateTime.now(),
                    type,
                    car,
                    spot
            );

            TicketDAO.saveTicket(ticket);

            return ticket;
        } catch (Exception e) {
            parkingQueue.releasePendingSpot();
            throw e;
        }

    }

    public static Payment exitCar(String platnumber, String paymentMethod) throws Exception {

        Car dbCar = CarDAO.getCarByplateNumber(platnumber);
        if (dbCar == null) {
            System.out.println("ERROR: Car not found");
            return null;
        }

        Ticket ticket = TicketDAO.getTicketByCarId(dbCar.getIdCar());
        if (ticket == null) {
            System.out.println("ERROR: Car is not parked");
            return null;
        }

        Spot sp = SpotDAO.getSpotById(ticket.getIdSpot());
        if (sp == null) {
            System.out.println("ERROR: Spot not found");
            return null;
        }

        // CRITICAL: Check and update spot status atomically
        synchronized (spotAllocator) {
            // Re-fetch spot to ensure latest status
            sp = SpotDAO.getSpotById(ticket.getIdSpot());

            if (!"OCCUPIED".equals(sp.getStatus())) {
                System.out.println("ERROR: Car already exited");
                return null;
            }

            LocalDateTime entry = ticket.getEntryTime();
            LocalDateTime exit = LocalDateTime.now();

            // before reentraanlock
//                    double fee = PaymentService.calculateFee(entry,exit);
//                    Payment py = new Payment(fee , exit ,paymentMethod, ticket.getIdTicket());
//                    PaymentDAO.savePayment(py);

            // after
            //payment event
            Payment py = paymentProcessor.processPayment(entry, exit, paymentMethod, ticket.getIdTicket());
            // end pymetn

            // Free the spot (still inside synchronized block)
            SpotDAO.updateSpotStatus(sp.getIdSpot(), "FREE");

            // Notify waiting cars
            parkingQueue.spotFreed();

            return py;
        }
    }


    public static List<Spot> findAvailableSpots() throws Exception {
        return SpotDAO.getFreeSpots();
    }

    public static List<Spot> findAvailableSpotsByType(String type) throws Exception {
        return SpotDAO.getFreeSpotsByType(type);
    }

    public static boolean isCarCurrentlyParked(String plateNumber) throws Exception {
        Car cr = CarDAO.getCarByplateNumber(plateNumber);
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
}