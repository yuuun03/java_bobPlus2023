package goods;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Vector;
import admin.*;
import main.*;
import user.UserInfoDetail;

public class SearchResult extends JFrame{
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SearchResult(); // SearchResult 객체 생성
        });
    }
	
	JPanel resultPanel = new JPanel();
	
	private JPanel product1, product2, product3, product4, product5, product6, product7, product8;
	
	public SearchResult() {
		MasterGoods mg = new MasterGoods();
		Search search = new Search();
		String searchName = search.getSearch();
		
		product1 = new JPanel();
		product2 = new JPanel();
		product3 = new JPanel();
		product4 = new JPanel();
		product5 = new JPanel();
		product6 = new JPanel();
		product7 = new JPanel();
		product8 = new JPanel();
		
		displayProduct(product1, mg.getProductAtIndex(0), searchName); //product가 null로 떠서 오류가 생김
		displayProduct(product2, mg.getProductAtIndex(1), searchName);
		displayProduct(product3, mg.getProductAtIndex(2), searchName);
		displayProduct(product4, mg.getProductAtIndex(3), searchName);
		displayProduct(product5, mg.getProductAtIndex(4), searchName);
		displayProduct(product6, mg.getProductAtIndex(5), searchName);
		displayProduct(product7, mg.getProductAtIndex(6), searchName);
		displayProduct(product8, mg.getProductAtIndex(7), searchName);
		
		ShowSearchFilter filter = new ShowSearchFilter();
		
		for(int i=0;i<8;i++) {
			for(String CU:filter.getFilterCU()) { //조리도구 필터링
				if(mg.getProductAtIndex(i).getCookingUtensils().contains(CU)) {
					hideProductionAtIndex(i);
				}
			}
		}
		
		for(int i=0;i<8;i++) {
			for(String CU:filter.getFilterAl()) { //알러지 정보 필터링
				if(mg.getProductAtIndex(i).getContainAllergy().contains(CU)) {
					hideProductionAtIndex(i);
				}
			}
		}
	}
	
	public void hideProductionAtIndex(int index) {
		if(index>=0 && index < 8) {
			switch (index) {
	        case 0:
	            product1.setVisible(false);
	            break;
	        case 1:
	            product2.setVisible(false);
	            break;
	        case 2:
	            product3.setVisible(false);
	            break;
	        case 3:
	            product4.setVisible(false);
	            break;
	        case 4:
	            product5.setVisible(false);
	            break;
	        case 5:
	            product6.setVisible(false);
	            break;
	        case 6:
	            product7.setVisible(false);
	            break;
	        case 7:
	            product8.setVisible(false);
	            break;
			}
		}
	}
	
	private void displayProduct(JPanel resultPanel, Product product, String searchName) {
		if(product != null) {
			resultPanel.setLayout(null);
			
			JLabel nameLabel = new JLabel(product.getName());
			
			Image productImg = new ImageIcon(product.getImage()).getImage(); //사진 이렇게 불러오는 게 맞는지..
			ImageIcon productIcon = new ImageIcon(productImg);
			JLabel proImg = new JLabel(productIcon);
			proImg.setSize(260, 260);
			
			double star = product.getProductStar();
			JLabel starLabel = new JLabel ("별점:" + Double.toString(star));
			int price = product.getPrice();
			JLabel priceLabel = new JLabel(Integer.toString(price));
			double onePrice = product.getOnePersonPrice();
			JLabel onePriceLabel = new JLabel ("1인분당 " + Double.toString(onePrice)+"원");
			double disrate = product.getProductDisRate();
			JLabel disrateLabel = new JLabel(Double.toHexString(disrate)+"%");
			
			resultPanel.add(nameLabel);
			resultPanel.add(proImg);
			resultPanel.add(starLabel);
			resultPanel.add(priceLabel);
			resultPanel.add(onePriceLabel);
			resultPanel.add(disrateLabel);
			
			if(searchName != null && !searchName.isEmpty() && product.getName().contains(searchName)) {
				resultPanel.setVisible(true);
			} else {
				resultPanel.setVisible(false);
			}
		} else {
			System.out.println("Product is null"); //디버깅 목적
		}
		resultPanel.setLayout(new GridLayout(2,4));
		
		resultPanel.add(product1);
		resultPanel.add(product2);
		resultPanel.add(product3);
		resultPanel.add(product4);
		resultPanel.add(product5);
		resultPanel.add(product6);
		resultPanel.add(product7);
		resultPanel.add(product8);
		
		setTitle("Search Result");
        setSize(800, 600); // 적절한 크기로 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // 프레임을 보이도록 설정
	}
	
	public SearchResult(UserInfoDetail myUser) {
		
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
}