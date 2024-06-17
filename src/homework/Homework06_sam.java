package homework;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Homework06_sam {
	public static void main(String[] args) throws Exception {
		
		FileInputStream fis = new FileInputStream("d:/D_Other/test/Tulips.jpg");
		FileOutputStream fos = new FileOutputStream("d:/D_Other/test/NesTulips.jpg");
		
		
		int data = 0;
		while ((data = fis.read()) != -1) {
			fos.write(data);
		}
		
		System.out.println();
		System.out.println("복사완료...");
		
	}
}
