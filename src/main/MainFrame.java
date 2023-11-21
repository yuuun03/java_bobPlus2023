package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame{
	//기초 설정
	JFrame mainPage = new JFrame(); //프레임 생성
	JPanel mainPanel = new JPanel();
	
	// !!! 실행되는 부분 !!!
	public static void main(String[] args) {
		MainFrame m = new MainFrame();
		
		
	}
	
	//메인 페이지 프레임 구현 내용
	public MainFrame() {
		//화면 기본 설정 - Start
		setTitle("밥심+"); //제목 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		//화면 버튼 설정
		//Container mainPane = getContentPane(); //컨텐트 팬 얻어오기. 여기서 awt 사용.
		mainPanel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
		
		//로고 설정
		Image ilImg = new ImageIcon("D:\\eclipseCode\\BobPlus2023\\bin\\graphics\\images\\iconAndLogo.png").getImage();
		ilImg = ilImg.getScaledInstance(230, 120, Image.SCALE_SMOOTH);
		ImageIcon iconAndLogo = new ImageIcon(ilImg);
		JLabel mainIL = new JLabel(iconAndLogo);
		mainIL.setSize(230,120);
		mainIL.setLocation(10, 10);
		mainPanel.add(mainIL);
		
		//검색창 설정
		JLabel searchTitle = new JLabel("검색 : ");
		JTextField search = new JTextField(); //한줄 입력창 생성
		
		searchTitle.setSize(100, 50);
		search.setSize(1000, 50);
		
		searchTitle.setLocation(260,50);
		search.setLocation(320, 50);
		
		mainPanel.add(searchTitle); //검색 표기 패널 위에 추가
		mainPanel.add(search); //검색창 패널 위에 추가
		
		//장바구니, 로그인 버튼
		//---로그인
		JButton login = new JButton("로그인");
		
		//---마이페이지 버튼 추가 예정
		JButton my = new JButton("MY");
		
		//---장바구니
		Image cartImg = new ImageIcon("D:\\eclipseCode\\BobPlus2023\\bin\\graphics\\images\\cart.png").getImage();
		cartImg = cartImg.getScaledInstance(75, 70, Image.SCALE_SMOOTH);
		ImageIcon cartIcon = new ImageIcon(cartImg);
		JButton cart = new JButton(cartIcon);
		
		JButton[] userB = {login, cart};
		
		for(int i = 0; i < userB.length; i++) {
			userB[i].setSize(75, 70);
			userB[i].setLocation(1350 + 85 * i, 40);
			mainPanel.add(userB[i]); //추가
		}
		
		//배너 설정
		Image bImg = new ImageIcon("D:\\eclipseCode\\BobPlus2023\\bin\\graphics\\images\\teseImage01.jpg").getImage();
		bImg = bImg.getScaledInstance(1920, 260, Image.SCALE_SMOOTH);
		ImageIcon bIcon = new ImageIcon(bImg);
		JLabel banner = new JLabel(bIcon);
		banner.setSize(1920, 260);
		banner.setLocation(0, 240);
		mainPanel.add(banner);
		
		//위쪽 레이블 표기
		JButton newHotGoods = new JButton("지금 뜨는 상품");
		JButton weekTop10Goods = new JButton("금주의 TOP 10");
		JButton checkAttendance = new JButton("출석 체크");
		JButton couponPoint = new JButton("쿠폰/포인트");
		JButton communityU = new JButton("커뮤니티");
		JButton newMonthGoods = new JButton("이달의 신상품");
		
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
			mainPanel.add(upLabel[i]); // 메인 페이지에 버튼 추가
			
			//버튼마다 액션 리스너 달아줌.
			upLabel[i].addActionListener(new MainActionListner());
		}
		
		//아래쪽 레이블
		Image popImg = new ImageIcon("D:\\eclipseCode\\BobPlus2023\\src\\graphics\\images\\popular.png").getImage();
		popImg = popImg.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
		ImageIcon popularIcon = new ImageIcon(popImg);
		JButton popularGoods = new JButton("인기 상품", popularIcon);
		
		Image todImg = new ImageIcon("D:\\eclipseCode\\BobPlus2023\\src\\graphics\\images\\today_s_pick.png").getImage();
		todImg = todImg.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
		ImageIcon todayIcon = new ImageIcon(todImg);
		JButton todaySPick = new JButton("오늘 뭐 먹지?", todayIcon);
		
		Image salImg = new ImageIcon("D:\\eclipseCode\\BobPlus2023\\src\\graphics\\images\\sale.png").getImage();
		salImg = salImg.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
		ImageIcon saleIcon = new ImageIcon(salImg);
		JButton sale = new JButton("지금 할인 중", saleIcon);
		
		Image hitImg = new ImageIcon("D:\\eclipseCode\\BobPlus2023\\src\\graphics\\images\\hit.png").getImage();
		hitImg = hitImg.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
		ImageIcon hitIcon = new ImageIcon(hitImg);
		JButton hit = new JButton("인기 급상승", hitIcon);
		
		Image comImg = new ImageIcon("D:\\eclipseCode\\BobPlus2023\\src\\graphics\\images\\community.png").getImage();
		comImg = comImg.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
		ImageIcon communityIcon = new ImageIcon(comImg);
		JButton communityD = new JButton("커뮤니티", communityIcon);
		
		JButton[] downLabel = {popularGoods, todaySPick, sale, hit, communityD};
		
		for(int j = 0; j < 5; j++) {
			//폰트 위치 설정
			downLabel[j].setVerticalTextPosition(JButton.BOTTOM); //수직 설정
			downLabel[j].setHorizontalTextPosition(JButton.CENTER);//수평 설정
			
			//형태 설정
			downLabel[j].setBorderPainted(false); //외곽선 없앰
			downLabel[j].setContentAreaFilled(false); //배경색 없앰	
			downLabel[j].setSize(150, 150); //사이즈 설정
			
			//버튼 위치 설정
			downLabel[j].setLocation(200 + 250 * j, 570); //위치 설정
			mainPanel.add(downLabel[j]);
		}
		
		
		//화면 기본 설정 - End
		setSize(1920, 1080); //윈도우 사이즈 1920, 1080 고정.
		add(mainPanel); //프레임에 패널 추가
		setVisible(true); // 프레임 출력
	}
}

//
//
//이벤트 처리 클래스들

class MainActionListner implements ActionListener{
	//Action : 버튼 클릭 
	public void actionPerformed(ActionEvent e) {
		JButton bRefer = (JButton)e.getSource(); //사용자가 클릭한 버튼 알아내기
		
	}
}