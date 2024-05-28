package homework01;

import java.util.Comparator;




class SortNumDesc implements Comparator<Student>{
	public int compare(Student mem1, Student mem2) {
		///Interger는 comparable 객체이다
		
		//1.정석으로 우리가 만든것
		if (mem1.getSum() > mem2.getSum()) {
			return -1;
		}else if (mem1.getSum() < mem2.getSum()) {
			return 1;
		}else {//점수가 같을 경우
			//학번의 내림차순으로
			//방법 ㅂ
			if (mem1.getIdNo().compareTo(mem2.getIdNo())==-1) {
				// mem1.getIdNo().compareTo(mem2.getIdNo())==-1 : 오른쪽이 더 크다는 뜻
				return 1; // 1이니깐 왼쪽이 더 크게 해준다 즉, 내림차순이다
			}else {
				return -1;
			}
			//방법2
//			 return mem1.getIdNo().compareTo(mem2.getIdNo())*-1
		}
		
		///2. 원래 있던것 가져온 것
//		return new Integer(mem1.getNum()).compareTo(mem2.getNum())*-1; ///원래 있던것 가져온 것
		///3. 원래 있던것 가져온 것2
//		return Integer.compare(mem1.getNum(), mem2.getNum())*-1;
		
	}
}



//public class Student {
public class Student implements Comparable <Student>{
//abstract //public class Student {
//class Student implements Comparable<Student> {
	private String idNo;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private int rank;
	



	public Student(String idNo, String name, int kor, int eng, int math) {
		super();
		this.idNo = idNo;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sum = (kor+eng+math);
		this.rank = rank;
	}




	public String getIdNo() {
		return idNo;
	}


	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getKor() {
		return kor;
	}


	public void setKor(int kor) {
		this.kor = kor;
	}


	public int getEng() {
		return eng;
	}


	public void setEng(int eng) {
		this.eng = eng;
	}


	public int getMath() {
		return math;
	}


	public void setMath(int math) {
		this.math = math;
	}


	public int getSum() {
		return sum;
	}


	public void setSum(int sum) {
		this.sum = sum;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return "Student [idNo=" + idNo + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math
				+ ", sum=" + sum + ", rank=" + rank + "]";
	}
	
	public int compareTo(Student mem) {
		///이미 이름은 오름차순으로 되어 있어서 그걸 가져다 쓴건다,comparable
		return this.getIdNo().compareTo(mem.getIdNo());
//		return this.getEng().comareTo(mem.getEng());
	}
	
}










