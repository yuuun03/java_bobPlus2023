package main;

import javax.swing.*;

import main.MainFrame.MainActionListner;

import java.awt.*;
import java.awt.event.*;

public class loginPage extends JFrame{
	//기초 설정
		JFrame loginFrame = new JFrame(); //프레임 생성
		JPanel loginPanel = new JPanel();
	
	// !!! 실행되는 부분 !!!
	public static void main(String[] args) {
		loginPage login = new loginPage(); //스윙 프레임 생성
	}
	
	// 마이페이지 프레임 구현 내용
	public loginPage() {
		//화면 기본 설정 - Start
		setTitle("밥심+"); //제목 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
			
		//화면 버튼 설정
		//Container mainPane = getContentPane(); //컨텐트 팬 얻어오기. 여기서 awt 사용.
		loginPanel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
		
		//화면 기본 설정 - End
		setSize(1920, 1080); //윈도우 사이즈 1920, 1080 고정.
		add(loginPanel); //프레임에 패널 추가
		setVisible(true); // 프레임 출력

	}
}