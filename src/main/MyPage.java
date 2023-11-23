package main;

import javax.swing.*;

import main.MainFrame.MainActionListener;
import user.SingUp;

import java.awt.*;
import java.awt.event.*;

public class MyPage extends JFrame{
	//기초 설정
		JPanel mypagePanel = new JPanel();
	// !!! 실행되는 부분 !!!
		public static void main(String[] args) {
			MyPage myPage = new MyPage(); //스윙 프레임 생성
		}
		
		// 마이페이지 프레임 구현 내용
		public MyPage() {
			//화면 기본 설정 - Start
			setTitle("밥심+"); //제목 설정
			
			Toolkit kit = Toolkit.getDefaultToolkit();
			Image img = kit.getImage("src/graphics/images/iconOnly.png");
			setIconImage(img);
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//화면 버튼 설정
			Container mypagePane = getContentPane(); //컨텐트 팬 얻어오기. 여기서 awt 사용.
			mypagePane.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
			
			//로고, 검색창, 위쪽 레이블, 로그인, 장바구니 등 기본 패널 추가
			CommonPanel df = new CommonPanel(); //패널 객체 생성
			add(df.commonPanel); //패널 추가
			
			//---액션 설정
			df.login.addActionListener(new MainActionListener());
			df.my.addActionListener(new MainActionListener()); //마이페이지
			df.cart.addActionListener(new MainActionListener());
			
			df.newHotGoods.addActionListener(new MainActionListener());
			df.weekTop10Goods.addActionListener(new MainActionListener());
			df.checkAttendance.addActionListener(new MainActionListener());
			df.couponPoint.addActionListener(new MainActionListener());
			df.communityU.addActionListener(new MainActionListener());
			df.newMonthGoods.addActionListener(new MainActionListener());
			
			JLabel mypagelogo = new JLabel("마이페이지");
			mypagelogo.setLocation(260, 50);
			mypagelogo.setSize(100,50);
			mypagePane.add(mypagelogo);
			
			//JList
			String [] leftSide = {"주문목록/배송조회", "취소/반품/교환/환불 내역", "영수증 조회/출력", " ", " ",
					"찜한 상품", "나의 장바구니", "배송지 관리", " ", " ", "문의하기", "문의내역 확인", " ", " ", 
					"회원정보 확인/수정", "알러지/조리기구 정보 수정"};
			JList<String> strList = new JList<String> (leftSide); // 왼쪽 사이드 리스트 생성
			strList.setLocation(30, 400);
			strList.setSize(250,350);
			mypagePane.add(strList);
			
			
			//화면 기본 설정 - End
			setSize(1920, 1080); //윈도우 사이즈 1920, 1080 고정.
			setVisible(true); // 프레임 출력
			
			
		}
		//이벤트 처리 클래스들
		class MainActionListener implements ActionListener{
			//Action : 버튼 클릭 
			public void actionPerformed(ActionEvent e) {
				JButton bRefer = (JButton)e.getSource(); //사용자가 클릭한 버튼 알아내기
				
				//버튼 종류마다 이벤트 다르게 지정
				switch(bRefer.getText()) {
				
				case "로그인": 
					new LoginPage(); //로그인 페이지 전환
					setVisible(false); //기존 페이지 안보이게 변경
					break;
				
				case "MY": 
					new MyPage();
					setVisible(false);
					break;
					
				case "인기 상품": case "지금 뜨는 상품" : case "금주의 TOP 10" :
					/*인기상품, 지금뜨는 상품, 금주의 TOP10 클릭시
					지금뜨는 상품과 금주의 TOP10은 인기 상품에 속해있는 원소긴 하나
					이는 추후 구현 예정*/ 
					JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
					break;
				
				case "출석 체크": 
					JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
					break;
				
				case "쿠폰/포인트": 
					JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
					break;
					
				case "커뮤니티": 
					JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
					break;
				
				case "이달의 신상품": 
					JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
					break;
				
				case "오늘 뭐 먹지?": 
					JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
					break;

				case "지금 할인 중": 
					JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
					break;

				case "인기 급상승": 
					JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
					break;
				
				default : //장바구니 클릭 시 
					JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
					break;
				}
			}
		}
}