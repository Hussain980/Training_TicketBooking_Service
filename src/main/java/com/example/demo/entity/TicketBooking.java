package com.example.demo.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The TicketBooking entity, to store the TicketBooking's information")
@Entity
public class TicketBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ApiModelProperty(notes = "The userId of the user for ticket booking")
	@NotEmpty(message = "userId is required")
	private String userId;
	
	@ApiModelProperty(notes = "The train number of the train for ticket booking")
	@NotNull(message = "train number is required")
	private Long trainNumber;
	
	@ApiModelProperty(notes = "The number of seats for ticket booking")
	@NotNull(message = "number of seats required")
	private Integer seats; 
	
	@Embedded
	@Valid
	private PassengerDetails detail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(Long trainNumber) {
		this.trainNumber = trainNumber;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public PassengerDetails getDetail() {
		return detail;
	}

	public void setDetail(PassengerDetails detail) {
		this.detail = detail;
	}
}
