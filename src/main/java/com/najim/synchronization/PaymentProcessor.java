package com.najim.synchronization;

import com.najim.DAO.PaymentDAO;
import com.najim.Service.PaymentService;
import com.najim.model.Payment;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class PaymentProcessor {
    private ReentrantLock lock = new ReentrantLock();

public static Payment processPayment(LocalDateTime entry , LocalDateTime exit ,String pymethod, Integer tkId) throws SQLException {
        double fee = PaymentService.calculateFee(entry,exit);
        Payment py = new Payment(fee , exit ,pymethod, tkId);
        PaymentDAO.savePayment(py);
        return py;
    }
}
