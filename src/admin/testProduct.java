package admin;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Vector;

import admin.*;

import javax.swing.ImageIcon;

public class testProduct{
	public static void main(String[] args) {
		MasterGoods mg = new MasterGoods();
		
		Image sdb = new ImageIcon("src/graphics/images/soondooboo.png").getImage();
		Image bd = new ImageIcon("src/graphics/images/budea.png").getImage();
		Image kch = new ImageIcon("src/graphics/images/kimchi.png").getImage();
		
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
	}
}