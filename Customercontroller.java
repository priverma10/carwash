package com.customer.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.customer.model.Bookingdetails;
import com.customer.model.CustomerRating;
import com.customer.model.Login;
import com.customer.model.Signup;
import com.customer.model.Washpacks;
import com.customer.service.CustomerServiceImplementation;
import com.customer.service.LoginService;

import io.swagger.annotations.ApiOperation;

import java.util.Collections;
import java.util.Map;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/user")
public class Customercontroller {

	@Autowired
	private LoginService user;
	@Autowired
	private CustomerServiceImplementation service;

	@Autowired
	private RestTemplate restTemplate;
	
	
	private static final Logger log = LoggerFactory.getLogger(Customercontroller.class);

	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/adduser")
	@ApiOperation(value = "Adds new Customer's details")
	public Signup saveUser(@RequestBody Signup signup) {
		signup.setId(service.getSequenceNumber(Signup.SEQUENCE_NAME));
		log.trace("Add User");
		
		return service.addUser(signup);
	}
	@CrossOrigin(origins="http://localhost:3000")
    @GetMapping("/allusers")
	@ApiOperation(value = "Shows all Customer's details")
	public List<Signup> findAllUsers() {
		log.trace("All User");
		return service.getuser();
	}

	@CrossOrigin(origins="http://localhost:3000")
    @PutMapping("/updateUser")
	@ApiOperation(value = "Updates Customer's details")
	public String updateUser(@RequestBody Signup signup) {
		String result = service.Updateuser(signup);
		log.trace("Update User");
		return result;
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@DeleteMapping("/deleteUser/{id}")
	@ApiOperation(value = "Deletes customer")
	public String deleteuser(@RequestParam int id) {
		log.trace("Delete User");
		return service.deleteUser(id);
	}

	@PostMapping("/login")
	@ApiOperation(value = "To Add Login Details")
	public String userLogin(@RequestBody Login login) {
		log.trace("Login User");
		return user.userLogin(login);
	}

	/*-------------------Resttemplates----------------------------- */

	@GetMapping("/allpacks")
	@ApiOperation(value = "Gets all packs")
	public List<Washpacks> getwashpacks() {
		String baseurl = "http://localhost:8081/admin/allpacks";
		Washpacks[] wp = restTemplate.getForObject(baseurl, Washpacks[].class);
		
		log.trace("All Packs User");
		return Arrays.asList(wp);
	}

	@PostMapping("/addorder")
	@ApiOperation(value = "Customer can add new order")
	public String addorder(@RequestBody Bookingdetails order) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Bookingdetails> entity = new HttpEntity<Bookingdetails>(order, headers);
	 

		return restTemplate.exchange("http://localhost:8083/order/addorder", HttpMethod.POST, entity, String.class)
				.getBody();
	}

	@DeleteMapping("/cancelorder")
	@ApiOperation(value = "Customer can cancel order")
	public String deleteorder(@RequestParam int id) {
		String baseurl = "http://localhost:8083/order/cancelorder";
		Bookingdetails order = restTemplate.getForObject(baseurl, Bookingdetails.class);
		return "Your Order is successfully Canceled " + order;
	}

	 

}
