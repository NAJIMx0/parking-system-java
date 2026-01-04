package com.najim.Service;

import java.time.LocalDateTime;

public class PaymentService {

    private static final double FIRST_HOUR_RATE = 2.0; //taman d sa3a (lowla) dayman 1h = 2 dh
    private static final double ADDITIONAL_HOUR_RATE = 1.5; // taman li (gaytzad )kola sa3a  2h = 2dh +1.5dh
    private static final double MAX_DAILY_RATE = 20.0; // max d nhar ila parka 3andk 20 dh
    private static final double MIN_RATE = 2.0;// ila dkhal ga 15 min


//    private static final double Discounts = 0.0;// vip
// if 2 hours 15 minutes = ? -- Chargilo  (2.5 hours × rate)

//   static kamlin  calculateFee(long hours)
//calculateFee(LocalDateTime entry, LocalDateTime exit) ta hado b streams ila khask
//getTotalRevenue() streams
//getRevenueByMethod(String method) streams
    // duration between entry time and exit in Hour  sf


 public  static  double calculateFee(LocalDateTime entry, LocalDateTime exit){
     return 0.0;
 }


}

