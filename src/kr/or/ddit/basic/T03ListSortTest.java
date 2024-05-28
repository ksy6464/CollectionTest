package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sun.security.provider.certpath.CollectionCertStore;

public class T03ListSortTest {
	public static void main(String[] args) {
		
		List<String> list = new ArrayList<String>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println("정렬 전 : "+list);
		
		// 정렬은 Collections.sort() 메서드를 이용하여 정렬한다.
		// 정렬은 기본적으로 '오름차순 정렬'을 수행한다
		/// 오름차순 1. 숫자 : 작은수부터 커지는 순서대로, 2. 문자 : 사전순서 
		Collections.sort(list);
		
		System.out.println("정렬 후 : "+list);
		
		
		
		//밑에 클래스를 만들었으므로이제 쓸 수가 있다
		Collections.shuffle(list); //섞기...
		
		System.out.println("섞은 후 : "+list);
		
		Collections.sort(list, new Desc());
		
		System.out.println("외부 정렬자를 이용한 정렬 후: "+list);
		///이것을 쓰는 이유는 이미 만들어진것이 아닌 나만의 정렬방식으로 쓰고 싶을때 외부 정렬방식을 쓰기 위해서 이다
		///정렬 기능을 담은 객체인 Comparator를 만들어서 나만의 정렬 정의
		
		
	}

}

///따로 클래스 바깥에다가 만들어주는걸 많이했는데 그냥 여기다가 함
///외부 정렬자
class Desc implements Comparator<String>{
/*
 * compare() 메서드의 반환값을 결정하는 방법
 * 
 * - 오름차순 정렬일 경우
 * => 앞의 값이 크면 양수, 같으면 0, 작으면 음수를 반환하도록 한다.
 * - 내림자순 정렬으 경우
 * */	
	@Override
	public int compare(String str1, String str2) {
//		return str1.compareTo(str2); ///이렇게 하면 오름차순 
		return str1.compareTo(str2)*-1 ; 
		///오름찬순, 내림차순은 부호가 반대이므로 -1을 곱해서 부호를바꿈으로서 내림차순으로 바꿔줌
	}
}
