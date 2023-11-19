package graphics;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Font {
	try {
		File GmarketSansMedium = getFontFile();
	}
	
	public static File getFontFile(String resource) {
		String filePath = Font.class.getResource(resource).getPath();
	}

}
