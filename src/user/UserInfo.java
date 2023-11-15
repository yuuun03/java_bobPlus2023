package user;

import java.util.Vector;

public class UserInfo {
	//---멤버 변수
	
	//---회원 이름
	private String name = "";
	
	//---아이디 비번
	private String id = "";
	private String password = "";
	
	//---이메일, 전화번호, 생일
	private String email = "";
	private long phoneNum = 0;
	private int birth = 0;
	
	//---알러지
	public Vector<> allergy = new Vector<> ();
	
	//---배송지 주소
	protected String address = "";
	
	//---본인인증
	protected boolean idVerification = false;
	
	//---
	//---생일 쿠폰
	protected int birthCoupon = 0;
	
	// 생성자
	public UserInfo() {}
	
	
	public String getName() {}
	public long getPhoneNum() {}
	private void isThatYou(String password) {}
	private void addAllergy(String allergy) {}
	private void delAllergy(String allergy) {}
	public void setPw(String password) {}
	public void setName(String name) {}
	public void setEmail(String email) {}
	public void setBirth(int birth) {}
	void showUserInfo() {}
	public String showDeliveryInfo() {}
}
