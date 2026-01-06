package com.najim.Service;

import com.najim.DAO.PaymentDAO;
import com.najim.Main;
import com.najim.model.Payment;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class PaymentService {


    private static final double FIRST_HOUR_PAY = 2.0;
    private static final double ADDITIONAL_HOUR_PAY = 1.5;
    private static final double MAX_DAILY_PAY = 20.0;
    //    private static final double Discounts = 0.0;// vip
// if 2 hours 15 minutes = ? -- Chargilo  (2.5 hours × rate)

//   static kamlin  calculateFee(long hours)
//calculateFee(LocalDateTime entry, LocalDateTime exit) ta hado b streams ila khask
//getTotalRevenue() streams
//getRevenueByMethod(String method) streams
    // duration between entry time and exit in Hour  sf

    public static double calculateFee(LocalDateTime entry, LocalDateTime exit) {

        long totalMinutes = Duration.between(entry, exit).toMinutes();//150  min
        if (totalMinutes <= 0) {
            return 0.0;
        }
        // 2 + 1.5 +1.5
        long hours = totalMinutes / 60;// 2 h
        long remainingMinutes = totalMinutes % 60; // 30minu

        if (remainingMinutes >= 30) {
            hours++;
        }

        double amount;
        if (hours == 0) {
            amount = 1;
        } else if (hours == 1) {
            amount = FIRST_HOUR_PAY;
        } else {
            amount = FIRST_HOUR_PAY + ((hours - 1) * ADDITIONAL_HOUR_PAY);
        }
        if (amount > 24) {
            amount = MAX_DAILY_PAY;
        }

        return amount;
    }
    public static double calculateFee(long hours) {
        if (hours <= 0) {
            return 0.0;
        }
        double amount;
        if (hours == 1) {
            amount = FIRST_HOUR_PAY;
        } else {
            amount = FIRST_HOUR_PAY + ((hours - 1) * ADDITIONAL_HOUR_PAY);
        }
        if (amount > MAX_DAILY_PAY) {
            amount = MAX_DAILY_PAY;
        }

        return amount;
    }
    public static double getTotalRevenue() throws SQLException {
        List<Payment> payments = PaymentDAO.getAllPayments();

        return payments.stream()
                .mapToDouble(py-> py.getAmount())
                .sum();
    }
    public static double getRevenueByMethod(String method) throws SQLException {
        List<Payment> payments = PaymentDAO.getPaymentsByMethod(method);

        return payments.stream()
                .mapToDouble(py-> py.getAmount())
                .sum();
    }




}

