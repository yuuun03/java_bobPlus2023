package user;

import javax.swing.*;
import javax.swing.text.Document;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import user.UserInfo;

public class SignUp extends JFrame{
	
	JPanel popUp = new JPanel(); //메인 패널
	
	Vector<String> userAllergy = new Vector<String>(); //사용자가 선택한 알러지 저장
	
	//유저 세팅 위한 함수
	//입력한 값을 유저 정보에 넣어줌.
	public static void setUser(UserInfoDetail myUser, UserInfoDetail n) {
		myUser.setName(n.getName()); myUser.setId(n.getId());
		myUser.setEmail(n.getEmail()); myUser.setPassword(n.getPassword());
		myUser.setPhoneNum(n.getPhoneNum()); myUser.setBirth(n.getBirth());
		myUser.setUserRank(n.getUserRank()); myUser.setAllergy(n.getAllergy());
		myUser.setAddress(n.getAddress()); myUser.setBirth(n.getBirth());
	}
		
	// 마이페이지 프레임 구현 내용
	public SignUp() {} //기본 생성자
	public SignUp(UserInfoDetail myUser) { //유저 정보 받아오는 생성자
		//화면 기본 설정 - Start
		setTitle("밥심+ 회원 가입"); //제목 설정
		popUp.setBounds(0, 0, 530, 1500); //위치 설정 (0,0), 크기 설정 530*1500 px
		
		//---아이콘 설정
		Toolkit kit = Toolkit.getDefaultToolkit(); //이미지 편집 위한 Toolkit 객체 생성
		Image img = kit.getImage("src/graphics/images/iconOnly.png"); //이미지 받아오기
		setIconImage(img); //받아온 이미지 아이콘으로 설정
			
		//배치 관리자 설정
		this.setLayout(null); //프레임 배치관리자 없음 : 개발자 자유 배치	
		popUp.setLayout(null); //팝업 패널 배치관리자 없음 : 개발자 자유 배치
		
		//폰트 설정
		//--- 로그인, 배송 조회 버튼
		Font buttonFont = new Font("G마켓 산스 TTF BOLD", Font.CENTER_BASELINE, 25);
		//--- 일반 라벨
		Font basic = new Font("G마켓 산스 TTF Medium", Font.PLAIN, 17);
		//--- 알러지 폰트
		Font alFont = new Font("G마켓 산스 TTF Medium", Font.PLAIN, 13);
		
		//정보 입력 필드
		// --- 이름, 아이디 , 이메일, PW, 전화번호, 생일
		// --- --- 이름 입력 : 최대 20자
		JLabel nLabel = new JLabel ("이름 : "); JTextField name = new JTextField(20);
		// --- --- 아이디 입력 : 최대 10자
		JLabel idLabel = new JLabel ("아이디 : "); JTextField id = new JTextField(10);
		// --- --- 이메일 입력 : 최대 50자
		JLabel eLabel = new JLabel ("이메일 : "); JTextField email = new JTextField(50);
		// --- --- 비밀번호 입력 : 최대 10자
		JLabel pwLabel = new JLabel ("비밀번호 : "); JPasswordField pw = new JPasswordField(10);
		// --- --- 전화번호 입력 : 최대 11자
		JLabel pnLabel = new JLabel ("전화번호 : "); JTextField phoneNum = new JTextField(11);
		// --- --- 생일 입력 : 최대 8자
		JLabel bLabel = new JLabel ("생일 : "); JTextField birth = new JTextField(8);		
		
		//--- --- 입력받아야할 컴포넌트들을 연결 해시맵에 저장(순서가 보장되어야하므로)
		Map <JLabel, JTextField> info = new LinkedHashMap(); //라벨과 텍스트 필드를 저장할 연결 해시맵 객체 생성
		//--- --- --- 저장
		info.put(nLabel, name); info.put(idLabel, id); info.put(eLabel, email);
		info.put(pwLabel, pw); info.put(pnLabel, phoneNum); info.put(bLabel, birth);
		
		//--- --- 배치 및 크기 전체 지정
		Set<JLabel> keys = info.keySet(); //라벨명들 전체 받아오기
		Iterator<JLabel> it = keys.iterator(); //반복위한 반복자 설정
		
		int k = 0; //위치 세부 조정 위한 변수
		while(it.hasNext()) { //연결된 모든 해시맵을 다 돌때까지 반복
			//라벨과 대응하는 텍스트 필드를 가져옴
			JLabel key = it.next(); JTextField val = info.get(key);
						
			key.setBounds(15, 15 + 60 * k, 300, 50); //라벨 위치 지정
			val.setBounds(100, 20 + 60 * k, 350, 45); //텍스트 필드 위치 지정
			key.setFont(basic); //라벨 폰트 지정
			popUp.add(key); popUp.add(val); //팝업 패널에 추가
			
			k++; //위치 세부 조정
		}
		
		//---알러지 처리
		JLabel alInfo = new JLabel("알러지 정보 : "); //알러지 라벨
		alInfo.setFont(basic); //폰트 설정
		alInfo.setBounds(15, 375, 300, 50); //위치 지정 (15, 375), 크기 300*50px
		popUp.add(alInfo); //팝업 패널에 추가
		
		//--- --- 기본으로 제공되는 알러지 정보
		JCheckBox infoAllergy[] = new JCheckBox[22]; //알러지 정보 체크할 체크박스
		String alName[] = {"가금류","게","고등어","굴","닭고기",
				"대두","돼지고기","땅콩","메밀","밀",
				"복숭아","새우","쇠고기","아황산포함","오징어",
				"우유","잣","전복","조개류","토마토",
				"호두","홍합"}; //공인 알러지 정보들 모음
		
		int w = 0; int v = 0; //세부 위치 조정 위한 변수
		for(int i = 0; i < alName.length; i++) { //모든 알러지 체크 박스 생성 및 설정
			infoAllergy[i] = (new JCheckBox(alName[i])); //체크 박스 생성
			popUp.add(infoAllergy[i]); //팝업 패널에 추가
			
			//디자인 설정
			infoAllergy[i].setFont(alFont); //폰트 설정
			infoAllergy[i].setBackground(Color.white); //배경색은 팝업 패널 배경색과 맞춤
			
			//위치 및 크기 조정
			infoAllergy[i].setSize(40 + 10 * alName[i].length(), 40);
			infoAllergy[i].setLocation(110 + 100 * w, 381 + 30 * v);
			
			w++;
			if (w == 4) {w = 0; v++;} //4개의 체크박스가 들어가면 줄바꿈
			
			//이벤트 처리
			infoAllergy[i].addItemListener(new signUpListner());
		}
		
		// --- 주소 처리
		JLabel adLabel = new JLabel ("주소 : "); JTextField address = new JTextField(100); //라벨과 최대 100자까지 입력 가능한 텍스트 필드 생성
		adLabel.setBounds(15, 585, 300, 50); //라벨 위치 (15, 585), 크기 300*50px
		address.setBounds(100, 590, 350, 45); //텍스트 필드 위치 (100, 590), 크기 350*45px
		adLabel.setFont(basic); //폰트 설정
		popUp.add(adLabel); popUp.add(address); //라벨과 텍스트 필드 설정
		
		
		//정보 처리 필드
		JButton sUp = new JButton("회원 가입"); //"회원가입"이라 적힌 버튼 생성
		sUp.setBounds(10, 665, 460, 70); //버튼 위치 (10, 665), 크기 460*70px
		sUp.setFont(buttonFont); //폰트 설정
		sUp.setBackground(new Color(200, 228, 137)); //색깔 설정 - 연두
		popUp.add(sUp); //팝업 패널에 버튼 추가
		
		sUp.addActionListener(new ActionListener(){ //회원가입 버튼 액션
			public void actionPerformed(ActionEvent e) { //버튼 클릭시
				UserInfo user = new UserInfo(); //받아온 정보를 저장할 임시 유저 객체 생성
				//받아온 정보를 임시 유저 객체에 저장
				user.newUser(name.getText(), id.getText(), email.getText(), pw.getText(), Long.parseLong(phoneNum.getText()),
						Integer.parseInt(birth.getText()), 5, userAllergy, address.getText(), false, 0);
				
				Vector<UserInfoDetail> temp = user.getUserList(); //저장용 임시 배열
				setUser(myUser, temp.lastElement()); //입력받은 유저 정보를 myUser에 넘겨줌
				dispose(); //창 닫음
			}
		});
		
		
		//화면 기본 설정 - End
		setSize(500, 820); //팝업 사이즈 500, 820.
		setBackground(Color.white); //프레임 배경색 : 흰색
		popUp.setBackground(Color.white); //팝업 패널 배경색 : 흰색
		add(popUp); //회원가입 프레임에 팝업 패널 추가
		setVisible(true); // 프레임 출력
	}
	
	//체크박스 이벤트 처리기
	class signUpListner implements ItemListener{ //아이템 이벤트 발생
		public void itemStateChanged(ItemEvent e) { //상태 변화(체크박스 체크/해제) 발생 시
			if(e.getStateChange() == ItemEvent.SELECTED) { //체크박스 선택 시
				JCheckBox now = (JCheckBox)(e.getSource()); //체크된 체크박스 위치 가져옴
				userAllergy.add(now.getText()); //해당 정보 유저알러지에 추가
			}
			else if(e.getStateChange() == ItemEvent.DESELECTED) { //체크박스 해제시
				JCheckBox now = (JCheckBox)(e.getSource()); //체크된 체크박스 위치 가져옴
				userAllergy.remove(now.getText()); //해당 정보 유저알러지에 제거
			}
		}
	}
}
