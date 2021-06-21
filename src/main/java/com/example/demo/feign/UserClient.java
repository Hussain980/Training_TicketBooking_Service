package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url ="http://localhost:8084/user/api" , value="UserService")
public interface UserClient {

	@GetMapping("/user")
	public User getUserByUserName(@RequestParam String userName);
}
