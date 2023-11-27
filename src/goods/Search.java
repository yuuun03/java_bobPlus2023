package goods;

import javax.swing.*;
import java.awt.event.*;

public class Search extends JFrame implements ActionListener{
	private JTextField searchField;
	private JButton searchButton;
	private String goodsName;
	
	public void actionPerformed(ActionEvent e) { //액션
		if(e.getSource() == searchButton) { //'검색'버튼 입력이 인식되면
			goodsName = searchField.getText(); //입력되어있는 텍스트 받아오기
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->{
			new Search();
		});
	}
	
	/*
	public void Search() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 200);
		
		JPanel pan = new JPanel();
		
		searchField = new JTextField(20);
		searchButton = new JButton("검색");
		searchButton.addActionListener(this);
		
		pan.add(searchField);
		pan.add(searchButton);
		
		add(pan);
		setVisible(true);
	}
	*/ //검색창 띄우는거 (CommonPanel이랑 겹침)
	
}
