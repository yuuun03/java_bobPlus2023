package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyPage extends JFrame{
	// !!! 실행되는 부분 !!!
		public static void main(String[] args) {
			MyPage myPage = new MyPage(); //스윙 프레임 생성
		}
		
		// 마이페이지 프레임 구현 내용
		public MyPage() {
			//---아래쪽 레이블 : 설정 중 . . .
			//JButton popularGoods = new
			//화면 기본 설정 - End
			setSize(1920, 1080); //윈도우 사이즈 1920, 1080 고정.
			setVisible(true); // 프레임 출력
			
			//화면 기본 설정 - Start
			setTitle("밥심+"); //제목 설정
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
}