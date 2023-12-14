package com.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

 
import com.order.model.Bookingdetails;
import com.order.repository.Bookingrepo;
import com.order.repository.service.Bookingservice;

import io.swagger.annotations.ApiOperation;
 
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/order")
public class ordercontroller {

	@Autowired
	private Bookingservice BS;

	@Autowired
	private Bookingrepo repo;
	
	
	private static final Logger log = LoggerFactory.getLogger(ordercontroller.class);


	// Order Operations
	@PostMapping("/addorder")
	@ApiOperation(value = "To Add new order")
	public String addorder(@RequestBody Bookingdetails order) {
		order.setOrderId(BS.getSequenceNumber(Bookingdetails.SEQUENCE_NAME));
		BS.addorder(order);
		log.trace("ADD orders");

		return "order placed with washer" + order;
	}

	@GetMapping("/allorders")
	@ApiOperation(value = "To Get all order Details")
	public List<Bookingdetails> getorder() {
		log.trace("All orders");
		return BS.orderdetails();
	}

	@PutMapping("/updateorder")
	@ApiOperation(value = "To update order Details")
	public String updateDetails(@RequestBody Bookingdetails order) {
		BS.updateorder(order);
		log.trace("Update orders");

		return "Updated sucessfully";
	}

	@DeleteMapping("/cancelorder/{id}")
	@ApiOperation(value = "Deletes order by Id")
	public ResponseEntity<Object> deletorder(@RequestParam int id) {
		boolean isOrderExist = repo.existsById(id);
		if (isOrderExist) {
			BS.deleteById(id);
			return new ResponseEntity<Object>("Order deleted with id " + id, HttpStatus.OK);
		}  
		log.trace("All Delete");

		return null;
	}
}
