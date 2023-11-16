package admin;

import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Coupon{

  static int couponNum;
  static String couponName;
  static double couponDisRate;
  static double couponDue;
  static int couponDisPrice;
  

  
  public int getBirth() {
	  return birth;
  }
  
  public static void main (String[] args){
	  
	 
	  
	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	  
	  
	  LocalDate date = LocalDate.now();
	  LocalDate date2 = LocalDate.of(birth); 
  }
}
