package junit.test;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import cn.mmk.utils.ImageSizer;


public class ImageTest {
	@Test
	public void createImage(){
		try {
			ImageSizer.resize(new File("d:\\100_2844.gif"), new File("d:\\testabc.gif"), 200, "gif");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
