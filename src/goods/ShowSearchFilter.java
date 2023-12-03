//컴퓨터공학부 2022136067 양희정

package goods;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Arrays;
import java.util.Vector;

public class ShowSearchFilter extends JFrame{
	public JPanel filterPanel = new JPanel();
	
	public Vector<String> filterCU = new Vector<String>(); //조리도구 필터링
    public Vector<String> filterAl = new Vector<String>(); //알러지 필터링
    
    public JCheckBox cookUtensils[] = new JCheckBox[5]; //조리도구 체크박스
    public JCheckBox infoAllergy[] = new JCheckBox[22]; //알러지 체크박스
    
    //알러지 정보
    public String alName[] = {"가금류","게","고등어","굴","닭고기","대두","돼지고기","땅콩","메밀","밀","복숭아","새우","쇠고기","아황산포함식품","오징어","우유","잣","전복","조개류","토마토","호두","홍합"};
    
	public ShowSearchFilter() { //기본 생성자
		filterPanel.setLayout(new GridLayout(0,1)); //그리드 레이아웃 활용
		JPanel UPanel = new JPanel();
		UPanel.setLayout(new GridLayout(0,1)); //그리드 레이아웃 활용
		JPanel alPanel = new JPanel();
		alPanel.setLayout(new GridLayout(0,2)); //그리드 레이아웃 활용
		
		filterPanel.add(UPanel); //필터 패널에 조리도구 패널 추가
		filterPanel.add(alPanel); //필터 패널에 알러지 패널 추가
		
		JLabel titleCU = new JLabel(" < 필요 조리기구 > "); //조리도구 필터 제목 추가
		UPanel.add(titleCU); //조리도구 패널에 필터 제목 추가
		
		String cuName[] = {"가스레인지","오븐","에어프라이기","전자레인지"	,"조리기구 미필요"}; //조리도구 정보
		
		for(int i=0; i < cookUtensils.length;i++) {
			cookUtensils[i] = new JCheckBox(cuName[i]);
			UPanel.add(cookUtensils[i]); //조리도구 패널에 조리도구 체크박스 추가
			cookUtensils[i].setContentAreaFilled(false);
		}
	
		
		JLabel blank = new JLabel(""); //한 줄 띄우기
		UPanel.add(blank); //패널에 빈칸 설정
		
		JLabel titleAl = new JLabel(" < 알러지 필터링 > "); //알러지 필터 제목 추가
		UPanel.add(titleAl); //패널에 추가 (GridLayOut 사용을 위해 UPanel에 추가)
		
		for(int i=0;i<alName.length;i++) {
			infoAllergy[i] = new JCheckBox(alName[i]);
			alPanel.add(infoAllergy[i]); //알러지 패널에 알러지 체크박스 추가
			infoAllergy[i].setContentAreaFilled(false);
		}
		
		UPanel.setBackground(new Color(200, 228, 137)); //조리도구 패널 사이즈, 위치 설정
		alPanel.setBackground(new Color(200, 228, 137)); //알러지 패널 사이즈, 위치 설정
		
		Font customFont = new Font("G마켓 산스 TTF Medium", Font.CENTER_BASELINE, 13); //폰트 설정
		setFonts(filterPanel, customFont);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Filter Settings");
	
		filterPanel.setSize(200,530); //필터 패널 사이즈 설정
		filterPanel.setLocation(15, 240); //필터 패널 위치 설정
	}
	
	private void setFonts(Container container, Font font) { //폰트 설정 함수
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
}