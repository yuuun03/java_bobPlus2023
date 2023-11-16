package user;

import java.util.Vector;

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
	private int birth = 0;
	private int userRank = 5;
	
	//---알러지
	public Vector<String> allergy = new Vector<String> ();
	
	//---배송지 주소
	protected String address = "";
	
	//---본인인증
	protected boolean idVerification = false;
	
	//---목록: 장바구니, 찜, 구매
	protected Vector<String> cartList = new Vector<String> ();
	protected Vector<String> likeList = new Vector<String> ();
	protected Vector<String> buyList = new Vector<String> ();
	
	//---생일 쿠폰
	protected int birthCoupon = 0;
	
	// 멤버 메소드
	// 생성자
	public UserInfoDetail() {}
	public UserInfoDetail(String name, String id, String email, String password, 
			long phoneNum, int birth, int userRank, Vector<String> allergy, String address, 
			boolean idVerification, Vector<String> cartList, Vector<String> liketList,
			Vector<String> buytList, int birthCoupon) {
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
		
		//---본인인증
		this.idVerification = idVerification;
		
		//---목록: 장바구니, 찜, 구매
		this.cartList = cartList;
		this.likeList = likeList;
		this.buyList = buyList;
		
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
	
	public boolean getIdVerification() {return this.idVerification;}
	
	public Vector<String> getCartList() {return this.cartList;}
	public Vector<String> getLiketList() {return this.likeList;}
	public Vector<String> getBuytList() {return this.buytList;}

	// 설정자
	protected void setName(String name) {this.name = name;}
	
	protected void setId(String id) {this.id = id;}
	protected void setPassword(String password) {this.password = password;}
	
	protected void setEmail(String email) {this.email = email;}
	protected void setPhoneNum(long phoneNum) {this.phoneNum = phoneNum;}
	protected void setBirth(int birth) {this.birth = birth;}
	protected void setUserRank(int userRank) {this.userRank = userRank;}
	
}
	
	
}
