package admin;

import java.util.Vector;

public class MasterGoods {
	//멤버 변수
	// > pList : 상품 저장 배열 
	private Vector<Product> pList = new Vector<Product>();//스택 사용
	
	//생성자
	public MasterGoods() {} //기본 생성자
	
	//상품 추가(생성자 아님 주의!!!) //test상품 추가를 위해 public으로 변경
	public void productAdd(String name, String image, double productStar,
			int serving, int productCount, int price,
			double productDisRate, Vector<String> cookingUtensils,
			Vector<String> containAllergy) { //상품에 들어가는 정보들 전부 매개변수로 받아옴
		
		//새 상품 객체 생성
		Product p = new Product(name, image, productStar,
				serving, productCount, price, productDisRate,
				cookingUtensils,containAllergy);
		
		//기존 상품 리스트에 추가
		pList.add(p);
	}
	
	public Vector<Product> getPList(){return this.pList;} //상품 리스트 getter
	public void setPList(Vector<Product> newList){this.pList = newList;} //상품 리스트 setter
	
	//상품 삭제 : 이름 입력 받으면 삭제.
	private void productDelete(String name) {
		if (pList.isEmpty()) {System.out.println("등록되어 있는 상품이 없습니다.");} //등록된 상품 자체가 없을 경우 경고문 출력
		for(int i = 0; i < pList.size(); i++) {
			//pList의 현재 인덱스에 있는 객체의 이름이 받아온 이름과 같을 경우
			if (pList.get(i).getName().equals(name)) {
				pList.remove(i); //삭제
				return; //함수 종료
			};
		}
		
		//입력한 상품명과 일치하는 상품이 없을 경우 경고문 출력
		System.out.println("찾으시는 상품이 없습니다.");
	}
	
	//상품 리스트 전체 출력 //확인을 위해 public으로 변경
	public void checkProduct() {
		//등록된 상품 자체가 없을 경우 경고문 출력
		if (pList.isEmpty()) {System.out.println("등록되어 있는 상품이 없습니다.");} 
		else { //출력
			for(Product i : pList) { /* 메모장 형식 */ System.out.println(i.toString());}
		}
	}
	
	//할인율 조정
	private void disRate(String name, double disRate) {
		//등록된 상품 자체가 없을 경우 경고문 출력
		if (pList.isEmpty()) {System.out.println("등록되어 있는 상품이 없습니다.");} 
		for(Product i : pList) {
			//pList의 현재 인덱스에 있는 객체의 이름이 받아온 이름과 같을 경우
			if (i.getName().equals(name)) {
				i.setProductDisRate(disRate);; //할인 적용
				return; //함수 종료
			};
		}
		//입력한 상품명과 일치하는 상품이 없을 경우 경고문 출력
		System.out.println("찾으시는 상품이 없습니다.");
	}
	
	//재고 수량 변경
	private void stackChange(String name, int productCount) {
		//등록된 상품 자체가 없을 경우 경고문 출력
		if (pList.isEmpty()) {System.out.println("등록되어 있는 상품이 없습니다.");} 
		for(Product i : pList) {
			//pList의 현재 인덱스에 있는 객체의 이름이 받아온 이름과 같을 경우
			if (i.getName().equals(name)) {
				i.setProductCount(productCount); //수량 변동
				return; //함수 종료
			};
		}
		//입력한 상품명과 일치하는 상품이 없을 경우 경고문 출력
		System.out.println("찾으시는 상품이 없습니다.");
	}
	
	//인기 상품 반환 함수
	public String productPopular() {
		return "추후 구현 예정";
	}
}
