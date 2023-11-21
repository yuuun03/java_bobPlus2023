package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CommonPanel extends JFrame{
	//기초 설정
	JPanel commonPanel = new JPanel();
	JButton login; JButton my; JButton cart; //로그인, 마이페이지, 장바구니
	
	//---위쪽 버튼
	JButton newHotGoods; JButton weekTop10Goods; JButton checkAttendance;
	JButton couponPoint; JButton communityU; JButton newMonthGoods;
	
	//메인 페이지 프레임 구현 내용
	public CommonPanel() {
		//화면 버튼 설정
		commonPanel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
			
		//로고 설정
		Image ilImg = new ImageIcon("D:\\eclipseCode\\BobPlus2023\\bin\\graphics\\images\\iconAndLogo.png").getImage();
		ilImg = ilImg.getScaledInstance(230, 120, Image.SCALE_SMOOTH);
		ImageIcon iconAndLogo = new ImageIcon(ilImg);
		JLabel mainIL = new JLabel(iconAndLogo);
		mainIL.setSize(230,120);
		mainIL.setLocation(10, 10);
		commonPanel.add(mainIL);
			
		//검색창 설정
		//검색창 검색 액션 구현 필요
		JLabel searchTitle = new JLabel("검색 : ");
		JTextField search = new JTextField(); //한줄 입력창 생성
			
		searchTitle.setSize(100, 50);
		search.setSize(1000, 50);
			
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
		Image cartImg = new ImageIcon("D:\\eclipseCode\\BobPlus2023\\bin\\graphics\\images\\cart.png").getImage();
		cartImg = cartImg.getScaledInstance(75, 70, Image.SCALE_SMOOTH);
		ImageIcon cartIcon = new ImageIcon(cartImg);
		cart = new JButton(cartIcon);
					
		JButton[] userB = {login, cart};
			
		for(int i = 0; i < userB.length; i++) {
			userB[i].setSize(75, 70);
			userB[i].setLocation(1340 + 95 * i, 40);
			commonPanel.add(userB[i]); //메인패널에 추가				
		}
			
		//위쪽 레이블 표기
		newHotGoods = new JButton("지금 뜨는 상품");
		weekTop10Goods = new JButton("금주의 TOP 10");
		checkAttendance = new JButton("출석 체크");
		couponPoint = new JButton("쿠폰/포인트");
		communityU = new JButton("커뮤니티");
		newMonthGoods = new JButton("이달의 신상품");
			
		JButton[] upLabel = {newHotGoods, weekTop10Goods, checkAttendance,
				couponPoint, communityU, newMonthGoods};

		for(int i = 0; i < 6 ; i++) {
			upLabel[i].setSize(190,43); //사이즈 설정
			upLabel[i].setLocation(58 + 245 * i, 160); //위치 설정
			/*위쪽 라벨 폰트 설정
			 * G마켓 산스 TTF Medium체, 사이즈 17
			 * 깃허브와 연동해보고 안되면 해당 주석 사용.
			*/
			//upLabel[i].setFont(new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 17));
			commonPanel.add(upLabel[i]); // 메인 페이지에 버튼 추가
		}
					
		//화면 기본 설정 - End
		commonPanel.setSize(1920, 203); //윈도우 사이즈 1920, 1080 고정.
	}
}
