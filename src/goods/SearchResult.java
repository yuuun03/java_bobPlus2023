package goods;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Vector;
import admin.*;
import main.*;
import user.UserInfoDetail;

public class SearchResult extends JFrame{	
	JPanel searchResultPanel = new JPanel();
	//큰 패널
	
	private JPanel product1, product2, product3, product4, product5, product6, product7, product8;
	//화면에 표시할 8개의 상품 패널
	
	public SearchResult() {}
	public SearchResult(UserInfoDetail myUser, Vector<Product> pList, String pName) {
		//공통 패널
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
		
		//검색 부분
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

		displayProduct(product1, pList.get(0), searchName); //product가 null로 떠서 오류가 생김
		displayProduct(product2, pList.get(1), searchName);
		displayProduct(product3, pList.get(2), searchName);
		//displayProduct(product4, pList.get(3), searchName);
		//displayProduct(product5, pList.get(4), searchName);
		//displayProduct(product6, pList.get(5), searchName);
		//displayProduct(product7, pList.get(6), searchName);
		//displayProduct(product8, pList.get(7), searchName);
		
		ShowSearchFilter filter = new ShowSearchFilter();
		
		for(int i=0;i<8;i++) {
			for(String CU:filter.getFilterCU()) { //조리도구 필터링
				if (pList.get(i).getCookingUtensils().contains(CU)) { //조리도구가 일치하면
					continue;
				}
				else {
					hideProductionAtIndex(i); //일치하지 않으면 숨기기
				}
			}
		}
		
		for(int i=0;i<8;i++) {
			for(String CU:filter.getFilterAl()) { //알러지 정보 필터링
				if(pList.get(i).getContainAllergy().contains(CU)) {
					hideProductionAtIndex(i); //일치하면 숨기기
				}
			}
		}
		searchResultPanel.setLayout(new GridLayout(2,4)); //8개 상품 패널 띄우기
		
		searchResultPanel.add(product1);
		searchResultPanel.add(product2);
		searchResultPanel.add(product3);
		searchResultPanel.add(product4);
		searchResultPanel.add(product5);
		searchResultPanel.add(product6);
		searchResultPanel.add(product7);
		searchResultPanel.add(product8);
		
		add(searchResultPanel);
		setTitle("Search Result");
		setSize(1920, 1080); // 적절한 크기로 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // 프레임을 보이도록 설정
	}
	
	public void hideProductionAtIndex(int index) { //index 번째 상품 숨기기
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
	
	private void displayProduct(JPanel resultPanel, Product product, String searchName) { //상품 패널을 표시하는 함수
		if(product != null) { //상품 정보가 null이 아니면
			resultPanel.setLayout(null);
			
			JLabel nameLabel = new JLabel(product.getName());
			
			Image productImg = new ImageIcon(product.getImage()).getImage(); //사진 이렇게 불러오는 게 맞는지...?
			ImageIcon productIcon = new ImageIcon(productImg);
			JLabel proImg = new JLabel(productIcon);
			proImg.setSize(260, 260); //사진 사이즈 설정
			
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
				//검색어가 빈칸이 아니고, 검색어가 상품명에 속해있으면
				resultPanel.setVisible(true); //상품 보이기
			} else {
				resultPanel.setVisible(false); //아니면 상품을 보이지 않음
			}
		} else {
			System.out.println("Product is null"); //디버깅 목적
		}
	}
	
	public SearchResult(UserInfoDetail myUser) {
		
		
		
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