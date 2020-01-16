package uk.gov.gsi.dwp.bpdts.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.web.client.RestClientException;

import uk.gov.gsi.dwp.bpdts.domain.User;

public interface UserClient {

    Collection<User> getAllUsers() throws RestClientException;

    Collection<User> getAllUsersFromCity(String city)  throws RestClientException;

    Optional<User> getUserById(int id)  throws RestClientException;
}
