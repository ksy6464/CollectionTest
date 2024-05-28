package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.sun.org.apache.bcel.internal.generic.NEW;
import sun.awt.image.IntegerComponentRaster;

public class T01ArrayListTest {
	
	public static void main(String[] args) {
		
		
		// Default Capacity = 10
		List list1 = new ArrayList();
//		List list1 = new Vector<E>(); //이걸로 해도 에러 안난다
		
//		List list1 = new ArrayList(100); 이렇게 기본값을 더 늘려줄 수 있다
		
		// add() 메서드를 사용하여 데이터를 추가한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111); ///Integer라는 객체를 만들어서 거기 안에 int111을 넣어서 add한거다
//		list1.add(new Integer(111)); ///wrapper class로 위의 코드와 같은 의미이다 
		list1.add('k'); ///character
		list1.add(true);
		list1.add(12.34);
		
		//size() => 데이터의개수
		System.out.println("size() =>" + list1.size());
		System.out.println("list1() =>" + list1);
		
		// get()으로 데이터 가져오기
		System.out.println("1번째 데이터 : " + list1.get(0));

		
		// 데이터 끼워 넣기
		list1.add(0, "zzz");
		System.out.println("zzz 끼워넣기 후 : "+list1);
		
		//데이터 변경하기
		String temp = (String) list1.set(0, "YYY");
		System.out.println("temp : "+temp);
		System.out.println("첫번째 데이터를 YYY로 데이터를 변경 한 후 : "+list1);
		
		//데이터 삭제하기
		list1.remove(0);
		System.out.println("첫번째 데이터 삭제 후 : "+list1);
		
		list1.remove("bbb");
		System.out.println("bbb 데이터 삭제 후 : "+list1);
		
		///111지우기
		//나
//		list1.remove(1);
		list1.remove(new Integer(111));
//		list1.remove(new String("aaa"));
		System.out.println("aaa를 이렇게 해도 삭제됨?");
		list1.remove("aaa"); ///이렇게 해도 삭제됨?
		System.out.println("응 됨");
		System.out.println(list1);
		System.out.println("111 데이터 삭제 후 : "+list1);
		System.out.println("------------------------------------");
		
		// 제너릭을 이용하여 선언할 수 있다.
		List<String> list2 = new ArrayList<String>();
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");
//		list2.add(1111);
		
		for (String str : list2) {
			System.out.println(str);
		}
		System.out.println("------------------------------------");
		
		// contains(비교객체) => 리스트에 '비교객체'가 존재하면 true, 없으면 false 리턴함.
		System.out.println("DDD");
		System.out.println("ZZZ");
		
		// indexOf(비교객체)  => 리스트에서 '비교객체'를 찾아 존재하는 인덱스 값을 반환한다
//								존재하지 않으면 -1을 반환한다
		System.out.println("DDD의 index값 : "+ list2.indexOf("DDD"));
		System.out.println("ZZZ의 index값 : "+ list2.indexOf("ZZZ"));
		System.out.println("------------------------------------");

		//이렇게 하면 지워지지 않는 것드이 생긴다 애냐하면 리셋이 되면서 앞으로 계속 땡겨져서 있지도않은 인덱스를 지운다
		System.out.println(list2);
		for(int i=0; i<list2.size(); i++) {
			list2.remove(i);
		}
		
		//list2의 size가 매번 줄어들으므로 인덱스 값을 0으로 고정해서 for문 사용한다
		System.out.println(list2);
		for(int i=0; i<=list2.size(); i++) {
			list2.remove(0);
		}
		
//		list2.clear(); 그냥 인덱스에해당하는 값들 다 지우는 법
		
		System.out.println(list2);
		System.out.println("list2의 갯수 : " + list2.size());
		System.out.println(list2);
		
	
	}
	


}