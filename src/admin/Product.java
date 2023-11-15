package admin;

import java.util.Vector;

public class Product {
	//멤버 변수
	//---기초 정보
	private String name = "";
	private String image = "";
	private double productStar = 0.0;
	
	//---수량 관련
	int serving = 1;
	private int productCount = 0;
		
	//---가격 관련
	private int price = 0;
	private double onePersonPrice = price / (double)serving;
	private double productDisRate = 0.0;
	
	//--제품 관련 정보
	private Vector<String> cookingUtensils = new Vector<String>();
	private Vector<String> containAllergy = new Vector<String>();
	
			
	//멤버 메소드
	//생성자
	public Product() {}
	
	public Product(String name, int price,
			Vector<String> containAllergy) {
		this.name = name;
		this.price = price;
		this.containAllergy = containAllergy;
	}
	
	public Product(String name, String image, double productStar,
			int serving, int productCount, int price,
			double productDisRate, Vector<String> cookingUtensils,
			Vector<String> containAllergy) {
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
	public String getName() {return this.name;}
	public String getImage() {return this.image;}
	public double getProductStar() {return this.productStar;}
	
	public int getServing() {return this.serving;}
	public int getProductCount() {return this.productCount;}
	
	public int getPrice() {return this.price;}
	public double getOnePersonPrice() {return this.onePersonPrice;}
	public double getProductDisRate() {return this.productDisRate;}
	
	public Vector<String> getCookingUtensils() {return this.cookingUtensils;}
	public Vector<String> getContainAllergy() {return this.containAllergy;}
			
	
	//설정자
	protected void setName(String name) {this.name = name;}
	protected void setImage(String image) {this.image = image;}
	protected void setProductStar(double productStar) {this.productStar = productStar;}
	
	protected void setServing(int serving) {this.serving = serving;}
	protected void setProductCount(int productCount) {this.productCount = productCount;}
	
	protected void setPrice(int price) {this.price = price;}
	protected void setOnePersonPrice(double onePersonPrice) {this.onePersonPrice = onePersonPrice;}
	protected void setProductDisRate(double productDisRate) {this.productDisRate = productDisRate;}
	
	public void setCookingUtensils(Vector<String> cookingUtensils) {
		this.cookingUtensils = cookingUtensils;}
	public void setContainAllergy (Vector<String> containAllergy){
		this.containAllergy = containAllergy;}

	
	@Override
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
	"조리도구 정보 : " + this.getCookingUtensils() + "\n" +
	"알러지 정보 : " + this.getContainAllergy() + "\n" +
	"======================================"+ "\n";
	}
	
}
