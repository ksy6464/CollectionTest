package homework01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class StudentExample {
	public static void main(String[] args) {	
		
		List<Student> studentList = new ArrayList<Student>();
		studentList.add(new Student("1", "홍길동", 90, 80, 70));
		studentList.add(new Student("5", "변학도", 85, 85, 75));
		studentList.add(new Student("9", "성춘향", 90, 95, 95));
		studentList.add(new Student("3", "이순신", 85, 85, 80));
		studentList.add(new Student("6", "강감참", 80, 85, 75));
		studentList.add(new Student("2", "일지매", 100, 100, 100));
		
		
		System.out.println("기본");
		for (Student student : studentList) {   
			System.out.println(student);
		}
		System.out.println();
		
		System.out.println("학번 오름차순으로 정렬후");
		Collections.sort(studentList);
		for (Student student : studentList) {
			System.out.println(student);
		}
		System.out.println();
		
		Collections.sort(studentList, new SortNumDesc());
		System.out.println("총점의 역순으로 정렬");
		int rank = 1;
		for (Student student : studentList) {
			student.setRank(rank++);
			System.out.println(student);
		}

	}

}




