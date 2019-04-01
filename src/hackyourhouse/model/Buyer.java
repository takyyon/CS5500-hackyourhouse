package hackyourhouse.model;

public class Buyer extends User{

	protected String Street1;
	protected String Street2;
	protected String City;
	protected String State;
	protected Integer Zip;
	protected UserType BuyerType;
	
	public enum UserType {
		Student, Professional, Family
	}

	public String getStreet1() {
		return Street1;
	}

	public void setStreet1(String street1) {
		Street1 = street1;
	}

	public String getStreet2() {
		return Street2;
	}

	public void setStreet2(String street2) {
		Street2 = street2;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public Integer getZip() {
		return Zip;
	}

	public void setZip(Integer zip) {
		Zip = zip;
	}

	public UserType getBuyerType() {
		return BuyerType;
	}

	public void setBuyerType(UserType type) {
		BuyerType = type;
	}
	
	public Buyer(String userName, String password, String firstName, String lastName, String email, String Usertype, String street1,
			String street2, String city, String state, Integer zip, UserType type) {
		super(userName, password, firstName, lastName, email, Usertype);
		Street1 = street1;
		Street2 = street2;
		City = city;
		State = state;
		Zip = zip;
		BuyerType = type;
	}
	
}
