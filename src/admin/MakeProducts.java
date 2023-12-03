package admin;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Vector;

import admin.*;

import javax.swing.ImageIcon;

public class MakeProducts{
	public MakeProducts() {} //기본 생성자
	
	public Vector<Product> make() {
		MasterGoods mg = new MasterGoods();
		
		String sdbImg = "src/graphics/images/soondooboo.png"; //순두부찌개 상품 이미지
		String bdImg = "src/graphics/images/budea.png"; //부대찌개 상품 이미지
		String kchImg = "src/graphics/images/kimchi.png"; //김치찌개 상품 이미지
		
		Vector<String> sdbCU = new Vector<>(); //순두부찌개 조리도구
		sdbCU.add("가스레인지");
		Vector<String> sdbAl = new Vector<>(); //순두부찌개 알러지 정보
		sdbAl.add("가금류");
				
		mg.productAdd("순두부 찌개",sdbImg,5.0,2,5,15000,20.0, sdbCU, sdbAl); //'순두부 찌개' 상품 추가
		
		
		Vector<String> bdCU = new Vector<>(); //부대찌개 조리도구
		bdCU.add("가스레인지");
		Vector<String> bdAl = new Vector<>(); //부대찌개 알러지 정보
		bdAl.add("가금류");
		bdAl.add("대두");
		bdAl.add("돼지고기");
				
		mg.productAdd("부대찌개",bdImg,5.0,2,5,16000,15.0, bdCU, bdAl); //'부대 찌개' 상품 추가
		
		
		Vector<String> kchCU = new Vector<>(); //김치찌개 조리도구
		kchCU.add("가스레인지");
		Vector<String> kchAl = new Vector<>(); //김치찌개 알러지 정보
		kchAl.add("돼지고기");
		kchAl.add("대두");
				
		mg.productAdd("김치찌개", kchImg,4.0,3,5,15000,12.0, kchCU, kchAl); //'김치 찌개' 상품 추가
		
		return mg.getPList();
	}
}