package yp.model.view;

import yp.model.TUser;

public class AddressJsonView {
	private Integer addressId;
	private Integer userId;
	private String addressAddress;
	private String addressPostcode;
	private String addressPhone;
	private String addressLinkman;
	private Boolean addressIsdefault;
	private Boolean addressIsdel;
	
	public AddressJsonView() {
		
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAddressAddress() {
		return addressAddress;
	}

	public void setAddressAddress(String addressAddress) {
		this.addressAddress = addressAddress;
	}

	public String getAddressPostcode() {
		return addressPostcode;
	}

	public void setAddressPostcode(String addressPostcode) {
		this.addressPostcode = addressPostcode;
	}

	public String getAddressPhone() {
		return addressPhone;
	}

	public void setAddressPhone(String addressPhone) {
		this.addressPhone = addressPhone;
	}

	public String getAddressLinkman() {
		return addressLinkman;
	}

	public void setAddressLinkman(String addressLinkman) {
		this.addressLinkman = addressLinkman;
	}

	public Boolean getAddressIsdefault() {
		return addressIsdefault;
	}

	public void setAddressIsdefault(Boolean addressIsdefault) {
		this.addressIsdefault = addressIsdefault;
	}

	public Boolean getAddressIsdel() {
		return addressIsdel;
	}

	public void setAddressIsdel(Boolean addressIsdel) {
		this.addressIsdel = addressIsdel;
	}
}
