package homework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Homework06_errorByDataCopy {
	public static void main(String[] args) throws IOException {
		
		FileInputStream fis = new FileInputStream("\\\\Desktop-l4q3igv\\공유폴더\\JAVA고급\\고급자바과제템플릿\\JavaIO\\파일복사\\Tulips.jpg");
				
				int data = 0;
				while ((data = fis.read()) != -1) {
		//			System.out.print(data);///숫자로 나온다
					System.out.print((char)data);
				}
				
				System.out.println();
				System.out.println("파일 읽기 작업 완료...");
				
		// 파일 출력하기
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream("d:/D_Other/Tulips.jpg");
			
			while ((data = fis.read())!=-1) {
				fos.write(data);
			}
			
			
			System.out.println("파일에 쓰기 작업 완료...");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
