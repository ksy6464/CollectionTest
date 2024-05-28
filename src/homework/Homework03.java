package homework;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.sun.jndi.toolkit.ctx.StringHeadTail;


public class Homework03 {
	Scanner sc = new Scanner(System.in);
	private Map <String, HotelVO> hotelBookMap= new HashMap<String, HotelVO>();

	public static void main(String[] args) {
		Homework03 obj = new Homework03();
		obj.process();
	}

	public void process() {
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		while (true) {
			System.out.println();
			System.out.println("**************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
			System.out.println("**************************");
			int sel = sc.nextInt();
			
			switch(sel){
			case 1 : checkIn();		 
				break;
			case 2 : checkOut();		 
				break;
			case 3 : roomCondition();		 
				break;
			case 4 : workEnd();		 
				return;
			default :
				System.out.println("잘못 입력했습니다. 다시입력하세요.");
			} 
		}
		
	}

	private void workEnd() {
		System.out.println("**************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("**************************");
	}

	private void roomCondition() {
		System.out.println();

		Set<String> keySet = hotelBookMap.keySet();
		//key값이 String이니깐 만약 int로 했으면 <Integer>로 하기
		
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			System.out.println(hotelBookMap.get(key));
		}

	}

	private void checkOut() {
		System.out.println("어느 방을 체크아웃 하시겠습니다?");
		System.out.println("방번호 입력 =>");
		String roomNo = sc.next(); 
		
		if (hotelBookMap.remove(roomNo)==null) {
			System.out.println(roomNo+"방에는 체크인한 사람이 없습니다.");
		}else {
			System.out.println(roomNo+"호가  체크아웃 되었습니다.");
			
		}
		
	}

	private void checkIn() {
		System.out.println("어느 방에 체크인 하시겠습니다?");
		System.out.println("방번호 입력 =>");
		String roomNo = sc.next(); 
		
		if (hotelBookMap.get(roomNo)!=null) {
			System.out.println();
			System.out.println("누구를 체크인 하시겠습니까?");
			System.out.println("이름 입력 =>");
			String name = sc.next();
			System.out.println(roomNo+"방에는 이미 사람이 있습니다.");
			return;
		}
		
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.println("이름 입력 =>");
		String name = sc.next();
		
		hotelBookMap.put(roomNo, new HotelVO(roomNo, name));
		System.out.println("체크인 되었습니다.");
		
		
	}

}



class HotelVO {
	private String roomNo;
	private String name;
	
	public HotelVO(String roomNo, String name) {
		super();
		this.roomNo = roomNo;
		this.name = name;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "방번호 : " + roomNo + ", 투숙겍 : " + name;
	}
	
	
	
	
}
