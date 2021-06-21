package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.TicketBooking;

@Service
public interface TicketBookingService {

	String bookTicket(TicketBooking ticket);

	
	List<TicketBooking> getBookingHistory(String userId);
}
