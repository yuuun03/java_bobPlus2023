package main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import user.SignUp;
import user.UserInfoDetail;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class MyPage extends JFrame{
	JPanel mainPanel = new JPanel();
		
	// 마이페이지 프레임 구현 내용
	public MyPage() {}
	public MyPage(UserInfoDetail myUser) {
		JPanel mainPanel = new JPanel();
		//화면 기본 설정 - Start
		setTitle("밥심+"); //제목 설정
			
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("src/graphics/images/iconOnly.png");
		setIconImage(img);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		//화면 버튼 설정
		setLayout(null);
		mainPanel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
		//로고, 검색창, 위쪽 레이블, 로그인, 장바구니 등 기본 패널 추가
		CommonPanel df = new CommonPanel(); //패널 객체 생성
		add(df.commonPanel); //패널 추가
			
		//---액션 설정
		df.login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginPage(myUser); //로그인 페이지 전환
				setVisible(false); //기존 페이지 안보이게 변경
			}});
		df.my.addActionListener(new MyPageActionListener()); //마이페이지
		df.cart.addActionListener(new MyPageActionListener());
		df.newHotGoods.addActionListener(new MyPageActionListener());
		df.weekTop10Goods.addActionListener(new MyPageActionListener());
		df.checkAttendance.addActionListener(new MyPageActionListener());
		df.couponPoint.addActionListener(new MyPageActionListener());
		df.communityU.addActionListener(new MyPageActionListener());
		df.newMonthGoods.addActionListener(new MyPageActionListener());
			
		//JList
		String [] leftSide = {"주문목록/배송조회", "취소/반품/교환/환불 내역", "영수증 조회/출력", " ", " ",
				"찜한 상품", "나의 장바구니", "배송지 관리", " ", " ", "문의하기", "문의내역 확인", " ", " ", 
				"회원정보 확인/수정", "알러지/조리기구 정보 수정"};
		JList<String> strList = new JList<String> (leftSide); // 왼쪽 사이드 리스트 생성
		strList.setLocation(30, 400);
		strList.setSize(250,350);
		mainPanel.add(strList);
		
		// 사용자 정보 출력
		JButton userName = new JButton("이찬비님 \n 등급: 밥알");
		userName.setBounds(0, 240, 400, 160);
		mainPanel.add(userName);
		
		// 배송 정보 출력
		JButton delivery = new JButton("배송중 \n 2개");
		delivery.setBounds(400, 240, 300, 160);
		mainPanel.add(delivery);
		
		// 쿠폰 및 포인트 정보 출력
		JButton userCoupon = new JButton("보유 쿠폰: 3장 \n 보유 포인트: 790P");
		userCoupon.setBounds(700, 240, 500, 160);
		mainPanel.add(userCoupon);
		
		// 내가 작성한 리뷰 정보 출력
		JButton userReview = new JButton("내가 작성한 리뷰 \n 35개");
		userReview.setBounds(1200, 240, 330, 160);
		mainPanel.add(userReview);
		
		//알러지 정보 출력 x: 280+ 알파, y: 400
		Image allergy = new ImageIcon("src/graphics/images/allegy.png").getImage();
		allergy = allergy.getScaledInstance(300, 400, Image.SCALE_SMOOTH);
		JCheckBox infoAllergy[] = new JCheckBox[22];
		String alName[] = {"가금류","게","고등어","굴","닭고기","대두","돼지고기","땅콩","메밀","밀","복숭아","새우","쇠고기","아황산포함","오징어","우유","잣","전복","조개류","토마토","호두","홍합"};
		
		int w = 0; int v = 0; //세부 위치 조정 위한 변수
		for(int i = 0; i < alName.length; i++) { //알러지 체크 박스 생성
			infoAllergy[i] = (new JCheckBox(alName[i]));
			mainPanel.add(infoAllergy[i]);
			
			//위치 조정
			infoAllergy[i].setSize(40 + 20 * alName[i].length(), 50);
			infoAllergy[i].setLocation(460 + 200 * w, 400 + 30 * v);
			
			w++;
			if (w == 5) {w = 0; v++;}
			
			//이벤트 처리
			//infoAllergy[i].addItemListener(new signUpListner());
		}
		
		mainPanel.setBounds(0,0, 1920,1080);
		//화면 기본 설정 - End
		setSize(1920, 1080); //윈도우 사이즈 1920, 1080 고정.
		add(mainPanel);
		setVisible(true); // 프레임 출력
			
			
	}
	//이벤트 처리 클래스들
	class MyPageActionListener implements ActionListener{
		//Action : 버튼 클릭 
		public void actionPerformed(ActionEvent e) {
			JButton bRefer = (JButton)e.getSource(); //사용자가 클릭한 버튼 알아내기
					
			//버튼 종류마다 이벤트 다르게 지정
			switch(bRefer.getText()) {
						
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
				new Cart();
				dispose();
				break;
			}
		}
	}
}