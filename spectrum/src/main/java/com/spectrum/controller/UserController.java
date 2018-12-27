package com.spectrum.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spectrum.constants.ApiConstants;
import com.spectrum.entity.Role;
import com.spectrum.entity.User;
import com.spectrum.service.RoleService;
import com.spectrum.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = ApiConstants.REST_BASE_PATH)
@Api(tags = { ApiConstants.USER_CONTROLLER_TAG })
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	// create a new user
	@SuppressWarnings("rawtypes")
	@PostMapping(value = ApiConstants.REST_USER_PATH, consumes = ApiConstants.REST_JSON_CONTENT_TYPE, produces = ApiConstants.REST_JSON_CONTENT_TYPE)
	@ApiOperation(value = ApiConstants.CREATE_USER)
	public ResponseEntity creatNewUser(@RequestBody User user) {

		JSONObject response = new JSONObject();
		User existingUser = userService.findByEmail(user.getEmail());
		if (existingUser != null) {
			response.put(ApiConstants.ERROR_RESPONSE, ApiConstants.EMAIL_ALREADY_EXISTS + user.getEmail());
			return new ResponseEntity<>(response.toString(), HttpStatus.OK);
		} else if ((existingUser = userService.findByUsername(user.getUsername())) != null) {
			response.put(ApiConstants.ERROR_RESPONSE,
					ApiConstants.USERNAME_ALREADY_EXISTS + user.getUsername().toString());
			return new ResponseEntity<>(response.toString(), HttpStatus.OK);
		}
		
		// if contact will be unique

		/*
		 * else if ((existingUser = userService.findByContact(user.getContact())) !=
		 * null) {
		 * 
		 * response.put(ApiConstants.ERROR_RESPONSE, ApiConstants.CONTACT_ALREADY_EXISTS
		 * + user.getContact().toString()); return new
		 * ResponseEntity<>(response.toString(), HttpStatus.OK); }
		 */

		Role existingRole = null;
		for (Role role : user.getRole()) {
			System.out.println(role.getRoleName());
			existingRole = roleService.findByRoleName(role.getRoleName());
			if (existingRole == null) {
				response.put(ApiConstants.ERROR_RESPONSE, ApiConstants.ROLE_NOT_FOUND);
				return new ResponseEntity<>(response.toString(), HttpStatus.OK);
			}
		}

		User createdUser = userService.createUser(user);
		if (createdUser == null) {
			response.put(ApiConstants.ERROR_RESPONSE, ApiConstants.FAIL_TO_USER_CREATION);
		}
		response.put(ApiConstants.SUCCESS_RESPONSE, ApiConstants.USER_CREATE_SUCCESSFULLY);
		return new ResponseEntity<>(response.toString(), HttpStatus.OK);
	}

	// Get all user
	@RequestMapping(value = ApiConstants.REST_USER_PATH, produces = ApiConstants.REST_JSON_CONTENT_TYPE, method = RequestMethod.GET)
	@ApiOperation(value = ApiConstants.GET_ALL_USER)
	public ResponseEntity getAllUser() {
		return new ResponseEntity<>(userService.listUsers(),HttpStatus.OK);
	}

	// Get a user
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = ApiConstants.REST_USER_PATH
			+ ApiConstants.REST_USER_ID_PARAM, produces = ApiConstants.REST_JSON_CONTENT_TYPE, method = RequestMethod.GET)
	@ApiOperation(value = ApiConstants.GET_ONE_USER)
	public ResponseEntity getOneUser(@PathVariable Integer userId) {
		JSONObject response = new JSONObject();
		boolean existsById = false;
		try {
			existsById = userService.existsById(userId);
		} catch (Exception e) {
			response.put(ApiConstants.ERROR_RESPONSE, ApiConstants.USER_ID_NOT_VALID);
			return new ResponseEntity<>(response.toString(), HttpStatus.OK);
		}
		if (!existsById) {
			response.put(ApiConstants.ERROR_RESPONSE, ApiConstants.USER_ID_NOT_FOUND);
			return new ResponseEntity<>(response.toString(), HttpStatus.OK);
		}
		return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);

	}

	// Update a user
	@PutMapping(value = ApiConstants.REST_USER_PATH, consumes = ApiConstants.REST_JSON_CONTENT_TYPE, produces = ApiConstants.REST_JSON_CONTENT_TYPE)
	@ApiOperation(value = ApiConstants.UPDATE_USER)
	public ResponseEntity getAllUser(@RequestBody User user) {
		JSONObject response = new JSONObject();
		boolean existsById = false;
		try {
			existsById = userService.existsById(user.getUserId());
		} catch (Exception e) {
			response.put(ApiConstants.ERROR_RESPONSE, ApiConstants.USER_ID_NOT_VALID);
			return new ResponseEntity<>(response.toString(), HttpStatus.OK);
		}
		if (!existsById) {
			response.put(ApiConstants.ERROR_RESPONSE, ApiConstants.USER_ID_NOT_FOUND);
			return new ResponseEntity<>(response.toString(), HttpStatus.OK);
		}
		
		Role existingRole = null;
		for (Role role : user.getRole()) {
			System.out.println(role.getRoleName());
			existingRole = roleService.findByRoleName(role.getRoleName());
			if (existingRole == null) {
				response.put(ApiConstants.ERROR_RESPONSE, ApiConstants.ROLE_NOT_FOUND);
				return new ResponseEntity<>(response.toString(), HttpStatus.OK);
			}
		}

		User updateUser = userService.updateUser(user);
		if (updateUser == null) {
			response.put(ApiConstants.ERROR_RESPONSE, ApiConstants.FAIL_TO_USER_CREATION);
		}
		response.put(ApiConstants.SUCCESS_RESPONSE, ApiConstants.USER_UPDATE_SUCCESSFULLY);
		return new ResponseEntity<>(response.toString(), HttpStatus.OK);
	}

}
