package user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class SingUp extends JFrame{
	
	JPanel popUp = new JPanel();
	
	// !!! 실행되는 부분 !!!
	public static void main(String[] args) {
		SingUp account = new SingUp(); //스윙 프레임 생성
	}
		
	// 마이페이지 프레임 구현 내용
	public SingUp() {
		//화면 기본 설정 - Start
		setTitle("밥심+ 회원 가입"); //제목 설정
		popUp.setBounds(0, 0, 530, 1500);
		
		//---아이콘 설정
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("D:\\eclipseCode\\BobPlus2023\\src\\graphics\\images\\iconOnly.png");
		setIconImage(img);	
			
		this.setLayout(null); //프레임 배치관리자 없음 : 개발자 자유 배치	
		popUp.setLayout(null);
		
		
		//...???
		//정보 입력 필드
		/*필요한 정보
		newUser(String name, String id, String email, String password, long phoneNum, int birth, 
				int userRank, Vector<String> allergy, String address, Vector<String> cartList, Vector<String> liketList,
				Vector<String> buytList, int birthCoupon)
				
				userLank 생성 당시 빼거나 관리자가 집어넣을 것.
				cartList, Vector<String> liketList,
				Vector<String> buytList는 무조건 빼는게 맞을듯... 
				birthCoupon는 관리자 관할 오케이.
				*/
		// --- 이름, 아이디 , 이메일, PW(재확인까지), 전화번호, 생일		
		JLabel nLabel = new JLabel ("이름 : "); JTextField name = new JTextField(20);
		JLabel idLabel = new JLabel ("아이디 : "); JTextField id = new JTextField(20);
		JLabel eLabel = new JLabel ("이메일 : "); JTextField email = new JTextField(50);
		JLabel pwLabel = new JLabel ("비밀번호 : "); JPasswordField pw = new JPasswordField(20);
		JLabel reLabel = new JLabel ("비밀번호 재확인 : "); JPasswordField recheck = new JPasswordField(20);
		JLabel pnLabel = new JLabel ("전화번호 : "); JTextField phoneNum = new JTextField(20);
		JLabel bLabel = new JLabel ("생일 : "); JTextField birth = new JTextField(20);		
		
		//--- --- 입력받아야할 컴포넌트들을 연결 해시맵에 저장(순서가 보장되어야하므로)
		Map <JLabel, JTextField> info = new LinkedHashMap();
		info.put(nLabel, name); info.put(idLabel, id); info.put(eLabel, email);
		info.put(pwLabel, pw); info.put(reLabel, recheck); info.put(pnLabel, phoneNum);
		info.put(bLabel, birth);
		
		//--- --- 배치 및 크기 전체 지정
		Set<JLabel> keys = info.keySet();
		Iterator<JLabel> it = keys.iterator();
		
		int i00 = 0;
		while(it.hasNext()) {
			JLabel key = it.next(); JTextField val = info.get(key);
			
			key.setBounds(15, 25 + 70 * i00, 300, 50);
			val.setBounds(120, 30 + 70 * i00, 350, 45);
			popUp.add(key); popUp.add(val);
			
			i00++;
		}
		
		//---알러지 처리
		JCheckBox[] allergies = new JCheckBox[22];
		
		//정보 처리 필드
		
		//---패널에 스크롤 페인 추가 : 스크롤 페인은 추가 안할 수도 있음.
		/*
		JScrollPane scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);		
		scroll.setBounds(0, 0, 516, 1500);
		popUp.add(scroll);*/
		
		//화면 기본 설정 - End
		setSize(530, 800); //윈도우 사이즈 1920, 1080 고정.
		add(popUp);
		setVisible(true); // 프레임 출력
	}
}
