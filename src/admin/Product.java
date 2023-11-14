package admin;

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
	
			
	//멤버 메소드
	//생성자
	public Product() {}
	
	public Product(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	public Product(String name, String image, double productStar,
			int serving, int productCount, int price,
			double productDisRate) {
		this.name = name;
		this.image = image;
		this.productStar = productStar;
		
		this.serving = serving;
		this.productCount = productCount;
			
		this.price = price;
		this.onePersonPrice = price / (double)serving;
		this.productDisRate = productDisRate;		
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
			
	
	//설정자
	public void setName(String name) {this.name = name;}
	public void setImage(String image) {this.image = image;}
	public void setProductStar(double productStar) {this.productStar = productStar;}
	
	public void setServing(int serving) {this.serving = serving;}
	public void setProductCount(int productCount) {this.productCount = productCount;}
	
	public void setPrice(int price) {this.price = price;}
	public void setOnePersonPrice(double onePersonPrice) {this.onePersonPrice = onePersonPrice;}
	public void setProductDisRate(double productDisRate) {this.productDisRate = productDisRate;}
	
	/*@Override
	public String toString() {
		return 0;
	}*/
	
}
