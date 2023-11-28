package admin;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Vector;

import admin.*;

import javax.swing.ImageIcon;

public class testProduct{
	public static void main(String[] args) {
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
		
		/*
		Product soondooboo = new Product("순두부찌개","sdb",5.0,2,5,15000,20.0, new Vector<>(), new Vector<>());
		
		Vector<String> sdbCU = soondooboo.getCookingUtensils();
		sdbCU.add("가스레인지");
		sdbCU.add("인덕션");
		soondooboo.setCookingUtensils(sdbCU);
		
		Vector<String> sdbAl = soondooboo.getContainAllergy();
		sdbAl.add("가금류");
		soondooboo.setContainAllergy(sdbAl);
		
		Product budae = new Product ("부대찌개","bd",5.0,2,5,16000,15.0,new Vector<>(),new Vector<>());
		
		Vector<String> bdCU = budae.getCookingUtensils();
		bdCU.add("가스레인지");
		bdCU.add("인덕션");
		budae.setCookingUtensils(bdCU);
		
		Vector<String> bdAl = budae.getContainAllergy();
		bdAl.add("가금류");
		bdAl.add("콩");
		bdAl.add("돼지고기");
		budae.setContainAllergy(bdAl);
		
		
		Product kimchi = new Product ("김치찌개", "kch",4.0,3,5,15000,12.0,new Vector<>(),new Vector<>());
		
		Vector<String> kchCU = kimchi.getCookingUtensils();
		kchCU.add("가스레인지");
		kchCU.add("인덕션");
		kimchi.setCookingUtensils(kchCU);
		
		Vector<String> kchAl = kimchi.getContainAllergy();
		kchAl.add("돼지고기");
		bdAl.add("콩");
		kimchi.setContainAllergy(kchAl);
		
		mg.getPList().add(soondooboo);
		mg.getPList().add(budae);
		mg.getPList().add(kimchi);
	*/
	}
}