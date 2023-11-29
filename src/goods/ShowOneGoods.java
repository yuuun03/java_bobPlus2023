package goods;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Arrays;
import java.util.Vector;

import admin.*;
import main.*;
import goods.*;
import user.*;

public class ShowOneGoods extends JFrame {
	JPanel oneGoodsPannel = new JPanel();
	private Thread th; //진동하는 스레드 구현 위함
	
	public ShowOneGoods() {}
	public ShowOneGoods(UserInfoDetail myUser, Vector<Product> pList, Product p) {
		//화면 기본 설정 - Start
		setTitle("밥심+"); //제목 설정
		//---아이콘 설정
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("src/graphics/images/iconOnly.png");
		setIconImage(img);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//종료 설정	
		
		setLayout(null);
				
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
				new MyPage(myUser, pList); //로그인 페이지 전환
				dispose(); //기존 페이지 안보이게 변경
			}});
					
		df.cart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Cart(myUser, pList); //로그인 페이지 전환
				dispose(); //기존 페이지 안보이게 변경
			}});
				
		df.search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pName = e.getActionCommand();
				//상품 객체까지 매개변수로 받아와야하나 지금 일시적으로 미지정함.
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
		
		//단일 상품 보기 상세 파트 구현

		for(String CU:myUser.getAllergy()) { //알러지 정보 필터링
			if(p.getContainAllergy().contains(CU)) {
				Image warn = new ImageIcon("src/graphics/images/warning.png").getImage();
				warn = warn.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				ImageIcon iconWarn = new ImageIcon(warn);
				JLabel warnAl = new JLabel(iconWarn);
				warnAl.setSize(100,100); //사이즈 다시 잡아야함
				warnAl.setLocation(1370, 333); //위치 다시 잡아야함
				oneGoodsPannel.add(warnAl);
				//진동하는거 추가...? (교재 참고)
			}
		}
		
		//상품 정보 출력
		displayOneProduct(oneGoodsPannel, p);
		
		//버튼
		Font bFont = new Font("G마켓 산스 TTF BOLD", Font.CENTER_BASELINE, 30);
		
		JButton productDetail = new JButton("상품 상세 정보");
		productDetail.addActionListener(new OneProductActionListener());
		productDetail.setBounds(580, 453, 300, 90);
		productDetail.setFont(bFont);
		productDetail.setBackground(new Color(255, 206, 90));
		
		
		JButton showReview = new JButton("리뷰");
		showReview.addActionListener(new OneProductActionListener());
		showReview.setBounds(890, 453, 170, 90);
		showReview.setFont(bFont);
		showReview.setBackground(new Color(255, 206, 90));
		
		JButton inCart = new JButton("장바구니");
		inCart.addActionListener(new OneProductActionListener());
		inCart.setBounds(1070, 453, 220, 90);
		inCart.setFont(bFont);
		inCart.setBackground(new Color(200, 228, 137));
		
		JButton goBuy = new JButton("구매");
		goBuy.addActionListener(new OneProductActionListener());
		goBuy.setBounds(1300, 453, 170, 90);
		goBuy.setFont(bFont);
		goBuy.setBackground(new Color(243, 138, 117));
		
		oneGoodsPannel.add(productDetail);
		oneGoodsPannel.add(showReview);
		oneGoodsPannel.add(inCart);
		oneGoodsPannel.add(goBuy);
		
		/*
		df.productDetail.addActionListener(new OneProductActionListener());
		df.showReview.addActionListener(new OneProductActionListener());
		df.inCart.addActionListener(new OneProductActionListener());
		df.goBuy.addActionListener(new OneProductActionListener());
		*/
		
		oneGoodsPannel.setLayout(null);
		
		oneGoodsPannel.setBackground(Color.white);
		oneGoodsPannel.setBounds(0, 203, 1920, 877);
		
		add(oneGoodsPannel);
		//setTitle("One Product");
		setSize(1920, 1080); // 적절한 크기로 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // 프레임을 보이도록 설정
	}
	
	private void displayOneProduct(JPanel oneGoodsPannel, Product product) { //상품 패널을 표시하는 함수
		if(product != null) { //상품 정보가 null이 아니면	
			//이미지 설정
			Image img = new ImageIcon(product.getImage()).getImage();
			img = img.getScaledInstance(520, 520, Image.SCALE_SMOOTH);
			ImageIcon productIcon = new ImageIcon(img);
			JLabel proImg = new JLabel(productIcon);
			
			proImg.setBounds(40, 30, 520, 520); //사진 사이즈 설정
			
			//이름 설정
			JLabel nameLabel = new JLabel(product.getName());
			nameLabel.setBounds(580, 25, 1000, 100);
			nameLabel.setFont(new Font("G마켓 산스 TTF BOLD", Font.CENTER_BASELINE, 80));
			
			//할인율 설정
			double disrate = product.getProductDisRate();
			JLabel disrateLabel = new JLabel(Double.toString(disrate)+"%");
			disrateLabel.setBounds(580, 115, 1000, 100);
			disrateLabel.setFont(new Font("G마켓 산스 TTF BOLD", Font.CENTER_BASELINE, 45));
			disrateLabel.setForeground(Color.red);
			
			//가격 설정
			int price = product.getPrice();
			double disPrice = price *((100-disrate)/100);
			JLabel disPriceLabel = new JLabel(Double.toString(disPrice) + " 원");
			disPriceLabel.setBounds(750, 115, 1000, 100);
			disPriceLabel.setFont(new Font("G마켓 산스 TTF BOLD", Font.CENTER_BASELINE, 45));
			
			JLabel priceLabel = new JLabel("원가 " + Integer.toString(price) + "원");
			priceLabel.setBounds( (1000 + (Double.toString(disPrice) + " 원").length()) , 125, 1000, 100);
			priceLabel.setFont(new Font("G마켓 산스 TTF Light", Font.CENTER_BASELINE, 15));
			priceLabel.setForeground(Color.gray);
			
			//1인분당 가격 설정
			double onePrice = product.getOnePersonPrice();
			JLabel onePriceLabel = new JLabel ("1인분당 : " + Double.toString(onePrice)+"원");
			onePriceLabel.setBounds(580, 170, 1000, 100);
			onePriceLabel.setFont(new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 30));
			
			//별점 설정
			double star = product.getProductStar();
			JLabel starLabel = new JLabel ("별점:" + Double.toString(star));
			starLabel.setBounds(580, 220, 1000, 100);
			starLabel.setFont(new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 30));
			
			//조리기구 정보
			Vector<String> cuBundle = product.getCookingUtensils();
			
			String uList = "";
			for(int i = 0 ; i < cuBundle.size(); i++ ) {
				uList += cuBundle.get(i);
				if(i == cuBundle.size()-1) {uList += " ";}
				else {uList +=", ";}
			}
			JLabel cUtensilLabel = new JLabel("조리 기구 : " + uList);
			cUtensilLabel.setBounds(580, 270, 1000, 100);
			cUtensilLabel.setFont(new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 30));
			
			//알러지 정보
            Vector<String> alBundle = product.getContainAllergy();
			
			String alList = "";
			for(int i = 0 ; i < alBundle.size(); i++ ) {
				alList += alBundle.get(i);
				if(i == alBundle.size()-1) {alList += " ";}
				else {alList +=", ";}
			}
			
			JLabel allergyLabel = new JLabel("알레르기 유발 식재료 : " + alList);
			allergyLabel.setBounds(580, 320, 1000, 100);
			allergyLabel.setFont(new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 30));
			
			oneGoodsPannel.add(nameLabel);
			oneGoodsPannel.add(proImg);
			oneGoodsPannel.add(starLabel);
			oneGoodsPannel.add(disPriceLabel);
			oneGoodsPannel.add(priceLabel);
			oneGoodsPannel.add(onePriceLabel);
			oneGoodsPannel.add(disrateLabel);
			oneGoodsPannel.add(cUtensilLabel);
			oneGoodsPannel.add(allergyLabel);
		}
		else {
			JLabel noProduct = new JLabel("상품이 조회되지 않습니다.");
			oneGoodsPannel.add(noProduct);
		}
	}
	
	//이벤트 처리 클래스들
		class MainActionListener implements ActionListener{
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
					break;
				}
			}
		}
	
	class OneProductActionListener implements ActionListener{
		//Action : 버튼 클릭 
		public void actionPerformed(ActionEvent e) {
			JButton bRefer = (JButton)e.getSource(); //사용자가 클릭한 버튼 알아내기
			
			//버튼 종류마다 이벤트 다르게 지정
			switch(bRefer.getText()) {
						
			case "상품 상세 정보":
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
					
			case "리뷰": 
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
					
			case "장바구니": 
				JOptionPane.showMessageDialog(null, "장바구니에 담겼습니다.");
				//장바구니 기능 추가하는 경우 추가적인 코드 필요 (상품 객체 넘기는 코드 필요)
				break;
						
			case "구매": 
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
			}
		}
	}
	
	/* //SearchResult 때랑 똑같이 빨간 줄 뜨는데 해결 어떻게 하는지 모르겠어서 주석처리 했어...
	public ShowOneGoods(UserInfoDetail myUser) {
		JPanel mainPanel = new JPanel();
		setTitle("밥심+"); //제목 설정
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("src/graphics/images/iconOnly.png");
		setIconImage(img);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(null);
		mainPanel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
		
		//로고, 검색창, 위쪽 레이블, 로그인, 장바구니 등 기본 패널 추가 -> MainFrame.java 복붙
		CommonPanel df = new CommonPanel(); //패널 객체 생성
		add(df.commonPanel); //패널 추가
				
		//---액션 설정
		df.mainIL.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				new MainFrame(myUser);
			}
		});
		df.login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginPage(myUser); //로그인 페이지 전환
				dispose(); //기존 페이지 안보이게 변경
			}});
		df.my.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MyPage(myUser); //로그인 페이지 전환
				dispose(); //기존 페이지 안보이게 변경
			}});
		df.cart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Cart(myUser); //로그인 페이지 전환
				dispose(); //기존 페이지 안보이게 변경
			}});
		
		df.newHotGoods.addActionListener(new MainActionListener());
		df.weekTop10Goods.addActionListener(new MainActionListener());
		df.checkAttendance.addActionListener(new MainActionListener());
		df.couponPoint.addActionListener(new MainActionListener());
		df.communityU.addActionListener(new MainActionListener());
		df.newMonthGoods.addActionListener(new MainActionListener());
		
	}
	//이벤트 처리 클래스들
	class MainActionListener implements ActionListener{
		//Action : 버튼 클릭 
		public void actionPerformed(ActionEvent e) {
			JButton bRefer = (JButton)e.getSource(); //사용자가 클릭한 버튼 알아내기
			
			//버튼 종류마다 이벤트 다르게 지정
			switch(bRefer.getText()) {
						
			case "인기 상품": case "지금 뜨는 상품" : case "금주의 TOP 10" :
						/*인기상품, 지금뜨는 상품, 금주의 TOP10 클릭시
						지금뜨는 상품과 금주의 TOP10은 인기 상품에 속해있는 원소긴 하나
						이는 추후 구현 예정 
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
				break;
			}
		}
	}
	*/
}