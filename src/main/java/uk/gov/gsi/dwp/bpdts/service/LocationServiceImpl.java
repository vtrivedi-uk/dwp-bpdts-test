package uk.gov.gsi.dwp.bpdts.service;

import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import uk.gov.gsi.dwp.bpdts.domain.Location;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Value("${location.data.file}")
    private String dataFilePath = "/static/data.json"; // from https://simplemaps.com/static/data/country-cities/gb/gb.json
	
	private Map<String, Location> cityMap = new HashMap<String, Location>();
	
	public LocationServiceImpl() {
		Location[] cityArr = readLocationsFromFile();
		if(cityArr == null)
			cityArr = this.getDefaultLocations();
		
		Stream.of(cityArr).forEach((x)->{
			this.cityMap.put(x.getName().toUpperCase().trim(), x);
		});
	}

	@Override
	public Location getLocation(String locationName) {
		return this.cityMap.get(locationName.trim().toUpperCase());
	}
	
	private Location[] readLocationsFromFile() {
		Location[] cityArr = null;
		try {
			System.out.println("Reading file --> "+ dataFilePath);
			ObjectMapper mapper = new ObjectMapper();
			cityArr = mapper.readValue(Files.readAllBytes(new ClassPathResource(dataFilePath).getFile().toPath()), Location[].class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityArr;
	}
	
	private Location[] getDefaultLocations() {
		return new Location[] {
				new Location("London", 51.50853, -0.12574),
				new Location("Newcastle", 54.97328, -1.61396),
				new Location("Cardiff", 51.48, -3.18),
				new Location("Edinburgh", 55.95206, -3.19648)
		};
	}

}
