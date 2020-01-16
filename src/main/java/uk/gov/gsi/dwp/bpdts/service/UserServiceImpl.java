package uk.gov.gsi.dwp.bpdts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.gov.gsi.dwp.bpdts.domain.Location;
import uk.gov.gsi.dwp.bpdts.domain.Coordinate;
import uk.gov.gsi.dwp.bpdts.domain.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserClient userClient;
	
	@Autowired
	private LocationService locationService;
	
	@Override
	public List<User> getUsersWithinDistanceOfCity(String city, double distance, String units) {
		
		//1. find the details of the reference location
		Location refCity = locationService.getLocation(city);
		// coordinates of the reference location
		Coordinate refCoords = new Coordinate(refCity.getLatitude(), refCity.getLongitude());
		// converts the user specified distance value to miles
		double distRange = convertDistanceToMiles(distance, units);
		// get all users in the system
		List<User> users = userClient.getAllUsers() 
				.stream() // convert them to a stream for navigation
				.map((x) -> {
					x.setDistanceFromCoords(refCoords.getDistance(x.getLatitude(), x.getLongitude()));
					x.setCity(refCity.getName());
					return x;
				}) // embellish each object with the distance and the name
				.filter((x) -> x.getDistanceFromCoords() <= distRange) // filter out records which dont fall within the distance restrictions
				.collect(Collectors.toList()); // collect them into a list
		return users;
	}
	
	private double convertDistanceToMiles(double distance, String units) {
		switch (units) {
		case "miles": return distance;
		case "km": return distance/1.609344;
		case "kilometer": return distance/1.609344;
		case "nm": return distance/0.8684;
		case "nauticalMiles": return distance/0.8684;
		case "m": return distance/1609.344;
		case "meter": return distance/1609.344;
		case "meters": return distance/1609.344;
		default:return distance;
		}
	}

}
