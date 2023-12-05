package main;

/*
 * 과목: 자바프로그래밍(04)
 * 학과: 컴퓨터공학부
 * 학번: 2022136095
 * 이름: 이찬비
 */

import javax.swing.*;
import javax.swing.event.*;

import admin.Product;
import goods.SearchResult;
import main.MainFrame.MainActionListener;
import main.MyPage.MyPageActionListener;
import user.SignUp;
import user.UserInfoDetail;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class Cart extends JFrame {
	boolean couponCheck = false; // 쿠폰 적용 버튼 눌렀는지 확인
	int productPay = 0;
	int mProductNum = 0;
	int mCouponNum = 0;
	int deliveryPay = 0;
	int intCount = 0;
	int totalPay = productPay - mProductNum - mCouponNum + deliveryPay;
	
	JPanel mainPanel = new JPanel(); // 메인 패널(= 전체 패널)
	JPanel payPanel = new JPanel(); // 결제 금액 패널
	JPanel deliveryInfoPanel = new JPanel(); // 배송지 정보 패널
	JPanel productInfoPanel = new JPanel(); // 상품 정보 패널
	
	// 장바구니 구현 내용
	public Cart() {} // 기본 생성자
	public Cart(UserInfoDetail myUser, Vector<Product> pList) { // 유저 정보와 상품정보를 넘겨받은 마이페이지 생성
		Vector<Product> cartList = myUser.getCartList(); // 유저 정보에서 장바구니 목록 불러오기
		
		//화면 기본 설정 - Start
		setTitle("밥심+"); //제목 설정
		
		//---아이콘 설정
		Toolkit kit = Toolkit.getDefaultToolkit(); //이미지 편집 위한 Toolkit 객체 생성
		Image img = kit.getImage("src/graphics/images/iconOnly.png"); //이미지 받아오기
		setIconImage(img); //받아온 이미지 아이콘으로 설정
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//종료 설정	 : 가위표 누르면 모든 프레임 종료.
		
		setLayout(null); //프레임 배치관리자 없음 : 개발자 자유 배치
		mainPanel.setLayout(null); //프레임 배치관리자 없음 : 개발자 자유 배치
		payPanel.setLayout(null); //프레임 배치관리자 없음 : 개발자 자유 배치
		deliveryInfoPanel.setLayout(null); //프레임 배치관리자 없음 : 개발자 자유 배치
		productInfoPanel.setLayout(null); //프레임 배치관리자 없음 : 개발자 자유 배치
		
		//로고, 검색창, 위쪽 레이블, 로그인, 장바구니 등 기본 패널 추가
		CommonPanel df = new CommonPanel(); //패널 객체 생성
		add(df.commonPanel); //패널 추가
				
		//---액션 설정
		df.mainIL.addMouseListener(new MouseAdapter() { //아이콘 액션 설정
			public void mouseReleased(MouseEvent e) { //마우스 클릭하면
				new MainFrame(myUser, pList); //메인 프레임으로 돌아감
				dispose(); //이전 프레임은 닫음 
			}
		});
		
		df.login.addActionListener(new ActionListener() { //로그인 버튼 액션
			public void actionPerformed(ActionEvent e) { //버튼 클릭 시
				new LoginPage(myUser, pList); //로그인 페이지 전환
				dispose(); //기존 페이지 닫음
			}});
		
		df.my.addActionListener(new ActionListener() { //마이페이지 버튼 액션
			public void actionPerformed(ActionEvent e) { //버튼 클릭시
				new MyPage(myUser, pList); //마이 페이지 전환
				dispose(); //기존 페이지 안보이게 변경
			}});
			
		df.cart.addActionListener(new ActionListener() { //장바구니 버튼 액션
			public void actionPerformed(ActionEvent e) { //버튼 클릭 시
				new Cart(myUser, pList); //장바구니 페이지 전환
				dispose(); //기존 페이지 안보이게 변경
			}});
		
		df.search.addActionListener(new ActionListener() { //검색창 액션
			public void actionPerformed(ActionEvent e) { //엔터 키 press시
				String pName = e.getActionCommand(); //text field 내 작성되어있는 텍스트 받아옴
				new SearchResult(myUser, pList, pName); //검색 결과창 전환
				dispose(); //기존 페이지 안보이게 변경
			}
		});
		
		//--- --- 기능이 미구현된 부분들의 액션 : 하단의 MainActionListener로 액션 지정
		df.newHotGoods.addActionListener(new MainActionListener()); //지금 뜨는 상품
		df.weekTop10Goods.addActionListener(new MainActionListener()); //금주의 TOP 10
		df.checkAttendance.addActionListener(new MainActionListener()); //출석 체크
		df.couponPoint.addActionListener(new MainActionListener()); //쿠폰/포인트
		df.communityU.addActionListener(new MainActionListener()); //커뮤니티
		df.newMonthGoods.addActionListener(new MainActionListener());  //이달의 신상품
		
		// 폰트 설정
		// --- 
		Font buttonFont = new Font("G마켓 산스 TTF BOLD", Font.CENTER_BASELINE, 30);
		// --- " 나의 장바구니" 출력 라벨
		Font buttonPlain = new Font("G마켓 산스 TTF Medium", Font.PLAIN, 30);
		// --- 
		Font labelFont = new Font("G마켓 산스 TTF Medium", Font.PLAIN, 20);
		// --- 
		Font basic = new Font("G마켓 산스 TTF Medium", Font.PLAIN, 17);
		// --- 
		Font miniBasic = new Font("G마켓 산스 TTF Medium", Font.PLAIN, 15);
		
		// 장바구니 구역 표시
		JLabel myCart = new JLabel(" 나의 장바구니"); // " 나의 장바구니" 출력 라벨
		myCart.setBounds(59 ,220, 1440, 50); // " 나의 장바구니" 출력 라벨 : 위치 (59, 220), 크기 1440*50px
		myCart.setOpaque(true); // 배경 투명하게 해줌
		myCart.setBackground(new Color(200, 228, 137)); // 배경 색깔 설정 - 연두
		myCart.setFont(buttonPlain); // " 나의 장바구니" 출력 라벨 폰트 설정
		mainPanel.add(myCart); // 메인 패널에 " 나의 장바구니" 출력 라벨 추가
		
		// 구분선1
		JLabel myCartLine1 = new JLabel(); // 구분선1로 출력 라벨
		myCartLine1.setBounds(60 ,310, 1125, 2); // 구분선1 : 위치 (60, 310), 크기 1125*2px
		myCartLine1.setOpaque(true); // 배경 투명하게 해줌
		myCartLine1.setBackground(Color.black); // 배경 색깔 설정 - 검정
		mainPanel.add(myCartLine1); // 메인 패널에 구분선1 출력 라벨 추가
		
		// 담은 상품 개수
		int copNum = cartList.size(); // 담은 개수; 장바구니 리스트 크기
		JLabel containProduct = new JLabel("담은 상품 :"); // "담은 상품" 출력 라벨
		JLabel containProductNum = new JLabel(copNum + " 개"); // 담은 상품 개수 출력 라벨
		
		containProduct.setBounds(60 ,320, 300, 50); // "담은 상품" 출력 라벨 : 위치 (60, 320), 크기 300*50px
		containProductNum.setBounds(220 ,320, 200, 50); // 담은 상품 개수 출력 라벨 : 위치 (220, 320), 크기 200*50px
		containProduct.setFont(buttonPlain); // "담은 상품" 출력 라벨 폰트 설정
		containProductNum.setFont(buttonPlain); // 담은 상품 개수 출력 라벨 폰트 설정
		
		mainPanel.add(containProduct);
		mainPanel.add(containProductNum);
		
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
		JLabel orderPay = new JLabel("주문 금액");
		JLabel orderPayNum = new JLabel(productPay + " 원");
		
		orderPayNum.setHorizontalAlignment(JLabel.RIGHT); // 레이블 오른쪽 정렬
						
		orderPay.setBounds(20, 0, 90, 50);
		orderPayNum.setBounds(170, 0, 100, 50);
						
		orderPay.setFont(labelFont);
		orderPayNum.setFont(labelFont);
		
		payPanel.add(orderPay);
		payPanel.add(orderPayNum);
						
		// 상품 할인
		JLabel disRateProduct = new JLabel("상품 할인");
		JLabel disRateProductNum = new JLabel(mProductNum + " 원");
						
		disRateProductNum.setHorizontalAlignment(JLabel.RIGHT); // 레이블 오른쪽 정렬
						
		disRateProduct.setBounds(20, 40, 90, 50);
		disRateProductNum.setBounds(170, 40, 100, 50);
						
		disRateProduct.setFont(labelFont);
		disRateProductNum.setFont(labelFont);
		
		payPanel.add(disRateProduct);
		payPanel.add(disRateProductNum);
					
		// 쿠폰 할인
		JLabel disRateCoupon = new JLabel("쿠폰 할인");
		JLabel disRateCouponNum = new JLabel(mCouponNum + " 원");
						
		disRateCouponNum.setHorizontalAlignment(JLabel.RIGHT); // 레이블 오른쪽 정렬
					
		disRateCoupon.setBounds(20, 80, 90, 50);
		disRateCouponNum.setBounds(160, 80, 110, 50);
						
		disRateCoupon.setFont(labelFont);
		disRateCouponNum.setFont(labelFont);	
		
		
		payPanel.add(disRateCoupon);
		payPanel.add(disRateCouponNum);
						
		// 배송비
		JLabel deliverycount = new JLabel("배송비");
		JLabel deliverycountNum = new JLabel(deliveryPay + " 원");
				
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
		
		buy.addActionListener(new MainActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "결제 진행 중입니다.");
			}});
		
		if (!cartList.isEmpty()) { // 장바구니에 상품을 담았다면 실행
			// 장바구니 담은 상품 정보 출력
			int index = -1;
			for (int i = 0; i < cartList.size(); i++) {
				index = pList.indexOf(cartList.get(i)); 
			}
			
			// 체크박스
			JCheckBox cartCheckProduct = new JCheckBox();
			cartCheckProduct.setBounds(0 ,0, 20, 20);
			cartCheckProduct.setContentAreaFilled(false); // 배경색 제거
			productInfoPanel.add(cartCheckProduct);
			
			// 액션
			allProductCheck.addItemListener(new MainActionListener() {
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED) { // 전체 상품 선택에 체크했을 경우
						cartCheckProduct.setSelected(true);
					}
					else if(e.getStateChange() == ItemEvent.DESELECTED) {
						cartCheckProduct.setSelected(false);
					}
				}});
			
			// 상품 이미지
			String productImage = pList.get(index).getImage();
			Image product = new ImageIcon(productImage).getImage();
			product = product.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
			ImageIcon iconProduct = new ImageIcon(product);
			
			JLabel checkProductImage = new JLabel(iconProduct);
			checkProductImage.setBounds(35, 0, 180, 180);
			productInfoPanel.add(checkProductImage);
			
			// 상품 이름
			String productName = pList.get(index).getName();
			JLabel pName = new JLabel(productName);
			pName.setFont(buttonFont);
			pName.setBounds(230, 60, 300, 40);
			productInfoPanel.add(pName);
			
			// 알러지 정보 표시
			JLabel allergy = new JLabel("알레르기 유발 식재료: ");
			allergy.setBounds(230, 100, 300, 40);
			allergy.setFont(new Font("G마켓 산스 TTF Light", Font.CENTER_BASELINE, 15));
			productInfoPanel.add(allergy);
			
			int w = 0; int v = 0; //세부 위치 조정 위한 변수
			Vector<String> containAllergy = pList.get(index).getContainAllergy();
			JLabel allergyInfo[] = new JLabel[containAllergy.size()];
			for(int i = 0; i < containAllergy.size(); i++) {
				allergyInfo[i] = new JLabel(containAllergy.get(i));
				allergyInfo[i].setFont(new Font("G마켓 산스 TTF Light", Font.CENTER_BASELINE, 15));
				productInfoPanel.add(allergyInfo[i]);
				// 위치 조정
				allergyInfo[i].setSize(230 + 4 * containAllergy.get(i).length(), 40);
				allergyInfo[i].setLocation( 390 + 50 * w, 100 + 4 * v);
				
				w++;
				if (w == 3) {w = 0; v++;}
			}
			
			
			// 1개당 가격 표시
			JLabel oneProduct = new JLabel("1개당");
			oneProduct.setFont(labelFont);
			oneProduct.setHorizontalAlignment(JLabel.RIGHT); // 레이블 오른쪽 정렬
			oneProduct.setBounds(570, 40, 100, 50);
			productInfoPanel.add(oneProduct);
			
			int price = pList.get(index).getPrice();
			JLabel oneProductRealPrice = new JLabel(price + " 원");
			oneProductRealPrice.setFont(new Font("G마켓 산스 TTF Light", Font.CENTER_BASELINE, 15));
			oneProductRealPrice.setForeground(Color.gray);
			oneProductRealPrice.setHorizontalAlignment(JLabel.RIGHT); // 레이블 오른쪽 정렬
			oneProductRealPrice.setBounds(570, 65, 100, 50);
			productInfoPanel.add(oneProductRealPrice);
			
			// 할인 된 가격
			double disRatePrice = pList.get(index).getPrice()*(pList.get(index).getProductDisRate()/100);
			int dP = pList.get(index).getPrice() - (int)disRatePrice;
			JLabel oneProductDisRate = new JLabel(dP + " 원");
			oneProductDisRate.setFont(labelFont);
			oneProductDisRate.setHorizontalAlignment(JLabel.RIGHT); // 레이블 오른쪽 정렬
			oneProductDisRate.setBounds(570, 90, 100, 50);
			productInfoPanel.add(oneProductDisRate);
			
			// 수량
			JLabel count = new JLabel("수량:");
			count.setFont(labelFont);
			count.setBounds(722, 50, 100, 50);
			productInfoPanel.add(count);
			
			String [] nowProductCount = new String[pList.get(index).getProductCount()];
			for (int i = 0; i < pList.get(index).getProductCount(); i++) {
				nowProductCount[i] = Integer.toString(i + 1);
			}
			SpinnerListModel SpinnerListModel = new SpinnerListModel(nowProductCount);
			JSpinner countNum = new JSpinner(SpinnerListModel);
			countNum.setFont(labelFont);
			countNum.setBounds(772, 62, 70, 25);
			productInfoPanel.add(countNum);
			
			// 쿠폰 적용
			JButton coupon = new JButton("쿠폰 적용");
			coupon.setFont(labelFont);
			coupon.setBounds(725, 99, 120, 28);
			productInfoPanel.add(coupon);
			
			// 주문 가격
			
			JLabel orderPrice = new JLabel("주문 가격");
			JLabel orderCoupon = new JLabel(" ");
			JLabel orderPriceNum = new JLabel(price - (int)disRatePrice - mCouponNum + " 원");

			orderPrice.setBounds(900, 40, 100, 50);
			orderCoupon.setBounds(700, 65, 300, 50);
			orderPriceNum.setBounds(800, 90, 200, 50);
			
			orderPrice.setFont(labelFont);
			orderCoupon.setFont(new Font("G마켓 산스 TTF Light", Font.CENTER_BASELINE, 15));
			orderCoupon.setForeground(Color.gray);
			orderPriceNum.setFont(labelFont);
			
			orderPrice.setHorizontalAlignment(JLabel.RIGHT); // 레이블 오른쪽 정렬
			orderCoupon.setHorizontalAlignment(JLabel.RIGHT); // 레이블 오른쪽 정렬
			orderPriceNum.setHorizontalAlignment(JLabel.RIGHT); // 레이블 오른쪽 정렬
			
			productInfoPanel.add(orderPrice);
			productInfoPanel.add(orderPriceNum);
			productInfoPanel.add(orderCoupon);
			
			// 액션
			// 장바구니에 담은 상품의 체크박스를 선택했을 경우
			cartCheckProduct.addItemListener(new MainActionListener() {
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED) { // 장바구니에 담은 상품의 체크박스를 선택했을 경우
						orderPayNum.setText(productPay + price + " 원");
						disRateProductNum .setText(mProductNum - (int)disRatePrice + " 원");
						deliverycountNum.setText(deliveryPay + 2500 + " 원");
						priPayNum.setText(totalPay + price - (int)disRatePrice + 2500 + "원");
						
						checkProductDelete.addMouseListener(new MouseAdapter() {// 선택 상품 삭제 선택 시
							public void mouseReleased(MouseEvent e) { //마우스를 클릭후 뗄 때
								productInfoPanel.setVisible(false); // 패널 감추기
								cartList.remove(0);
								containProductNum.setText(copNum - 1 + " 개");
								containProductNum.setVisible(true);
								containProductNum.repaint();
								orderPayNum.setText(productPay + " 원");
								disRateProductNum .setText(mProductNum + " 원");
								disRateCouponNum.setText(mCouponNum + " 원");
								deliverycountNum.setText(deliveryPay + " 원");
								priPayNum.setText(totalPay + "원");
							}});
						
					}
					else if(e.getStateChange() == ItemEvent.DESELECTED) { // 장바구니에 담은 상품의 체크박스를 선택 해제했을 경우
						couponCheck = false;
						orderCoupon.setVisible(false);
						
						orderPayNum.setText(productPay + " 원");
						disRateProductNum .setText(mProductNum + " 원");
						disRateCouponNum.setText(mCouponNum + " 원");
						deliverycountNum.setText(deliveryPay + " 원");
						priPayNum.setText(totalPay + "원");
					}
				}});
			
			// JSpinner를 활용하여 상품의 수량을 선택할 경우
			countNum.addChangeListener(new MainActionListener() {
				public void stateChanged(ChangeEvent e) {
					String count = countNum.getValue().toString();
					intCount = Integer.valueOf(count);
					if (!couponCheck){orderPriceNum.setText(price*(intCount) - (int)disRatePrice*(intCount) - mCouponNum + " 원");}
					orderPayNum.setText(price*(intCount) + " 원");
					disRateProductNum.setText(- (int)disRatePrice*(intCount) + " 원");
					deliverycountNum.setText(deliveryPay + 2500 + " 원");
					priPayNum.setText(price*(intCount) - (int)disRatePrice*(intCount) + 2500  + "원");
					
					
				}});
			// 쿠폰 적용 버튼을 눌렀을 경우
			coupon.addActionListener(new MainActionListener() {
				public void actionPerformed(ActionEvent e) {
					couponCheck = true;
					orderCoupon.setVisible(true);
					orderCoupon.setText("쿠폰 -3000 원");
					disRateCouponNum.setText(- 3000 + " 원");
					deliverycountNum.setText(deliveryPay + 2500 + " 원");
					orderPriceNum.setText(price*(intCount) - (int)disRatePrice*(intCount) - 3000 + " 원");
					priPayNum.setText(price*(intCount) - (int)disRatePrice*(intCount) - 3000 + 2500 + "원"); 
				}});
		}
		
		//화면 기본 설정 - End
		setSize(1920, 1080); //윈도우 사이즈 1920, 1080 고정.
		
		mainPanel.setBounds(0,0, 1920, 1080); // 메인 패널 : 위치 (0, 0), 크기 1920*1080px
		payPanel.setBounds(1198, 284, 300, 260); // 결제 금액 패널 : 위치 (1198, 284), 크기 300*260px
		deliveryInfoPanel.setBounds(1198, 560, 300,100); // 배송지 정보 패널 : 위치 (1198, 560), 크기 300*100px
		productInfoPanel.setBounds(59, 375, 1125, 180); // 상품 정보 패널 : 위치 (59, 375), 크기 1125*180px
		
		mainPanel.setBackground(Color.white); // 배경 색깔 설정 - 하양
		payPanel.setBackground(new Color(200, 228, 137)); // 배경 색깔 설정 - 연두
		deliveryInfoPanel.setBackground(new Color(255, 206, 89)); // 배경 색깔 설정 - 빨강
		productInfoPanel.setBackground(Color.white); // 배경 색깔 설정 - 하양
		
		
		add(deliveryInfoPanel); // 프레임에 배송지 정보 패널 추가 
		add(productInfoPanel); // 프레임에 상품 정보 패널 추가
		add(payPanel); // 프레임에 결제 금액 패널 추가
		add(mainPanel); // 프레임에 메인 패널 추가
		
		setVisible(true); // 프레임 출력
	}
	
	//이벤트 처리 클래스들
	class MainActionListener implements ActionListener, ItemListener, ChangeListener{
		//Action : 버튼 클릭 
		public void actionPerformed(ActionEvent e) {
			JButton bRefer = (JButton)e.getSource(); //사용자가 클릭한 버튼 알아내기
			
			
			//버튼 종류마다 이벤트 다르게 지정
			switch(bRefer.getText()) {
			case "지금 뜨는 상품" : case "금주의 TOP 10" :
				/*인기상품, 지금뜨는 상품, 금주의 TOP10 클릭시
				지금뜨는 상품과 금주의 TOP10은 인기 상품에 속해있는 원소긴 하나
				이는 추후 구현 예정*/ 	
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다."); // 회원 알림
				break;
				
			case "출석 체크":
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다."); // 회원 알림
				break;
				
			case "쿠폰/포인트": 
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다."); // 회원 알림
				break;
					
			case "커뮤니티": 
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다."); // 회원 알림
				break;
				
			case "이달의 신상품": 
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다."); // 회원 알림
				break;
				
			default : //장바구니 클릭 시 
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다."); // 회원 알림
				break;
			}
		}
		// 체크박스 선택 시
		public void itemStateChanged(ItemEvent e) {}
		public void valueChanged(ListSelectionEvent e) {}
		public void stateChanged(ChangeEvent e) {}
		
	}
}
