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
	JPanel deliveryInfoPanel = new JPanel();
	JPanel productInfoPanel = new JPanel();
	
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
		deliveryInfoPanel.setLayout(null); //프레임 배치관리자 없음 : 개발자 자유 배치
		productInfoPanel.setLayout(null); //프레임 배치관리자 없음 : 개발자 자유 배치
		
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
		
		// 구분선1
		JLabel myCartLine1 = new JLabel();
		myCartLine1.setBounds(60 ,310, 1125, 2);
		myCartLine1.setOpaque(true);
		myCartLine1.setBackground(Color.black);
		mainPanel.add(myCartLine1);
		
		// 담은 상품 개수
		int copNum = 2;
		JLabel containProduct = new JLabel("담은 상품 :");
		JLabel containProductNum = new JLabel(copNum + " 개");
		
		containProduct.setBounds(60 ,320, 300, 50);
		containProductNum.setBounds(220 ,320, 200, 50);
		
		containProduct.setFont(buttonPlain);
		containProductNum.setFont(buttonPlain);
		
		mainPanel.add(containProduct);
		mainPanel.add(containProductNum);
		
		// 선택 상품 개수
		int chpNum = 1;
		JLabel checkProduct = new JLabel("선택 상품 :");
		JLabel checkProductNum = new JLabel(chpNum + " 개");
		
		checkProductNum.setHorizontalAlignment(JLabel.RIGHT); // 레이블 오른쪽 정렬
		
		checkProduct.setBounds(958 ,320, 300, 50);
		checkProductNum.setBounds(1085, 320, 100, 50);
		
		checkProduct.setFont(buttonPlain);
		checkProductNum.setFont(buttonPlain);
		
		mainPanel.add(checkProduct);
		mainPanel.add(checkProductNum);
		
		// 전체 상품 선택 체크리스트
		JCheckBox allProductCheck = new JCheckBox("전체 상품 선택");
		allProductCheck.setBounds(59 ,240, 300, 100);
		allProductCheck.setFont(basic);
		allProductCheck.setContentAreaFilled(false); // 배경색 제거
		mainPanel.add(allProductCheck);
		
		// 품정상품 삭제 및 선택 상품 삭제 버튼
		JLabel soldoutProductDelete = new JLabel("품절상품 삭제 |");
		JLabel checkProductDelete = new JLabel("선택상품 삭제");
		
		soldoutProductDelete.setBounds(969, 275, 120, 30);
		checkProductDelete.setBounds(1084, 275, 120, 30);
		
		soldoutProductDelete.setFont(basic);
		checkProductDelete.setFont(basic);
		
		mainPanel.add(soldoutProductDelete);
		mainPanel.add(checkProductDelete);
		
		// 결제 금액 확인 패널
		// 주문 금액
		int productPay = 16000;
		JLabel orderPay = new JLabel("주문 금액");
		JLabel orderPayNum = new JLabel(productPay + " 원");
		
		orderPayNum.setHorizontalAlignment(JLabel.RIGHT); // 레이블 오른쪽 정렬
		
		orderPay.setBounds(20, 0, 90, 50);
		orderPayNum.setBounds(180, 0, 90, 50);
		
		orderPay.setFont(labelFont);
		orderPayNum.setFont(labelFont);
		
		payPanel.add(orderPay);
		payPanel.add(orderPayNum);
		
		// 상품 할인
		int mProductNum = 2400;
		JLabel disRateProduct = new JLabel("상품 할인");
		JLabel disRateProductNum = new JLabel("- "+ mProductNum + " 원");
		
		disRateProductNum.setHorizontalAlignment(JLabel.RIGHT); // 레이블 오른쪽 정렬
		
		disRateProduct.setBounds(20, 40, 90, 50);
		disRateProductNum.setBounds(170, 40, 100, 50);
		
		disRateProduct.setFont(labelFont);
		disRateProductNum.setFont(labelFont);
		
		payPanel.add(disRateProduct);
		payPanel.add(disRateProductNum);
		
		// 쿠폰 할인
		int mCouponNum = 1600;
		JLabel disRateCoupon = new JLabel("쿠폰 할인");
		JLabel disRateCouponNum = new JLabel("- "+ mCouponNum + " 원");
		
		disRateCouponNum.setHorizontalAlignment(JLabel.RIGHT); // 레이블 오른쪽 정렬
		
		disRateCoupon.setBounds(20, 80, 90, 50);
		disRateCouponNum.setBounds(160, 80, 110, 50);
		
		disRateCoupon.setFont(labelFont);
		disRateCouponNum.setFont(labelFont);
		
		payPanel.add(disRateCoupon);
		payPanel.add(disRateCouponNum);
		
		// 배송비
		int deliveryPay = 2500;
		JLabel deliverycount = new JLabel("배송비");
		JLabel deliverycountNum = new JLabel("+ "+ deliveryPay + " 원");
		
		deliverycountNum.setHorizontalAlignment(JLabel.RIGHT); // 레이블 오른쪽 정렬
		
		deliverycount.setBounds(20, 120, 90, 50);
		deliverycountNum.setBounds(160, 120, 110, 50);
		
		deliverycount.setFont(labelFont);
		deliverycountNum.setFont(labelFont);
		
		payPanel.add(deliverycount);
		payPanel.add(deliverycountNum);
		
		// 구분 선2
		JLabel myCartLine2 = new JLabel();
		myCartLine2.setBounds(20, 170, 260, 2);
		myCartLine2.setOpaque(true);
		myCartLine2.setBackground(Color.black);
		payPanel.add(myCartLine2);
		
		// 결제 예정 금액
		int totalPay = productPay - mProductNum - mCouponNum + deliveryPay;
		JLabel priPay = new JLabel("결제예정금액");
		JLabel priPayNum = new JLabel(totalPay + "원");
		
		priPayNum.setHorizontalAlignment(JLabel.RIGHT); // 레이블 오른쪽 정렬
		
		priPay.setBounds(20, 175, 200, 50);
		priPayNum.setBounds(120, 202, 160, 60);
		
		priPay.setFont(labelFont);
		priPayNum.setFont(buttonFont);
		
		priPay.setForeground(new Color(56, 87, 36));
		priPayNum.setForeground(new Color(56, 87, 36));
		
		payPanel.add(priPay);
		payPanel.add(priPayNum);
		
		// 배송지 정보 출력
		String mainAddress = myUser.getAddress();
		mainAddress = "충남 천안시 동남구 병천면 충절로 1600";
		JLabel deliveryInfo = new JLabel("배송지 정보");
		JLabel mainDeliveryInfo = new JLabel(mainAddress);
		
		deliveryInfo.setBounds(20, 0, 150, 50);
		mainDeliveryInfo.setBounds(20, 30, 290, 50);
		
		deliveryInfo.setFont(labelFont);
		mainDeliveryInfo.setFont(miniBasic);
		
		deliveryInfoPanel.add(deliveryInfo);
		deliveryInfoPanel.add(mainDeliveryInfo);
		
		// 주문하기
		JButton buy = new JButton("주문하기");
		buy.setBounds(1198, 670, 300, 50);
		buy.setFont(buttonFont);
		buy.setBackground(new Color(241, 133, 115));
		buy.setBorderPainted(false); // 외각선 제거
		mainPanel.add(buy);
		
		//화면 기본 설정 - End
		setSize(1920, 1080); //윈도우 사이즈 1920, 1080 고정.
		
		mainPanel.setBounds(0,0, 1920, 1080);
		payPanel.setBounds(1198, 284, 300, 260);
		deliveryInfoPanel.setBounds(1198, 560, 300,100);
		productInfoPanel.setBounds(59, 375, 1125, 180);
		
		mainPanel.setBackground(Color.white);
		payPanel.setBackground(new Color(200, 228, 137));
		deliveryInfoPanel.setBackground(new Color(255, 206, 89));
		productInfoPanel.setBackground(new Color(255, 206, 89));
		
		add(productInfoPanel);
		add(deliveryInfoPanel);
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
