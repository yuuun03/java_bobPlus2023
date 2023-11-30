package main;

import javax.swing.*;

import admin.Product;
import goods.SearchResult;
import user.UserInfoDetail;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class Cart extends JFrame {
	
	JPanel mainPanel = new JPanel();
	JPanel payPanel = new JPanel();
	
	public Cart() {}
	public Cart(UserInfoDetail myUser, Vector<Product> pList) {
		//화면 기본 설정 - Start
		setTitle("밥심+"); //제목 설정
		
		//---아이콘 설정
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("src/graphics/images/iconOnly.png");
		setIconImage(img);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		setLayout(null); //프레임 배치관리자 없음 : 개발자 자유 배치
		mainPanel.setLayout(null); //프레임 배치관리자 없음 : 개발자 자유 배치
		payPanel.setLayout(null); //프레임 배치관리자 없음 : 개발자 자유 배치
		
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
		
		df.search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pName = e.getActionCommand();
				new SearchResult(myUser, pList, pName);
				dispose();
			}
		});
						
		df.newHotGoods.addActionListener(new MainActionListener());
		df.weekTop10Goods.addActionListener(new MainActionListener());
		df.checkAttendance.addActionListener(new MainActionListener());
		df.couponPoint.addActionListener(new MainActionListener());
		df.communityU.addActionListener(new MainActionListener());
		df.newMonthGoods.addActionListener(new MainActionListener());
		
		// 폰트
		Font buttonFont = new Font("G마켓 산스 TTF BOLD", Font.CENTER_BASELINE, 30);
		Font buttonPlain = new Font("G마켓 산스 TTF Medium", Font.PLAIN, 30);
		Font labelFont = new Font("G마켓 산스 TTF Medium", Font.PLAIN, 20);
		Font basic = new Font("G마켓 산스 TTF Medium", Font.PLAIN, 17);
		Font miniBasic = new Font("G마켓 산스 TTF Medium", Font.PLAIN, 15);
		
		// 장바구니 구역 표시
		JLabel myCart = new JLabel(" 나의 장바구니");
		myCart.setBounds(59 ,220, 1440, 50);
		myCart.setOpaque(true);
		myCart.setBackground(new Color(200, 228, 137));
		myCart.setFont(buttonPlain);
		mainPanel.add(myCart);
		
		JLabel myCartLine1 = new JLabel();
		myCartLine1.setBounds(60 ,310, 1125, 2);
		myCartLine1.setOpaque(true);
		myCartLine1.setBackground(Color.black);
		mainPanel.add(myCartLine1);
		
		// 전체 상품 선택 체크리스트
		JCheckBox allProductCheck = new JCheckBox("전체 상품 선택");
		allProductCheck.setBounds(59 ,240, 300, 100);
		allProductCheck.setFont(basic);
		allProductCheck.setContentAreaFilled(false); // 배경색 제거
		mainPanel.add(allProductCheck);
		
		// 품정상품 삭제 및 선택 상품 삭제 버튼
		JLabel soldoutProduct = new JLabel("품절상품 삭제 |");
		JLabel checkProduct = new JLabel("선택상품 삭제");
		
		soldoutProduct.setBounds(969, 275, 120, 30);
		checkProduct.setBounds(1084, 275, 120, 30);
		
		soldoutProduct.setFont(basic);
		checkProduct.setFont(basic);
		
		mainPanel.add(soldoutProduct);
		mainPanel.add(checkProduct);
		
		// 결제 금액 확인 패널
		// 주문 금액
		JLabel orderPay = new JLabel("주문 금액");
		orderPay.setBounds(20, 0, 90, 50);
		orderPay.setFont(labelFont);
		payPanel.add(orderPay);
		
		// 상품 할인
		JLabel disRateProduct = new JLabel("상품 할인");
		disRateProduct.setBounds(20, 40, 90, 50);
		disRateProduct.setFont(labelFont);
		payPanel.add(disRateProduct);
		
		// 쿠폰 할인
		JLabel disRateCoupon = new JLabel("상품 할인");
		disRateCoupon.setBounds(20, 80, 90, 50);
		disRateCoupon.setFont(labelFont);
		payPanel.add(disRateCoupon);
		
		// 배송비
		JLabel deliverycount = new JLabel("배송비");
		deliverycount.setBounds(20, 120, 90, 50);
		deliverycount.setFont(labelFont);
		payPanel.add(deliverycount);
		
		// 구분 선
		JLabel myCartLine2 = new JLabel();
		myCartLine2.setBounds(20, 170, 150, 2);
		myCartLine2.setOpaque(true);
		myCartLine2.setBackground(Color.black);
		payPanel.add(myCartLine2);
		
		//
		
		//화면 기본 설정 - End
		setSize(1920, 1080); //윈도우 사이즈 1920, 1080 고정.
		
		mainPanel.setBounds(0,0, 1920,1080);
		payPanel.setBounds(1198, 284, 300,300);
		
		mainPanel.setBackground(Color.white);
		payPanel.setBackground(new Color(200, 228, 137));
		
		add(payPanel);
		add(mainPanel);
		setVisible(true); // 프레임 출력
	}
	
	//이벤트 처리 클래스들
	class MainActionListener implements ActionListener{
		//Action : 버튼 클릭 
		public void actionPerformed(ActionEvent e) {
			JButton bRefer = (JButton)e.getSource(); //사용자가 클릭한 버튼 알아내기
			
			//버튼 종류마다 이벤트 다르게 지정
			switch(bRefer.getText()) {
			case "지금 뜨는 상품" : case "금주의 TOP 10" :
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
				
			default : //장바구니 클릭 시 
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
			}
		}
	}
}
