package uk.gov.gsi.dwp.bpdts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import uk.gov.gsi.dwp.bpdts.domain.User;
import uk.gov.gsi.dwp.bpdts.service.UserService;

@RestController
@RequestMapping("/api/users")
public class ApiController {
	@Autowired
	private UserService userService;
	
	@GetMapping ("/within/{distance}/{units}/of/{city}")
	@ResponseBody
	public List<User> getUsersWithinDistanceOfCity(@PathVariable String city, @PathVariable double distance, @PathVariable String units) {
		return userService.getUsersWithinDistanceOfCity(city, distance, units);
	}
	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
	    return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
