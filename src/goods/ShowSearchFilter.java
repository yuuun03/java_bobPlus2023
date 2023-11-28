package goods;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Arrays;
import java.util.Vector;

public class ShowSearchFilter extends JFrame {
	JPanel filterPanel = new JPanel();
	
	public ShowSearchFilter() {
		filterPanel.setLayout(null); //배치관리자 없음 : 개발자 자유 배치
	
		JCheckBox cookUtensils[] = new JCheckBox[7];
		String cuName[] = {"가스레인지","뚝배기","오븐","에어프라이기","인덕션(전기레인지)","전자레인지"	,"조리기구 미필요"};
		Vector<String> filterCU = new Vector<String>(); //사용자가 선택한 조리기구
	
		JCheckBox storage[] = new JCheckBox[3];
		String stName[] = {"냉장보관","냉동보관","실온보관"};
		Vector<String> filterST = new Vector<String>(); //사용자가 선택한 보관 방식
	
		JCheckBox infoAllergy[] = new JCheckBox[22];
		String alName[] = {"가금류","게","고등어","굴","닭고기","대두","돼지고기","땅콩","메밀","밀","복숭아","새우","쇠고기","아황산포함식품","오징어","우유","잣","전복","조개류","토마토","호두","홍합"};
		Vector<String> filterAl = new Vector<String>(); //사용자가 선택한 알러지
		
		JLabel titleCU = new JLabel(" < 필요 조리기구 > ");
		JLabel titleST = new JLabel(" <보관 방식 > ");
		JLabel titleAl = new JLabel(" < 알러지 필터링 > ");
		
		for(int i=0; i<cookUtensils.length;i++) {
			cookUtensils[i] = new JCheckBox(cuName[i]);
			filterPanel.add(cookUtensils[i]);
			cookUtensils[i].addItemListener(this); //왜 이벤트 처리가 안될까요 ㅜㅜ,,,
		}
		for(int i=0;i<stName.length;i++) {
			storage[i] = new JCheckBox(stName[i]);
			filterPanel.add(storage[i]);
			storage[i].addItemListener(this);
		}
		for(int i=0;i<alName.length;i++) {
			infoAllergy[i] = new JCheckBox(alName[i]);
			filterPanel.add(infoAllergy[i]);
			infoAllergy[i].addItemListener(this);
		}
		
		cookUtensils[i].addItemListener(new FilterActionListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					JCheckBox now = (JCheckBox)(e.getSource());
					filterAl.add(now.getText());
				}
				else if(e.getStateChange() == ItemEvent.DESELECTED) {
					JCheckBox now = (JCheckBox)(e.getSource());
					filterAl.remove(now.getText());
				}
			}
		});
	}
}
	/*
	private int[] selectedStatusCookUtensils = new int[7];
    private int[] selectedStatusStorage = new int[3];
    private int[] selectedStatusInfoAllergy = new int[22];
    
    private void updateSelectedStatus(String[] names, int[] selectedStatus, int itemIndex, boolean isSelected) {
        if (isSelected) {
            selectedStatus[itemIndex] = 1;
        } else {
            selectedStatus[itemIndex] = -1;
        }
        getSelectedStatus(names, selectedStatus);
    }

    private int[] convertStatusToBinaryArray(int[] status) { //Vector로 변경??
        int[] binaryArray = new int[status.length];
        for (int i = 0; i < status.length; i++) {
            if (status[i] == 1) {
                binaryArray[i] = 1;
            } else {
                binaryArray[i] = 0;
            }
        }
        return binaryArray;
    }

    private int[] getSelectedStatus(String[] names, int[] selectedStatus) {
        System.out.println("Selected Status" + ": " + Arrays.toString(selectedStatus));
        return convertStatusToBinaryArray(selectedStatus);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();

        if (source instanceof JCheckBox) {
            JCheckBox checkBox = (JCheckBox) source;

            if (Arrays.asList(storage).contains(checkBox)) {
                int selectedIndex = Arrays.asList(storage).indexOf(checkBox);
                updateSelectedStatus(stName, selectedStatusStorage, selectedIndex, e.getStateChange() == ItemEvent.SELECTED);
            } else if (Arrays.asList(cookUtensils).contains(checkBox)) {
                int selectedIndex = Arrays.asList(cookUtensils).indexOf(checkBox);
                updateSelectedStatus(cuName, selectedStatusCookUtensils, selectedIndex, e.getStateChange() == ItemEvent.SELECTED);
            } else if (Arrays.asList(infoAllergy).contains(checkBox)) {
                int selectedIndex = Arrays.asList(infoAllergy).indexOf(checkBox);
                updateSelectedStatus(alName, selectedStatusInfoAllergy, selectedIndex, e.getStateChange() == ItemEvent.SELECTED);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ShowSearchFilter filter = new ShowSearchFilter("Filter");
            filter.setSize(300, 700);
            //필터 들어가는 프레임 사이즈 (이후 프레임 없애고 검색된 화면에 붙이는 방식으로 변경
            filter.setVisible(true); //보이게 하기
        });
    }
*/
