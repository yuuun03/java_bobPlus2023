package main;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
	public MainFrame() { //메인페이지 프레임
		//화면 기본 설정 - Start
		setTitle("밥심+"); //제목 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//화면 버튼 설정
		Container mainPane = getContentPane(); //컨텐트 팬 얻어오기. 여기서 awt 사용.
		mainPane.setLayout(new FlowLayout()); //플로우로 컴포넌트 배치관리
		
		mainPane.add(new JButton("지금 뜨는 상품"));
		mainPane.add(new JButton("금주의 TOP 10"));
		mainPane.add(new JButton("출석 체크"));
		mainPane.add(new JButton("쿠폰/포인트"));
		mainPane.add(new JButton("커뮤니티"));
		mainPane.add(new JButton("이달의 신상품"));
		
		
		//화면 기본 설정 - End
		setSize(1920, 1080); //윈도우 사이즈 1920, 1080 고정.
		setVisible(true); // 프레임 출력
	}
	
	public static void main(String[] args) {
		MainFrame mainPage = new MainFrame(); //스윙 프레임 생성

	}
}