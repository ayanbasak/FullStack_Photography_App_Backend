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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.ayan.tiles.entity.Tile;
import com.ayan.tiles.service.TileService;
import com.ayan.tiles.service.ValidationErrorsService;

@RestController
@RequestMapping("/tile")
@CrossOrigin
public class TileController {
	@Autowired
	private TileService service;

	@Autowired
	private ValidationErrorsService validationErrorsService;

	@GetMapping("/all")
	public Iterable<Tile> all() {
		return service.findAll();
	}

	@PostMapping("add")
	public ResponseEntity<?> createNew(@Valid @RequestBody Tile tile, BindingResult result) {

		ResponseEntity<?> errorMap = validationErrorsService.ValidationErrorsFind(result);
		if (errorMap != null)
			return errorMap;

		Tile newEntity = service.save(tile);
		return new ResponseEntity<Tile>(newEntity, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getEntityById(@PathVariable Long id) {
		Tile tile = service.findById(id);
		//System.out.println(id);
		return new ResponseEntity<Tile>(tile, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEntity(@PathVariable Long id) {
		service.deleteById(id);
		return new ResponseEntity<String>("Tile " + id + " deleted successfully", HttpStatus.OK);
	}
	
	@GetMapping("/search/{searchText}")
	ResponseEntity<Page<Tile>> findAll(Pageable pageable, @PathVariable String searchText){
		return new ResponseEntity<>(service.findAll(pageable, searchText), HttpStatus.OK);
	}

	@GetMapping("/get")
	ResponseEntity<Page<Tile>> findAll(int pageNumber, int pageSize, String sortBy, String sortDir){
		//System.out.println("  pageNumber: "+ pageNumber + "  , pageSize: "+pageSize+" , sortBy: "+sortBy +" , sortDir: "+sortDir);
		return new ResponseEntity<>(service.findAll(
				PageRequest.of(
						pageNumber, pageSize,
						sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
				)
		), HttpStatus.OK);
	}

}
