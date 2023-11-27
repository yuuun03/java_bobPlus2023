package goods;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Vector;
import admin.*;
import main.Cart;
import main.CommonPanel;
import user.UserInfoDetail;


public class SearchResult{
	JFrame resultFrame = new JFrame("Search Result");
	JPanel resultPanel = new JPanel ();
	
	public static void main(String[] args) {
		UserInfoDetail myUser = new UserInfoDetail(); //사용자 객체 미리 생성.
		SearchResult sr = new SearchResult(myUser);
	}
	
	public SearchResult() {}
	public SearchResult(UserInfoDetail myUser) {
		
		JPanel mainPanel = new JPanel();
		setTitle("밥심+"); //제목 설정
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("src/graphics/images/iconOnly.png");
		setIconImage(img);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(null);
		mainPanel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
		resultPanel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
		
		//로고, 검색창, 위쪽 레이블, 로그인, 장바구니 등 기본 패널 추가 -> MainFrame.java 복붙
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
		
		MasterGoods mg = new MasterGoods();
		Vector<Product> productList = mg.getPList(); //상품...받아오기...?...??
		
		for (int i=0;i<3;i++) {
			Product product = productList.get(i);
			
			JLabel name = new JLabel(product.getName());
			Image bImg = new ImageIcon(product.getImage()).getImage(); //사진 이렇게 불러오는 게 맞나요
			double star = product.getProductStar();
			JLabel starLabel = new JLabel ("별점:" + Double.toString(star));
			int price = product.getPrice();
			JLabel priceLabel = new JLabel(Integer.toString(price));
			double onePrice = product.getOnePersonPrice();
			JLabel onePriceLabel = new JLabel ("1인분당 " + Double.toString(onePrice)+"원");
			double disrate = product.getProductDisRate();
			JLabel disrateLabel = new JLabel(Double.toHexString(disrate)+"%");
			
			//출력 problem...
		}
	}
}
