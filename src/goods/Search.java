package goods;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Search extends JFrame implements ActionListener{
	private JTextField searchField;
	private JButton searchButton;
	private String goodsName;
	
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == searchButton) {
			goodsName = searchField.getText();
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->{
			new Search();
		});
	}
}
