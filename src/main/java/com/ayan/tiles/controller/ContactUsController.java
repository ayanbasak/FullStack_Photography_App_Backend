package com.ayan.tiles.controller;

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

import com.ayan.tiles.entity.ContactUs;
import com.ayan.tiles.service.ContactUsService;
import com.ayan.tiles.service.ValidationErrorsService;

@RestController
@RequestMapping("/contactus")
@CrossOrigin
public class ContactUsController {
	@Autowired
	private ContactUsService service;

	@Autowired
	private ValidationErrorsService validationErrorsService;

	@GetMapping("/all")
	public Iterable<ContactUs> all() {
		return service.findAll();
	}

	@PostMapping("add")
	public ResponseEntity<?> createNew(@Valid @RequestBody ContactUs cous, BindingResult result) {
		System.out.println(cous);
		ResponseEntity<?> errorMap = validationErrorsService.ValidationErrorsFind(result);
		if (errorMap != null)
			return errorMap;

		ContactUs newEntity = service.save(cous);
		return new ResponseEntity<ContactUs>(newEntity, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getEntityById(@PathVariable Long id) {
		ContactUs cous = service.findById(id);
		return new ResponseEntity<ContactUs>(cous, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEntity(@PathVariable Long id) {
		service.deleteById(id);
		return new ResponseEntity<String>("ContactUs " + id + " deleted successfully", HttpStatus.OK);
	}

}
