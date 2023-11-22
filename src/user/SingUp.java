package user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SingUp extends JFrame{
	//기초 설정
	JPanel memberPanel = new JPanel();
	JPanel noMemberPanel = new JPanel();
		
	// !!! 실행되는 부분 !!!
	public static void main(String[] args) {
		SingUp account = new SingUp(); //스윙 프레임 생성
	}
		
	// 마이페이지 프레임 구현 내용
	public SingUp() {
		//화면 기본 설정 - Start
		setTitle("밥심+"); //제목 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
			
		this.setLayout(null); //프레임 배치관리자 없음 : 개발자 자유 배치
		memberPanel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치		
		noMemberPanel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치	
			
		//화면 기본 설정 - End
		setSize(1920, 1080); //윈도우 사이즈 1920, 1080 고정.
		add(memberPanel); //프레임에 패널 추가
		add(noMemberPanel); //프레임에 패널 추가
		setVisible(true); // 프레임 출력
	}
}
