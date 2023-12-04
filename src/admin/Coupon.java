// 컴퓨터공학부 2022136102 임혜원

package admin;


import java.util.Random;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class Coupon{
	
	LocalDate now = LocalDate.now();
	
	// 변수 선언 
	static int couponNum;
	static double couponDisRate = 0.8;
	static double couponDue;
	static double couponDisPrice;
	static int birth, price;
	static int monthValue = getMonthValue();

	// 접근으로 공유 변수 받아오기
	public int getBirth() {
		return birth; // 생일 달 변수 받아오기
  	}
  
	public int getPrice() {
		return price;
	}
	
	// 현재 달 받아오기
	private static int getMonthValue() {
		return monthValue;
	}

	
	// 쿠폰 번호 생성 
	public static int couponNumMake(){
		Random random = new Random();

        	// 8자리 무작위 숫자 생성 (10000000에서 99999999 사이의 숫자)
        	int minRange = 10000000;
        	int maxRange = 99999999;
        	int couponNum = random.nextInt(maxRange - minRange + 1) + minRange;

        	return couponNum;
	}

	// 쿠폰 발행 메시지 출력
	public static void couponPrint(String message, String title) {

		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);

	}

	// 쿠폰 사용
	public static void couponUse(int couponNum) {
		
		// 가격 할인 (price의 경우 다른 클래스에서 참조)
		couponDisPrice = price * couponDisRate;
		
	}

	// 쿠폰 삭제 (보이지 않음) 
	public static void couponDelete(int couponNum) {
		couponNum = 0; // 쿠폰 번호가 0이 되면 쿠폰 프린트 안 함 
	} 

 
	
	public static void main (String[] args){
		int monthValue = getMonthValue();
		if (monthValue == birth) {
			couponNumMake();
	  		couponPrint("생일 쿠폰이 발급되었습니다!", "쿠폰 알림");
		}
		if (couponNum != 0) {
			
			// 결제 창에서 쿠폰이 존재한다는 사실 보여주기 
			// 결제 창 x
			
			couponUse(couponNum);
			couponDelete(couponNum);
		}
	}
}
