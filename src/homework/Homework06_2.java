package homework;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Homework06_2 {
	public static void main(String[] args) throws Exception {
		
		FileInputStream fis = new FileInputStream("d:/D_Other/test/Tulips.jpg");
		
//		FileOutputStream fos = new FileOutputStream("d:/D_Other/test/NesTulips.jpg");
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/test/NesTulips2.jpg");
		
		///입력도 버퍼 있으면 좋다고 하심 동영상 보고 추가하기
		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedOutputStream bos = new BufferedOutputStream(fos2,5);
		
		
		
		long startTime = System.currentTimeMillis();
		int data = 0;
//		while ((data = fis.read()) != -1) {
//			fos.write(data);
//		}
//		
//		System.out.println();
//		System.out.println("기본스트림으로 복사완료...");
//		long endTime = System.currentTimeMillis();
//		System.out.println("경과 시간(ms) : "+(endTime - startTime));
//		fos.close();
		
		System.out.println();
		startTime = System.currentTimeMillis();
		data = 0;
		while ((data = fis.read()) != -1) {
			bos.write(data);
		}
		bos.flush();
		bos.close();
		
		System.out.println();
		System.out.println("보조스트림복사완료...");
//		endTime = System.currentTimeMillis();
		long endTime = System.currentTimeMillis();
		System.out.println("경과 시간(ms) : "+(endTime - startTime));
		
	}
}
