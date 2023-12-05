/*
 * 컴퓨터공학부 2022136067 양희정 - 알러지 필터링 기능 구현, 상품 정보 출력 함수 구현
 * 컴퓨터공학부 2022136117 조윤서 - 마우스 클릭, 장바구니 담기, 페이지 진동, 화면 배치
 * */

package goods;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

import admin.*;
import main.*;
import goods.*;
import user.*;

public class ShowOneGoods extends JFrame implements Runnable {
	JPanel oneGoodsPannel = new JPanel(); //단일 상품 굿즈 패널
	private Thread th; //진동위한 스레드 추가

	public ShowOneGoods() {}
	public ShowOneGoods(UserInfoDetail myUser, Vector<Product> pList, Product p) {
		//화면 기본 설정 - Start
		setTitle("밥심+"); //제목 설정
		//---아이콘 설정
		Toolkit kit = Toolkit.getDefaultToolkit(); //이미지 편집 위한 Toolkit 객체 생성
		Image img = kit.getImage("src/graphics/images/iconOnly.png"); //이미지 받아오기
		setIconImage(img); //받아온 이미지 아이콘으로 설정
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//종료 설정	 : 가위표 누르면 모든 프레임 종료.
		
		setLayout(null); //프레임 배치관리자 없음 : 개발자 자유 배치
		oneGoodsPannel.setLayout(null); //단일 상품 패널 배치 관리자 없음 : 사용자 자유 배치
				
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
		
		//단일 상품 보기 상세 파트 구현
		for(String CU:myUser.getAllergy()) { //알러지 정보 필터링
			if(p.getContainAllergy().contains(CU)) { //알러지가 포함된 제품을 클릭했다면 경고 아이콘 출력
				Image warn = new ImageIcon("src/graphics/images/warning.png").getImage(); //경고 아이콘 가져옴
				warn = warn.getScaledInstance(100, 100, Image.SCALE_SMOOTH); //경고 아이콘 사이즈 재조정
				ImageIcon iconWarn = new ImageIcon(warn); 
				JLabel warnAl = new JLabel(iconWarn);
				warnAl.setSize(100,100); //경고 아이콘 사이즈 : 100 * 100px
				warnAl.setLocation(1370, 333); //경고 아이콘 위치 (1370, 333)
				oneGoodsPannel.add(warnAl);
				
				//알러지 있는 상품 페이지일 경우 프레임 진동
				this.addMouseListener(new MouseAdapter() { //마우스로 프레임 클릭시 진동 종료
					public void mousePressed(MouseEvent e) {
						if(!th.isAlive()) {return;} //진동하고 있지 않으면 작동하지 않음
						else {th.interrupt();} //진동하고 있을시 interrupt줘서 진동 멈춤
					}				
				});
				
				th = new Thread(this); //프레임에 진동 기능 추가
				th.start(); //진동 시작
				
				break; //반복문 탈출
			}
		}
		
		//상품 정보 출력
		displayOneProduct(p); //해당 상품에 대한 상품 정보 출력
		
		//버튼
		//--- 버튼 공통 폰트 지정
		Font bFont = new Font("G마켓 산스 TTF BOLD", Font.CENTER_BASELINE, 30);
		
		//--- 상품 상세 정보 버튼
		JButton productDetail = new JButton("상품 상세 정보");
		productDetail.addActionListener(new OneProductActionListener()); //액션 지정 : OneProductActionListener
		productDetail.setBounds(580, 453, 300, 90); //위치 (580, 453), 크기 300*90px
		productDetail.setFont(bFont); //폰트 설정
		productDetail.setBackground(new Color(255, 206, 90)); //색깔 설정 : 노란색
		
		//--- 리뷰 버튼
		JButton showReview = new JButton("리뷰");
		showReview.addActionListener(new OneProductActionListener()); //액션 지정 : OneProductActionListener
		showReview.setBounds(890, 453, 170, 90); //위치 (890, 453), 크기 170*90px
		showReview.setFont(bFont); //폰트 설정
		showReview.setBackground(new Color(255, 206, 90)); //색깔 설정 : 노란색
		
		//--- 장바구니 담기 버튼
		JButton inCart = new JButton("장바구니");
		inCart.addActionListener(new ActionListener() { //장바구니 버튼 담기 액션
			public void actionPerformed(ActionEvent e) { //버튼 클릭시
				myUser.cartList.add(p); //장바구니에 담기
				JOptionPane.showMessageDialog(null, "장바구니에 담겼습니다."); //안내 메시지
			}
		});
		inCart.setBounds(1070, 453, 220, 90);//위치 (1070, 453), 크기 220*90px
		inCart.setFont(bFont); //폰트 설정
		inCart.setBackground(new Color(200, 228, 137)); //색깔 설정 : 연두색
		
		//--- 구매 버튼
		JButton goBuy = new JButton("구매");
		goBuy.addActionListener(new OneProductActionListener()); //액션 지정 : OneProductActionListener
		goBuy.setBounds(1300, 453, 170, 90); //위치 (1300, 453), 크기 170*90px
		goBuy.setFont(bFont); //폰트 설정
		goBuy.setBackground(new Color(243, 138, 117)); //색깔 지정 : 붉은색
		
		//패널에 버튼들 추가
		oneGoodsPannel.add(productDetail);
		oneGoodsPannel.add(showReview);
		oneGoodsPannel.add(inCart);
		oneGoodsPannel.add(goBuy);
		
		//단일 상품 패널 디자인 설정
		oneGoodsPannel.setBackground(Color.white);
		oneGoodsPannel.setBounds(0, 203, 1920, 877); //위치 (0, 203), 크기 1920*877px
		
		add(oneGoodsPannel); //프레임에 단일 상품 패널 추가
		setSize(1920, 1080); //프레임 크기 1920*1080
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // 프레임을 보이도록 설정
	}
	
	private void displayOneProduct(Product product) { //상품 패널을 표시하는 함수
		if(product != null) { //상품 정보가 null이 아니면	
			//이미지 설정
			Image img = new ImageIcon(product.getImage()).getImage(); //이미지 가져오기
			img = img.getScaledInstance(520, 520, Image.SCALE_SMOOTH); //이미지 크기 조절 520*520px
			ImageIcon productIcon = new ImageIcon(img);
			JLabel proImg = new JLabel(productIcon); //상품 이미지 라벨로 출력
			
			proImg.setBounds(40, 30, 520, 520); //위치 (40, 30), 크기 520*520px
			
			//이름 설정
			JLabel nameLabel = new JLabel(product.getName());
			nameLabel.setBounds(580, 25, 1000, 100); //위치 (580, 25), 크기 1000*100px
			nameLabel.setFont(new Font("G마켓 산스 TTF BOLD", Font.CENTER_BASELINE, 80)); //폰트 설정
			
			//할인율 설정
			double disrate = product.getProductDisRate(); //할인율 가져옴
			JLabel disrateLabel = new JLabel(Double.toString(disrate)+"%"); //출력 위한 문자열 변환
			disrateLabel.setBounds(580, 115, 1000, 100); //위치 (580, 115), 크기 1000*100px
			disrateLabel.setFont(new Font("G마켓 산스 TTF BOLD", Font.CENTER_BASELINE, 45)); //폰트 설정
			disrateLabel.setForeground(Color.red); //색깔 설정 : 빨강
			
			//가격 설정
			int price = product.getPrice(); //원가 가져오기
			double disPrice = price *((100-disrate)/100); //할인 적용한 가격 계싼
			JLabel disPriceLabel = new JLabel(Double.toString(disPrice) + " 원"); //출력 위한 문자열 변환
			disPriceLabel.setBounds(750, 115, 1000, 100); //위치 (750, 115), 크기 1000*100px
			disPriceLabel.setFont(new Font("G마켓 산스 TTF BOLD", Font.CENTER_BASELINE, 45)); //폰트 설정
			
			JLabel priceLabel = new JLabel("원가 " + Integer.toString(price) + "원"); //원가 출력
			// . . . 할인된 가격의 크기에 따라 원가의 위치도 조정되어야 하므로 x좌표를 (1000 + (Double.toString(disPrice) + " 원").length())로 지정
			priceLabel.setBounds( (1000 + (Double.toString(disPrice) + " 원").length()) , 125, 1000, 100); //y좌표 125, 크기 1000*100px
			priceLabel.setFont(new Font("G마켓 산스 TTF Light", Font.CENTER_BASELINE, 15)); //폰트 설정
			priceLabel.setForeground(Color.gray); //폰트 색깔 회색
			
			//1인분당 가격 설정
			double onePrice = product.getOnePersonPrice(); //1인분당 가격 가져오기
			JLabel onePriceLabel = new JLabel ("1인분당 : " + Double.toString(onePrice)+"원"); //출력 위한 문자열 지정
			onePriceLabel.setBounds(580, 170, 1000, 100); //위치 (580, 170), 크기 1000*100px
			onePriceLabel.setFont(new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 30)); //폰트 설정
			
			//별점 설정
			double star = product.getProductStar();
			JLabel starLabel = new JLabel ("별점:" + Double.toString(star)); //출력 위한 문자열 변환
			starLabel.setBounds(580, 220, 1000, 100); //위치 (580, 220), 크기 1000*100px
			starLabel.setFont(new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 30)); //폰트 설정
			
			//조리기구 정보
			Vector<String> cuBundle = product.getCookingUtensils(); //해당 상품의 조리기구 정보 가져옴
			
			String uList = ""; //출력을 위한 빈 문자열
			for(int i = 0 ; i < cuBundle.size(); i++ ) {
				uList += cuBundle.get(i); //조리기구 하나씩 가져와 출력 문자열에 붙임
				if(i == cuBundle.size()-1) {uList += " ";} //마지막 조리기구면 공백으로 띄우기
				else {uList +=", ";} //다음 조리기구가 있다면 콤마로 구분
			}
			JLabel cUtensilLabel = new JLabel("조리 기구 : " + uList); //조리기구 출력
			cUtensilLabel.setBounds(580, 270, 1000, 100); //위치 (580, 270), 크기 1000*100px
			cUtensilLabel.setFont(new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 30)); //폰트 설정
			
			//알러지 정보
            Vector<String> alBundle = product.getContainAllergy(); //해당 상품의 알러지 정보 가져옴
			
			String alList = ""; //출력을 위한 빈 문자열
			for(int i = 0 ; i < alBundle.size(); i++ ) {
				alList += alBundle.get(i); //알러지 정보 하나씩 가져와 출력 문자열에 붙임
				if(i == alBundle.size()-1) {alList += " ";} //마지막 알러지 정보면 공백으로 띄우기
				else {alList +=", ";} //다음 알러지 정보가 있다면 콤마로 구분
			}
			
			JLabel allergyLabel = new JLabel("알레르기 유발 식재료 : " + alList); //알러지 출력
			allergyLabel.setBounds(580, 320, 1000, 100); //위치 (580, 320), 크기 1000*100px
			allergyLabel.setFont(new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 30)); //폰트 설정
			
			//단일 상품 패널에 정보들 추가
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
			JLabel noProduct = new JLabel("상품이 조회되지 않습니다."); //안내문 출력
			oneGoodsPannel.add(noProduct); //단일 상품 패널에 아무것도 아닌 것 추가
		}
	}
	
	//이벤트 처리 클래스들
	@Override
	//--- 진동하는 스레드 : 알러지 있는 경우
	public void run() { 
		Random r = new Random(); //난수 생성
		while(true) {
			try {
				Thread.sleep(20); //20ms 잠자기
			}
			catch(InterruptedException e) {
				return; //인터럽트 들어오면 진동 종료
			}
			
			int x = getX() + r.nextInt() % 5; //x축으로 이동
			int y = getY() + r.nextInt() % 5; //y축으로 이동
 			setLocation(x, y); //새로운 위치 지정
		}
	}
	
	//--- 공통패널 버튼 액션 지정
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
				//사용자 안내문 출력
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
				
			case "쿠폰/포인트": 
				//사용자 안내문 출력
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
					
			case "커뮤니티": 
				//사용자 안내문 출력
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
				
			case "이달의 신상품": 
				//사용자 안내문 출력
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
				
			case "오늘 뭐 먹지?": 
				//사용자 안내문 출력
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;

			case "지금 할인 중": 
				//사용자 안내문 출력
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;

			case "인기 급상승": 
				//사용자 안내문 출력
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
				
			default : //장바구니 클릭 시 
				break;
			}
		}
	}
	
	//--- 상품 페이지 내 버튼들
	class OneProductActionListener implements ActionListener{
		//Action : 버튼 클릭 
		public void actionPerformed(ActionEvent e) {
			JButton bRefer = (JButton)e.getSource(); //사용자가 클릭한 버튼 알아내기
			
			//버튼 종류마다 이벤트 다르게 지정
			switch(bRefer.getText()) {
			case "상품 상세 정보":
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다."); //사용자 안내문 출력
				break;
					
			case "리뷰": 
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다."); //사용자 안내문 출력
				break;
						
			case "구매": 
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다."); //사용자 안내문 출력
				break;
			
			default :
				break;
			}
		}
	}
}