package hackyourhouse.model;

import java.math.BigDecimal;

import hackyourhouse.model.Buyer.UserType;

public class Crime {

	private int crimeId;
	private BigDecimal longitude;
	private BigDecimal latitude;
	private int totalCrimes;
	
	protected CrimeType type;
	
	public enum CrimeType {
		Murder, Harassment, Robbery
	}

	public Crime(int crimeId, BigDecimal longitude, BigDecimal latitude, int totalCrimes, CrimeType type) {
		super();
		this.crimeId = crimeId;
		this.longitude = longitude;
		this.latitude = latitude;
		this.totalCrimes = totalCrimes;
		this.type = type;
	}
	
	public Crime(BigDecimal longitude, BigDecimal latitude, int totalCrimes, CrimeType type) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.totalCrimes = totalCrimes;
		this.type = type;
	}


	public int getCrimeId() {
		return crimeId;
	}

	public void setCrimeId(int crimeId) {
		this.crimeId = crimeId;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public int getTotalCrimes() {
		return totalCrimes;
	}

	public void setTotalCrimes(int totalCrimes) {
		this.totalCrimes = totalCrimes;
	}

	public CrimeType getType() {
		return type;
	}

	public void setType(CrimeType type) {
		this.type = type;
	}

}
