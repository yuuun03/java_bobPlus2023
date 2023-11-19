package admin;

import java.awt.*;
import javax.swing.*;

public class MemberLogin extends JFrame{
	public MemberLogin() {
		JPanel panel = new JPanel();
		JLabel idL = new JLabel("ID : ");
		JLabel pwL = new JLabel("PASSWORD : ");
		JTextField id = new JTextField(10);
		JPasswordField pw = new JPasswordField(10);
		JButton loginBtn = new JButton("LOGIN");
		
		panel.add(idL);
		panel.add(id);
		panel.add(pwL);
		panel.add(pw);
		panel.add(loginBtn);
		
		loginBtn.addActionListener(new ActionListener()) {
			public void actionPerformed(ActionEvent e) {
				String tID = id; // 아이디어케찾지
				String tPW = pw; // pw는id랑세트로찾으면됨
				
				if(tID.equals(id.getText()) && tPW.equals(pw.getText())) {
					JOptionPane.showMessageDialog(null, "로그인되었습니다.");
				} else {
					JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다.");
				}
			}
		});
		add(panel);
		
		setVisible(true);
		setSize(450, 80);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
