package uk.gov.gsi.dwp.bpdts.domain;

public final class Location {
	private String name;
	private double latitude;
	private double longitude;
	
	public Location() {
		
	}
	public Location(String name, double latitude, double longitude) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public String getName() {
		return name;
	}
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	@Override
	public String toString() {
		return "City [name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
}
