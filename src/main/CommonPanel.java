package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CommonPanel extends JFrame{ //공통패널(검색창, 로고, 로그인 버튼 등... 상단 기능들)
	//기초 설정
	public JPanel commonPanel = new JPanel(); //공통 패널 객체 생성
	public JButton login; public JButton my; public JButton cart; //로그인, 마이페이지, 장바구니
	
	//---위쪽 버튼
	public JButton newHotGoods; public JButton weekTop10Goods; public JButton checkAttendance;
	public JButton couponPoint; public JButton communityU; public JButton newMonthGoods;
	
	//로고
	public JLabel mainIL;
	
	//검색창
	public JTextField search;
	
	//메인 페이지 프레임 구현 내용
	public CommonPanel() { //기본 생성자
		//화면 버튼 설정
		commonPanel.setLayout(null); //공통패널 배치관리자 없음 : 개발자 자유 배치
			
		//로고 설정
		Image ilImg = new ImageIcon("src/graphics/images/iconAndLogo.png").getImage(); //이미지 가져오기
		ilImg = ilImg.getScaledInstance(230, 120, Image.SCALE_SMOOTH); //로고 배치될 크기에 맞게 크기 재편집
		ImageIcon iconAndLogo = new ImageIcon(ilImg); //편집된 아이콘으로 이미지 아이콘 생성
		mainIL = new JLabel(iconAndLogo); //이미지 아이콘으로 라벨 생성
		mainIL.setSize(230,120); //크기 지정
		mainIL.setLocation(10, 10); //위치 지정
		commonPanel.add(mainIL); //공통 패널에 추가
		
		//검색창 설정
		JLabel searchTitle = new JLabel("검색 : "); //검색 안내 라벨
		searchTitle.setFont(new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 20));
		search = new JTextField(); //한줄 입력창 생성
			
		//--- 위치 및 크기 지정
		searchTitle.setBounds(260, 50, 100, 50); //위치 (260, 50), 크기 100*50px
		search.setBounds(320, 50, 900, 50); //위치 (260, 50), 크기 100*50px
		
		//--- 공통 패널에 추가
		commonPanel.add(searchTitle); //검색 표기 패널 위에 추가
		commonPanel.add(search); //검색창 패널 위에 추가
			
		//장바구니, 로그인 버튼
		login = new JButton("로그인"); //로그인 버튼
		my = new JButton("MY"); //마이페이지 버튼
			
		//---장바구니
		Image cartImg = new ImageIcon("src/graphics/images/cart.png").getImage();
		cartImg = cartImg.getScaledInstance(75, 70, Image.SCALE_SMOOTH);
		ImageIcon cartIcon = new ImageIcon(cartImg);
		cart = new JButton(cartIcon);
		
		//--- 버튼 위치 및 크기 지정, 공통 패널에 추가
		JButton[] userB = {login, my, cart}; //버튼 모음
			
		for(int i = 0; i < userB.length; i++) {
			userB[i].setSize(75, 70); //크기 설정
			userB[i].setLocation(1245 + 85 * i, 40); //위치 지정
			commonPanel.add(userB[i]); //공통패널에 추가				
		}
		//--- 폰트 설정
		userB[0].setFont(new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 13)); //로그인 버튼 폰트 지정
		userB[1].setFont(new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 20)); //마이페이지 버튼 폰트 지정
			
		//위쪽 라벨 표기
		newHotGoods = new JButton("지금 뜨는 상품"); //지금 뜨는 상품
		weekTop10Goods = new JButton("금주의 TOP 10"); //금주의 TOP 10
		checkAttendance = new JButton("출석 체크"); //출석 체크
		couponPoint = new JButton("쿠폰/포인트"); //쿠폰/포인트
		communityU = new JButton("커뮤니티"); //커뮤니티
		newMonthGoods = new JButton("이달의 신상품"); //이달의 신상품
		
		//--- 상단 버튼 위치 및 크기, 폰트 지정, 공통 패널에 추가
		JButton[] upLabel = {newHotGoods, weekTop10Goods, checkAttendance,
				couponPoint, communityU, newMonthGoods}; //상단 버튼 모음
		
		Font uLabelFont = new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 17); //단체로 맞출 폰트 설정
		for(int i = 0; i < 6 ; i++) {
			upLabel[i].setSize(190,43); //사이즈 설정
			upLabel[i].setLocation(58 + 245 * i, 160); //위치 설정
		
			upLabel[i].setFont(uLabelFont); //폰트 지정
			commonPanel.add(upLabel[i]); // 메인 페이지에 버튼 추가
		}
					
		//화면 기본 설정 - End
		commonPanel.setSize(1920, 203); //공통패널 사이즈 1920*1080px
		commonPanel.setBackground(Color.white); //배경색 흰색
	}
}
