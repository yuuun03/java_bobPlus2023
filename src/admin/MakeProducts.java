package admin;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Vector;

import admin.*;

import javax.swing.ImageIcon;

public class MakeProducts{
	public MakeProducts() {}
	
	public Vector<Product> make() {
		MasterGoods mg = new MasterGoods();
		
		String sdbImg = "src/graphics/images/soondooboo.png";
		String bdImg = "src/graphics/images/budea.png";
		String kchImg = "src/graphics/images/kimchi.png";
		
		Vector<String> sdbCU = new Vector<>();
		sdbCU.add("가스레인지");
		sdbCU.add("인덕션");
		Vector<String> sdbAl = new Vector<>();
		sdbAl.add("가금류");
				
		mg.productAdd("순두부찌개",sdbImg,5.0,2,5,15000,20.0, sdbCU, sdbAl);
		
		
		Vector<String> bdCU = new Vector<>();
		bdCU.add("가스레인지");
		bdCU.add("인덕션");
		Vector<String> bdAl = new Vector<>();
		bdAl.add("가금류");
		bdAl.add("콩");
		bdAl.add("돼지고기");
				
		mg.productAdd("부대찌개",bdImg,5.0,2,5,16000,15.0, bdCU, bdAl);
		
		
		Vector<String> kchCU = new Vector<>();
		kchCU.add("가스레인지");
		kchCU.add("인덕션");
		Vector<String> kchAl = new Vector<>();
		kchAl.add("돼지고기");
		bdAl.add("콩");
				
		mg.productAdd("김치찌개", kchImg,4.0,3,5,15000,12.0, kchCU, kchAl);
		
		
		mg.checkProduct(); //확인용 출력
		return mg.getPList();
	}
}