package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class T05HashSetTest {
	public static void main(String[] args) {
		
		Set hs1 = new HashSet();
		// Set에 데이터를 추가할 때에도 add() 메서드를 이용한다.
		///끼워넣기가 불가능하다 why?내부적으로 인덱스 개념을 사용하지 않으므로
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
		System.out.println("Set 데이터 : "+hs1);
		System.out.println();
		
		//Set은 데이터의 인덱스 개념을 사용하지 않고, 중복을 허용하지 않는다.
		// 그래서이미 존재하는 데이터를 add하면 false를 반환화고, 데이터는 추가 되지 않는다
		boolean isAdded = hs1.add("FF");
		System.out.println("중복되지 않을 때 : "+isAdded);
		System.out.println("Set 데이터 : "+hs1);
		System.out.println();

		isAdded = hs1.add("CC");
		System.out.println("중복될 때 : "+isAdded);
		System.out.println("Set 데이터 : "+hs1);
		System.out.println();
		
		// Set은 데이터를 수정하는 방법을 따로 제공하지 않기때문에 해당 데이터를 삭제 한 후
		// 새로운 데이터를 추가해 주어야 한다.
		
		// 삭제하는 메서드
		// 1) clear() => Set 데이터 전체 삭제
		// 2) remove(삭제할 데이터) => 해당 데이터 삭제
		
		// 예) "FF"를 "EE"로 수정하기
		hs1.remove("FF");
		System.out.println("FF 삭제 후 Set 데이터 : "+hs1);
		System.out.println();
		
		hs1.add("EE");
		System.out.println("EE 추가 후 Set 데이터 : "+hs1);
		System.out.println("Set의 데이터 개수 : "+hs1.size());
		System.out.println();
		
		// Set은 인덱스 개념을 사용하지 않기때문에 List처름 인덱스를 하나씩 가져올 수 없다.
		// 그래서 데이터를 하나씩 접급하기 위한 Iterator객체를 사용한다.
		
		Iterator it = hs1.iterator();
		
		while (it.hasNext()) { // 다음에 가져올 데이터가 존재하면  true, 존재하지 않으면 false를 반환함
			/// it.hasNext() :  다음 값을 갖고 있나? 있으면  true라서 계속 실행, 값이 없으면 false여서 while문 멈춤
			// 다음 데이터를 가져오기
			System.out.println(it.next());
		}
		
		
		///로또 예제?랑 비슷하다고 하신듯...
		// 1~100 사이의 중복되지 않는 정수 5개 만들기
		Set<Integer> intRnd = new HashSet<Integer>();
		
		while (intRnd.size() <5) {
			int num = (int) (Math.random()*100+1);
			intRnd.add(num);
		}
		
		System.out.println("만들어진 난수들 : "+ intRnd);
		
		//Collection유형의 객체들은 서로 다른 자료구조로 쉽게 변경해서 사용할 수 있다.
		///Set으로 만든것을 List로 만들고 싶을때가 있을 수가 있다
		///ex.List를 갖고있는데 중복된 값들을 제거하고 싶으면 Set으로바꿔주면된다
		List<Integer> intRnList = new ArrayList<Integer>(intRnd);
		
		for (Integer num : intRnList) {
			System.out.print(num+" ");
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
