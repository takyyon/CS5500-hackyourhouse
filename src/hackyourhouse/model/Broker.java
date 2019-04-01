package hackyourhouse.model;

import java.math.BigDecimal;

public class Broker extends User{
	protected String Phone;
	protected BigDecimal Rating;
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public BigDecimal getRating() {
		return Rating;
	}
	public void setRating(BigDecimal rating) {
		Rating = rating;
	}
	public Broker(String userName, String password, String firstName, String lastName, String email, String type, String phone,
			BigDecimal rating) {
		super(userName, password, firstName, lastName, email, type);
		Phone = phone;
		Rating = rating;
	}
	
	
}
