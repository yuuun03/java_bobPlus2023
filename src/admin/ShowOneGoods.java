package admin;

import java.util.Vector;

public interface ShowOneGoods{
	//추상 클래스
	void printReview(); //얘도 객체 받아올 수도 있음.
	//. . . 일단 알러지를 받아오는 걸로 했는데 객체를 받아올 수도 있음.
	void alarmAllergy(Vector<String> allergy);
	void printProduct(); //얘도 객체 받아올 수 있음.
}
