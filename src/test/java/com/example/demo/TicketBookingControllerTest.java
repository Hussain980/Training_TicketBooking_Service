
package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.controller.TicketBookingController;
import com.example.demo.entity.PassengerDetails;
import com.example.demo.entity.TicketBooking;
import com.example.demo.exception.TrainNotFoundException;
import com.example.demo.feign.Train;
import com.example.demo.feign.User;
import com.example.demo.service.TicketBookingService;

/**
 * @author mohd.hussain
 *
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TicketBookingControllerTest {

	@Mock
	TicketBookingService ticketService;

	@InjectMocks
	TicketBookingController ticketController;

	static TicketBooking ticketBooking1;
	
	static TicketBooking ticketBooking2;

	static PassengerDetails details;

	static Train train;

	static User user;

	@BeforeAll
	public static void setup() {
		ticketBooking1 = new TicketBooking();
		ticketBooking2 = new TicketBooking();
		details = new PassengerDetails();
		train = new Train();
		user = new User();
		LocalDate date = LocalDate.of(2021, 7, 12);
		train.setDateOfJourney(date);
		train.setDestination("delhi");
		train.setSource("bengaluru");
		train.setSeats(10);
		train.setTrainNumber(38920L);
		train.setTrainName("KSBD");
		user.setUserName("hussain@gmail.com");
		details.setAadharId("58721288");
		details.setUserName("hussain");
		ticketBooking1.setDetail(details);
		ticketBooking1.setSeats(2);
		ticketBooking1.setTrainNumber(38920L);
		ticketBooking1.setUserId("hussain@gmail.com");
		
		ticketBooking2.setDetail(details);
		ticketBooking2.setSeats(1);
		ticketBooking2.setTrainNumber(32920L);
		ticketBooking2.setUserId("hussain@gmail.com");

	}

	@Test
	@DisplayName("ticket booking : positive scenario")
	public void testBookTicket() {
		when(ticketService.bookTicket(ticketBooking1)).thenReturn("booking success");
		String result = ticketController.bookTicket(ticketBooking1);
		assertEquals("booking success", result);

	}

	@Test
	@DisplayName("ticket booking : negative scenario")
	public void testBookTicket1() {
		when(ticketService.bookTicket(ticketBooking1)).thenThrow(TrainNotFoundException.class);
		assertThrows(TrainNotFoundException.class, () -> ticketController.bookTicket(ticketBooking1));

	}
	
	@Test
	@DisplayName("ticket booking : positive scenario")
	public void testGetBookingHistory() {
	 	List<TicketBooking> ticketBookingList = new ArrayList<>();
      	ticketBookingList.add(ticketBooking1);
      	ticketBookingList.add(ticketBooking2);
		when(ticketService.getBookingHistory(ticketBooking1.getUserId())).thenReturn(ticketBookingList);
		List<TicketBooking> ticketBooking = ticketController.getBookingHistory(ticketBooking1.getUserId());
		assertEquals(ticketBookingList, ticketBooking);

	}
	
	@Test
	@DisplayName("ticket booking : negative scenario")
	public void testGetBookingHistory1() {
	 	List<TicketBooking> ticketBookingList = new ArrayList<>();
      	ticketBookingList.add(ticketBooking1);
      	ticketBookingList.add(ticketBooking2);
		when(ticketService.getBookingHistory(ticketBooking1.getUserId())).thenThrow(TrainNotFoundException.class);
		assertThrows(TrainNotFoundException.class, () -> ticketController.getBookingHistory(ticketBooking1.getUserId()));

	}

}
