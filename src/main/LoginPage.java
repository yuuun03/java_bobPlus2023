package main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame{
	//기초 설정
	JFrame loginFrame = new JFrame(); //프레임 생성
	JPanel memberPanel = new JPanel();
	JPanel noMemberPanel = new JPanel();
	
	// !!! 실행되는 부분 !!!
	public static void main(String[] args) {
		LoginPage login = new LoginPage(); //스윙 프레임 생성
	}
	
	// 마이페이지 프레임 구현 내용
	public LoginPage() {
		//화면 기본 설정 - Start
		setTitle("밥심+"); //제목 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		
		//멤버 로그인
		memberPanel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치		
		
		memberPanel.setSize(500,290); //패널 사이즈 설정
		memberPanel.setLocation(100, 303);
		memberPanel.setBorder(new LineBorder(Color.black, 2));
		
		//---외곽 요소들
		JLabel mTitle = new JLabel("회원 로그인"); //타이틀
		mTitle.setBounds(420, 200, 300, 45);
		add(mTitle);
		
		//---아이디
		JLabel idLabel = new JLabel("아이디 : ");
		JTextField id = new JTextField(10);
		idLabel.setBounds(50, 20, 100, 100);
		id.setBounds(150, 50, 300, 45);
		
		//---패스워드
		JLabel pwLabel = new JLabel("비밀번호 : ");
		JPasswordField pw = new JPasswordField(10);
		pwLabel.setBounds(50, 80, 100, 100);
		pw.setBounds(150, 110, 300, 45);
		
		//---로그인 버튼
		JButton lgB = new JButton("로그인");
		lgB.setBounds(50, 180, 400, 65);
		
		//---추가
		memberPanel.add(idLabel); memberPanel.add(id);
		memberPanel.add(pwLabel); memberPanel.add(pw);
		memberPanel.add(lgB);
		
		
		/* 패널 라인이랑 그런거 자꾸 오류나는데...확인좀...
		//비회원 주문조회
		noMemberPanel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
		
		noMemberPanel.setBounds(800, 500, 500, 290);
		noMemberPanel.setBorder(new LineBorder(Color.black, 2));
				
		//---외곽 요소들
		JLabel nmTitle = new JLabel("비회원 주문 조회"); //타이틀
		nmTitle.setBounds(820, 200, 300, 45);
		add(nmTitle);
				
		//---주문번호
		JLabel odNumLabel = new JLabel("주문번호 : ");
		JTextField odNum = new JTextField(10);
		odNumLabel.setBounds(50, 20, 100, 100);
		odNum.setBounds(150, 50, 300, 45);
				
		//---패스워드
		JLabel phoneNumLabel = new JLabel("전화번호 : ");
		JTextField phoneNum = new JTextField(11);
		phoneNumLabel.setBounds(50, 80, 100, 100);
		phoneNum.setBounds(150, 110, 300, 45);
				
		//---로그인 버튼
		JButton nmB = new JButton("비회원 주문 조회");
		nmB.setBounds(50, 180, 400, 65);
			
		//---추가
		noMemberPanel.add(odNumLabel); noMemberPanel.add(odNum);
		noMemberPanel.add(phoneNumLabel); noMemberPanel.add(phoneNum);
		noMemberPanel.add(nmB);*/
		
		//로고, 검색창, 위쪽 레이블, 로그인, 장바구니 등 기본 패널 추가
		CommonPanel df = new CommonPanel(); //패널 객체 생성
		add(df.commonPanel); //패널 추가
		
		//---액션 설정
		df.login.addActionListener(new MainActionListner());
		df.my.addActionListener(new MainActionListner()); //마이페이지
		df.cart.addActionListener(new MainActionListner());
				
		df.newHotGoods.addActionListener(new MainActionListner());
		df.weekTop10Goods.addActionListener(new MainActionListner());
		df.checkAttendance.addActionListener(new MainActionListner());
		df.couponPoint.addActionListener(new MainActionListner());
		df.communityU.addActionListener(new MainActionListner());
		df.newMonthGoods.addActionListener(new MainActionListner());
				
		
		//화면 기본 설정 - End
		setSize(1920, 1080); //윈도우 사이즈 1920, 1080 고정.
		add(memberPanel); //프레임에 패널 추가
		add(noMemberPanel); //프레임에 패널 추가
		setVisible(true); // 프레임 출력
	}
	
	//이벤트 처리 클래스들
	class MainActionListner implements ActionListener{
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
					
			case "지금 뜨는 상품" : case "금주의 TOP 10" :
				/*인기상품, 지금뜨는 상품, 금주의 TOP10 클릭시
				지금뜨는 상품과 금주의 TOP10은 인기 상품에 속해있는 원소긴 하나
				이는 추후 구현 예정*/ 			
				break;
				
			case "출석 체크": 
				break;
				
			case "쿠폰/포인트": 
				break;
					
			case "커뮤니티": 
				break;
				
			case "이달의 신상품": 
				break;
				
			default : //장바구니 클릭 시 
				break;
			}
		}
	}
}