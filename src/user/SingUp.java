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

public class SingUp extends JFrame{
	
	JPanel popUp = new JPanel(); //메인 패널
	
	Vector<String> userAllergy = new Vector<String>(); //사용자가 선택한 알러지

	
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
		JLabel pnLabel = new JLabel ("전화번호 : "); JTextField phoneNum = new JTextField(20);
		JLabel bLabel = new JLabel ("생일 : "); JTextField birth = new JTextField(20);		
		
		//--- --- 입력받아야할 컴포넌트들을 연결 해시맵에 저장(순서가 보장되어야하므로)
		Map <JLabel, JTextField> info = new LinkedHashMap();
		info.put(nLabel, name); info.put(idLabel, id); info.put(eLabel, email);
		info.put(pwLabel, pw); info.put(pnLabel, phoneNum); info.put(bLabel, birth);
		
		//--- --- 배치 및 크기 전체 지정
		Set<JLabel> keys = info.keySet();
		Iterator<JLabel> it = keys.iterator();
		
		int k = 0;
		while(it.hasNext()) {
			JLabel key = it.next(); JTextField val = info.get(key);
						
			key.setBounds(15, 15 + 60 * k, 300, 50);
			val.setBounds(100, 20 + 60 * k, 350, 45);
			popUp.add(key); popUp.add(val);
			
			k++;
		}
		
		//---알러지 처리
		JLabel alInfo = new JLabel("알러지 정보 : "); //알러지 라벨
		alInfo.setBounds(15, 375, 300, 50);
		popUp.add(alInfo);
		
		//--- --- 기본으로 제공되는 알러지 정보
		JCheckBox infoAllergy[] = new JCheckBox[22];
		String alName[] = {"가금류","게","고등어","굴","닭고기","대두","돼지고기","땅콩","메밀","밀","복숭아","새우","쇠고기","아황산포함","오징어","우유","잣","전복","조개류","토마토","호두","홍합"};
		
		int w = 0; int v = 0; //세부 위치 조정 위한 변수
		for(int i = 0; i < alName.length; i++) { //알러지 체크 박스 생성
			infoAllergy[i] = (new JCheckBox(alName[i]));
			popUp.add(infoAllergy[i]);
			
			//위치 조정
			infoAllergy[i].setSize(40 + 10 * alName[i].length(), 40);
			infoAllergy[i].setLocation(100 + 100 * w, 381 + 30 * v);
			
			w++;
			if (w == 4) {w = 0; v++;}
			
			//이벤트 처리
			infoAllergy[i].addItemListener(new singUpListner());
		}
		
		// --- 주소 처리
		JLabel adLabel = new JLabel ("주소 : "); JTextField address = new JTextField(100);
		adLabel.setBounds(15, 585, 300, 50);
		address.setBounds(100, 590, 350, 45);
		popUp.add(adLabel); popUp.add(address);
		
		
		//정보 처리 필드
		JButton sUp = new JButton("회원 가입");
		sUp.setBounds(10, 665, 460, 70);
		popUp.add(sUp);
		
		sUp.addActionListener(new ActionListener(){ //회원가입 액션
			public void actionPerformed(ActionEvent e) {
				UserInfo user = new UserInfo();
				user.newUser(name.getText(), id.getText(), email.getText(), pw.getText(), Long.parseLong(phoneNum.getText()),
						Integer.parseInt(birth.getText()), 0, userAllergy, address.getText(), 0);
			}
		});
		
		
		//화면 기본 설정 - End
		setSize(500, 820); //윈도우 사이즈 1920, 1080 고정.
		add(popUp);
		setVisible(true); // 프레임 출력
	}
	
	//체크박스 이벤트 처리기
	class singUpListner implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				JCheckBox now = (JCheckBox)(e.getSource());
				userAllergy.add(now.getText());
			}
			else if(e.getStateChange() == ItemEvent.DESELECTED) {
				JCheckBox now = (JCheckBox)(e.getSource());
				userAllergy.remove(now.getText());
			}
		}
	}
}
