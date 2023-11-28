package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CommonPanel extends JFrame{
	//기초 설정
	public JPanel commonPanel = new JPanel();
	public JButton login; public JButton my; public JButton cart; //로그인, 마이페이지, 장바구니
	
	//---위쪽 버튼
	public JButton newHotGoods; public JButton weekTop10Goods; public JButton checkAttendance;
	public JButton couponPoint; public JButton communityU; public JButton newMonthGoods;
	
	//로고
	public JLabel mainIL;
	
	//검색창
	public JTextField search;
	
	//메인 페이지 프레임 구현 내용
	public CommonPanel() {
		//화면 버튼 설정
		commonPanel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
			
		//로고 설정
		Image ilImg = new ImageIcon("src/graphics/images/iconAndLogo.png").getImage();
		ilImg = ilImg.getScaledInstance(230, 120, Image.SCALE_SMOOTH);
		ImageIcon iconAndLogo = new ImageIcon(ilImg);
		mainIL = new JLabel(iconAndLogo);
		mainIL.setSize(230,120);
		mainIL.setLocation(10, 10);
		commonPanel.add(mainIL);
		
		//검색창 설정
		//검색창 검색 액션 구현 필요 (Search.java)
		JLabel searchTitle = new JLabel("검색 : ");
		searchTitle.setFont(new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 20));
		search = new JTextField(); //한줄 입력창 생성
			
		searchTitle.setSize(100, 50);
		search.setSize(900, 50);
			
		searchTitle.setLocation(260,50);
		search.setLocation(320, 50);
			
		commonPanel.add(searchTitle); //검색 표기 패널 위에 추가
		commonPanel.add(search); //검색창 패널 위에 추가
			
		//장바구니, 로그인 버튼
		//---로그인
		login = new JButton("로그인");
			
		//---마이페이지 버튼 추가 예정
		my = new JButton("MY");
			
		//---장바구니
		Image cartImg = new ImageIcon("src/graphics/images/cart.png").getImage();
		cartImg = cartImg.getScaledInstance(75, 70, Image.SCALE_SMOOTH);
		ImageIcon cartIcon = new ImageIcon(cartImg);
		cart = new JButton(cartIcon);
					
		JButton[] userB = {login, my, cart};
			
		for(int i = 0; i < userB.length; i++) {
			userB[i].setSize(75, 70);
			userB[i].setLocation(1245 + 85 * i, 40);
			commonPanel.add(userB[i]); //메인패널에 추가				
		}
		userB[0].setFont(new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 13));
		userB[1].setFont(new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 20));
			
		//위쪽 레이블 표기
		newHotGoods = new JButton("지금 뜨는 상품");
		weekTop10Goods = new JButton("금주의 TOP 10");
		checkAttendance = new JButton("출석 체크");
		couponPoint = new JButton("쿠폰/포인트");
		communityU = new JButton("커뮤니티");
		newMonthGoods = new JButton("이달의 신상품");
			
		JButton[] upLabel = {newHotGoods, weekTop10Goods, checkAttendance,
				couponPoint, communityU, newMonthGoods};
		
		Font uLabelFont = new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 17);
		for(int i = 0; i < 6 ; i++) {
			upLabel[i].setSize(190,43); //사이즈 설정
			upLabel[i].setLocation(58 + 245 * i, 160); //위치 설정
		
			upLabel[i].setFont(uLabelFont);
			commonPanel.add(upLabel[i]); // 메인 페이지에 버튼 추가
		}
					
		//화면 기본 설정 - End
		commonPanel.setSize(1920, 203); //윈도우 사이즈 1920, 1080 고정.
		commonPanel.setBackground(Color.white);
	}
}
