package main;

import javax.swing.*;

import admin.MakeProducts;
import admin.MasterGoods;
import admin.Product;
import goods.SearchResult;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import user.SignUp;
import user.UserInfoDetail;

public class MainFrame extends JFrame{
	//기초 설정
	JPanel mainPanel = new JPanel();
	
	// !!! 실행되는 부분 !!!
	public static void main(String[] args) {
		//상품 시작 전 미리 설정
		MakeProducts mk = new MakeProducts(); //상품 추가를 위한 객체 생성
		MasterGoods mg = new MasterGoods(); //상품 관리를 위한 객체 생성
		
		mg.setPList(mk.make()); //관리되는 상품 목록에 새 상품 추가
		Vector<Product> pList = mg.getPList(); //전달받을 상품 목록 객체 생성
		
		//빈 사용자 객체 생성
		UserInfoDetail myUser = new UserInfoDetail();
		
		//메인 프레임 생성 및 호출
		MainFrame m = new MainFrame(myUser, pList);
	}
	
	
	//메인 페이지 프레임 구현 내용
	public MainFrame() {} //기본 생성자 - 사용하지 않음
	public MainFrame(UserInfoDetail myUser, Vector<Product> pList) { //유저 정보와 상품정보를 넘겨받은 메인 프레임 생성
		//화면 기본 설정 - Start
		setTitle("밥심+"); //제목 설정
		//---아이콘 설정
		Toolkit kit = Toolkit.getDefaultToolkit(); //이미지 편집 위한 Toolkit 객체 생성
		Image img = kit.getImage("src/graphics/images/iconOnly.png"); //이미지 받아오기
		setIconImage(img); //받아온 이미지 아이콘으로 설정
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//종료 설정	 : 가위표 누르면 모든 프레임 종료.
		
		//화면 버튼 설정
		mainPanel.setLayout(null); //메인패널 배치관리자 없음 : 개발자 자유 배치
		
		//공통 패널(상단의 로고, 검색창, 위쪽 레이블, 로그인, 장바구니 등 기본 패널) 추가
		CommonPanel df = new CommonPanel(); //공통 패널 추가 위한 객체 생성
		add(df.commonPanel); //공통패널패널 추가
		
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
		
		//배너 설정
		//배너 이미지 사이즈 맞출것 및 이미지 전체 경로 설정 재설정 필요
		Image bImg = new ImageIcon("src/graphics/images/banner.png").getImage(); //배너로 사용할 이미지 가져오기
		bImg = bImg.getScaledInstance(1920, 260, Image.SCALE_SMOOTH); //배너 이미지 1920*260 px로 재가공
		ImageIcon bIcon = new ImageIcon(bImg); //배너 이미지로 이미지 아이콘 생성
		JLabel banner = new JLabel(bIcon); //배너 생성
		banner.setSize(1920, 260); //배너 사이즈 : 1920*260px
		banner.setLocation(0, 240); //배너 위치 : 0, 240
		mainPanel.add(banner); //메인 패널에 배너 추가
		
		//아래쪽 레이블
		// --- 인기상품
		Image popImg = new ImageIcon("src/graphics/images/popular.png").getImage(); //인기상품 이미지 가져오기
		popImg = popImg.getScaledInstance(130, 130, Image.SCALE_SMOOTH); //이미지 재가공 > 130*130 px
		ImageIcon popularIcon = new ImageIcon(popImg); //가져온 이미지로 이미지 아이콘 생성
		JButton popularGoods = new JButton("인기 상품", popularIcon); //아이콘 생성
		
		// --- 오늘 뭐 먹지?
		Image todImg = new ImageIcon("src/graphics/images/today_s_pick.png").getImage(); //오늘 뭐 먹지? 이미지 가져오기
		todImg = todImg.getScaledInstance(130, 130, Image.SCALE_SMOOTH); //이미지 재가공 > 130*130 px
		ImageIcon todayIcon = new ImageIcon(todImg); //가져온 이미지로 이미지 아이콘 생성
		JButton todaySPick = new JButton("오늘 뭐 먹지?", todayIcon); //아이콘 생성
		
		// --- 지금 할인 중
		Image salImg = new ImageIcon("src/graphics/images/sale.png").getImage(); //지금 할인 중 이미지 가져오기
		salImg = salImg.getScaledInstance(130, 130, Image.SCALE_SMOOTH); //이미지 재가공 > 130*130 px
		ImageIcon saleIcon = new ImageIcon(salImg); //가져온 이미지로 이미지 아이콘 생성
		JButton sale = new JButton("지금 할인 중", saleIcon); //아이콘 생성
		
		// --- 인기 급상승
		Image hitImg = new ImageIcon("src/graphics/images/hit.png").getImage(); //인기 급상승 이미지 가져오기
		hitImg = hitImg.getScaledInstance(130, 130, Image.SCALE_SMOOTH); //이미지 재가공 > 130*130 px
		ImageIcon hitIcon = new ImageIcon(hitImg); //가져온 이미지로 이미지 아이콘 생성
		JButton hit = new JButton("인기 급상승", hitIcon); //아이콘 생성
		
		// --- 커뮤니티
		Image comImg = new ImageIcon("src/graphics/images/community.png").getImage(); //커뮤니티 이미지 가져오기
		comImg = comImg.getScaledInstance(130, 130, Image.SCALE_SMOOTH); //이미지 재가공 > 130*130 px
		ImageIcon communityIcon = new ImageIcon(comImg); //가져온 이미지로 이미지 아이콘 생성
		JButton communityD = new JButton("커뮤니티", communityIcon); //아이콘 생성
		
		JButton[] downLabel = {popularGoods, todaySPick, sale, hit, communityD}; //공통 패널 하단에 위치할 버튼들 모음
		
		//버튼 설정
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