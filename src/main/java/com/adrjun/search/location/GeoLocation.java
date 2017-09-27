package com.adrjun.search.location;

/**
 * Implemented based on the theory given by Jan Philip Matuschek in his blog
 * http://janmatuschek.de/LatitudeLongitudeBoundingCoordinates
 */
public class GeoLocation {

	private double radLat;  // latitude in radians
	private double radLon;  // longitude in radians

	private double degLat;  // latitude in degrees
	private double degLon;  // longitude in degrees

	private static final double MIN_LAT = Math.toRadians(-90d);  // -PI/2
	private static final double MAX_LAT = Math.toRadians(90d);   //  PI/2
	private static final double MIN_LON = Math.toRadians(-180d); // -PI
	private static final double MAX_LON = Math.toRadians(180d);  //  PI

	private GeoLocation () {
	}

	
	public static GeoLocation fromDegrees(double latitude, double longitude) {
		GeoLocation result = new GeoLocation();
		result.radLat = Math.toRadians(latitude);
		result.radLon = Math.toRadians(longitude);
		result.degLat = latitude;
		result.degLon = longitude;
		result.checkBounds();
		return result;
	}

	public static GeoLocation fromRadians(double latitude, double longitude) {
		GeoLocation result = new GeoLocation();
		result.radLat = latitude;
		result.radLon = longitude;
		result.degLat = Math.toDegrees(latitude);
		result.degLon = Math.toDegrees(longitude);
		result.checkBounds();
		return result;
	}

	private void checkBounds() {
		if (radLat < MIN_LAT || radLat > MAX_LAT ||
				radLon < MIN_LON || radLon > MAX_LON)
			throw new IllegalArgumentException();
	}

	
	public double getLatitudeInDegrees() {
		return degLat;
	}

	
	public double getLongitudeInDegrees() {
		return degLon;
	}

	public double getLatitudeInRadians() {
		return radLat;
	}

	public double getLongitudeInRadians() {
		return radLon;
	}

//	@Override
//	public String toString() {
//		return "(" + degLat + "\u00B0, " + degLon + "\u00B0) = (" +
//				 radLat + " rad, " + radLon + " rad)";
//	}
	@Override
	public String toString() {
		return "(" + degLat +", " + degLon + ")";
	}


	public double distanceTo(GeoLocation location, double radius) {
		return Math.acos(Math.sin(radLat) * Math.sin(location.radLat) +
				Math.cos(radLat) * Math.cos(location.radLat) *
				Math.cos(radLon - location.radLon)) * radius;
	}


	public GeoLocation[] boundingCoordinates(double distanceInKiloMeter, double earthRadius) {

		if (earthRadius < 0d || distanceInKiloMeter < 0d)
			throw new IllegalArgumentException();

		// angular distance in radians on a great circle
		double radDist = distanceInKiloMeter / earthRadius;

		double minLat = radLat - radDist;
		double maxLat = radLat + radDist;

		double minLon, maxLon;
		if (minLat > MIN_LAT && maxLat < MAX_LAT) {
			double deltaLon = Math.asin(Math.sin(radDist) /
				Math.cos(radLat));
			minLon = radLon - deltaLon;
			if (minLon < MIN_LON) minLon += 2d * Math.PI;
			maxLon = radLon + deltaLon;
			if (maxLon > MAX_LON) maxLon -= 2d * Math.PI;
		} else {
			// a pole is within the distance
			minLat = Math.max(minLat, MIN_LAT);
			maxLat = Math.min(maxLat, MAX_LAT);
			minLon = MIN_LON;
			maxLon = MAX_LON;
		}

		return new GeoLocation[]{fromRadians(minLat, minLon),
				fromRadians(maxLat, maxLon)};
	}

}