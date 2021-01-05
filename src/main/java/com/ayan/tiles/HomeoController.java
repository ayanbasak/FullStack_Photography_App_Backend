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
@RequestMapping("/homeo")
public class HomeoController {

	
	@GetMapping("/test/all")	
	public List<Object> testAll() {
		String url = "https://roomroom.pythonanywhere.com/api/crud/list";
		RestTemplate restTemplate = new RestTemplate();
		
		Object[] objects = restTemplate.getForObject(url, Object[].class);
		return Arrays.asList(objects);
	}
	
	@GetMapping("new/create/mess")		
	public Object createMess() {
		String url = "https://roomroom.pythonanywhere.com/api/mess/create/";
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> map = null;
		ResponseEntity<Object> object = restTemplate.postForEntity(url, map,  Object.class);
		return object;
	}
	
	@GetMapping("/mess/list")	   	
	public List<Object> messList() {
		String url = "https://roomroom.pythonanywhere.com/api/mess/list";
		RestTemplate restTemplate = new RestTemplate();
		Object objects = restTemplate.getForObject(url, Object.class);
		return Arrays.asList(objects);
	}
	
	
	@GetMapping("/mess/list/{limit}/{offset}")	   	
	public List<Object> messListWithPagination(@PathVariable int limit,@PathVariable int offset) {
		String url = "https://roomroom.pythonanywhere.com/api/mess/list/?limit="+ limit +"12&offset="+offset ;
		RestTemplate restTemplate = new RestTemplate();
		Object objects = restTemplate.getForObject(url, Object.class);
		return Arrays.asList(objects);
	}
	
	@GetMapping("/mess/details/{messId}")	   	
	public Object messDetails(@PathVariable String messId) {
		String url = "https://roomroom.pythonanywhere.com/api/mess/details/"+messId;
		RestTemplate restTemplate = new RestTemplate();
		Object object = restTemplate.getForObject(url, Object.class);
		return object;
	}
	
	@GetMapping("/mess/amenities/details/{messId}")	   	
	public Object messAmenities(@PathVariable String messId) {
		String url = "https://roomroom.pythonanywhere.com/api/mess/create/amenities/"+messId;
		RestTemplate restTemplate = new RestTemplate();
		Object object = restTemplate.getForObject(url, Object.class);
		return object;
	}
	
	@GetMapping("/mess/otherdetails/{messId}")	   	
	public Object messDetail(@PathVariable String messId) {
		String url = "https://roomroom.pythonanywhere.com/api/mess/create/details/"+messId;
		RestTemplate restTemplate = new RestTemplate();
		Object object = restTemplate.getForObject(url, Object.class);
		return object;
	}
	
	@GetMapping("/mess/room/details/{messId}")	   	
	public Object roomDetails(@PathVariable String messId) {
		String url = "https://roomroom.pythonanywhere.com/api/mess/create/room/"+messId;
		RestTemplate restTemplate = new RestTemplate();
		Object object = restTemplate.getForObject(url, Object.class);
		return object;
	}
	
	@GetMapping("/mess/address/details/{messId}")	   	
	public Object messAddress(@PathVariable String messId) {
		String url = "https://roomroom.pythonanywhere.com/api/mess/create/address/"+messId;
		RestTemplate restTemplate = new RestTemplate();
		Object object = restTemplate.getForObject(url, Object.class);
		return object;
	}
	
	@GetMapping("/mess/image/details/{messId}")	   	
	public Object messImage(@PathVariable String messId) {
		String url = "https://roomroom.pythonanywhere.com/api/mess/create/image/"+messId;
		RestTemplate restTemplate = new RestTemplate();
		Object object = restTemplate.getForObject(url, Object.class);
		return object;
	}
	
	/*    POST Mappings     */
	
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
		return new ResponseEntity<Object>(response, HttpStatus.CREATED);
	}
	

	@PostMapping("add/amenities/{messId}")
	public ResponseEntity<?> addAmenities(@RequestBody Object object,@PathVariable String messId) {
		
		String url = "https://roomroom.pythonanywhere.com/api/mess/create/amenities/"+messId+"/";
		RestTemplate restTemplate = new RestTemplate();
		
		    ObjectMapper mapper = new ObjectMapper();
		    String jsonString = null;
			try {
				jsonString = mapper.writeValueAsString(object);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		      
		    JSONObject j = new JSONObject(jsonString);
		    
		    /*
		     *  "laundry": true,
			    "room_cleaning": true,
			    "warden_facility": true,
			    "lift": true,
			    "wifi": true,
			    "cooking_allow": false,
			    "power_backup": false,
			    "parking_for_cycle": false,
			    "parking_for_motor_bike": false,
			    "parking_for_car": false
		     * 
		     */
		    
				Map<String, Boolean> map = new HashMap<>();
				map.put("laundry", 			j.getBoolean("laundry"));
				map.put("room_cleaning", 	j.getBoolean("room_cleaning"));
				map.put("warden_facility", 	j.getBoolean("warden_facility"));
				map.put("lift", 			j.getBoolean("lift"));
				map.put("wifi", 			j.getBoolean("wifi"));
				map.put("cooking_allow", 	j.getBoolean("cooking_allow"));
				map.put("power_backup", 	j.getBoolean("power_backup"));
				map.put("parking_for_cycle",j.getBoolean("parking_for_cycle"));
				map.put("parking_for_motor_bike", j.getBoolean("parking_for_motor_bike"));
				map.put("parking_for_car", 	j.getBoolean("parking_for_car"));
				
				
				ResponseEntity<Object> response = restTemplate.postForEntity(url, map, Object.class);
		if (response.getStatusCode() == HttpStatus.CREATED) {
		    System.out.println("Request Successful");
		} else {
		    System.out.println("Request Failed");
		}		
		return new ResponseEntity<Object>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("add/messdetails/{messId}")
	public ResponseEntity<?> addMess(@RequestBody Object object,@PathVariable String messId) {
		
		String url = "https://roomroom.pythonanywhere.com/api/mess/create/details/"+messId+"/";
		RestTemplate restTemplate = new RestTemplate();
		
		    ObjectMapper mapper = new ObjectMapper();
		    String jsonString = null;
			try {
				jsonString = mapper.writeValueAsString(object);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		      
		    JSONObject j = new JSONObject(jsonString);
		    
		    /*
		     *  {
				    "place_available_for": "M",
				    "prefferable_guest": "S",
				    "available_from": "2000-02-12",
				    "food_included": false,
				    "no_smoking": false,
				    "no_guardians_stay": false,
				    "no_drinking": true,
				    "no_girl_entry": false,
				    "no_boys_entry": false,
				    "no_non_veg": false,
				    "gate_closing_time": "23:00:00"
				}
		     * 
		     */
		    
				Map<String, String> map = new HashMap<>();
				map.put("place_available_for", 	j.getString("place_available_for"));
				map.put("prefferable_guest", 	j.getString("prefferable_guest"));
				map.put("available_from", 		j.getString("available_from"));
				map.put("food_included", 		String.valueOf(j.getBoolean("food_included")));
				map.put("no_smoking", 			String.valueOf(j.getBoolean("no_smoking")));
				map.put("no_guardians_stay", 	String.valueOf(j.getBoolean("no_guardians_stay")));
				map.put("no_drinking", 			String.valueOf(j.getBoolean("no_drinking")));
				map.put("no_girl_entry", 		String.valueOf(j.getBoolean("no_girl_entry")));
				map.put("no_boys_entry", 		String.valueOf(j.getBoolean("no_boys_entry")));
				map.put("no_non_veg", 			String.valueOf(j.getBoolean("no_non_veg")));
				map.put("gate_closing_time", 	j.getString("gate_closing_time"));
				
				ResponseEntity<Object> response = restTemplate.postForEntity(url, map, Object.class);
		if (response.getStatusCode() == HttpStatus.CREATED) {
		    System.out.println("Request Successful");
		} else {
		    System.out.println("Request Failed");
		}		
		return new ResponseEntity<Object>(response, HttpStatus.CREATED);
	}
	

	@PostMapping("add/room/{messId}")
	public ResponseEntity<?> addRoom(@RequestBody Object object,@PathVariable String messId) {
		
		String url = "https://roomroom.pythonanywhere.com/api/mess/create/room/"+messId+"/";
		RestTemplate restTemplate = new RestTemplate();
		
		    ObjectMapper mapper = new ObjectMapper();
		    String jsonString = null;
			try {
				jsonString = mapper.writeValueAsString(object);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		      
		    JSONObject j = new JSONObject(jsonString);
		    
		    /*
		     *  "persons_per_room": "3",
	            "expected_rent_per_person": "14000.00",
	            "expected_deposit_per_person": "1200.00",
	            "cupboard": true,
	            "bedding": false,
	            "ac": false,
	            "tv": false,
	            "geyser": false,
	            "attached_bathroom": false
		     * 
		     */
		    
				Map<String, String> map = new HashMap<>();
				map.put("persons_per_room", 			String.valueOf(j.getInt("persons_per_room")));
				map.put("expected_rent_per_person", 	String.valueOf(j.getFloat("expected_rent_per_person")));
				map.put("expected_deposit_per_person", 	String.valueOf(j.getFloat("expected_deposit_per_person")));
				map.put("cupboard", 					String.valueOf(j.getBoolean("cupboard")));
				map.put("bedding", 						String.valueOf(j.getBoolean("bedding")));
				map.put("ac", 							String.valueOf(j.getBoolean("ac")));
				map.put("tv", 							String.valueOf(j.getBoolean("tv")));
				map.put("geyser",						String.valueOf(j.getBoolean("geyser")));
				map.put("attached_bathroom", 			String.valueOf(j.getBoolean("attached_bathroom")));
				
				ResponseEntity<Object> response = restTemplate.postForEntity(url, map, Object.class);
		if (response.getStatusCode() == HttpStatus.CREATED) {
		    System.out.println("Request Successful");
		} else {
		    System.out.println("Request Failed");
		}		
		return new ResponseEntity<Object>(response, HttpStatus.CREATED);
	}
	
	

	@PostMapping("add/address/{messId}")
	public ResponseEntity<?> addAddress(@RequestBody Object object, @PathVariable String messId) {
		
		String url = "https://roomroom.pythonanywhere.com/api/mess/create/address/"+messId+"/";
		RestTemplate restTemplate = new RestTemplate();
		
		    ObjectMapper mapper = new ObjectMapper();
		    String jsonString = null;
			try {
				jsonString = mapper.writeValueAsString(object);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		      
		    JSONObject j = new JSONObject(jsonString);
		    
		    /*
		     *  "street": "Old-Baharampur Road",
		        "area": "Ranaghat",
		        "zip_code": "741201"
		     */
		    
				Map<String, String> map = new HashMap<>();
				map.put("street", 	j.getString("zip_code"));
				map.put("area", 	j.getString("area"));
				map.put("zip_code",	String.valueOf(j.getInt("zip_code")));
				
				ResponseEntity<Object> response = restTemplate.postForEntity(url, map, Object.class);
		if (response.getStatusCode() == HttpStatus.CREATED) {
		    System.out.println("Request Successful");
		} else {
		    System.out.println("Request Failed");
		}		
		return new ResponseEntity<Object>(response, HttpStatus.CREATED);
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

	/*    DELETE Mappings     */
	
	@DeleteMapping("/test/{id}")
	public ResponseEntity<?> deleteEntity(@PathVariable Long id) {
		String url = "https://roomroom.pythonanywhere.com/api/crud/delete/"+id+"/";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);
		
		return new ResponseEntity<String>("Test sucessfully " + ",object deleted successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/mess/{messId}")
	public ResponseEntity<?> deleteMess(@PathVariable String messId) {
		String url = "https://roomroom.pythonanywhere.com/api/mess/delete/"+messId+"/";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);
		
		return new ResponseEntity<String>("Test sucessfully " + ",mess details deleted successfully", HttpStatus.OK);
	}
}
