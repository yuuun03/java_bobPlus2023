package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame{
	// !!! 실행되는 부분 !!!
	public static void main(String[] args) {
		MainFrame mainPage = new MainFrame(); //스윙 프레임 생성
	}
	
	//메인 페이지 프레임 구현 내용
	public MainFrame() {
		//화면 기본 설정 - Start
		setTitle("밥심+"); //제목 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//화면 버튼 설정
		Container mainPane = getContentPane(); //컨텐트 팬 얻어오기. 여기서 awt 사용.
		mainPane.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
		
		//---위쪽 레이블 표기
		JButton newHotGoods = new JButton("지금 뜨는 상품");
		JButton weekTop10Goods = new JButton("금주의 TOP 10");
		JButton checkAttendance = new JButton("출석 체크");
		JButton couponPoint = new JButton("쿠폰/포인트");
		JButton community = new JButton("커뮤니티");
		JButton newMonthGoods = new JButton("이달의 신상품");
		
		JButton[] upLabel = {newHotGoods, weekTop10Goods, checkAttendance,
				couponPoint, community, newMonthGoods};

		for(int i = 0; i < 6 ; i++) {
			upLabel[i].setSize(190,43); //사이즈 설정
			upLabel[i].setLocation(58 + 245 * i, 160); //위치 설정
			/*위쪽 라벨 폰트 설정
			 * G마켓 산스 TTF Medium체, 사이즈 17
			*/
			//upLabel[i].setFont(new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 17));
			mainPane.add(upLabel[i]); // 메인 페이지에 버튼 추가
			
			//버튼마다 액션 리스터 달아줌.
			upLabel[i].addActionListener(new MainActionListner());
		}
		
		//---아래쪽 레이블 : 설정 중 . . .
		//JButton popularGoods = new
		//화면 기본 설정 - End
		setSize(1920, 1080); //윈도우 사이즈 1920, 1080 고정.
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