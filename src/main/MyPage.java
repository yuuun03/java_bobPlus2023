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

import javax.swing.border.LineBorder;
import user.SignUp;
import user.UserInfoDetail;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Vector;

public class MyPage extends JFrame{
	JPanel mainPanel = new JPanel(); // 메인 패널(= 전체 패널)
	JPanel allergyPanel = new JPanel(); // 알레르기 정보 패널
	JPanel userInfoPanel = new JPanel(); // 회원 정보 패널
	JPanel line1Panel = new JPanel(); // 회원 정보 구분선 1 패널
	JPanel line2Panel = new JPanel(); // 회원 정보 구분선 2 패널
	JPanel line3Panel = new JPanel(); // 회원 정보 구분선 3 패널
	JPanel newOrderPanel = new JPanel(); // 최근 주문 내역 패널
	
	// 마이페이지 구현 내용
	public MyPage() {} // 기본 생성자
	public MyPage(UserInfoDetail myUser, Vector<Product> pList) { //유저 정보와 상품정보를 넘겨받은 마이페이지 생성
		//화면 기본 설정 - Start
		setTitle("밥심+"); //제목 설정
		//---아이콘 설정
		Toolkit kit = Toolkit.getDefaultToolkit(); //이미지 편집 위한 Toolkit 객체 생성
		Image img = kit.getImage("src/graphics/images/iconOnly.png"); //이미지 받아오기
		setIconImage(img); //받아온 이미지 아이콘으로 설정
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//종료 설정	 : 가위표 누르면 모든 프레임 종료.
			
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
		df.newHotGoods.addActionListener(new MyPageActionListener()); //지금 뜨는 상품
		df.weekTop10Goods.addActionListener(new MyPageActionListener()); //금주의 TOP 10
		df.checkAttendance.addActionListener(new MyPageActionListener()); //출석 체크
		df.couponPoint.addActionListener(new MyPageActionListener()); //쿠폰/포인트
		df.communityU.addActionListener(new MyPageActionListener()); //커뮤니티
		df.newMonthGoods.addActionListener(new MyPageActionListener());  //이달의 신상품

		// 폰트 설정
		// --- 회원 이름을 출력하는 버튼, 회원 등급을 출력하는 버튼, "배 송 중"라는 글자가 있는 버튼, 배송 중인 상품의 개수를 출력하는 버튼, 회원이 보유한 쿠폰 출력 버튼, 회원이 보유한 포인트 출력 버튼, 
		//						"내가 작성한 리뷰"라는 글자가 있는 버튼, 회원이 작성한 리뷰 개수 출력 버튼
		Font buttonFont = new Font("G마켓 산스 TTF BOLD", Font.CENTER_BASELINE, 30);
		// --- "최근 주문 내역" 출력 라벨
		Font buttonPlain = new Font("G마켓 산스 TTF Medium", Font.PLAIN, 30);
		// --- 왼쪽 사이드 각 목록 이름,  각 공인 알레르기 정보의 이름의 체크 박스, "최근 0건" 출력 라벨
		Font basic = new Font("G마켓 산스 TTF Medium", Font.PLAIN, 17);
		// --- "날짜" 출력 라벨, "상품정보" 출력 라벨, "배송 상태" 출력 라벨, "리뷰" 출력 라벨
		Font miniBasic = new Font("G마켓 산스 TTF Medium", Font.PLAIN, 15);
		
		// 왼쪽 사이드 리스트
		String [] leftSide = {" ", "    주문목록/배송조회", "    취소/반품/교환/환불 내역", "    영수증 조회/출력", " ", " ",
				"    찜한 상품", "    나의 장바구니", "    배송지 관리", " ", " ", "    문의하기", "    문의내역 확인", " ", " ", 
				"    회원정보 확인/수정", "    알레르기/조리도구 정보 수정"}; // 왼쪽 사이드에 들어갈 목록 이름
		JList<String> strList = new JList<String> (leftSide); // 왼쪽 사이드 리스트 생성
		strList.setBounds(30, 400, 250, 390); // 각 목록의 버튼 : 위치 (30, 400), 크기 250*390px
		strList.setBackground(new Color(151, 192, 48)); // 배경 색깔 설정 - 초록
		strList.setFont(basic); // 왼쪽 사이드에 들어갈 목록의 각 이름 폰트 설정
		strList.setForeground(Color.WHITE); // 글씨 색깔 설정 - 하양
		strList.setSelectionBackground(new Color(151, 192, 48)); // 각 목록 선택시 배경 색깔 설정 - 초록
		strList.setSelectionForeground(Color.WHITE); // 각 목록 선택시 글씨 색깔 설정 - 하양
		mainPanel.add(strList); // 메인 패널에 요소 추가
		
		// 액션
		strList.addListSelectionListener(new MyPageActionListener() { // 왼쪽 사이드에 들어갈 목록의 각 이름 선택 액션
			public void valueChanged(ListSelectionEvent e) {
				int selectedIndex = strList.getSelectedIndex(); // 선택된 이름의 인덱스 번호
				String selectedItem = (String)strList.getModel().getElementAt(selectedIndex); // 선택된 인덱의 이름을 불러옴
				JOptionPane.showMessageDialog(null, selectedItem + " 부분은 현재 기능 구현 중에 있습니다."); // 회원 안내문
			}
		});
		
		// 사용자 이름 및 등급 출력
		String[] rankList = {"전설밥알", "영웅밥알", "고급밥알", "중급밥알", "초급밥알", "입문밥알"}; // 회원 등급은 총 6개로 나뉨
		String name = "O O O"; // 로그인 전 O O O으로 이름 초기화
		int rank = 5; // 맨 밑 등급이 '입문밥알'이므로 로그인 전 5로 초기화
		
		name = myUser.getName(); // 회원 정보에서 회원 이름을 불러옴
		rank = myUser.getUserRank(); // 회원 정보에서 회원 등급을 불러옴
		if (name.equals("")) { // 회원 정보에 이름이 등록되어 있지 않을 경우
			name = "O O O"; //O O O으로 이름 설정
		}
		
		JButton userName = new JButton(name + "           님"); // 회원 이름을 출력하는 버튼
		JButton userRank = new JButton("등급:   " + rankList[rank]); // 회원 등급을 출력하는 버튼
		userName.setBounds(0, 10, 335, 80); // 회원 이름을 출력하는 버튼 : 위치 (0, 10), 크기 335*80px
		userRank.setBounds(0, 70, 335, 80); // 회원 등급을 출력하는 버튼 : 위치 (0, 70), 크기 335*80px
		
		userName.setFont(buttonFont); // 회원 이름을 출력하는 버튼 폰트 설정
		userRank.setFont(buttonFont); // 회원 등급을 출력하는 버튼 폰트 설정
		
		userName.setBorderPainted(false); userRank.setBorderPainted(false); // 회원 이름을 출력하는 버튼, 회원 등급을 출력하는 버튼 외각선 제거
		userName.setContentAreaFilled(false); userRank.setContentAreaFilled(false); // 회원 이름을 출력하는 버튼, 회원 등급을 출력하는 버튼 배경색 제거
		
		userInfoPanel.add(userName); // 회원 정보 패널에 회원 이름을 출력하는 버튼 추가
		userInfoPanel.add(userRank); // 회원 정보 패널에 회원 등급을 출력하는 버튼 추가
		
		// 배송 상태 및 배송 개수 정보 출력
		Vector<String> buyList = new Vector<String>(); // 구매한 배송 상품 목록
		//buyList = myUser.getBuyList(); 추후 구현
		int buyListNum = 0; // 배송 개수를 0으로 초기화
		
		if (buyList.isEmpty()) { } // 구매한 배송 상품 목록이 비어있다면 배송 개수를 그대로 0으로 설정
		else {buyListNum = buyList.size();} // 구매한 상품목록이 비어있지않다면 배송 개수에 구매한 배송 상품의 목록 크기를 부여해줌
		
		JButton delivery = new JButton("배 송 중"); // "배 송 중"라는 글자가 있는 버튼
		JButton deliveryNum = new JButton("     " + buyListNum+ " 개"); // 배송 중인 상품의 개수를 출력하는 버튼
		
		delivery.setBounds(375, 10, 250, 80); // "배 송 중"라는 글자가 있는 버튼 : 위치 (375, 10), 크기 250*80px
		deliveryNum.setBounds(375, 70, 250, 80); // 배송 중인 상품의 개수를 출력하는 버튼 : 위치 (375, 70), 크기 250*80px
		
		delivery.setFont(buttonFont); // "배 송 중"라는 글자가 있는 버튼 폰트 설정
		deliveryNum.setFont(buttonFont); // 배송 중인 상품의 개수를 출력하는 버튼 폰트 설정
		
		delivery.setBorderPainted(false); deliveryNum.setBorderPainted(false); // "배 송 중"라는 글자가 있는 버튼, 배송 중인 상품의 개수를 출력하는 버튼 외각선 제거
		delivery.setContentAreaFilled(false); deliveryNum.setContentAreaFilled(false); // "배 송 중"라는 글자가 있는 버튼, 배송 중인 상품의 개수를 출력하는 버튼 배경색 제거
		
		userInfoPanel.add(delivery); // 회원 정보 패널에 "배 송 중"라는 글자가 있는 버튼 추가
		userInfoPanel.add(deliveryNum); // 회원 정보 패널에 배송 중인 상품의 개수를 출력하는 버튼 추가
		
		// 쿠폰 및 포인트 정보 출력
		// 사용자 정보로 쿠폰 및 포인트를  받아오는 것은 추후 구현
		JButton userCoupon = new JButton("보유 쿠폰:                 0 장"); // 회원이 보유한 쿠폰 출력 버튼
		JButton userPoint = new JButton("보유 포인트:              0 P"); // 회원이 보유한 포인트 출력 버튼
		
		userCoupon.setBounds(645, 10, 493, 79); // 회원이 보유한 쿠폰 출력 버튼 : 위치 (645, 10), 크기 493*79px
		userPoint.setBounds(645, 70, 493, 79); // 회원이 보유한 포인트 출력 버튼 : 위치 (645, 70), 크기 493*79px
		
		userCoupon.setFont(buttonFont); // 회원이 보유한 쿠폰 출력 버튼 폰트 설정
		userPoint.setFont(buttonFont); // 회원이 보유한 포인트 출력 버튼 폰트 설정
		
		userCoupon.setBorderPainted(false); userPoint.setBorderPainted(false); // 회원이 보유한 쿠폰 출력 버튼, 회원이 보유한 버튼 출력 포인트 외각선 제거
		userCoupon.setContentAreaFilled(false); userPoint.setContentAreaFilled(false); // 회원이 보유한 쿠폰 출력 버튼, 회원이 보유한 포인트 출력 버튼 배경색 제거
		
		userInfoPanel.add(userCoupon); // 회원 정보 패널에 회원이 보유한 쿠폰 출력 버튼 추가
		userInfoPanel.add(userPoint); // 회원 정보 패널에 회원이 보유한 포인트 출력 버튼 추가
		
		// 내가 작성한 리뷰 및 개수 출력
		// 사용자 정보로 쿠폰 및 포인트를  받아오는 것은 추후 구현
		JButton userReview = new JButton("내가 작성한 리뷰"); // "내가 작성한 리뷰"라는 글자가 있는 버튼
		JButton userReviewNum = new JButton("                  0 개"); // 회원이 작성한 리뷰 개수 출력 버튼
		
		userReview.setBounds(1175, 10, 330, 80); // "내가 작성한 리뷰"라는 글자가 있는 버튼 : 위치 (1175, 10), 크기 330*80px
		userReviewNum.setBounds(1175, 70, 330, 80); // 회원이 작성한 리뷰 개수 출력 버튼 : 위치 (1175, 70), 크기 330*80px
		
		userReview.setFont(buttonFont); // "내가 작성한 리뷰"라는 글자가 있는 버튼 폰트 설정
		userReviewNum.setFont(buttonFont); // 회원이 작성한 리뷰 개수 출력 버튼 폰트 설정
		
		userReview.setBorderPainted(false); userReviewNum.setBorderPainted(false); // "내가 작성한 리뷰"라는 글자가 있는 버튼, 회원이 작성한 리뷰 개수 출력 버튼 외각선 제거
		userReview.setContentAreaFilled(false); userReviewNum.setContentAreaFilled(false); // "내가 작성한 리뷰"라는 글자가 있는 버튼, 회원이 작성한 리뷰 개수 출력 버튼 배경색 제거
		
		userInfoPanel.add(userReview); // 회원 정보 패널에 "내가 작성한 리뷰"라는 글자가 있는 버튼 추가
		userInfoPanel.add(userReviewNum); // 회원 정보 패널에 회원이 작성한 리뷰 개수 출력 버튼 추가
		
		//알러지 정보 출력 
		Image allergy = new ImageIcon("src/graphics/images/allegy.png").getImage(); // 알레르기 아이콘 가져옴
		allergy = allergy.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // 알레르기 아이콘 사이즈 재조정
		ImageIcon iconAllergy = new ImageIcon(allergy); 
		JLabel mainAll = new JLabel(iconAllergy);
		mainAll.setSize(100,100); // 알레르기 아이콘 크기: 100*100px
		mainAll.setLocation(20, 20); // 알레르기 아이콘 위치: (20, 20)
		allergyPanel.add(mainAll); // 알레르기 정보 패널에 알레르기 아이콘 추가
		
		JCheckBox infoAllergy[] = new JCheckBox[22]; // 알레르기 정보 체크할 체크박스
		String alName[] = {"가금류","게","고등어","굴","닭고기","대두","돼지고기","땅콩","메밀","밀","복숭아","새우","쇠고기",
				"아황산포함","오징어","우유","잣","전복","조개류","토마토","호두","홍합"};  // 공인 알레르기 정보들 모음
		
		Vector<String> userAllergy = myUser.getAllergy(); // 회원이 선택한 알레르기
		
		int w = 0; int v = 0; // 세부 위치 조정 위한 변수
		for(int i = 0; i < alName.length; i++) { // 공인 알레르기 정보만큼 반복
			boolean isUserAllergy = false; // 알레르기가 있다고 체크했는지 확인; 체크박스에 체크 표시가 안됨
			if (!userAllergy.isEmpty()) { // 회원이 선택한 알레르기가 있는 경우
				if (userAllergy.contains(alName[i])) { // 회원이 선택한 알레르기 중 공인 알레르기가 포함되어 있는 경우 
					isUserAllergy = true; // 체크 박스에 체크 표시가 됨
				}
			}
			else {isUserAllergy = false;} // 회원이 선택한 알레르기가 없는 경우 체크박스에 체크 표시가 안됨
			infoAllergy[i] = (new JCheckBox(alName[i], isUserAllergy)); // 각 공인 알레르기 정보의 이름의 체크 박스 생성
			allergyPanel.add(infoAllergy[i]); // 알레르기 정보 패널에 공인 알레르기 정보들에서 각 알레르기 이름의 체크 박스 추가
			
			//크기 및 위치 조정
			infoAllergy[i].setSize(40 + 20 * alName[i].length(), 40);
			infoAllergy[i].setLocation( 140 + 147 * w, 10+ 35 * v);
			
			// 체크 박스 배경색 제거 및 폰트 설정
			infoAllergy[i].setContentAreaFilled(false);
			infoAllergy[i].setFont(basic);
			
			w++;
			if (w == 7) {w = 0; v++;} // 7개의 체크박스가 들어가면 줄바꿈
		}
		
		//이벤트 처리
		for (int i = 0; i < alName.length; i++) { // 공인 알레르기 정보만큼 반복
			infoAllergy[i].addItemListener(new MyPageActionListener() { // 회원이 클릭한 버튼 알아내기
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED) { // 회원이 체크 박스에 체크 표시했다면
						JCheckBox now = (JCheckBox)(e.getSource()); // 현재 체크 박스에 표시된 알레르기 이름 불러오기
						userAllergy.add(now.getText()); // 회원이 선택한 알레르기 정보에 추가하기 
					}
					else if(e.getStateChange() == ItemEvent.DESELECTED) { // 회원이 체크 박스에 체크를 해제했다면
						JCheckBox now = (JCheckBox)(e.getSource()); // 현재 체크 박스에 체크가 해제된 알레르기 이름 불러오기
						userAllergy.remove(now.getText()); // 회원이 선택한 알레르기 정보에서 삭제하기
					}
					myUser.setAllergy(userAllergy); // 회원 정보에서 알레르기 정보 수정하기
				}
			});
		}
		
		// 최근 주문 내역
		JLabel newOrder = new JLabel("최근 주문내역"); // "최근 주문 내역" 출력 라벨
		JLabel newOrderNum = new JLabel("최근 0건"); // 추후 상세 구현
		JLabel newOrderDay = new JLabel("날짜"); // "날짜" 출력 라벨
		JLabel newOrderInfo = new JLabel("상품정보"); // "상품정보" 출력 라벨
		JLabel newOrderDeli = new JLabel("배송 상태"); // "배송 상태" 출력 라벨
		JLabel newOrderReview = new JLabel("리뷰"); // "리뷰" 출력 라벨
		
		
		newOrder.setBounds(300,580, 300, 70); // "최근 주문 내역" 출력 라벨 : 위치 (300, 580), 크기 300*70px
		newOrderNum.setBounds(500,584, 200, 70); // "최근 0건" 출력 라벨 : 위치 (500, 584), 크기 200*70px
		newOrderDay.setBounds(385,620, 200, 50); // "날짜" 출력 라벨 : 위치 (385, 620), 크기 200*50px
		newOrderInfo.setBounds(675,620, 200, 50); // "상품정보" 출력 라벨 : 위치 (675, 620), 크기 200*50px
		newOrderDeli.setBounds(995,620, 200, 50); // "배송 상태" 출력 라벨 : 위치 (995, 620), 크기 200*50px
		newOrderReview.setBounds(1300,620, 200, 50); // "리뷰" 출력 라벨 : 위치 (1300, 620), 크기 200*50px
		
		newOrder.setFont(buttonPlain); // "최근 주문 내역" 출력 라벨 폰트 설정
		newOrderNum.setFont(basic); // "최근 0건" 출력 라벨 폰트 설정
		newOrderDay.setFont(miniBasic); // "날짜" 출력 라벨 폰트 설정
		newOrderInfo.setFont(miniBasic); // "상품정보" 출력 라벨 폰트 설정
		newOrderDeli.setFont(miniBasic); // "배송 상태" 출력 라벨 폰트 설정
		newOrderReview.setFont(miniBasic); // "리뷰" 출력 라벨 폰트 설정
		
		mainPanel.add(newOrder); // 메인 패널에 "최근 주문 내역" 출력 라벨 추가
		mainPanel.add(newOrderNum); // 메인 패널에 "최근 0건" 출력 라벨 추가
		mainPanel.add(newOrderDay); // 메인 패널에 "날짜" 출력 라벨 추가
		mainPanel.add(newOrderInfo); // 메인 패널에 "상품정보" 출력 라벨 추가
		mainPanel.add(newOrderDeli); // 메인 패널에 "배송 상태" 출력 라벨 추가
		mainPanel.add(newOrderReview); // 메인 패널에 "리뷰" 출력 라벨 추가
		
		
		//화면 기본 설정 - End
		setSize(1920, 1080); //윈도우 사이즈 1920, 1080 고정.
		
		mainPanel.setBounds(0,0, 1920,1080); // 메인 패널 : 위치 (0, 0), 크기 1920*1080px
		allergyPanel.setBounds(300 ,420, 1230, 160); // 알레르기 정보 패널 : 위치 (300, 420), 크기 1230*160px
		userInfoPanel.setBounds(0 ,240, 1920, 160); // 회원 정보 패널 : 위치 (0, 240), 크기 1920*160px
		newOrderPanel.setBounds(300 ,630, 1250, 30); // 최근 주문 내역 패널 : 위치 (300, 630), 크기 1250*30px
		line1Panel.setBounds(360 ,240, 2, 160); // 회원 정보 구분선 1 패널 : 위치 (360, 240), 크기 2*160px
		line2Panel.setBounds(640 ,240, 2, 160); // 회원 정보 구분선 2 패널 : 위치 (640, 240), 크기 2*160px
		line3Panel.setBounds(1140 ,240, 2, 160); // 회원 정보 구분선 3 : 위치 (1140, 240), 크기 2*160px
		
		mainPanel.setBackground(Color.white); // 배경 색깔 설정 - 하양
		allergyPanel.setBackground(new Color(200, 228, 137)); // 배경 색깔 설정 - 연두
		userInfoPanel.setBackground(new Color(200, 228, 137)); // 배경 색깔 설정 - 연두
		newOrderPanel.setBackground(new Color(200, 228, 137)); // 배경 색깔 설정 - 연두
		line1Panel.setBackground(new Color(151, 192, 48)); // 배경 색깔 설정 - 초록
		line2Panel.setBackground(new Color(151, 192, 48)); // 배경 색깔 설정 - 초록
		line3Panel.setBackground(new Color(151, 192, 48)); // 배경 색깔 설정 - 초록
		
		mainPanel.add(line1Panel); // 메인 패널에 회원 정보 구분선 1 패널 추가
		mainPanel.add(line2Panel); // 메인 패널에 회원 정보 구분선 2 패널 추가
		mainPanel.add(line3Panel); // 메인 패널에회원 정보 구분선 3 패널 추가
		mainPanel.add(newOrderPanel); // 메인 패널에 최근 주문 내역 패널 추가
		mainPanel.add(userInfoPanel); // 메인 패널에 회원 정보 패널 추가
		mainPanel.add(allergyPanel); // 메인 패널에 알레르기 정보 패널 추가
		add(mainPanel); // 프레임에 메인 패널 추가
		
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
					
			case "오늘 뭐 먹지?": 
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다."); // 회원 알림
				break;

			case "지금 할인 중": 
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다."); // 회원 알림
				break;

			case "인기 급상승": 
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다."); // 회원 알림
				break;
					
			default : 
				break;
			}
		}
		// 체크박스 선택 시
		public void itemStateChanged(ItemEvent e) {}
		public void valueChanged(ListSelectionEvent e) {}
	}
}