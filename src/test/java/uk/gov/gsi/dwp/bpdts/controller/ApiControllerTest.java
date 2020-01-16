package uk.gov.gsi.dwp.bpdts.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import uk.gov.gsi.dwp.bpdts.domain.Location;
import uk.gov.gsi.dwp.bpdts.domain.User;
import uk.gov.gsi.dwp.bpdts.service.LocationService;
import uk.gov.gsi.dwp.bpdts.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(ApiController.class)
class ApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    UserService userService;
    
    @MockBean
    LocationService locationService;

    @MockBean
    RestTemplate template;

    @BeforeEach
    void setUp() {
    	List<User> list = new ArrayList<>();
    	list.add(new User());
    	when(locationService.getLocation("london")).thenReturn(new Location("London", 51.50853, -0.12574));
    	when(locationService.getLocation("Newcastle")).thenReturn(new Location("Newcastle", 54.97328, -1.61396));
        when(userService.getUsersWithinDistanceOfCity("London", 50.0, "miles")).thenReturn(list);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetUsersWithin50MilesOfLondonExpectSuccess() throws Exception {
        
        Location loc = locationService.getLocation("london");
        MvcResult result = mvc.perform(get("/api/users/within/50/miles/of/london")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    void testGetUsersWithin50MilesOfNewcastleExpectSuccess() throws Exception {
        
        Location loc = locationService.getLocation("Newcastle");
        MvcResult result = mvc.perform(get("/api/users/within/50/miles/of/Newcastle")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}