package com.example.demo.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TicketBooking;
import com.example.demo.exception.TrainNotFoundException;
import com.example.demo.feign.Train;
import com.example.demo.feign.TrainClient;
import com.example.demo.feign.User;
import com.example.demo.feign.UserClient;
import com.example.demo.repository.TicketBookingRepository;
import com.example.demo.service.TicketBookingService;

@Service
public class TicketBookingServiceImpl implements TicketBookingService {

	private static final Logger logger = LoggerFactory.getLogger(TicketBookingServiceImpl.class);

	@Autowired
	TrainClient trainClient;

	@Autowired
	UserClient userClient;

	@Autowired
	TicketBookingRepository ticketRepo;

	@Override
	@Transactional
	/*
	 * @HystrixCommand(fallbackMethod = "defaultMethod", commandProperties = {
	 * 
	 * @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
	 * value = "300000") })
	 */
	public String bookTicket(TicketBooking ticket) {
		Train train = trainClient.getTrainDetails(ticket.getTrainNumber());
		User user = userClient.getUserByUserName(ticket.getUserId());

		if (train == null) {
			throw new TrainNotFoundException("Train Not Found");
		} else if (train.getSeats() < ticket.getSeats()) {
			throw new TrainNotFoundException("Seats are not available");
		} else if (user == null) {
			throw new TrainNotFoundException("user id does not exist");
		}

		train.setTrainNumber(ticket.getTrainNumber());

		int availableSeats = train.getSeats() - ticket.getSeats();
		logger.debug("available seats are  ===== {}  " + availableSeats);
		train.setSeats(availableSeats);

		logger.info("Going to update Train Seats ===== {}");
		trainClient.UpdateSeats(train);
		ticketRepo.save(ticket);
		logger.info("Ticket Booked Successfully ===== {}");
		return "booking success";

	}

	@Override
	public List<TicketBooking> getBookingHistory(String userId) {
		List<TicketBooking> lists = ticketRepo.findByUserId(userId);
		if (lists.isEmpty()) {
			throw new TrainNotFoundException("Please enter valid user Id");
		}
		return lists;
	}

	/*
	 * public String defaultMethod(TicketBooking ticket) { return
	 * "Server is down, please try later!!"; }
	 */
}
