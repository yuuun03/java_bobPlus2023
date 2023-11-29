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

public class ShowOneGoods {
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ShowOneGoods(); // SearchResult 객체 생성
        });
    }
	
	JPanel oneGoodsPannel = new JPanel();
	
	public ShowOneGoods() {
		MasterGoods mg = new MasterGoods();
		UserInfoDetail user = new UserInfoDetail();
		
		Product oneProduct = new Product();
		//oneProduct = SearchResult의 이름 클릭으로부터 받아온 Product 정보
		
		for(String CU:user.getAllergy()) { //알러지 정보 필터링
			if(oneProduct.getContainAllergy().contains(CU)) {
				Image warn = new ImageIcon("src/graphics/images/warning.png").getImage();
				warn = warn.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				ImageIcon iconWarn = new ImageIcon(warn);
				JLabel warnAl = new JLabel(iconWarn);
				warnAl.setSize(30,30); //사이즈 다시 잡아야함
				warnAl.setLocation(20, 20); //위치 다시 잡아야함
				oneGoodsPannel.add(warnAl);
				//진동하는거 추가...? (교재 참고)
			}
		}
		displayOneProduct(oneGoodsPannel, oneProduct);
		
		JButton productDetail = new JButton("상품 상세 정보 더보기");
		JButton showReview = new JButton("리뷰");
		JButton inCart = new JButton("장바구니 담기");
		JButton goBuy = new JButton("구매하기");
		
		df.productDetail.addActionListener(new OneProductActionListener());
		df.showReview.addActionListener(new OneProductActionListener());
		df.inCart.addActionListener(new OneProductActionListener());
		df.goBuy.addActionListener(new OneProductActionListener());
		
		oneGoodsPannel.setLayout(null);
		
		oneGoodsPannel.setBackground(Color.white);
		oneGoodsPannel.setBounds(240, 240, 1260, 700);
		
		mainPanel.add(oneGoodsPannel);
		
		setTitle("One Product");
		setSize(1920, 1080); // 적절한 크기로 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // 프레임을 보이도록 설정
	}
	
	private void displayOneProduct(JPanel oneGoodsPannel, Product product) { //상품 패널을 표시하는 함수
		if(product != null) { //상품 정보가 null이 아니면
			JLabel nameLabel = new JLabel(product.getName());
				
			Image productImg = new ImageIcon(product.getImage()).getImage(); //사진 이렇게 불러오는 게 맞는지...?
			ImageIcon productIcon = new ImageIcon(productImg);
			JLabel proImg = new JLabel(productIcon);
			proImg.setSize(500,500); //사진 사이즈 설정
				
			double star = product.getProductStar();
			JLabel starLabel = new JLabel ("별점:" + Double.toString(star));
				
			int price = product.getPrice();
			JLabel priceLabel = new JLabel(Integer.toString(price));
				
			double onePrice = product.getOnePersonPrice();
			JLabel onePriceLabel = new JLabel ("1인분당 " + Double.toString(onePrice)+"원");
				
			double disrate = product.getProductDisRate();
			JLabel disrateLabel = new JLabel(Double.toHexString(disrate)+"%");
				
			oneGoodsPannel.add(nameLabel);
			oneGoodsPannel.add(proImg);
			oneGoodsPannel.add(starLabel);
			oneGoodsPannel.add(priceLabel);
			oneGoodsPannel.add(onePriceLabel);
			oneGoodsPannel.add(disrateLabel);
		}
		else {
			JLabel noProduct = new JLabel("상품이 조회되지 않습니다.");
			oneGoodsPannel.add(noProduct);
		}
	}
	class OneProductActionListener implements ActionListener{
		//Action : 버튼 클릭 
		public void actionPerformed(ActionEvent e) {
			JButton bRefer = (JButton)e.getSource(); //사용자가 클릭한 버튼 알아내기
			
			//버튼 종류마다 이벤트 다르게 지정
			switch(bRefer.getText()) {
						
			case "상품 상세 정보 더보기":
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
					
			case "리뷰": 
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
					
			case "장바구니 담기": 
				JOptionPane.showMessageDialog(null, "장바구니에 담겼습니다.");
				//장바구니 기능 추가하는 경우 추가적인 코드 필요 (상품 객체 넘기는 코드 필요)
				break;
						
			case "구매하기": 
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