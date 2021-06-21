package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url ="http://localhost:8085/train/api" , value="TrainService")
public interface TrainClient {
	

	@GetMapping("/getTrainByNumber/{trainNumber}")
	public Train getTrainDetails(@PathVariable("trainNumber") Long trainNumber);
	
	
	@PutMapping("/updateTrainSeats")
	public Void UpdateSeats(@RequestBody Train train);
}

