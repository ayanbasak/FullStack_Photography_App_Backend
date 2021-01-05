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
import com.ayan.tiles.service.CartService;
import com.ayan.tiles.service.ValidationErrorsService;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {
	@Autowired
	private CartService service;

	@Autowired
	private ValidationErrorsService validationErrorsService;

	@GetMapping("/all")
	public Iterable<Cart> all() {
		return service.findAll();
	}
	
	@GetMapping("/all/by/user")
	public Iterable<Cart> getCartByUser() {
		String email = "abc@gmail.com";
		
		return service.findAllByUserEmail(email);
	}
	
	@PostMapping("add/save")      // /cart/add
	public ResponseEntity<?> createNew( @RequestBody List<Cart> carts) {

		String email = "abc@gmail.com";
		
		List<Cart> list = service.findAllByUserEmail(email);
		if(list != null) {
			for (int i = 0; i < list.size(); i++) {
				service.deleteById(list.get(i).getId());
				//System.out.println(list.get(i).getId());
			}
		}
		
		Cart c = new Cart();
		if (carts != null) {
			for (int i = 0; i < carts.size(); i++) {
				c=carts.get(i);
				c.setUserEmail(email);				
				service.save(c);
			}
		}
		return new ResponseEntity<String>("Cart Items Saved Successfully", HttpStatus.CREATED);
	}

	@PostMapping("add")      // /cart/add
	public ResponseEntity<?> createNew(@Valid @RequestBody Cart cart, BindingResult result) {

		ResponseEntity<?> errorMap = validationErrorsService.ValidationErrorsFind(result);
		if (errorMap != null)
			return errorMap;

		Cart newEntity = service.save(cart);
		return new ResponseEntity<Cart>(newEntity, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getEntityById(@PathVariable Long id) {
		Cart cart = service.findById(id);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEntity(@PathVariable Long id) {
		service.deleteById(id);
		return new ResponseEntity<String>("Cart " + id + " deleted successfully", HttpStatus.OK);
	}

}
