package com.ayan.tiles.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayan.tiles.entity.Cart;
import com.ayan.tiles.entity.OrderedItems;
import com.ayan.tiles.service.CartService;
import com.ayan.tiles.service.OrderedItemsService;
import com.ayan.tiles.service.ValidationErrorsService;

@RestController
@RequestMapping("/ordered")
@CrossOrigin
public class OrderedController {

	@Autowired
	private OrderedItemsService oservice;
	@Autowired
	private CartService cservice;

	@Autowired
	private ValidationErrorsService validationErrorsService;

	@PostMapping("ordered/add/{totalQty}/{totalCost}")   //work
	public ResponseEntity<?> orderedAccept(@Valid @RequestBody List<Cart> carts, @PathVariable int totalQty,
			@PathVariable float totalCost) {

		System.out.println(carts);
		String email = "recived-" + "abc@gmail.com";
		String userFullname = "abc";

		for (int i = 0; i < carts.size(); i++) {
			Cart cart = carts.get(i);
			cart.setUserEmail(email);
			cservice.save(cart);
		}
		/*
		for (int i = 0; i < carts.size(); i++) {
			Cart cart = carts.get(i);
			System.out.println("id----  "+cart.getId());
			cservice.deleteById(cart.getId());
		}		*/

		OrderedItems oi = new OrderedItems();
		oi.setTotalQty(totalQty);
		oi.setTotalPrice(totalCost);
		oi.setUserEmail(email);
		oi.setUserFullname(userFullname);
		oi.setStatus("accept");
		oservice.save(oi);

		oi.setCarts(carts);

		return new ResponseEntity<OrderedItems>(oi, HttpStatus.CREATED);
	}

	@GetMapping("/allordered")
	public ResponseEntity<List<OrderedItems>> allOrdered() {

		List<OrderedItems> oi = oservice.findAll();
		if (oi != null) {
			for (int i = 0; i < oi.size(); i++) {
				OrderedItems ord = oi.get(i);
				ord.setCarts(cservice.findAllByUserEmail(ord.getUserEmail()));
			}
		}
		return new ResponseEntity<List<OrderedItems>>(oi, HttpStatus.OK);
	}

	@GetMapping("/allordered/status/{statustype}")
	public ResponseEntity<List<OrderedItems>> allOrderedbyStatus(@PathVariable String statustype) {

		List<OrderedItems> oi = oservice.findAllByStatus(statustype);
		if (oi != null) {
			for (int i = 0; i < oi.size(); i++) {
				OrderedItems ord = oi.get(i);
				ord.setCarts(cservice.findAllByUserEmail("recived-" + ord.getUserEmail()));
			}
		}
		return new ResponseEntity<List<OrderedItems>>(oi, HttpStatus.OK);
	}

	@GetMapping("/orderofuser")
	public ResponseEntity<List<OrderedItems>> orderByUser() {

		String email = "abc@gmail.com";

		List<OrderedItems> oi = oservice.findAllByUserEmail("recived-" + email);
		//System.out.println("|executed--------   "+oi);
		if (oi != null) {
			for (int i = 0; i < oi.size(); i++) {
				OrderedItems ord = oi.get(i);
				ord.setCarts(cservice.findAllByUserEmail("recived-" + email));
			}
		}
		return new ResponseEntity<List<OrderedItems>>(oi, HttpStatus.OK);
	}

	@GetMapping("order/completed/{id}")
	public ResponseEntity<?> orderCompleted(@PathVariable Long id) {
		OrderedItems orderedItems = oservice.findById(id);
		orderedItems.setStatus("completed");
		oservice.save(orderedItems);
		return new ResponseEntity<OrderedItems>(orderedItems, HttpStatus.OK);
	}

	@GetMapping("/all")
	public Iterable<OrderedItems> all() {
		return oservice.findAll();
	}

	@PostMapping("add")
	public ResponseEntity<?> createNew(@Valid @RequestBody OrderedItems orderedItems, BindingResult result) {

		ResponseEntity<?> errorMap = validationErrorsService.ValidationErrorsFind(result);
		if (errorMap != null)
			return errorMap;

		OrderedItems newEntity = oservice.save(orderedItems);
		return new ResponseEntity<OrderedItems>(newEntity, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getEntityById(@PathVariable Long id) {
		OrderedItems orderedItems = oservice.findById(id);
		return new ResponseEntity<OrderedItems>(orderedItems, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEntity(@PathVariable Long id) {
		oservice.deleteById(id);
		return new ResponseEntity<String>("OrderedItems " + id + " deleted successfully", HttpStatus.OK);
	}

}
