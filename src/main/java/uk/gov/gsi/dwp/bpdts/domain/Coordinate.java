package uk.gov.gsi.dwp.bpdts.domain;

public class Coordinate {

    private final double latitude;
    private final double longitude;

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

	@Override
	public String toString() {
		return "Coordinates [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}
	public double getDistance(double destLat, double destLong) {
		return getDistance(new Coordinate(destLat, destLong));
	}

	public double getDistance(Coordinate dest) {
		if (this.equals(dest)) {
			return 0;
		}
		else {
			double theta = this.getLongitude() - dest.getLongitude();
			double dist = Math.sin(Math.toRadians(this.getLatitude())) * Math.sin(Math.toRadians(dest.getLatitude())) 
					+ Math.cos(Math.toRadians(this.getLatitude())) * Math.cos(Math.toRadians(dest.getLatitude())) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			return (dist);
		}
	}
    
}
