//컴퓨터공학부 2022136117 조윤서
package main;

import javax.swing.*;
import javax.swing.border.LineBorder;

import admin.Product;
import goods.SearchResult;
import main.MainFrame.MainActionListener;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import user.SignUp; //회원가입 팝업창
import user.UserInfoDetail;

public class LoginPage extends JFrame{
	//기초 설정
	JPanel memberPanel = new JPanel(); //비회원 단독 패널
	JPanel noMemberPanel = new JPanel(); //회원 로그인 단독 패널
	JPanel allPanel = new JPanel(); //전체 패널
	
	
	// 마이페이지 프레임 구현 내용
	public LoginPage() {} //기본 생성자
	public LoginPage(UserInfoDetail myUser, Vector<Product> pList) {
		//화면 기본 설정 - Start
		setTitle("밥심+"); //제목 설정
		
		//---아이콘 설정
		Toolkit kit = Toolkit.getDefaultToolkit(); //이미지 편집 위한 Toolkit 객체 생성
		Image img = kit.getImage("src/graphics/images/iconOnly.png"); //이미지 받아오기
		setIconImage(img); //받아온 이미지 아이콘으로 설정
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//종료 설정 : 가위표 누르면 모든 프레임 종료.
		
		//프레임 및 패널들 배치 관리
		this.setLayout(null); //프레임 배치관리자 없음 : 개발자 자유 배치
		allPanel.setLayout(null);//전체 패널 배치 관리자 없음 : 개발자 자유 배치
		memberPanel.setLayout(null); //회원 단독 패널 배치관리자 없음 : 개발자 자유 배치		
		noMemberPanel.setLayout(null); //비회원 단독 패널 배치관리자 없음 : 개발자 자유 배치
		
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
				
		//폰트 설정
		//--- 로그인, 배송 조회 버튼
		Font buttonFont = new Font("G마켓 산스 TTF BOLD", Font.CENTER_BASELINE, 25);
		//--- 로그인, 주문 조회 타이틀
		Font miniTitle = new Font("G마켓 산스 TTF BOLD", Font.CENTER_BASELINE, 23);
		//--- 일반 라벨
		Font basic = new Font("G마켓 산스 TTF Medium", Font.PLAIN, 17);
		//--- 회원가입 버튼 전용
		Font sFont = new Font("G마켓 산스 TTF Light", Font.PLAIN, 15);
		
		//기본 패널 설정
		allPanel.setSize(1920,1080); //전체 패널 크기 1920*1080px
		add(allPanel); //로그인 프레임에 추가
		
		//멤버 로그인
		memberPanel.setSize(500,290); //패널 사이즈 설정
		memberPanel.setLocation(170, 343); //위치 지정(170, 343)
		memberPanel.setBorder(new LineBorder(Color.black, 2)); //패널 윤곽선 검정색 2pt 지정
		
		//---타이틀(회원 패널 밖)
		JLabel mTitle = new JLabel("회원 로그인"); //타이틀
		mTitle.setFont(miniTitle); //폰트 지정 
		mTitle.setBounds(362, 270, 300, 45); //위치 (362, 270), 크기 300*45px
		allPanel.add(mTitle); //전체 패널에 추가
		
		//---아이디
		JLabel idLabel = new JLabel("아이디 : "); //아이디 안내 라벨
		JTextField id = new JTextField(10); //텍스트 최대 10자까지 입력받게 함
		idLabel.setBounds(50, 20, 100, 100); //아이디 안내 라벨 : 위치 (50, 20), 크기 100*100px
		id.setBounds(150, 50, 300, 45);//아이디 텍스트 필드 : 위치 (150, 50), 크기 300*45px
		idLabel.setFont(basic); //아이디 안내 라벨 폰트 설정
		
		//---패스워드
		JLabel pwLabel = new JLabel("비밀번호 : "); //비밀번호 안내 라벨
		JPasswordField pw = new JPasswordField(10); //비밀번호 최대 10자까지 입력받게
		pwLabel.setBounds(50, 80, 100, 100); //비밀번호 안내 라벨 : 위치 (50, 80), 크기 100*100px
		pw.setBounds(150, 110, 300, 45); //비밀번호 텍스트 필드 : 위치 (150, 110), 크기 300*45px
		pwLabel.setFont(basic); //비밀번호 안내 라벨 폰트 설정
		
		//---로그인 버튼
		JButton lgB = new JButton("L O G I N"); //"L O G I N"이라 써져 있는 로그인 버튼
		lgB.setBounds(50, 180, 400, 65); //로그인 버튼 : 위치 (50, 180), 크기 400*65px
		lgB.setFont(buttonFont);//폰트 설정
		lgB.setBackground(new Color(200, 228, 137)); //색깔 설정 - 연두
		
		lgB.addActionListener(new ActionListener(){ //로그인 버튼 액션
			public void actionPerformed(ActionEvent e) { //버튼 클릭시
				//입력한 아이디와 비밀번호가 회원가입 당시 가입한 아이디와 비밀번호랑 일치하면 로그인
				if (myUser.getId().equals(id.getText()) && myUser.getPassword().equals(pw.getText())){
					myUser.setIsLogin(true); //로그인 여부 true로 전환
					new MainFrame(myUser, pList); //메인프레임으로 돌아가기
					dispose(); //이전 프레임 닫기
				}
				//아닐 시 사용자 경고문 출력
				else {
					JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다.");
				}
			}
		});
		
		//---요소들 회원 단독 패널에 추가
		memberPanel.add(idLabel); memberPanel.add(id);
		memberPanel.add(pwLabel); memberPanel.add(pw);
		memberPanel.add(lgB);
		
		//회원가입
		JLabel sign = new JLabel("회원 가입 >"); //회원가입 안내 라벨
		sign.setBounds(170, 600, 100, 100); //회원가입 안내 라벨 : 위치 (170, 600), 크기 100*100px
		sign.setFont(sFont); //폰트 지정
		allPanel.add(sign); //전체 패널에 추가
		
		sign.addMouseListener(new MouseAdapter() {//회원가입 액션 설정
			public void mouseReleased(MouseEvent e) { //마우스를 클릭후 뗄 때
				new SignUp(myUser); //회원가입 창 띄우기
			}
		});
		
		
		//비회원 주문조회
		noMemberPanel.setSize(500,290); //비회원 패널 사이즈 설정 500 * 290px
		noMemberPanel.setLocation(870, 343); //비회원 패널 위치 설정
		noMemberPanel.setBorder(new LineBorder(Color.black, 2)); //패널 외곽선 검정, 2pt
				
		//---타이틀 (비회원 단독 패널 밖)
		JLabel nmTitle = new JLabel("비회원 주문 조회"); //타이틀
		nmTitle.setBounds(1043, 270, 300, 45); //타이틀 : 위치 (1043, 270), 크기 300*45px
		nmTitle.setFont(miniTitle); //폰트 설정
		allPanel.add(nmTitle); //전체 패널에 추가
				
		//---주문번호
		JLabel odNumLabel = new JLabel("주문번호 : "); //주문 번호 안내 라벨
		JTextField odNum = new JTextField(10); //주문번호 최대 10자까지 입력
		odNumLabel.setBounds(50, 20, 100, 100); //주문번호 안내 라벨 : 위치 (50, 20), 크기 100*100px
		odNum.setBounds(150, 50, 300, 45); //주문번호 텍스트 필드 : 위치 (150, 50), 크기 300*45px
		odNumLabel.setFont(basic); //폰트 설정
		
		//---전화번호
		JLabel phoneNumLabel = new JLabel("전화번호 : "); //전화번호 안내 라벨
		JTextField phoneNum = new JTextField(11); //전화번호 최대 11자 입력
		phoneNumLabel.setBounds(50, 80, 100, 100);//아이디 안내 라벨 : 위치 (50, 80), 크기 100*100px
		phoneNum.setBounds(150, 110, 300, 45);//아이디 안내 라벨 : 위치 (250, 220), 크기 300*45px
		phoneNumLabel.setFont(basic); //폰트 설정
		
		//---주문조회 버튼
		JButton nmB = new JButton("주 문  조 회"); //"주문조회" 글자가 있는 버튼
		nmB.setBounds(50, 180, 400, 65); //주문조회 버튼: 위치 (50, 180), 크기 400*65px
		nmB.setFont(buttonFont); //폰트 설정
		nmB.setBackground(new Color(255, 206, 90)); //색깔 설정 - 오렌지
		
		nmB.addActionListener(new ActionListener(){ //로그인 액션
			public void actionPerformed(ActionEvent e) {
				//마스터 오더에 주문번호 넣을 것...
				//myUser.getBuyNumList()(주문번호 담는 리스트)를 통해 odNum.getText와 비교합니다.
				//현재는 기능 미구현 됐으므로 정상적인 컴파일을 위해 임의의 주문번호와 전화번호를 부여합니다.
				String testON = "12345678";
				String testPN = "01012345678";
				
				//입력한 주문번호와 전화번호가 상품 주문시 입력한 전화번호와 부여받은 주문번호랑 같을 때
				if (testON.equals(odNum.getText()) && testPN.equals(phoneNum.getText())){
					//사용자 안내문
					JOptionPane.showMessageDialog(null, "조회성공.\n현재 기능 구현 중에 있습니다.");
				}
				else {
					JOptionPane.showMessageDialog(null, "조회에 실패하였습니다."); //사용자 경고문
				}
			}
		});
			
		//---비회원 단독 패널에 요소 추가
		noMemberPanel.add(odNumLabel); noMemberPanel.add(odNum);
		noMemberPanel.add(phoneNumLabel); noMemberPanel.add(phoneNum);
		noMemberPanel.add(nmB);		
		
		//화면 기본 설정 - End
		setSize(1920, 1080); //윈도우 사이즈 1920, 1080 고정.
		allPanel.setBackground(Color.white); //전체 패널 색깔 : 흰색
		memberPanel.setBackground(Color.white); //회원 로그인 패널 색깔 : 흰색
		noMemberPanel.setBackground(Color.white); //비회원 주문조회 패널 색깔 : 흰색
		
		allPanel.add(memberPanel); //프레임에 패널 추가
		allPanel.add(noMemberPanel); //프레임에 패널 추가
		setVisible(true); // 프레임 출력
	}
	
	//이벤트 처리 클래스들
	class MainActionListener implements ActionListener{
		//Action : 버튼 클릭 
		public void actionPerformed(ActionEvent e) {
			JButton bRefer = (JButton)e.getSource(); //사용자가 클릭한 버튼 알아내기
			
			//버튼 종류마다 이벤트 다르게 지정
			switch(bRefer.getText()) {
			case "지금 뜨는 상품" : case "금주의 TOP 10" :
				/*인기상품, 지금뜨는 상품, 금주의 TOP10 클릭시
				지금뜨는 상품과 금주의 TOP10은 인기 상품에 속해있는 원소긴 하나
				이는 추후 구현 예정*/ 	
				//사용자 알림
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
				
			case "출석 체크":
				//사용자 알림
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
				
			case "쿠폰/포인트":
				//사용자 알림
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
					
			case "커뮤니티": 
				//사용자 알림
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
				
			case "이달의 신상품": 
				//사용자 알림
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
				
			default :
				break;
			}
		}
	}
}