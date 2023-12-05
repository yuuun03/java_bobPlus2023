package user;

/*
 * 과목: 자바프로그래밍(04)
 * 학과: 컴퓨터공학부
 * 학번: 2022136095
 * 이름: 이찬비
 */

import java.util.Vector;

public class UserInfo {
	//---멤버 변수
	// > userList : 회원 정보 저장 배열
	private Vector<UserInfoDetail> userList = new Vector<UserInfoDetail>();//스택 사용
	
	// 생성자
	public UserInfo() {}
	
	//신규 회원(생성자 아님 주의!!!)
	void newUser(String name, String id, String email, String password, long phoneNum, int birth, 
			int userRank, Vector<String> allergy, String address, boolean isLogin, int birthCoupon) {
		
		//새 회원 정보 객체 생성.
		UserInfoDetail user = new UserInfoDetail(name, id, email, password, phoneNum, birth, 
				userRank, allergy, address, isLogin, birthCoupon);

		//기존 회원 정보 리스트에 추가
		userList.add(user);
	}
	public Vector<UserInfoDetail> getUserList(){
		return this.userList;
	}
	// 회원 탈퇴
	private void userDelete(String id, String password) {
		if (userList.isEmpty()) {System.out.println("등록되어 있는 회원이 없습니다.");} // 회원 정보가 없다면 "등록되어 있는 회원이 없습니다."라고 출력
		for(int i = 0; i < userList.size(); i++) {
			// userList의 현재 인덱스에 있는 객체의 아이디와 비밀번호가 받아온 아이디와 비밀번호와 같을 경우
			if(userList.get(i).getId().equals(id) && userList.get(i).getPassword().equals(password)) {
				userList.remove(i);  // 회원 정보가 들어있는 userList에서 삭제
				return;
			}
		}
		System.out.println("현재 아이디와 비밀번호가 일치하지 않습니다."); // 회원이 입력한 정보에서 아이디와 비밀번호가 다를 경우
	}
	
	// 회원 인증(isThatYou를 checkUser로 이름 변경!!! void를 boolean으로 변경!!!)
	private boolean checkUser (String id, String password) {
		for (int i = 0; i < userList.size(); i++) {
			// userList의 현재 인덱스에 있는 객체의 아이디와 비밀번호가 받아온 아이디와 비밀번호와 같을 경우
			if(userList.get(i).getId().equals(id) && userList.get(i).getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	// 아이디 중복 인증
		private boolean availableId(String newId) {
			for (int i = 0; i < userList.size(); i++) {
				// userList의 현재 인덱스에 있는 객체의 아이디와 새로 받아온 아이디와 같을 경우
				if (userList.get(i).getId().equals(newId)) {return true;}
			}
			return false;
		}
	
	// 회원 이름 변경
	private void changeName(String id, String password, String newName) {
		if (userList.isEmpty()) {System.out.println("등록되어 있는 회원이 없습니다.");} // 회원 정보가 없다면 "등록되어 있는 회원이 없습니다."라고 출력
		// 회원 인증
		if (checkUser(id, password)) {
			// id
			for (UserInfoDetail i : userList) {
				if (i.getId().equals(id)) { // userList의 현재 인덱스에 있는 객체의 아이디 받아온 아이디가 같을 경우
					i.setName(newName); // 회원의 이름 정보를 수정
					return;
				}
			}
		}else { // 회원 인증에 실패했을 경우
			System.out.println("현재 아이디와 비밀번호가 일치하지 않습니다."); // 회원이 입력한 정보에서 아이디와 비밀번호가 다를 경우
		}
	}
	
	// 회원 아이디 변경
	private void changeId(String id, String password, String newId) {
		if (userList.isEmpty()) {System.out.println("등록되어 있는 회원이 없습니다.");} // 회원 정보가 없다면 "등록되어 있는 회원이 없습니다."라고 출력
		// 회원 인증
		if (checkUser(id, password)) {
			for (UserInfoDetail i : userList) {
				if (i.getId().equals(id)) { // userList의 현재 인덱스에 있는 객체의 아이디 받아온 아이디가 같을 경우
					// 아이디 중복 확인
					if (availableId(newId)) {System.out.println("사용할 수 없는 아이디입니다.");} // 아이디 중복 인증 함수에서 동일한 아이디가 있다면 사용할 수 없다는 문구 출력
					else{i.setId(newId);} // 회원 아이디 정보 수정
					return;
				}
			}
		}else { // 회원 인증에 실패했을 경우
			System.out.println("현재 아이디와 비밀번호가 일치하지 않습니다."); // 회원이 입력한 정보에서 아이디와 비밀번호가 다를 경우
		}
	}
	
	// 회원 비밀번호 변경
	private void changePassword(String id, String password, String newPassword) {
		if (userList.isEmpty()) {System.out.println("등록되어 있는 회원이 없습니다.");} // 회원 정보가 없다면 "등록되어 있는 회원이 없습니다."라고 출력
		// 회원 인증
		if (checkUser(id, password)) {
			for (UserInfoDetail i : userList) {
				if (i.getId().equals(id)) { // userList의 현재 인덱스에 있는 객체의 아이디 받아온 아이디가 같을 경우
					i.setPassword(newPassword); // 회원 비밀번호 정보 수정
					return;
				}
			}
		}else { // 회원 인증에 실패했을 경우
			System.out.println("현재 아이디와 비밀번호가 일치하지 않습니다."); // 회원이 입력한 정보에서 아이디와 비밀번호가 다를 경우
		}
	}
	
	// 회원 이메일 변경
		private void changeEmail(String id, String password, String newEmail) {
			if (userList.isEmpty()) {System.out.println("등록되어 있는 회원이 없습니다.");} // 회원 정보가 없다면 "등록되어 있는 회원이 없습니다."라고 출력
			// 회원 인증
			if (checkUser(id, password)) {
				for (UserInfoDetail i : userList) {
					if (i.getId().equals(id)) { // userList의 현재 인덱스에 있는 객체의 아이디 받아온 아이디가 같을 경우
						i.setEmail(newEmail); // 회원 이메일 정보 수정
						return;
					}
				}
			}else { // 회원 인증에 실패했을 경우
				System.out.println("현재 아이디와 비밀번호가 일치하지 않습니다."); // 회원이 입력한 정보에서 아이디와 비밀번호가 다를 경우
			}
		}
	
	// 회원 휴대폰 번호 변경
	private void changePhoneNum(String id, String password, long newPhoneNum) {
		if (userList.isEmpty()) {System.out.println("등록되어 있는 회원이 없습니다.");} // 회원 정보가 없다면 "등록되어 있는 회원이 없습니다."라고 출력
		// 회원 인증
		if (checkUser(id, password)) {
			for (UserInfoDetail i : userList) {
				if (i.getId().equals(id)) { // userList의 현재 인덱스에 있는 객체의 아이디 받아온 아이디가 같을 경우
					i.setPhoneNum(newPhoneNum); // 회원 전화번호 정보 수정
					return;
				}
			}
		}else { // 회원 인증에 실패했을 경우
			System.out.println("현재 아이디와 비밀번호가 일치하지 않습니다."); // 회원이 입력한 정보에서 아이디와 비밀번호가 다를 경우
		}
	}
	
	// 회원 등급 변경
	private void changeUserRank(String id, String password, int userRank) {
		// 추후 구현
	}
	
	// 회원 알레르기 정보 변경
	private void changeAllergy(String id, String password, Vector<String> newAllergy) {
		if (userList.isEmpty()) {System.out.println("등록되어 있는 회원이 없습니다.");} // 회원 정보가 없다면 "등록되어 있는 회원이 없습니다."라고 출력
		// 회원 인증
		if (checkUser(id, password)) {
			for (UserInfoDetail i : userList) {
				if (i.getId().equals(id)) { // userList의 현재 인덱스에 있는 객체의 아이디 받아온 아이디가 같을 경우
					i.setAllergy(newAllergy); // 회원 알레르기 정보 수정
					return;
				}
			}
		}else { // 회원 인증에 실패했을 경우
			System.out.println("현재 아이디와 비밀번호가 일치하지 않습니다."); // 회원이 입력한 정보에서 아이디와 비밀번호가 다를 경우
		}
	}
	
	// void showUserInfo() {} -- user에서 UX/UI로 따로 실행 예정
	// public String showDeliveryInfo() {} -- 추후 구현함
}
