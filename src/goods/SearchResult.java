/*
 * 컴퓨터공학부 2022136067 양희정 - 조리도구/알러지 필터링, 상품 패널 출력
 * 컴퓨터공학부 2022136117 조윤서 - 상품 패널의 세부 사항 추가
 */

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
		//화면 기본 설정 - Start
		setTitle("밥심+"); //제목 설정
		//---아이콘 설정
		Toolkit kit = Toolkit.getDefaultToolkit(); //이미지 편집 위한 Toolkit 객체 생성
		Image img = kit.getImage("src/graphics/images/iconOnly.png"); //이미지 받아오기
		setIconImage(img); //받아온 이미지 아이콘으로 설정
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//종료 설정	 : 가위표 누르면 모든 프레임 종료.
				
		
		setLayout(null);
		mainPanel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
		
		//로고, 검색창, 위쪽 레이블, 로그인, 장바구니 등 기본 패널 추가 -> MainFrame.java 복붙
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
		
		//상품 출력
		int emptyCnt = 0; //얼마만큼 상품이 비어있는지 체크.
		int check = 0; //패널에 대한 인덱스. 상품이 대응하는 패널을 채우지 않으면 그 다음 상품이 이전 패널을 채움.
		for(int i = 0; i < pBundle.length; i++, check++) { 
			//1. 상품에 대한 인덱스 i가 상품 전체 리스트의 크기를 넘지 않고
			//2. 상품명에 검색한 내용이 포함되어 있으면 상품 보여줌
			if(i < pList.size() && pList.get(i).getName().contains(pName)) {
				displayProduct(pBundle[check], pList.get(i), myUser,  pList);
			}
			else { //아닐 시 비어있는 상품으로 처리
				emptyCnt++; //비어있는 상품 수 증가
				check--; //못 채운 패널은 재사용
			}
		}
		
		//비어있는 부분을 메꾸기 위한 부분. 흰색으로 패널 부분을 메꿔서 출력
		for(int i = 0; i < emptyCnt; i++) {
			displayProduct(pBundle[8-emptyCnt + i], null, myUser,  pList);
		}
		
		//필터 패널 액션 추가(필터링)
		//--- 조리기구
		Vector<Integer> hiddenIdxCU = new Vector<Integer>(); //선택한 조리기구 정보를 담을 Vector
		for(int i=0; i< filter.cookUtensils.length;i++) { //조리기구 필터링을 위한 반복문
			filter.cookUtensils[i].addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent e) {
					Object source = e.getItemSelectable();
					if (source instanceof JCheckBox) {
						JCheckBox checkBox = (JCheckBox) source;
						
				    // 선택 상태에 따라 해당하는 조리도구 Vector를 업데이트
					if (checkBox.isSelected()) { //체크박스가 선택된 경우
						if (Arrays.asList(filter.cookUtensils).contains(checkBox)) {
							filter.filterCU.add(checkBox.getText()); //선택된 항목 Vector에 추가
							for(int i=0;i<8;i++) { //페이지에 표시되는 상품 개수 8개
								if (pList.get(i).getCookingUtensils().contains(checkBox.getText())) {
									//조리도구가 상품 정보에 있는 조리도구일 경우
									continue; //다음 상품 비교로 넘어가기
								}
								else {
									hideProductionAtIndex(i); //일치하지 않으면 숨기기
									hiddenIdxCU.add(i);
								}
								}
							}
				            }
					else { //체크박스를 선택하지 않은 경우
						filter.filterCU.remove(checkBox.getText()); //Vector 에서 삭제
				        for(int i:hiddenIdxCU) {
				        	pBundle[i].setVisible(true);
				            }
				        }
					}
				}
			});
		}
		
		//--- 알러지
		Vector<Integer> hiddenIdxAL = new Vector<Integer>(); //선택한 알러지 정보를 담을 Vector
		for(int i=0;i<filter.alName.length;i++) { //알러지 필터링을 위한 반복문
			filter.infoAllergy[i].addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent e) {
					Object source = e.getItemSelectable();
					if (source instanceof JCheckBox) {
						JCheckBox checkBox = (JCheckBox) source;
						
				    // 선택 상태에 따라 해당하는 알러지 Vector를 업데이트
					if (checkBox.isSelected()) { //체크박스가 선택된 경우
						if (Arrays.asList(filter.infoAllergy).contains(checkBox)) {
				            	filter.filterAl.add(checkBox.getText()); //선택된 항목 Vector에 추가
				            	for(int i=0;i<8;i++) { //페이지에 표시되는 상품 개수 8개
			        				if(pList.get(i).getContainAllergy().contains(checkBox.getText())) {
			        					hideProductionAtIndex(i); //상품이 알러지 항목을 가지고 있으면(일치하면) 숨기기
			        					hiddenIdxAL.add(i);
			        				}
				        		}
				        	}
				         }
				    
					else { //체크박스를 선택하지 않은 경우
				          filter.filterAl.remove(checkBox.getText()); //Vector 에서 삭제
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
		if(index>=0 && index < 8) { //페이지에 표시되는 상품이 8개이므로 index는 0~7 값
			switch (index) { //index번째 상품을 숨기기 위해 switch 문 사용
	        case 0:
	            product1.setVisible(false); //index가 0이면 첫번째 상품 숨기기
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
	
	//상품 패널을 표시하는 함수 - 표시할 패널과 상품 정보, 검색
	private void displayProduct(JPanel resultPanel, Product product, UserInfoDetail myUser, Vector<Product> pList) { 
		if(product != null) { //상품 정보가 null이 아니면
			resultPanel.setLayout(null); //결과 패널 배치관리자 없음 : 개발자 자유 배치		
			
			//이미지 설정
			Image img = new ImageIcon(product.getImage()).getImage(); //상품 사진 가져오기
			img = img.getScaledInstance(400, 150, Image.SCALE_SMOOTH); //크기 조정 400*150px
			ImageIcon productIcon = new ImageIcon(img);
			JLabel proImg = new JLabel(productIcon); //이미지 라벨로 추가하기
			
			proImg.setBounds(0, 0, 400, 150); //사진 사이즈, 위치 설정
			
			//라벨 설정
			JLabel nameLabel = new JLabel(product.getName()); //상품 명 가져오기
			
			nameLabel.setBounds(0, 155, 315, 50); //크기, 위치 설정
			nameLabel.setFont(new Font("G마켓 산스 TTF BOLD", Font.CENTER_BASELINE, 40)); //폰트 설정
			nameLabel.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) { //마우스 클릭 이벤트 설정
					new ShowOneGoods(myUser, pList, product); //단일 상품 페이지로 넘어감
					dispose(); //현재 페이지 닫음
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
			
			//---원가
			JLabel priceLabel = new JLabel(Double.toString(price *((100-disrate)/100))+" 원");
			priceLabel.setBounds(0, 235, 315, 50);
			priceLabel.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 20)); //폰트 설정
			
			//--할인가
			JLabel disrateLabel = new JLabel("할인율 : " + Double.toString(disrate)+"%");
			disrateLabel.setBounds(0, 300, 315, 50);
			disrateLabel.setFont(new Font("G마켓 산스 TTF Light", Font.PLAIN, 15)); //폰트 설정
	
			//---1인분 가격
			double onePrice = product.getOnePersonPrice();
			JLabel onePriceLabel = new JLabel ("1인분당 " + Double.toString(onePrice)+"원");
			onePriceLabel.setBounds(0, 275, 315, 50);
			onePriceLabel.setFont(new Font("G마켓 산스 TTF Medium", Font.PLAIN, 20)); //폰트 설정
			
			resultPanel.add(proImg); //상품 패널에 각 항목 추가(이미지, 이름 등)
			resultPanel.add(nameLabel);
			resultPanel.add(starLabel);
			resultPanel.add(priceLabel);
			resultPanel.add(onePriceLabel);
			resultPanel.add(disrateLabel);
			
			resultPanel.setBackground(Color.white); //패널 배경색 흰색으로 설정
		}
		else {
			resultPanel.setSize(50, 50); //패널 사이즈 설정
			resultPanel.setBackground(Color.white); //패널 배경색 흰 색으로 설정
			searchResultPanel.add(resultPanel);
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
				//사용자 안내문
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
					
			case "쿠폰/포인트": 
				//사용자 안내문
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
						
			case "커뮤니티": 
				//사용자 안내문
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
					
			case "이달의 신상품": 
				//사용자 안내문
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
					
			case "오늘 뭐 먹지?": 
				//사용자 안내문
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;

			case "지금 할인 중": 
				//사용자 안내문
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;

			case "인기 급상승": 
				//사용자 안내문
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
					
			default : //장바구니 클릭 시 
				break;
			}
		}
	}
}