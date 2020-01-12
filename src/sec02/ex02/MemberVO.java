package sec02.ex02;

import java.util.Date;

public class MemberVO {
	private String username;
	private String password;
	private String name;
	private String email;
	private String phone1;
	private String phone2;
	private String phone3;
	private String zipCode;
	private String strAddress;
	private String address;
	private String detailedAddress;
	private String referAddress;
	private Date joinDate;
	
	
	
	
	public MemberVO() {
	}

	public MemberVO(String username, String password, String name, String email, String phone1, String phone2,
			String phone3, String zipCode, String strAddress, String address, String detailedAddress,
			String referAddress) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.phone3 = phone3;
		this.zipCode = zipCode;
		this.strAddress = strAddress;
		this.address = address;
		this.detailedAddress = detailedAddress;
		this.referAddress = referAddress;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getPhone3() {
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getStrAddress() {
		return strAddress;
	}
	public void setStrAddress(String strAddress) {
		this.strAddress = strAddress;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetailedAddress() {
		return detailedAddress;
	}
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}
	public String getReferAddress() {
		return referAddress;
	}
	public void setReferAddress(String referAddress) {
		this.referAddress = referAddress;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "MemberVO [username=" + username + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", phone1=" + phone1 + ", phone2=" + phone2 + ", phone3=" + phone3 + ", zipCode=" + zipCode
				+ ", strAddress=" + strAddress + ", address=" + address + ", detailedAddress=" + detailedAddress
				+ ", referAddress=" + referAddress + ", joinDate=" + joinDate + "]";
	}
	
	
	
}
