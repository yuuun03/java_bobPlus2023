package user;

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
	
	//---알러지
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
		
		//---알러지
		this.allergy = allergy;
		
		//---배송지 주소
		this.address = address;
		
		//--- 로그인 확인
		this.logCheck = logCheck;
		
		//---생일 쿠폰
		this.birthCoupon = birthCoupon;
	}
	
	// 접근자
	public String getName() {return this.name;}
	
	public String getId() {return this.id;}
	public String getPassword() {return this.password;}
	
	public String getEmail() {return this.email;}
	public long getPhoneNum() {return this.phoneNum;}
	public int getBirth() {return this.birth;}
	public int getUserRank() {return this.userRank;}
	
	public Vector<String> getAllergy() {return this.allergy;}
	
	public String getAddress() {return this.address;}
	
	public boolean getLogCheck() {return this.logCheck;}
	
	public Vector<Product> getCartList() {return this.cartList;}
	public Vector<Product> getLiketList() {return this.likeList;}
	public Vector<Product> getBuyList() {return this.buyList;}

	// 설정자
	protected void setName(String name) {this.name = name;}
	
	protected void setId(String id) {this.id = id;}
	protected void setPassword(String password) {this.password = password;}
	
	protected void setEmail(String email) {this.email = email;}
	protected void setPhoneNum(long phoneNum) {this.phoneNum = phoneNum;}
	protected void setBirth(int birth) {this.birth = birth;}
	protected void setUserRank(int userRank) {this.userRank = userRank;}
	
	public void setAllergy(Vector<String> allergy) {this.allergy = allergy;}
	
	protected void setAddress(String address) {this.address = address;}
	
	public void setLogCheck(boolean logCheck) {this.logCheck = logCheck;}

	protected void setCartList(Vector<Product> cartList) {this.cartList = cartList;}
	protected void setLikeList(Vector<Product> likeList) {this.likeList = likeList;}
	protected void setBuyList(Vector<Product> buyList) {this.buyList = buyList;}
}
