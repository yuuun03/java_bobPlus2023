package goods;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.*;
import java.awt.*;

public class Search {
	public void Search() {
		JButton button = new JButton("검색");
		JTextField goodsName = new JTextField(20);
		JPanel pan = new JPanel();
		pan.add(button);
		pan.add(goodsName);
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				label.setText(goodsName.getText());
			}
		});
		
		setVisible(true);
		setSize(600, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {

	};
}
