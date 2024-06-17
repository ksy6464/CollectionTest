package homework;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Homework07_Lee {
	private Scanner scan = new Scanner(System.in);
	private Map<Integer, MemberVO> hotelMap;
	FileInputStream fis = null;
	BufferedInputStream bis = null;
	ObjectInputStream ois = null;
	FileOutputStream fos = null;
	BufferedOutputStream bos = null;
	ObjectOutputStream oos = null;
	boolean out=false;

	public static void main(String[] args) {
		new Homework07_Lee().hotelStart();
	}

	public Homework07_Lee() {
		scan = new Scanner(System.in);
		hotelMap = new HashMap<Integer, MemberVO>();
	}

	public void printMenu() {

		System.out.println();
		System.out.println("**************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("**************************");

	}

	public void hotelStart() {
		Object obj = null;
		try {
			fis = new FileInputStream("d:/D_Other/hotel.txt");
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);

			while ((obj = ois.readObject()) != null) { // 역직렬화
				// 읽어온 데이터를 원래의 객체형으로 변환 후 사용된다.
				MemberVO mem = (MemberVO) obj;
				hotelMap.put(mem.getNum(), new MemberVO(mem.getNum(), mem.getName()));
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");

		while (true) {
			if(out)break;
			printMenu();

			int menuNum = scan.nextInt();
			
			switch (menuNum) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				printAll();
				break;
			case 4:
				hotelEnd();
				break;

			default:
				System.out.println("잘못 입력했습니다. 다시입력하세요.");
				break;
			}
		}
	}

	private void hotelEnd() {

		System.out.println("**************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("**************************");
		Set<Integer> keySet = hotelMap.keySet();
		Iterator<Integer> it = keySet.iterator();
		try {
			fos = new FileOutputStream("d:/D_Other/hotel.txt");
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);
			while (it.hasNext()) {
				int num = it.next();
				oos.writeObject(hotelMap.get(num));
			}
			oos.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		out=true;
	}

	private void printAll() {

		Set<Integer> keySet = hotelMap.keySet();

		if (keySet.size() == 0) {
			System.out.println("등록된 고객의 정보가 존재하지 않습니다.");
		} else {
			Iterator<Integer> it = keySet.iterator();
			int cnt = 0;
			while (it.hasNext()) {
				cnt++;
				int num = it.next();
				MemberVO p = hotelMap.get(num);
				System.out.println(" " + cnt + "\t" + p.getName() + "\t" + num);
			}
		}
	}

	private void checkOut() {
		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int num = scan.nextInt();

		if (!hotelMap.containsKey(num)) {
			System.out.println(num + "방에는 체크인한 사람이 없습니다.");
			return;
		}
		hotelMap.remove(num);
		System.out.println("체크아웃 되었습니다.");
	}

	private void checkIn() {
		System.out.println();
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int num = scan.nextInt();

		if (hotelMap.get(num) != null) {
			System.out.println(hotelMap.get(num).getName() + "씨는 이미 등록된 사람입니다.");
			return;
		}
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String name = scan.next();

		hotelMap.put(num, new MemberVO(num, name));

		System.out.println("체크인 되었습니다.");

	}
}

class MemberVO implements Serializable {
	private int num;
	private String name;

	public MemberVO(int num, String name) {
		super();
		this.num = num;
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MemberVO [num=" + num + ", name=" + name + "]";
	}
}