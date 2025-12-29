package com.najim.model;

import java.time.LocalDateTime;

public class Payment {
    private Integer idPayment;
    private Double amount;
    private LocalDateTime paymentTime;
    private String paymentMethod;
    private Ticket ticket;

    public Payment() {
    }

    public Payment(Double amount, LocalDateTime paymentTime, String paymentMethod, Integer idTicket) {
        this.amount = amount;
        this.paymentTime = paymentTime;
        this.paymentMethod = paymentMethod;
        this.ticket = new Ticket();
        this.ticket.setIdTicket(idTicket);
    }

    public Payment(Double amount, LocalDateTime paymentTime, String paymentMethod, Ticket ticket) {
        this.amount = amount;
        this.paymentTime = paymentTime;
        this.paymentMethod = paymentMethod;
        this.ticket = ticket;
    }

    public Integer getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Integer idPayment) {
        this.idPayment = idPayment;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Integer getIdTicket() {
        return ticket != null ? ticket.getIdTicket() : null;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "idPayment=" + idPayment +
                ", amount=" + amount +
                ", paymentTime=" + paymentTime +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", ticket=" + (ticket != null ? ticket.getIdTicket() : "null") +
                '}';
    }
}