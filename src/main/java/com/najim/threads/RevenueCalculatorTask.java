package com.najim.threads;

import com.najim.Service.PaymentService;

import java.util.concurrent.Callable;



public class RevenueCalculatorTask implements Callable<Double> {

    @Override
    public Double call() throws Exception {
        return PaymentService.getTotalRevenue();
    }

//    Future<Double> future = executor.submit(new RevenueCalculatorTask());
//    Double revenue = future.get();
}
