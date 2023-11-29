package main;

import javax.swing.*;
import javax.swing.event.*;

import admin.Product;

import javax.swing.border.LineBorder;
import user.SignUp;
import user.UserInfoDetail;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Vector;

public class MyPage extends JFrame{
	JPanel mainPanel = new JPanel();
	JPanel allergyPanel = new JPanel();
	JPanel userInfoPanel = new JPanel();
	JPanel line1Panel = new JPanel();
	JPanel line2Panel = new JPanel();
	JPanel line3Panel = new JPanel();
	JPanel newOrderPanel = new JPanel();
	
	// 마이페이지 프레임 구현 내용
	public MyPage() {}
	public MyPage(UserInfoDetail myUser, Vector<Product> pList) {
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
		allergyPanel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
		userInfoPanel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
		line1Panel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
		line2Panel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
		line3Panel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
		newOrderPanel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
		
		//로고, 검색창, 위쪽 레이블, 로그인, 장바구니 등 기본 패널 추가
		CommonPanel df = new CommonPanel(); //패널 객체 생성
		add(df.commonPanel); //패널 추가
		
		//---액션 설정
				df.mainIL.addMouseListener(new MouseAdapter() {
					public void mouseReleased(MouseEvent e) {
						new MainFrame(myUser, pList);
					}
				});
				
				df.login.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new LoginPage(myUser, pList); //로그인 페이지 전환
						dispose(); //기존 페이지 안보이게 변경
					}});
				
				df.my.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new MyPage(myUser, pList); //마이 페이지 전환
						dispose(); //기존 페이지 안보이게 변경
					}});
					
				df.cart.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new Cart(myUser, pList); //장바구니 페이지 전환
						dispose(); //기존 페이지 안보이게 변경
					}});
				
		df.newHotGoods.addActionListener(new MyPageActionListener());
		df.weekTop10Goods.addActionListener(new MyPageActionListener());
		df.checkAttendance.addActionListener(new MyPageActionListener());
		df.couponPoint.addActionListener(new MyPageActionListener());
		df.communityU.addActionListener(new MyPageActionListener());
		df.newMonthGoods.addActionListener(new MyPageActionListener());
		
		// 폰트
		Font buttonFont = new Font("G마켓 산스 TTF BOLD", Font.CENTER_BASELINE, 30);
		Font buttonPlain = new Font("G마켓 산스 TTF Medium", Font.PLAIN, 30);
		Font basic = new Font("G마켓 산스 TTF Medium", Font.PLAIN, 17);
		Font miniBasic = new Font("G마켓 산스 TTF Medium", Font.PLAIN, 15);
		
		//JList
		String [] leftSide = {" ", "    주문목록/배송조회", "    취소/반품/교환/환불 내역", "    영수증 조회/출력", " ", " ",
				"    찜한 상품", "    나의 장바구니", "    배송지 관리", " ", " ", "    문의하기", "    문의내역 확인", " ", " ", 
				"    회원정보 확인/수정", "    알러지/조리기구 정보 수정"};
		JList<String> strList = new JList<String> (leftSide); // 왼쪽 사이드 리스트 생성
		strList.setBounds(30, 400, 250, 390);
		strList.setBackground(new Color(151, 192, 48));
		strList.setFont(basic);
		strList.setForeground(Color.WHITE);
		strList.setSelectionBackground(new Color(151, 192, 48));
		strList.setSelectionForeground(Color.WHITE);
		mainPanel.add(strList);
		
		// 액션
		strList.addListSelectionListener(new MyPageActionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int selectedIndex = strList.getSelectedIndex();
				String selectedItem = (String)strList.getModel().getElementAt(selectedIndex);
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
			}
		});
		
		
		
		// 사용자 이름 및 등급 출력
		String[] rankList = {"전설밥알", "영웅밥알", "고급밥알", "중급밥알", "초급밥알", "입문밥알"};
		String name = "O O O";
		int rank = 5; 
		
		name = myUser.getName();
		rank = myUser.getUserRank();
		if (name.equals("")) {
			name = "O O O";
		}
		
		JButton userName = new JButton(name + "           님");
		JButton userRank = new JButton("등급:   " + rankList[rank]);
		userName.setBounds(0, 10, 335, 80);
		userRank.setBounds(0, 70, 335, 80);
		
		userName.setFont(buttonFont);
		userRank.setFont(buttonFont);
		
		userName.setBorderPainted(false); userRank.setBorderPainted(false); // 외각선 제거
		userName.setContentAreaFilled(false); userRank.setContentAreaFilled(false); // 배경색 제거
		
		userInfoPanel.add(userName);
		userInfoPanel.add(userRank);
		
		// 배송 상태 및 배송 개수 정보 출력
		Vector<String> buyList = new Vector<String>();
		//buyList = myUser.getBuytList(); 추후 구현
		int buyListNum;
		
		if (buyList.isEmpty()) { buyListNum = 0; }
		else {buyListNum = buyList.size();}
		
		JButton delivery = new JButton("배 송 중");
		JButton deliveryNum = new JButton("     " + buyListNum+ " 개");
		
		delivery.setBounds(375, 10, 250, 80);
		deliveryNum.setBounds(375, 70, 250, 80);
		
		delivery.setFont(buttonFont);
		deliveryNum.setFont(buttonFont);
		
		delivery.setBorderPainted(false); deliveryNum.setBorderPainted(false); // 외각선 제거
		delivery.setContentAreaFilled(false); deliveryNum.setContentAreaFilled(false); // 배경색 제거
		
		userInfoPanel.add(delivery);
		userInfoPanel.add(deliveryNum);
		
		// 쿠폰 및 포인트 정보 출력
		// 사용자 정보로 쿠폰 및 포인트를  받아오는 것은 추후 구현
		JButton userCoupon = new JButton("보유 쿠폰:                 0 장");
		JButton userPoint = new JButton("보유 포인트:              0 P");
		
		userCoupon.setBounds(645, 10, 493, 79);
		userPoint.setBounds(645, 70, 493, 79);
		
		userCoupon.setFont(buttonFont);
		userPoint.setFont(buttonFont);
		
		userCoupon.setBorderPainted(false); userPoint.setBorderPainted(false); // 외각선 제거
		userCoupon.setContentAreaFilled(false); userPoint.setContentAreaFilled(false); // 배경색 제거
		
		userInfoPanel.add(userCoupon);
		userInfoPanel.add(userPoint);
		
		// 내가 작성한 리뷰 및 개수 출력
		// 사용자 정보로 쿠폰 및 포인트를  받아오는 것은 추후 구현
		JButton userReview = new JButton("내가 작성한 리뷰");
		JButton userReviewNum = new JButton("                  0 개");
		
		userReview.setBounds(1175, 10, 330, 80);
		userReviewNum.setBounds(1175, 70, 330, 80);
		
		userReview.setFont(buttonFont);
		userReviewNum.setFont(buttonFont);
		
		userReview.setBorderPainted(false); userReviewNum.setBorderPainted(false); // 외각선 제거
		userReview.setContentAreaFilled(false); userReviewNum.setContentAreaFilled(false); // 배경색 제거
		
		userInfoPanel.add(userReview);
		userInfoPanel.add(userReviewNum);
		
		//알러지 정보 출력 
		Image allergy = new ImageIcon("src/graphics/images/allegy.png").getImage();
		allergy = allergy.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon iconAllergy = new ImageIcon(allergy);
		JLabel mainAll = new JLabel(iconAllergy);
		mainAll.setSize(100,100);
		mainAll.setLocation(20, 20);
		allergyPanel.add(mainAll);
		JCheckBox infoAllergy[] = new JCheckBox[22];
		String alName[] = {"가금류","게","고등어","굴","닭고기","대두","돼지고기","땅콩","메밀","밀","복숭아","새우","쇠고기","아황산포함","오징어","우유","잣","전복","조개류","토마토","호두","홍합"};
		
		Vector<String> userAllergy = new Vector<String>(); //사용자가 선택한 알러지
		
		int w = 0; int v = 0; //세부 위치 조정 위한 변수
		for(int i = 0; i < alName.length; i++) { //알러지 체크 박스 생성
			infoAllergy[i] = (new JCheckBox(alName[i]));
			allergyPanel.add(infoAllergy[i]);
			
			//위치 조정
			infoAllergy[i].setSize(40 + 20 * alName[i].length(), 40);
			infoAllergy[i].setLocation( 140 + 147 * w, 10+ 35 * v);
			
			// 배경색 제거
			infoAllergy[i].setContentAreaFilled(false);
			infoAllergy[i].setFont(basic);
			
			w++;
			if (w == 7) {w = 0; v++;}
			
			//이벤트 처리
			infoAllergy[i].addItemListener(new MyPageActionListener() {
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED) {
						JCheckBox now = (JCheckBox)(e.getSource());
						userAllergy.add(now.getText());
					}
					else if(e.getStateChange() == ItemEvent.DESELECTED) {
						JCheckBox now = (JCheckBox)(e.getSource());
						userAllergy.remove(now.getText());
					}
					myUser.setAllergy(userAllergy);
				}
			});
			
		}
		
		// 최근 주문 내역
		JLabel newOrder = new JLabel("최근 주문내역");
		JLabel newOrderNum = new JLabel("최근 0건");
		JLabel newOrderDay = new JLabel("날짜");
		JLabel newOrderInfo = new JLabel("상품정보");
		JLabel newOrderDeli = new JLabel("배송 상태");
		JLabel newOrderReview = new JLabel("리뷰");
		
		
		newOrder.setBounds(300,580, 300, 70);
		newOrderNum.setBounds(500,584, 200, 70);
		newOrderDay.setBounds(385,620, 200, 50);
		newOrderInfo.setBounds(675,620, 200, 50);
		newOrderDeli.setBounds(995,620, 200, 50);
		newOrderReview.setBounds(1300,620, 200, 50);
		
		newOrder.setFont(buttonPlain);
		newOrderNum.setFont(basic);
		newOrderDay.setFont(miniBasic);
		newOrderInfo.setFont(miniBasic);
		newOrderDeli.setFont(miniBasic);
		newOrderReview.setFont(miniBasic);
		
		mainPanel.add(newOrder);
		mainPanel.add(newOrderNum);
		mainPanel.add(newOrderDay);
		mainPanel.add(newOrderInfo);
		mainPanel.add(newOrderDeli);
		mainPanel.add(newOrderReview);
		
		
		//화면 기본 설정 - End
		setSize(1920, 1080); //윈도우 사이즈 1920, 1080 고정.
		
		mainPanel.setBounds(0,0, 1920,1080);
		allergyPanel.setBounds(300 ,420, 1230, 160);
		userInfoPanel.setBounds(0 ,240, 1920, 160);
		newOrderPanel.setBounds(300 ,630, 1250, 30);
		line1Panel.setBounds(360 ,240, 2, 160);
		line2Panel.setBounds(640 ,240, 2, 160);
		line3Panel.setBounds(1140 ,240, 2, 160);
		
		mainPanel.setBackground(Color.white);
		allergyPanel.setBackground(new Color(200, 228, 137));
		userInfoPanel.setBackground(new Color(200, 228, 137));
		newOrderPanel.setBackground(new Color(200, 228, 137));
		line1Panel.setBackground(new Color(151, 192, 48));
		line2Panel.setBackground(new Color(151, 192, 48));
		line3Panel.setBackground(new Color(151, 192, 48));
		
		mainPanel.add(line1Panel);
		mainPanel.add(line2Panel);
		mainPanel.add(line3Panel);
		mainPanel.add(newOrderPanel);
		mainPanel.add(userInfoPanel);
		mainPanel.add(allergyPanel);
		add(mainPanel);
		
		setVisible(true); // 프레임 출력
			
			
	}
	//이벤트 처리 클래스들
	class MyPageActionListener implements ActionListener, ItemListener, ListSelectionListener{
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
		// 체크박스 선택 시
		public void itemStateChanged(ItemEvent e) {}
		public void valueChanged(ListSelectionEvent e) {}
	}
}