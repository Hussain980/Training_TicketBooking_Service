package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.TicketBooking;
import com.example.demo.service.TicketBookingService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Validated
public class TicketBookingController {

	private static final Logger logger = LoggerFactory.getLogger(TicketBookingController.class);
	
	@Autowired
	TicketBookingService ticketService;

	@Value("${server.port}")
	private int portNumber;

	@ApiOperation(value = "server port number")
	@GetMapping("/port")
	public int getPortNumber() {
		return portNumber;
	}

	@PostMapping("/ticketBooking")
	public String bookTicket(@Valid @RequestBody TicketBooking ticket) {
		logger.info("Inside ticket booking method ===========  {} ");
		return ticketService.bookTicket(ticket);

	}

	@GetMapping("/bookingHistory")
	public List<TicketBooking> getBookingHistory(@RequestParam @NotEmpty(message = "userId can not be empty") String userId) {
         return ticketService.getBookingHistory(userId);
	}
}