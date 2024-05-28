package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Set;

public class T07EqualsHashCodeTest {
/*
	 해사함수(Hash function)는 임의의 길이의 데이터를 고정된 길의 데이터로 매핑해주는함수이다
	 해시함수에 의해 얻어지는 값은 해시값, 해시코드, 또는 간단하게 해시라고도 부른다
	 
   	 HashSet, HashMap, HashTable과 같은 컬렉션 클래스를 사용할 경우에는
 	객체가 서로 같은지를 비료하기 위해 hashCode()와 equals() 메서드를 호출한다.
 	그러므로 객체가 서로 같은지를 절적히 판단하기 위해서는 두 메서드를 재정의 해주어야 한다.
 	 (객체가 추가되는 시점에 객체가 같은지 여부를 판단한다.)
 	 
 	 - equals()는 두 객체의 내용(값)이 같은지 비교하기 위한 메서드이고,
 	 - hashCode()는 객체에 대한 해시코드를 가져오기 위한 메서드이다. => 해시테이블 작성시 사용됨.
 	
 	 - euals()와 hashCode()메서드에 관련된 규칙(Convention) ///규칙, 지켜야될 사항
 	 
 	 1. 두 객체가 같으면 반드시 같은 hashCode값을 가져야 한다.
 	 2. 두 객체가 같으면 equals() 메서드를 호출했들 때 true를 반환해야 한다.
 	즉, 객체 a,b가 같아면 a.equals(b)와 b.equals(a) 둘다 true를 반환해야 한다.
 	 3. 두 객체의 hashCode가 같닥 해서 두 객체가 반드시 같은 객체는아니다.
 	하지만, 두객체가 같으면 반드시 hashCode가 같아야한다.
 	 4. equals()를 재정의 할때는 hashCode()도 재저의 해주어야 한다.
 	 5. hashCode()는 기본적으로 Heap메모리에 있는 각 객체에 메모리 주소 매칭 정보를 기반으로 정수값을 반환한다.
 	 ///hashCode()만 재정의 하고 equals()는 재정이 안하면 해쉬코드충돌이일어난다
 	그러므로, 클래스에서 hashCode()를 재정의 하지 않으면 절대로 두 객체가 같은 것으로 간주될 수 없다.
 	 
 	 
 */
	public static void main(String[] args) {
		
		Person p1 = new Person(1, "홍길동");
		Person p2 = new Person(1, "홍길동");
		Person p3 = new Person(1, "이순신");
		
		///만들어진 객체가 동일한지 체크하기 위해서 ==써보자 => object을 비교할때 ==의 의미는 두객체의 identity가 일치하는지 보는거다, 
		// 즉 본인이 맞는지, 나는 하나이다
		System.out.println("p1==p1 : "+ (p1 == p1)); ///이렇게 하면 같은게 나온다
		System.out.println("p1==p2 : "+ (p1 == p2));
		///만들어진 객체가 동일한지 체크하기 위해서 equals 써보자
		
		System.out.println("홍길동1".hashCode());
		System.out.println("홍길동1".hashCode());
		///문자열이 같으면  hashCode가 동일한다
		
		///해쉬함수가 충돌이 일어나는 예
		System.out.println("Aa".hashCode());
		System.out.println("BB".hashCode());
		///hashCode가 동일한 값이 나온다
		///이렇게 충돌이 있으므로 equals()도 호출에 줘야 한다
		
		
		
		
		System.out.println("p1.equals(p2) : "+ p1.equals(p2)); ///원래 false였다
		///우리는 이게 true가 나온게 하고 싶은거다 그래서 equal를 재정의 하겠다
		///equal재정의 해서 true 나옴

		
		Set<Person> pSet = new HashSet<Person>();
		
		System.out.println("add(p1) 성공여부 : "+pSet.add(p1));
		System.out.println("add(p2) 성공여부 : "+pSet.add(p2)); ///원래 true였다
		
		System.out.println("p1, p2 등록 데이터 : ");
		for (Person p : pSet) {
			System.out.println(p.getId() +" : "+p.getname());
		}
		///p1, p2는 서로 같은거로 보고 add가 안되게 하고 싶다. 중복을 허용안하고 싶다
		///그래서 override를 Hashcode도 해줘야 한다
		
		///hashCode() overriding 하고 나니깐 중복 체크가 돼서 add(p2)가 false한다
		
		
		System.out.println("add(p3) 성공여부 : "+pSet.add(p3)); 
		
		System.out.println("p3 등록 후 데이터 : ");
		for (Person p : pSet) {
			System.out.println(p.getId() +" : "+p.getname());
		}

		///우리가 만든 클래스는 원래있던 것들을 쓸수가 없으니깐 override를 많이 한다
		
		
		
	}


	
	
	

}


///hashCode()와  equals()를 재정의 하지 않았을떼 어떻게 되는지 먼저 살펴보겠다
class Person{ 
	private int id;
	private String name;
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}
	
	///이클립스에어 이미 만들어줘서 그냥 써도 된다
	///alt+shif+s > generate hashCode() & equals()
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	///우리가 만들어준 hashCode() & equals()
//	@Override
//	public boolean equals(Object obj) {
//		Person p = (Person) obj;
//		
//		return this.getId() == p.getId() && this.getname().equals(p.getname());
//	}
//	@Override
//	public int hashCode() {
//		
//		return (name + id).hashCode();///String class에 있는 hashCode호출한거다, 문자열이 동일하면 주소가 달라도 같은것으로 보겠다는거다
//	}

	
	
	
	
	
	
	
	
	
}
