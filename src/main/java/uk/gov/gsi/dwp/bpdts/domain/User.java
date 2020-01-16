package uk.gov.gsi.dwp.bpdts.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class User {

    //Json properties
    private int id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String email;
    @JsonProperty("ip_address")
    private String ipAddress;
    private double latitude;
    private double longitude;

    // calculated results
    @JsonProperty("distance")
    private double distanceFromCoords;
    @JsonProperty("from_city")
    private String city;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getDistanceFromCoords() {
        return distanceFromCoords;
    }

    public void setDistanceFromCoords(double distanceFromCoords) {
        this.distanceFromCoords = distanceFromCoords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", ipAddress=" + ipAddress + ", latitude=" + latitude + ", longitude=" + longitude + ", city=" + city
				+ ", distanceFromCoords=" + distanceFromCoords + "]";
	}
    
}
