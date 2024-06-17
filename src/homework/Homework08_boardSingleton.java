package homework;
import java.util.List;
import java.util.Scanner;

import homework.service.BoardServiceWithSingleton;
import homework.service.IBoardService;
import homework.vo.boardVO;

/*
	위의 테이블을 작성하고 게시판을 관리하는
	다음 기능들을 구현하시오.
	
	기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
	 
	------------------------------------------------------------
	
	게시판 테이블 구조 및 시퀀스
	
	create table jdbc_board(
	    board_no number not null,  -- 번호(자동증가)
	    board_title varchar2(100) not null, -- 제목
	    board_writer varchar2(50) not null, -- 작성자
	    board_date date not null,   -- 작성날짜
	    board_content clob,     -- 내용
	    constraint pk_jdbc_board primary key (board_no)
	);
	create sequence board_seq
	    start with 1   -- 시작번호
	    increment by 1; -- 증가값
			
	----------------------------------------------------------
	
	// 시퀀스의 다음 값 구하기
	//  시퀀스이름.nextVal

*/
public class Homework08_boardSingleton {
	
	private Scanner scan;
	private IBoardService boardService;
	
	public Homework08_boardSingleton() {
		boardService = BoardServiceWithSingleton.getInstance();
		scan = new Scanner(System.in);
		
	}
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 게시글 작성");
		System.out.println("  2. 게시글 삭제");
		System.out.println("  3. 게시글 수정");
		System.out.println("  4. 전체 목록 출력");
		System.out.println("  5. 게시글 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	

	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  
					insert();
					break;
				case 2 :  
					delete();
					break;
				case 3 : 
					update();
					break;
				case 4 :  
					displayAll();
					break;
				case 5 :  
					search();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
	}
	
	

	private void displayAll() {
		
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println(" 게시글 번호\t제목\t작성자\t내용\t\t작성일");
		System.out.println("-----------------------------------------");
		
		List<boardVO> boardList = boardService.displayAllBoard();
		
		if (boardList.size() == 0) {
			System.out.println("게시글 정보가 존재하지 않습니다.");
		}else {
			for (boardVO bv2 : boardList) {
				
				System.out.println(bv2.getBoardNo()+"\t"+bv2.getBoardTitle()+"\t"+bv2.getBoardWriter()+"\t"
				+bv2.getBoardContent()+"\t"+bv2.getBoardDate());
			}
		}
		System.out.println("-----------------------------------------");
		System.out.println("출력 끝...");

		
	}

	private void search() {
		scan.nextLine(); 
		System.out.println();
		System.out.println("검색할 정보를 입력하세요");
		
		System.out.println("제목 >>");
		String boardTitle = scan.nextLine().trim();
		
		System.out.println("작성자 >>");
		String boardWriter = scan.nextLine().trim(); ///좌우의 불필요한 공백 제거
		
		System.out.println("내용 >>");
		String boardContent = scan.nextLine().trim();
		
		boardVO bv = new boardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardContent(boardContent);
		
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println(" 게시글 번호\t제목\t작성자\t내용\t\t작성일");
		System.out.println("-----------------------------------------");
		
		List<boardVO> boardList = boardService.searchBoard(bv);
		
		if (boardList.size() == 0) {
			System.out.println("게시글 정보가 존재하지 않습니다.");
		}else {
			for (boardVO bv2 : boardList) {
				
				System.out.println(bv2.getBoardNo()+"\t"+bv2.getBoardTitle()+"\t"+bv2.getBoardWriter()+"\t"
				+bv2.getBoardContent()+"\t"+bv2.getBoardDate());
			}
		}
		System.out.println("-----------------------------------------");
		System.out.println("출력 끝...");
	
		
	}

	

	private void delete() {
		System.out.println();
		System.out.println("삭제할 게시글의 번호를 입력해주세요");
		System.out.println("게시글 번호 >>");
		int boardNo = scan.nextInt();
		
		
		int cnt = boardService.deleteBoard(boardNo);
		
		if(cnt > 0) {
			System.out.println("게시글 삭제 성공!");
		}else {
			System.out.println("게시글 삭제 실패!!!");
			
		}
		
	}
	
	private void update() {

		System.out.println();
		System.out.println("수정할 게시글의 번호를 입력해주세요");
		System.out.println("게시글 번호 >>");
		int boardNo = scan.nextInt();
		
		System.out.println("제목 >>");
		String boardTitle = scan.next();
		
		System.out.println("작성자 >>");
		String boardWriter = scan.next();
		
		scan.nextLine();
		
		System.out.println("작성 내용 >>");
		String boardContent = scan.nextLine(); 
		
		boardVO bv = new boardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardContent(boardContent);
	
		
		int cnt = boardService.insertBoard(bv);
		
		if(cnt > 0) {
			System.out.println("게시글 수정 성공!");
		}else {
			System.out.println("게시글 수정 실패!!!");
			
		}

	}

	/**
	 * 회원정보를 등록하기 위한 메서드
	 */
	private void insert() {
		

		
				
		System.out.println();
		System.out.println("게시글 작성을 위한 정보를 입력해주세요");
		System.out.println("제목 >>");
		String boardTitle = scan.next();
		
		System.out.println("작성자 >>");
		String boardWriter = scan.next();
		
		scan.nextLine();
		
		System.out.println("작성 내용 >>");
		String boardContent = scan.nextLine(); 
		/// insert에 필요한 기본 정보 다 받았다. 이제  JDBC 해보겠다
		
		//////////////////////////////////////////////////////

		boardVO bv = new boardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardContent(boardContent);
	
		
		int cnt = boardService.insertBoard(bv);
		
		if(cnt > 0) {
			System.out.println("게시글 등록 성공!");
		}else {
			System.out.println("게시글 등록 실패!!!");
			
		}
		
		
	}
	public static void main(String[] args) {
		Homework08_boardSingleton memObj = new Homework08_boardSingleton();
		memObj.start();
	}
	

	

}



