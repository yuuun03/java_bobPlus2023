package main;

import javax.swing.*;

import main.MainFrame.MainActionListner;
import user.SingUp;

import java.awt.*;
import java.awt.event.*;

public class MyPage extends JFrame{
	//기초 설정
		JPanel mypagePanel = new JPanel();
		JPanel sidePanel = new JPanel();
	// !!! 실행되는 부분 !!!
		public static void main(String[] args) {
			MyPage myPage = new MyPage(); //스윙 프레임 생성
		}
		
		// 마이페이지 프레임 구현 내용
		public MyPage() {
			//화면 기본 설정 - Start
			setTitle("밥심+"); //제목 설정
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//화면 버튼 설정
			Container mypagePane = getContentPane(); //컨텐트 팬 얻어오기. 여기서 awt 사용.
			mypagePane.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
			sidePanel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
			
			
			// 로그인 전이면 로그인 화면으로 넘어가서 확인 후 다시 마이페이지로 이동
			// 로그인 후
			JLabel mypagelogo = new JLabel("마이페이지");
			mypagelogo.setLocation(200, 10);
			mypagelogo.setSize(200,100);
			mypagePane.add(mypagelogo);
			
			//JList
			String [] leftSide = {"주문목록/배송조회", "취소/반품/교환/환불 내역", "영수증 조회/출력\n", 
					"찜한 상품", "나의 장바구니", "배송지 관리\n", "문의하기", "문의내역 확인\n", 
					"회원정보 확인/수정", "알러지/조리기구 정보 수정"};
			JList<String> strList = new JList<String> (leftSide); // 왼쪽 사이드 리스트 생성
			strList.setLocation(100, 200);
			strList.setSize(200,100);
			sidePanel.add(strList);
			
			//화면 기본 설정 - End
			setSize(1920, 1080); //윈도우 사이즈 1920, 1080 고정.
			setVisible(true); // 프레임 출력
			
			
		}
}