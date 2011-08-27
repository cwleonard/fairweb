package com.amphibian.fair.account.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.amphibian.fair.beans.User;

public class UserForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String id;
	
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
	
	private String createPassword;
	
	private String retypePassword;
	
	private String email;
	
	private String webAdmin;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getCreatePassword() {
		return createPassword;
	}

	public void setCreatePassword(String createPassword) {
		this.createPassword = createPassword;
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

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getWebAdmin() {
		return webAdmin;
	}

	public void setWebAdmin(String webAdmin) {
		this.webAdmin = webAdmin;
	}
	
	public User getUserObject() {
		
		User user = new User();
		
		user.setId(Integer.parseInt(id));
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setOrgName(orgName);
		user.setTaxId(taxId);
		user.setAddress(address);
		user.setCity(city);
		user.setState(state);
		user.setZip(zip);
		user.setPhone(phone);
		user.setUserId(userId);
		user.setPassword(createPassword);
		user.setEmail(email);
		user.setWebAdmin(Boolean.valueOf(webAdmin).booleanValue());
		
		return user;
		
	}
	
	public void setFromUserObject(User user) {
		
		id = String.valueOf(user.getId());
		firstName = user.getFirstName();
		lastName = user.getLastName();
		orgName = user.getOrgName();
		taxId = user.getTaxId();
		address = user.getAddress();
		city = user.getCity();
		state = user.getState();
		zip = user.getZip();
		phone = user.getPhone();
		userId = user.getUserId();
		email = user.getEmail();
		
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		id = "-1";
		firstName = "";
		lastName = "";
		orgName = "";
		taxId = "";
		address = "";
		city = "";
		state = "";
		zip = "";
		phone = "";
		userId = "";
		createPassword = "";
		retypePassword = "";
		email = "";
		webAdmin = "";
		
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		
		if ( ((firstName == null || firstName.length() == 0) ||
			 (lastName == null || lastName.length() == 0)) &&
			 (orgName == null || orgName.length() == 0) )
		{
			errors.add("fisrtName", new ActionMessage("name.required"));
			errors.add("orgName", new ActionMessage("name.required"));
		}
		
		if (address == null || address.length() == 0) {
			errors.add("address", new ActionMessage("address.required"));
		}
		
		if (city == null || city.length() == 0) {
			errors.add("city", new ActionMessage("city.required"));
		}
		
		if (zip == null || zip.length() == 0) {
			errors.add("zip", new ActionMessage("zip.required"));
		}
		
		if (userId == null || userId.length() == 0) {
			errors.add("userId", new ActionMessage("userid.required"));
		}

		if (getUserObject().getId() == -1) {

			boolean matchPasswords = true;
			if (createPassword == null || createPassword.length() == 0) {
				errors.add("password", new ActionMessage("password.required"));
				matchPasswords = false;
			}

			if (retypePassword == null || retypePassword.length() == 0) {
				errors.add("password", new ActionMessage("password.retype.required"));
				matchPasswords = false;
			}

			if (matchPasswords) {
				if (!createPassword.equals(retypePassword)) {
					errors.add("password", new ActionMessage("passwords.dont.match"));
				}
			}

		}

		if (email == null || email.length() == 0) {
			errors.add("email", new ActionMessage("email.required"));
		}
		
		request.setAttribute("user", getUserObject());
		if (getUserObject().getId() != -1) {
			request.setAttribute("allowEditUserId", new Boolean(false));
		} else {
			request.setAttribute("allowEditUserId", new Boolean(true));
		}
		
		return errors;
		
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

	/**
	 * @return the retypePassword
	 */
	public String getRetypePassword() {
		return retypePassword;
	}

	/**
	 * @param retypePassword the retypePassword to set
	 */
	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}
	
}
