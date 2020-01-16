package uk.gov.gsi.dwp.bpdts.service;

import java.util.List;

import uk.gov.gsi.dwp.bpdts.domain.User;

public interface UserService {

	List<User> getUsersWithinDistanceOfCity(String city, double distance, String units);

}