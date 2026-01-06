package com.najim.synchronization;

import com.najim.DAO.PaymentDAO;
import com.najim.Service.PaymentService;
import com.najim.model.Payment;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class PaymentProcessor {
    private static ReentrantLock lock = new ReentrantLock();

public static Payment processPayment(LocalDateTime entry , LocalDateTime exit ,String pymethod, Integer tkId) throws Exception {
    if(lock.tryLock(5, TimeUnit.SECONDS)){
        try {
            double fee = PaymentService.calculateFee(entry,exit);
            Payment py = new Payment(fee , exit ,pymethod, tkId);
            PaymentDAO.savePayment(py);
            return py;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            lock.unlock();
        }
    }else{
        throw new Exception("Payment processing timeout - too busy!");
    }
    return null;
}
}
