package admin;

import java.util.Vector;

public class MasterGoods {
	//멤버 변수
	// > pList : 상품 저장 배열 
	private Vector<Product> pList = new Vector<Product>();//스택 사용
	
	//생성자
	public MasterGoods() {}
	
	//상품 추가
	private void productAdd(String name, String image, double productStar,
			int serving, int productCount, int price,
			double productDisRate) {
		
		//새 상품 객체 생성.
		Product p = new Product(name, image, productStar,
				serving, productCount, price, productDisRate);
		
		//기존 상품 리스트에 추가
		pList.add(p);
	}
	
	//상품 삭제 : 이름 입력 받으면 삭제.
	private void productDelete(String name) {
		if (pList.isEmpty()) {System.out.println("등록되어 있는 상품이 없습니다.");} 
		for(int i = 0; i < pList.size(); i++) {
			//pList의 현재 인덱스에 있는 객체의 이름이 받아온 이름과 같을 경우
			if (pList.get(i).getName().equals(name)) {
				pList.remove(i);
				return;
			};
		}
		
		System.out.println("찾으시는 상품이 없습니다.");
	}
	
	//상품 리스트 전체 출력
	private void checkProduct() {
		if (pList.isEmpty()) {System.out.println("등록되어 있는 상품이 없습니다.");} 
		else {
			for(Product i : pList) {
				/*
				 * 출력할 때 화면상에 출력 예정.
				 * 단순 출력이면 Product 클래스에 toString 오버라이딩 하여 사용.
				 * 아닐 경우 양식보고 지정
				 * (현재 오버라이딩 되지 않은 상태임.)
				 * */
				System.out.println(i.toString());
			}
		}
	}
	
	//할인율 조정
	private void disRate(String name, double disRate) {
		if (pList.isEmpty()) {System.out.println("등록되어 있는 상품이 없습니다.");} 
		for(Product i : pList) {
			//pList의 현재 인덱스에 있는 객체의 이름이 받아온 이름과 같을 경우
			if (i.getName().equals(name)) {
				i.setProductDisRate(disRate);;
				return;
			};
		}
		System.out.println("찾으시는 상품이 없습니다.");
	}
	
	//재고 수량 변경
	private void stackChange(String name, int productCount) {
		if (pList.isEmpty()) {System.out.println("등록되어 있는 상품이 없습니다.");} 
		for(Product i : pList) {
			//pList의 현재 인덱스에 있는 객체의 이름이 받아온 이름과 같을 경우
			if (i.getName().equals(name)) {
				i.setProductCount(productCount);;
				return;
			};
		}
		System.out.println("찾으시는 상품이 없습니다.");
	}
	
	public String productPopular() {
		return "추후 구현 예정";
	}
	
	//scrollPage 어떻게 구상한거지?? 봐야 알 듯.
	public void scrollPage() {}

}
