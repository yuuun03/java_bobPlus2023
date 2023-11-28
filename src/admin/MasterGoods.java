package admin;

import java.util.Vector;

public class MasterGoods {
	//멤버 변수
	// > pList : 상품 저장 배열 
	private Vector<Product> pList = new Vector<Product>();//스택 사용
	
	//생성자
	public MasterGoods() {}
	
	//상품 추가(생성자 아님 주의!!!) //test상품 추가를 위해 public으로 변경
	public void productAdd(String name, String image, double productStar,
			int serving, int productCount, int price,
			double productDisRate, Vector<String> cookingUtensils,
			Vector<String> containAllergy) {
		
		//새 상품 객체 생성
		Product p = new Product(name, image, productStar,
				serving, productCount, price, productDisRate,
				cookingUtensils,containAllergy);
		
		//기존 상품 리스트에 추가
		pList.add(p);
	}
	
	public Vector<Product> getPList(){return this.pList;}
	
	public Product getProductAtIndex(int index) { //특정 인덱스의 Product 반환하는 코드 > 양희정 작성
		if(index>=0 && index < pList.size()) {
			return pList.get(index);
		}
		return null; //유효하지 않은 인덱스인 경우
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
	
	//상품 리스트 전체 출력 //확인을 위해 public으로 변경
	public void checkProduct() {
		if (pList.isEmpty()) {System.out.println("등록되어 있는 상품이 없습니다.");} 
		else {
			for(Product i : pList) { /* 메모장 형식 */ System.out.println(i.toString());}
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
				i.setProductCount(productCount);
				return;
			};
		}
		System.out.println("찾으시는 상품이 없습니다.");
	}
	
	public String productPopular() {
		return "추후 구현 예정";
	}
	
	//scrollPage - 메인 UI/UX에서 실행 예정
	//public void scrollPage() {}
}
