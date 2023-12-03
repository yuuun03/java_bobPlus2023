package goods;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.util.Arrays;
import java.util.Vector;
import admin.*;
import main.*;
import user.UserInfoDetail;

public class SearchResult extends JFrame{	
	JPanel searchResultPanel = new JPanel();
	//상품 추가할 큰 패널
	
	private JPanel product1, product2, product3, product4, product5, product6, product7, product8;
	//화면(searchResultPanel)에 표시할 8개의 상품 패널
	
	public SearchResult() {} //기본 생성자
	public SearchResult(UserInfoDetail myUser, Vector<Product> pList, String pName) {
		JPanel mainPanel = new JPanel(); //공통 패널
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
				dispose();
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
		add(mainPanel);
		mainPanel.setSize(1920, 1080);
		mainPanel.setBackground(Color.white);
		
		//필터패널
		ShowSearchFilter filter = new ShowSearchFilter();
		
		mainPanel.add(filter.filterPanel);
		
		//검색 결과 출력 부분
		product1 = new JPanel();
		product2 = new JPanel();
		product3 = new JPanel();
		product4 = new JPanel();
		product5 = new JPanel();
		product6 = new JPanel();
		product7 = new JPanel();
		product8 = new JPanel();

		JPanel[] pBundle = {product1, product2, product3, product4, product5, product6, product7, product8};
		
		int emptyCnt = 0;
		for(int i = 0; i < pBundle.length; i++) {
			if(i < pList.size()) {
				displayProduct(pBundle[i], pList.get(i), pName, myUser,  pList);
			}
			else {
				emptyCnt++;
			}
		}
		for(int i = 0; i < emptyCnt; i++) {
			displayProduct(pBundle[8-emptyCnt + i], null, pName, myUser,  pList);
		}
		
		//필터 패널 액션 추가(필터링)
		//--- 조리기구
		Vector<Integer> hiddenIdxCU = new Vector<Integer>();
		for(int i=0; i< filter.cookUtensils.length;i++) {
			filter.cookUtensils[i].addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent e) {
					Object source = e.getItemSelectable();
					if (source instanceof JCheckBox) {
						JCheckBox checkBox = (JCheckBox) source;
						
				    // 선택 상태에 따라 해당하는 Vector를 업데이트
					if (checkBox.isSelected()) {
						if (Arrays.asList(filter.cookUtensils).contains(checkBox)) {
							filter.filterCU.add(checkBox.getText());
							for(int i=0;i<8;i++) {
								if (pList.get(i).getCookingUtensils().contains(checkBox.getText())) { //조리도구가 일치하면
									continue;
								}
								else {
									hideProductionAtIndex(i); //일치하지 않으면 숨기기
									hiddenIdxCU.add(i);
								}
								}
							}
				            }
					else {
						filter.filterCU.remove(checkBox.getText());
				        for(int i:hiddenIdxCU) {
				        	pBundle[i].setVisible(true);
				            }
				        }
					}
				}
			});
		}
		
		//--- 알러지
		Vector<Integer> hiddenIdxAL = new Vector<Integer>();
		for(int i=0;i<filter.alName.length;i++) {
			filter.infoAllergy[i].addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent e) {
					Object source = e.getItemSelectable();
					if (source instanceof JCheckBox) {
						JCheckBox checkBox = (JCheckBox) source;
						
				    // 선택 상태에 따라 해당하는 Vector를 업데이트
					if (checkBox.isSelected()) {
						if (Arrays.asList(filter.infoAllergy).contains(checkBox)) {
				            	filter.filterAl.add(checkBox.getText());
				            	for(int i=0;i<8;i++) {
			        				if(pList.get(i).getContainAllergy().contains(checkBox.getText())) {
			        					hideProductionAtIndex(i); //일치하면 숨기기
			        					hiddenIdxAL.add(i);
			        				}
				        		}
				        	}
				         }
				    
					else {
				          filter.filterAl.remove(checkBox.getText());
				          for(int i:hiddenIdxAL) {
					           	pBundle[i].setVisible(true);
					           }
				            }
				        }
					}
				}
			);
		}
		
		searchResultPanel.setBackground(Color.white); //배경 흰색으로 설정
		searchResultPanel.setLayout(new GridLayout(2,4)); //8개 상품을 그리드 배치
		
		searchResultPanel.add(product1); //화면 패널에 상품 1~8 추가
		searchResultPanel.add(product2);
		searchResultPanel.add(product3);
		searchResultPanel.add(product4);
		searchResultPanel.add(product5);
		searchResultPanel.add(product6);
		searchResultPanel.add(product7);
		searchResultPanel.add(product8);
		
		searchResultPanel.setBounds(240, 240, 1260, 700); //사이즈, 위치 설정
				
		mainPanel.add(searchResultPanel); //메인 패널에 상품 패널 추가
		
		setSize(1920, 1080); // 적절한 크기로 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // 프레임을 보이도록 설정
	}
	
	public void hideProductionAtIndex(int index) { //index 번째 상품 숨기는 함수
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
	
	//상품 패널을 표시하는 함수
	private void displayProduct(JPanel resultPanel, Product product, String searchName, UserInfoDetail myUser, Vector<Product> pList) { 
		if(product != null) { //상품 정보가 null이 아니면
			resultPanel.setLayout(null);			
			
			//이미지 설정
			Image img = new ImageIcon(product.getImage()).getImage();
			img = img.getScaledInstance(400, 150, Image.SCALE_SMOOTH);
			ImageIcon productIcon = new ImageIcon(img);
			JLabel proImg = new JLabel(productIcon);
			
			proImg.setBounds(0, 0, 400, 150); //사진 사이즈 설정
			
			//라벨 설정
			JLabel nameLabel = new JLabel(product.getName());
			
			nameLabel.setBounds(0, 155, 315, 50); //크기, 위치 설정
			nameLabel.setFont(new Font("G마켓 산스 TTF BOLD", Font.CENTER_BASELINE, 40)); //폰트 설정
			nameLabel.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) { //마우스 클릭 이벤트 설정
					new ShowOneGoods(myUser, pList, product);
					dispose();
				}
			});
			
			//별점 설정
			double star = product.getProductStar();
			JLabel starLabel = new JLabel ("별점:" + Double.toString(star));
			starLabel.setBounds(0, 195, 315, 50); //크기, 위치 설정
			starLabel.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 20)); //폰트 설정
			
			//가격, 할인율 설정(가격은 할인율 적용하여 출력)
			int price = product.getPrice();
			double disrate = product.getProductDisRate();
			
			JLabel priceLabel = new JLabel(Double.toString(price *((100-disrate)/100))+" 원");
			priceLabel.setBounds(0, 235, 315, 50);
			priceLabel.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 20));
			
			JLabel disrateLabel = new JLabel("할인율 : " + Double.toString(disrate)+"%");
			disrateLabel.setBounds(0, 300, 315, 50);
			disrateLabel.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15));
	
			double onePrice = product.getOnePersonPrice();
			JLabel onePriceLabel = new JLabel ("1인분당 " + Double.toString(onePrice)+"원");
			onePriceLabel.setBounds(0, 275, 315, 50);
			onePriceLabel.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 20));
			
			
			
			resultPanel.add(proImg); //상품 패널에 각 항목 추가(이미지, 이름 등)
			resultPanel.add(nameLabel);
			resultPanel.add(starLabel);
			resultPanel.add(priceLabel);
			resultPanel.add(onePriceLabel);
			resultPanel.add(disrateLabel);
			
			resultPanel.setBackground(Color.white); //패널 배경색 흰색으로 설정
			
			
			if(searchName != null && !(searchName.isEmpty()) && product.getName().contains(searchName)) {
				//검색어가 빈칸이 아니고, 검색어가 상품명에 속해있으면
				searchResultPanel.add(resultPanel);
			}
		}
		else {
			resultPanel.setSize(50, 50); //패널 사이즈 설정
			resultPanel.setBackground(Color.white);
			
			searchResultPanel.add(resultPanel);
			System.out.println("Product is null"); //디버깅 목적
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
}