package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class T04ListSortTest {
	
	public static void main(String[] args) {
		List<Member> memList = new ArrayList<Member>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "변학도", "010-2222-1111"));
		memList.add(new Member(9, "성춘향", "010-3333-1111"));
		memList.add(new Member(3, "이순신", "010-4444-1111"));
		memList.add(new Member(6, "강감참", "010-5555-1111"));
		memList.add(new Member(2, "일지매", "010-6666-1111"));
		
		System.out.println("정렬 전 : ");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("===============");
		
		Collections.sort(memList);
		
		System.out.println("이름 오름차순 정렬 후: ");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("===============");
		
		Collections.shuffle(memList);
		System.out.println("섞은 후: ");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("===============");
		
		Collections.sort(memList, new SortNumDesc()); //번호의내림차순
		System.out.println("번호의 내림차순 정렬 후: ");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("===============");
		
		
		
	}
	

	
}

// Member 객체의 번호를  내림차순으로 정렬하기
class SortNumDesc implements Comparator<Member>{
	public int compare(Member mem1, Member mem2) {
		///Interger는 comparable 객체이다
		
		//1.정석으로 우리가 만든것
//		if (mem1.getNum() > mem2.getNum()) {
//			return -1;
//			///음수만 리턴되면 되니깐 -100해도 상관 없음
//		}else if (mem1.getNum() == mem2.getNum()) {
//			return 0;
//		}else {
//			return 1;
//			
//		}
		
		///2. 원래 있던것 가져온 것
//		return new Integer(mem1.getNum()).compareTo(mem2.getNum())*-1; ///원래 있던것 가져온 것
		///3. 원래 있던것 가져온 것2
		return Integer.compare(mem1.getNum(), mem2.getNum())*-1;
		
	}
}





class Member implements Comparable<Member> {
	private int num;
	private String name;
	private String tel;
	
	//alt+shift+s+o
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}
	//	alt+shift+s+s
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}
	/**
	 * 이름을 기준으로 오름차순 정렬이 되로록 구현한다.
	 */
	@Override
	public int compareTo(Member mem) {
		return this.getName().compareTo(mem.getName());
		///이미 이름은 오름차순으로 되어 있어서 그걸 가져다 쓴건다,comparable
	}
	
}













