package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import user.SignUp;
import user.UserInfoDetail;

public class MainFrame extends JFrame{
	//기초 설정
	JPanel mainPanel = new JPanel();
	
	// !!! 실행되는 부분 !!!
	public static void main(String[] args) {
		
		UserInfoDetail myUser = new UserInfoDetail(); //사용자 객체 미리 생성.
		MainFrame m = new MainFrame(myUser);
	}
	
	
	//메인 페이지 프레임 구현 내용
	public MainFrame() {}
	public MainFrame(UserInfoDetail myUser) {
		//화면 기본 설정 - Start
		setTitle("밥심+"); //제목 설정
		//---아이콘 설정
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("src/graphics/images/iconOnly.png");
		setIconImage(img);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//종료 설정		
		
		//화면 버튼 설정
		mainPanel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
		
		//로고, 검색창, 위쪽 레이블, 로그인, 장바구니 등 기본 패널 추가
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
		
		//배너 설정
		//배너 이미지 사이즈 맞출것 및 이미지 전체 경로 설정 재설정 필요
		Image bImg = new ImageIcon("src/graphics/images/banner.png").getImage();
		bImg = bImg.getScaledInstance(1920, 260, Image.SCALE_SMOOTH);
		ImageIcon bIcon = new ImageIcon(bImg);
		JLabel banner = new JLabel(bIcon);
		banner.setSize(1920, 260);
		banner.setLocation(0, 240);
		mainPanel.add(banner);
		
		//아래쪽 레이블
		Image popImg = new ImageIcon("src/graphics/images/popular.png").getImage();
		popImg = popImg.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
		ImageIcon popularIcon = new ImageIcon(popImg);
		JButton popularGoods = new JButton("인기 상품", popularIcon);
		
		Image todImg = new ImageIcon("src/graphics/images/today_s_pick.png").getImage();
		todImg = todImg.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
		ImageIcon todayIcon = new ImageIcon(todImg);
		JButton todaySPick = new JButton("오늘 뭐 먹지?", todayIcon);
		
		Image salImg = new ImageIcon("src/graphics/images/sale.png").getImage();
		salImg = salImg.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
		ImageIcon saleIcon = new ImageIcon(salImg);
		JButton sale = new JButton("지금 할인 중", saleIcon);
		
		Image hitImg = new ImageIcon("src/graphics/images/hit.png").getImage();
		hitImg = hitImg.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
		ImageIcon hitIcon = new ImageIcon(hitImg);
		JButton hit = new JButton("인기 급상승", hitIcon);
		
		Image comImg = new ImageIcon("src/graphics/images/community.png").getImage();
		comImg = comImg.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
		ImageIcon communityIcon = new ImageIcon(comImg);
		JButton communityD = new JButton("커뮤니티", communityIcon);
		
		JButton[] downLabel = {popularGoods, todaySPick, sale, hit, communityD};
		
		Font dLabelFont = new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 20);
		for(int j = 0; j < 5; j++) {
			//폰트 위치 설정
			downLabel[j].setVerticalTextPosition(JButton.BOTTOM); //수직 설정
			downLabel[j].setHorizontalTextPosition(JButton.CENTER);//수평 설정
			
			//형태 설정
			downLabel[j].setBorderPainted(false); //외곽선 없앰
			downLabel[j].setContentAreaFilled(false); //배경색 없앰	
			downLabel[j].setSize(200, 200); //사이즈 설정
			downLabel[j].setFont(dLabelFont);
			
			//버튼 위치 설정
			downLabel[j].setLocation(150 + 260 * j, 550); //위치 설정
			mainPanel.add(downLabel[j]);
			downLabel[j].addActionListener(new MainActionListener());
		}
		
		
		//화면 기본 설정 - End
		setSize(1920, 1080); //윈도우 사이즈 1920, 1080 고정.
		add(mainPanel); //프레임에 패널 추가
		mainPanel.setBackground(Color.white);
		setBackground(Color.white);
		setVisible(true); // 프레임 출력
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