package com.ayan.tiles.controller;

import static com.ayan.tiles.security.SecurityConstants.TOKEN_PREFIX;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayan.tiles.entity.User;
import com.ayan.tiles.payload.JWTLoginSucessReponse;
import com.ayan.tiles.payload.LoginRequest;
import com.ayan.tiles.security.JwtTokenProvider;
import com.ayan.tiles.service.UserService;
import com.ayan.tiles.service.ValidationErrorsService;
import com.ayan.tiles.validator.UserValidator;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

	@Autowired
	private ValidationErrorsService validationErrorsService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
		ResponseEntity<?> errorMap = validationErrorsService.ValidationErrorsFind(result);
		if (errorMap != null)
			return errorMap;

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);

		return ResponseEntity.ok(new JWTLoginSucessReponse(true, jwt));
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
		// System.out.println("1 user "+user);
		userValidator.validate(user, result);

		ResponseEntity<?> errorMap = validationErrorsService.ValidationErrorsFind(result);
		// System.out.println("2 errorMap "+errorMap);
		if (errorMap != null)
			return errorMap;

		User newUser = userService.saveUser(user);
		// System.out.println("3 newUser "+newUser);
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}

	/** Custom code **/

	@GetMapping("/all")
	public Iterable<User> all() {
		return userService.findAll();
	}

	@PostMapping("add")
	public ResponseEntity<?> createNew(@Valid @RequestBody User user, BindingResult result) {

		ResponseEntity<?> errorMap = validationErrorsService.ValidationErrorsFind(result);
		if (errorMap != null)
			return errorMap;

		User newEntity = userService.save(user);
		return new ResponseEntity<User>(newEntity, HttpStatus.CREATED);
	}

	@PostMapping("add/me")
	public ResponseEntity<?> editMember(@Valid @RequestBody User user, BindingResult result) {
		System.out.println("user--->  " + user);

		String email = "yoo@gmail.com";

		ResponseEntity<?> errorMap = validationErrorsService.ValidationErrorsFind(result);
		if (errorMap != null)
			return errorMap;

		User oldUser = userService.findByEmail(email);
		User newUser = new User();
		if (user.getConfirmPassword() != oldUser.getPassword()) {
			if (oldUser.getPassword() != user.getPassword()) {
				newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			} else {
				newUser.setPassword(oldUser.getPassword());
			}
		}
		newUser.setId(oldUser.getId());
		newUser.setFullName(user.getFullName());
		newUser.setUsername(user.getUsername());
		newUser.setContactNo(user.getContactNo());

		User newEntity = userService.save(newUser);
		return new ResponseEntity<User>(newEntity, HttpStatus.CREATED);
	}

	@GetMapping("/me")
	public ResponseEntity<?> getEntityByMyId() {
		System.out.println("executed--->  ");
		String email = "yoo@gmail.com";

		User user = userService.findByEmail(email);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getEntityById(@PathVariable Long id) {
		User user = userService.findById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEntity(@PathVariable Long id) {
		userService.deleteById(id);
		return new ResponseEntity<String>("User " + id + " deleted successfully", HttpStatus.OK);
	}
}