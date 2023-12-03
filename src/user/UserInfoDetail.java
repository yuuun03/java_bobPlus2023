package user;

/*
 * 과목: 자바프로그래밍(04)
 * 학과: 컴퓨터공학부
 * 학번: 2022136095
 * 이름: 이찬비
 */

import java.util.Vector;

import admin.Product;

public class UserInfoDetail {
	//---멤버 변수
	//---회원 이름
	private String name = "";
	
	//---아이디 비번
	private String id = "";
	private String password = "";
	
	//---이메일, 전화번호, 생일, 회원 등급
	private String email = "";
	private long phoneNum = 0;
	private int birth;
	private int userRank = 5;
	
	//---알레르기
	public Vector<String> allergy = new Vector<String> ();
	
	//---배송지 주소
	protected String address = "";
	
	//---목록: 장바구니, 찜, 구매
	public Vector<Product> cartList = new Vector<Product>();
	protected Vector<Product> likeList = new Vector<Product>();
	protected Vector<Product> buyList = new Vector<Product>();
	
	// 로그인 확인
	private boolean logCheck = false;
	
	//---생일 쿠폰
	protected int birthCoupon = 0;
	
	// 멤버 메소드
	// 생성자
	public UserInfoDetail() {}
	public UserInfoDetail(String name, String id, String email, String password, long phoneNum, 
			int birth, int userRank, Vector<String> allergy, String address, boolean logCheck, int birthCoupon) {
		//---회원 이름
		this.name = name;
		
		//---아이디 비번
		this.id = id;
		this.password = password;
		
		//---이메일, 전화번호, 생일, 회원 등급
		this.email = email;
		this.phoneNum = phoneNum;
		this.birth = birth;
		this.userRank = userRank;
		
		//---알레르기
		this.allergy = allergy;
		
		//---배송지 주소
		this.address = address;
		
		//--- 로그인 확인
		this.logCheck = logCheck;
		
		//---생일 쿠폰
		this.birthCoupon = birthCoupon;
	}
	
	// 접근자
	public String getName() {return this.name;} // 회원 이름
	
	public String getId() {return this.id;} // 회원 아이디
	public String getPassword() {return this.password;} // 회원 비밀번호
	
	public String getEmail() {return this.email;} // 회원 이메일
	public long getPhoneNum() {return this.phoneNum;} // 회원 전화번호
	public int getBirth() {return this.birth;} // 회원 생일
	public int getUserRank() {return this.userRank;} // 회원 등급
	
	public Vector<String> getAllergy() {return this.allergy;} // 회원 알레르기 정보
	
	public String getAddress() {return this.address;} // 회원 주소
	
	public boolean getLogCheck() {return this.logCheck;} // 회원이 로그인 했는지 확인
	
	public Vector<Product> getCartList() {return this.cartList;} // 회원 장바구니 목록
	public Vector<Product> getLiketList() {return this.likeList;} // 회원 찜 목록
	public Vector<Product> getBuyList() {return this.buyList;} // 회원 구매 목록

	// 설정자
	protected void setName(String name) {this.name = name;} // 회원 이름
	
	protected void setId(String id) {this.id = id;} // 회원 아이디
	protected void setPassword(String password) {this.password = password;} // 회원 비밀번호
	
	protected void setEmail(String email) {this.email = email;} // 회원 이메일
	protected void setPhoneNum(long phoneNum) {this.phoneNum = phoneNum;} // 회원 전화번호
	protected void setBirth(int birth) {this.birth = birth;} // 회원 생일
	protected void setUserRank(int userRank) {this.userRank = userRank;} // 회원 등급
	
	public void setAllergy(Vector<String> allergy) {this.allergy = allergy;} // 회원 알르레기 정보
	
	protected void setAddress(String address) {this.address = address;} // 회원 주소
	
	public void setLogCheck(boolean logCheck) {this.logCheck = logCheck;} // 회원이 로그인 했는지 확인

	protected void setCartList(Vector<Product> cartList) {this.cartList = cartList;} // 회원 장바구니 목록
	protected void setLikeList(Vector<Product> likeList) {this.likeList = likeList;} // 회원 찜 목록
	protected void setBuyList(Vector<Product> buyList) {this.buyList = buyList;} // 회원 구매 목록
}
