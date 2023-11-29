package goods;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Arrays;
import java.util.Vector;

public class ShowSearchFilter extends JFrame{
	public JPanel filterPanel = new JPanel();
	
	public Vector<String> filterCU = new Vector<>();
    public Vector<String> filterAl = new Vector<>();
    
    public JCheckBox cookUtensils[] = new JCheckBox[5];
    public JCheckBox infoAllergy[] = new JCheckBox[22];
    
    //알러지 정보
    public String alName[] = {"가금류","게","고등어","굴","닭고기","대두","돼지고기","땅콩","메밀","밀","복숭아","새우","쇠고기","아황산포함식품","오징어","우유","잣","전복","조개류","토마토","호두","홍합"};
    
	public ShowSearchFilter() {
		filterPanel.setLayout(new GridLayout(0,1));
		JPanel UPanel = new JPanel();
		UPanel.setLayout(new GridLayout(0,1));
		JPanel alPanel = new JPanel();
		alPanel.setLayout(new GridLayout(0,2));
		
		filterPanel.add(UPanel);
		filterPanel.add(alPanel);
		
		JLabel titleCU = new JLabel(" < 필요 조리기구 > ");
		UPanel.add(titleCU);
		
		String cuName[] = {"가스레인지","오븐","에어프라이기","전자레인지"	,"조리기구 미필요"};
		
		for(int i=0; i < cookUtensils.length;i++) {
			cookUtensils[i] = new JCheckBox(cuName[i]);
			UPanel.add(cookUtensils[i]);
			cookUtensils[i].setContentAreaFilled(false);
		}
	
		
		JLabel blank = new JLabel("");
		UPanel.add(blank);
		
		JLabel titleAl = new JLabel(" < 알러지 필터링 > ");
		UPanel.add(titleAl);
		
		for(int i=0;i<alName.length;i++) {
			infoAllergy[i] = new JCheckBox(alName[i]);
			alPanel.add(infoAllergy[i]);
			infoAllergy[i].setContentAreaFilled(false);
		}
		
		UPanel.setBackground(new Color(200, 228, 137));
		alPanel.setBackground(new Color(200, 228, 137));
		
		Font customFont = new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 13);
		setFonts(filterPanel, customFont);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Filter Settings");
	
		filterPanel.setSize(200,530);
		filterPanel.setLocation(15, 240);
	}
	
	private void setFonts(Container container, Font font) {
		Component[] components = container.getComponents();

        for (Component comp : components) {
            if (comp instanceof JComponent) {
                ((JComponent) comp).setFont(font);
            }
            if (comp instanceof Container) {
                setFonts((Container) comp, font);
            }
        }
	}
	
	public Vector<String> getFilterCU() {return this.filterCU;}
	public Vector<String> getFilterAl() {return this.filterAl;}
	
	/*
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getItemSelectable();
		if (source instanceof JCheckBox) {
			JCheckBox checkBox = (JCheckBox) source;
			
	    // 선택 상태에 따라 해당하는 Vector를 업데이트
		if (checkBox.isSelected()) {
			if (Arrays.asList(cookUtensils).contains(checkBox)) {
				filterCU.add(checkBox.getText());
	            } else if (Arrays.asList(infoAllergy).contains(checkBox)) {
	                filterAl.add(checkBox.getText());
	            }
	        } else {
	            if (Arrays.asList(cookUtensils).contains(checkBox)) {
	                filterCU.remove(checkBox.getText());
	            } else if (Arrays.asList(infoAllergy).contains(checkBox)) {
	                filterAl.remove(checkBox.getText());
	            }
	        }
		}
	}*/
}