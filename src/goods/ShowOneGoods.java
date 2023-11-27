package goods;

import admin.*;
import main.*;

import java.util.Vector;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ShowOneGoods {
	
	public static void main(String[] args) {
		MasterGoods mg = new MasterGoods();
		Vector<Product> pList = mg.getPList(); //상품...받아오기...?...??
		
		ShowOneGoods g = new ShowOneGoods(mg);
	}
	
	public ShowOneGoods() {}
	public ShowOneGoods(MasterGoods mg) { //매개변수로 상품을 받아야할 것 같은데 대체 어케 하나요...
		JPanel goodsPanel = new JPanel();
		
		setTitle("밥심+"); //제목 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		goodsPanel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
		
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
			}
		});
				
		df.my.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MyPage(myUser); //로그인 페이지 전환
				dispose(); //기존 페이지 안보이게 변경
			}
		});
					
		df.cart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Cart(myUser); //로그인 페이지 전환
				dispose(); //기존 페이지 안보이게 변경
			}
		});
		df.newHotGoods.addActionListener(new MyPageActionListener());
		df.weekTop10Goods.addActionListener(new MyPageActionListener());
		df.checkAttendance.addActionListener(new MyPageActionListener());
		df.couponPoint.addActionListener(new MyPageActionListener());
		df.communityU.addActionListener(new MyPageActionListener());
		df.newMonthGoods.addActionListener(new MyPageActionListener());
		
		
		JButton goodsInformation = new JButton("상품 상세 정보 더보기");
		JButton review = new JButton("리뷰");
		JButton intoCart = new JButton("장바구니 담기");
		JButton goBuy = new JButton("구매하기");
		
		JButton[] downLabel = {goodsInformation, review, intoCart, goBuy};
		
		//폰트 설정
		Font dLabelFont = new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 20);
		for(int j = 0; j < 4; j++) {
			downLabel[j].setVerticalTextPosition(JButton.BOTTOM); //수직 설정
			downLabel[j].setHorizontalTextPosition(JButton.CENTER);//수평 설정
		}
		
		setSize(1920, 1080);
		add(goodsPanel); //프레임에 패널 추가
		goodsPanel.setBackground(Color.white);
		setBackground(Color.white);
		setVisible(true); // 프레임 출력
	}
	
	//버튼 눌렀을 시 행동
	class OneGoodsAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton bRefer = (JButton)e.getSource();
			
			switch(bRefer.getText()) {
			
			case "상품 상세 정보 더보기": case "리뷰" :
				JOptionPane.showMessageDialog(null, "현재 기능 구현 중에 있습니다.");
				break;
			
			case "장바구니 담기":
				break; //장바구니로 넘어가는 기능
			
			case "구매하기":
				break; //구매 창으로 넘어가는 기능
			}
		}
	}
}