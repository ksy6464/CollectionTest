package homework;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Homework02 {
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Homework02 obj = new Homework02();
		obj.process();
	}

	public void process() {
		while (true) {
			System.out.println("==========================");
			System.out.println("\tLotto 프로그램\t");
			System.out.println("--------------------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("==========================");
			System.out.println("메뉴선택 : 1  <-- 입력");
			System.out.println("메뉴선택 : 2  <-- 입력");
			int sel = sc.nextInt();
			if (sel==1) {
				System.out.println("(1000원에 로또번호 하나입니다.)");
				System.out.println("구입할 금액 입력 : ");
				int money = sc.nextInt();
				int ticket = (int) money/1000;
				
				
				TreeSet<Integer> intRnd = new TreeSet<Integer>();

				
				
				for (int i = 0; i < ticket; i++) {
					while (intRnd.size() <6) {
						int num = (int) (Math.random()*45+1);
						intRnd.add(num);
					}
					System.out.printf("로또번호%d : "+ intRnd,i+1);
					System.out.println();
					intRnd.clear();
				}

				//거스름돈 
				int change = money%2500;
				System.out.println();
				System.out.printf("받은 금액은 %d원이고 거스름돈은 %d원입니다.", money, change);
				System.out.println();
			}
			if (sel==2) {
				System.out.println("감사합니다.");
				break;
			}
			
		}
		

	}

}
