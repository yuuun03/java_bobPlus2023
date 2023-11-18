package user;

import java.util.Vector;

public class UserInfo {
	//---멤버 변수
	// > userList : 사용자 정보 저장 배열
	private Vector<UserInfoDetail> userList = new Vector<UserInfoDetail>();//스택 사용
	
	// 생성자
	public UserInfo() {}
	
	// 사용자 정보 추가(생성자 아님 주의!!!)
	private void userInfoAdd(String name, String id, String email, String password, 
			long phoneNum, int birth, int userRank, Vector<String> allergy, String address, 
			boolean idVerification, Vector<String> cartList, Vector<String> liketList,
			Vector<String> buytList, int birthCoupon) {
		
		//새 사용자 정보 객체 생성.
		UserInfoDetail user = new UserInfoDetail(name, id, email, password, phoneNum, birth, 
				userRank, allergy, address, idVerification, cartList, liketList, buytList, birthCoupon);
		
		//기존 사용자 정보 리스트에 추가
		userList.add(user);
	}
	
	
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
