package goods;

import javax.swing.*;
import java.awt.event.*;

public class Search extends JFrame implements ActionListener{
	private JTextField searchField;
	private JButton searchButton;
	private String goodsName;
	
	public void actionPerformed(ActionEvent e) { //액션 설정
		if(e.getSource() == searchButton) { //'검색'버튼 입력이 인식되면
			goodsName = searchField.getText(); //입력 되어있는 텍스트 받아오기
		}
	}
	
	public String getSearch() { //getter
		return goodsName; //상품 이름 반환
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->{
			new Search();
		});
	}
}
