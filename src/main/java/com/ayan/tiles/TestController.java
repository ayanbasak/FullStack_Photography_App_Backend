package com.ayan.tiles;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/home")
public class TestController {

	
	@GetMapping("/test/all")		
	public List<Object> testAll() {
		String url = "https://roomroom.pythonanywhere.com/api/crud/list";
		RestTemplate restTemplate = new RestTemplate();
		
		Object[] objects = restTemplate.getForObject(url, Object[].class);
		return Arrays.asList(objects);
	}
	
	@PostMapping("test/add")
	public ResponseEntity<?> createNew(@RequestBody Object object) {
		
		String url = "https://roomroom.pythonanywhere.com/api/crud/create/";
		RestTemplate restTemplate = new RestTemplate();
		
	    ObjectMapper mapper = new ObjectMapper();
	    String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	      
	    JSONObject j = new JSONObject(jsonString);
	    
		Map<String, String> map = new HashMap<>();
		map.put("author", j.getString("author"));
		map.put("title", j.getString("title"));
		map.put("body", j.getString("body"));
		
		ResponseEntity<Object> response = restTemplate.postForEntity(url, map, Object.class);
		if (response.getStatusCode() == HttpStatus.CREATED) {
		    System.out.println("Request Successful");
		} else {
		    System.out.println("Request Failed");
		}		
		return new ResponseEntity<Object>(response.getBody(), HttpStatus.CREATED);
	}

	@PostMapping("test/update/{id}")
	public ResponseEntity<?> update(@RequestBody Object object,@PathVariable Long id) {
		
		String url = "https://roomroom.pythonanywhere.com/api/crud/create/"+id+"/";
		RestTemplate restTemplate = new RestTemplate();
		
	    ObjectMapper mapper = new ObjectMapper();
	    String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	      
	    JSONObject j = new JSONObject(jsonString);
	    
		Map<String, String> map = new HashMap<>();
		map.put("author", j.getString("author"));
		map.put("title", j.getString("title"));
		map.put("body", j.getString("body"));
		
		ResponseEntity<Object> response = restTemplate.postForEntity(url, map, Object.class);
		if (response.getStatusCode() == HttpStatus.CREATED) {
		    System.out.println("Request Successful");
		} else {
		    System.out.println("Request Failed");
		}		
		return new ResponseEntity<Object>(response.getBody(), HttpStatus.CREATED);
	}

	@GetMapping("/test/{id}")
	public ResponseEntity<Object> getObjectById(@PathVariable Long id) {
		String url = "https://roomroom.pythonanywhere.com/api/crud/details/"+id;
		RestTemplate restTemplate = new RestTemplate();
		Object object = restTemplate.getForObject(url, Object.class);
		
		return new ResponseEntity<Object>(object, HttpStatus.OK);
	}

	@DeleteMapping("/test/{id}")
	public ResponseEntity<?> deleteEntity(@PathVariable Long id) {
		String url = "https://roomroom.pythonanywhere.com/api/crud/delete/"+id+"/";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);
		
		return new ResponseEntity<String>("Test sucessfully " + ",object deleted successfully", HttpStatus.OK);
	}
}
