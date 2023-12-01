package admin;

import java.util.Vector;

public class Product {
	//멤버 변수
	//---기초 정보
	private String name = ""; //상품 이름
	private String image = ""; //상품 사진
	private double productStar = 0.0; //상품 별점
	
	//---수량 관련
	int serving = 1; //인분 수
	private int productCount = 0; //상품 수량
		
	//---가격 관련
	private int price = 0; //상품 원가
	private double onePersonPrice = price / (double)serving; //상품 1인분 가격
	private double productDisRate = 0.0; //상품 할인율
	
	//--제품 관련 정보
	private Vector<String> cookingUtensils = new Vector<String>(); //상품 조리시 필요 조리기구
	private Vector<String> containAllergy = new Vector<String>(); //상품에 포함된 알러지 정보
	
			
	//멤버 메소드
	//생성자
	public Product() {} //기본 생성자
	
	public Product(String name, int price,
			Vector<String> containAllergy) { //이름, 가격, 알러지 정보만 받아와서 생성
		this.name = name;
		this.price = price;
		this.containAllergy = containAllergy;
	}
	
	public Product(String name, String image, double productStar,
			int serving, int productCount, int price,
			double productDisRate, Vector<String> cookingUtensils,
			Vector<String> containAllergy) { //상품의 모든 정보를 입력받아 생성하는 생성자
		//--기본 정보
		this.name = name;
		this.image = image;
		this.productStar = productStar;
		
		//--수량 관련 정보
		this.serving = serving;
		this.productCount = productCount;
		
		//--가격 관련 정보
		this.price = price;
		this.onePersonPrice = price / (double)serving;
		this.productDisRate = productDisRate;
		
		//--제품 관련 정보
		this.cookingUtensils = cookingUtensils;
		this.containAllergy = containAllergy;
	}
	
	//접근자
	public String getName() {return this.name;} //상품명
	public String getImage() {return this.image;} //상품 사진
	public double getProductStar() {return this.productStar;} //별점
	
	public int getServing() {return this.serving;} //인분 수
	public int getProductCount() {return this.productCount;} //상품 수량
	
	public int getPrice() {return this.price;} //상품 가격
	public double getOnePersonPrice() {return this.onePersonPrice;} //상품 1인분 가격
	public double getProductDisRate() {return this.productDisRate;} //할인률
	
	public Vector<String> getCookingUtensils() {return this.cookingUtensils;} //상품 조리 시 필요한 조리 기구
	public Vector<String> getContainAllergy() {return this.containAllergy;} //상품에 포함된 알러지
			
	
	//설정자
	protected void setName(String name) {this.name = name;} //상품 이름
	protected void setImage(String image) {this.image = image;} //상품 이미지
	protected void setProductStar(double productStar) {this.productStar = productStar;} //별점
	
	protected void setServing(int serving) {this.serving = serving;} //상품 인분 수 
	protected void setProductCount(int productCount) {this.productCount = productCount;} //상품 수량
	
	protected void setPrice(int price) {this.price = price;} //상품 가격
	protected void setOnePersonPrice(double onePersonPrice) {this.onePersonPrice = onePersonPrice;} //상품 1인분당 가격
	protected void setProductDisRate(double productDisRate) {this.productDisRate = productDisRate;} //할인율
	
	public void setCookingUtensils(Vector<String> cookingUtensils) { //상품 조리 시 필요 조리기구
		this.cookingUtensils = cookingUtensils;}
	public void setContainAllergy (Vector<String> containAllergy){ //상품 포함된 알러지 정보
		this.containAllergy = containAllergy;}

	
	@Override
	//상품 정보 출력(운영자 콘솔 확인용)
	public String toString() { //이미지는 출력 안함.
		return "======================================"+ "\n" +
	"제품명 : " + this.getName() + "\n" +
	"사진(Link) : " + this.getImage() + "\n" +
	"별점 : " + this.getProductStar() + "점\n" +
	"현재 수량 : " + this.getProductCount() + "개\n" +
	"가격 : " + this.getPrice() + "원\n" +
	"1인분 당 가격 : " + this.getOnePersonPrice() + "원\n" +
	"현재 밀키트에서 설정된 인분(N인분) : " + this.getServing() + "인분\n" +
	"할인율 : " + this.getProductDisRate() + "%\n" +
	"--------------------------------------"+ "\n" +
	"조리도구 정보 : " + this.getCookingUtensils() + "\n" + // 조리도구 기능 삭제할 수도
	"알러지 정보 : " + this.getContainAllergy() + "\n" +
	"======================================"+ "\n";
	}
	
}
