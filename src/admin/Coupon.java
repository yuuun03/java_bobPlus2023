package admin;

import java.util.Date;
import java.util.Random;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

public class Coupon{

	// 변수 선언 
	static int couponNum;
	static double couponDisRate;
	static double couponDue;
	static int couponDisPrice;

	// 쿠폰 번호 생성 
	public static int couponNumMake(){
		Random random = new Random();

        	// 8자리 무작위 숫자 생성 (10000000에서 99999999 사이의 숫자)
        	int minRange = 10000000;
        	int maxRange = 99999999;
        	int couponNum = random.nextInt(maxRange - minRange + 1) + minRange;

        	return randomNumber;
		couponDisRate = 0.8;
	}

	public static void couponPrint(String message, String title) {

		JoptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);

	}

	public static void couponUse(int) {
		
		// 가격 할인해주는 코드 써야 함 
		couponDisPrice = price * couponDisRate;
		
	}

	public static void couponDelete(int) {
		couponNum = 0; // 쿠폰 번호가 0이 되면 쿠폰 프린트 안 함 
	} 

  
	public int getBirth() {
		return birth;
  	}
  
	public static void main (String[] args){
		int monthValue = now.getMonthValue();
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
