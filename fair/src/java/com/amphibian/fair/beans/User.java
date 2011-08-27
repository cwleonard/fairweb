package com.amphibian.fair.beans;

public class User implements Comparable<User> {

	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String orgName;
	
	private String taxId;
	
	private String address;
	
	private String city;
	
	private String state;
	
	private String zip;
	
	private String phone;
	
	private String userId;
	
	private String password;
	
	private String email;
	
	private boolean webAdmin;

	public User() {
		id = -1;
		webAdmin = false;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isWebAdmin() {
		return webAdmin;
	}

	public void setWebAdmin(boolean webAdmin) {
		this.webAdmin = webAdmin;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String getDisplayName() {
		if (orgName != null && orgName.length() > 0) {
			return orgName;
		} else {
			return lastName + ", " + firstName;
		}
	}
	
	public String toString() {
		if (orgName != null && orgName.length() > 0) {
			return orgName;
		} else {
			return firstName + " " + lastName;
		}
	}
	
	
	public String toStringXML() {
		
		StringBuffer buf = new StringBuffer();
		
		buf.append("<Exhibitor>");
		buf.append("<Id>").append(id).append("</Id>");
		buf.append("<FirstName>");
		if (firstName != null) {
			buf.append(firstName.replaceAll("&", "&amp;"));
		}
		buf.append("</FirstName>");
		buf.append("<LastName>");
		if (lastName != null) {
			buf.append(lastName.replaceAll("&", "&amp;"));
		}
		buf.append("</LastName>");
		buf.append("<OrgName>");
		if (orgName != null) {
			buf.append(orgName.replaceAll("&", "&amp;"));
		}
		buf.append("</OrgName>");
		buf.append("<DisplayName>").append(this.getDisplayName().replaceAll("&", "&amp;")).append("</DisplayName>");
		buf.append("<TaxId>").append(taxId).append("</TaxId>");
		buf.append("<Address>").append(address).append("</Address>");
		buf.append("<City>").append(city).append("</City>");
		buf.append("<State>").append(state).append("</State>");
		buf.append("<Zip>").append(zip).append("</Zip>");
		buf.append("<Phone>").append(phone).append("</Phone>");
		buf.append("<UserId>").append(userId).append("</UserId>");
		buf.append("<Password>").append(password).append("</Password>");
		buf.append("<Email>").append(email).append("</Email>");
		buf.append("<WebAdmin>").append(webAdmin).append("</WebAdmin>");
		buf.append("</Exhibitor>");

		return buf.toString();
		
	}

	public int compareTo(User arg0) {
		User o = (User)arg0;
		return this.getDisplayName().compareTo(o.getDisplayName());
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	
}
