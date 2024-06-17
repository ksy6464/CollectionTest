package homework;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

import util.JDBCUtil2;

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
public class Homework08_noSearch {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
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
	
	public void searchDisplayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 검 색 선택 ===");
		System.out.println("  1. 작성자 검색");
		System.out.println("  2. 제목 검색");
		System.out.println("  3. 내용 검색");
		System.out.println("  4. 검색 종료");
		System.out.println("----------------------");
		System.out.print("원하는 검색 선택 >> ");
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
		
		try {
			conn = JDBCUtil2.getConnection();
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(" select * from jdbc_board ");
			
		
			
			while (rs.next()) {
				int boardNo = rs.getInt("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				String boardContent = rs.getString("board_content");
				
				LocalDate boardDate = rs.getTimestamp("board_date").toLocalDateTime().toLocalDate();
				
				System.out.println(boardNo+"\t"+boardTitle+"\t"+boardWriter+"\t"+boardContent+"\t"+boardDate);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
			
		}
		
	}

	private void search() {
		int choice;
		do{
			searchDisplayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  
					searchWriter();
					break;
				case 2 :  
					searchTitle();
					break;
				case 3 : 
					searchContent();
					break;
				case 4 :  // 작업 끝
					System.out.println("검색 작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=4);
		
		
	}

	private void searchContent() {
		scan.nextLine();
		System.out.println();
		System.out.println("검색할 내용을 입력해주세요");
		System.out.println("내용 >>");
		String boardContent = scan.nextLine(); 
		
		try {
			conn = JDBCUtil2.getConnection();
			
			String sql = "select * from jdbc_board where board_content like ?";
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+boardContent+"%");
			
			rs = pstmt.executeQuery();
			
			System.out.println();
			System.out.println("-----------------------------------------");
			System.out.println(" 게시글 번호\t제목\t작성자\t내용\t\t작성일");
			System.out.println("-----------------------------------------");
		
			
			while (rs.next()) {
				int boardNo = rs.getInt("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				boardContent = rs.getString("board_content");
				
				LocalDate boardDate = rs.getTimestamp("board_date").toLocalDateTime().toLocalDate();
				
				System.out.println(boardNo+"\t"+boardTitle+"\t"+boardWriter+"\t"+boardContent+"\t"+boardDate);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
			
		}
	}

	private void searchTitle() {
		System.out.println();
		System.out.println("검색할 제목을 입력해주세요");
		System.out.println("제목 >>");
		String boardTitle = scan.next();
		
		try {
			conn = JDBCUtil2.getConnection();
			
			String sql = "select * from jdbc_board where board_title like ?";
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+boardTitle+"%");
			
			rs = pstmt.executeQuery();
			
			System.out.println();
			System.out.println("-----------------------------------------");
			System.out.println(" 게시글 번호\t제목\t작성자\t내용\t\t작성일");
			System.out.println("-----------------------------------------");
		
			
			while (rs.next()) {
				int boardNo = rs.getInt("board_no");
				boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				String boardContent = rs.getString("board_content");
				
				LocalDate boardDate = rs.getTimestamp("board_date").toLocalDateTime().toLocalDate();
				
				System.out.println(boardNo+"\t"+boardTitle+"\t"+boardWriter+"\t"+boardContent+"\t"+boardDate);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
			
		}
	}

	private void searchWriter() {
		System.out.println();
		System.out.println("검색할 작성자를 입력해주세요");
		System.out.println("작성자 >>");
		String boardWriter = scan.next();
		
		try {
			conn = JDBCUtil2.getConnection();
			
			String sql = "select * from jdbc_board where board_writer like ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardWriter);
			
			rs = pstmt.executeQuery();
			
			System.out.println();
			System.out.println("-----------------------------------------");
			System.out.println(" 게시글 번호\t제목\t작성자\t내용\t\t작성일");
			System.out.println("-----------------------------------------");
		
			
			while (rs.next()) {
				int boardNo = rs.getInt("board_no");
				String boardTitle = rs.getString("board_title");
				boardWriter = rs.getString("board_writer");
				String boardContent = rs.getString("board_content");
				
				LocalDate boardDate = rs.getTimestamp("board_date").toLocalDateTime().toLocalDate();
				
				System.out.println(boardNo+"\t"+boardTitle+"\t"+boardWriter+"\t"+boardContent+"\t"+boardDate);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
			
		}
		
		
	}


	private void delete() {
		System.out.println();
		System.out.println("삭제할 게시글의 번호를 입력해주세요");
		System.out.println("게시글 번호 >>");
		int boardNo = scan.nextInt();
		
		try {
			conn = JDBCUtil2.getConnection();
			
			String sql = "delete from jdbc_board where board_no=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("게시글 삭제 성공!");
			}else {
				System.out.println("게시글 삭제 실패!!!");
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
			
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
		/// insert에 필요한 기본 정보 다 받았다. 이제  JDBC 해보겠다
		
		//////////////////////////////////////////////////////
		
		try {
			
			conn = JDBCUtil2.getConnection();
			
			String sql = "update jdbc_board set board_title=?, board_writer=?, board_content=?\r\n" + 
					"where board_no=?";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardWriter);
			pstmt.setString(3, boardContent);
			pstmt.setInt(4, boardNo);
			
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt);
			
			if(cnt > 0) {
				System.out.println("게시글 수정 성공!");
			}else {
				System.out.println("게시글 수정 실패!!!");
				
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
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
		try {
			conn = JDBCUtil2.getConnection();
			
			String sql = "insert into jdbc_board(board_no, board_title, board_writer, board_date, board_content)\r\n" + 
					"values(board_seq.nextval, ?, ?, sysdate,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardWriter);
			pstmt.setString(3, boardContent);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("게시글 등록 성공!");
			}else {
				System.out.println("게시글 등록 실패!!!");
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
			
		}
		
		
		
	}
	public static void main(String[] args) {
		Homework08_noSearch memObj = new Homework08_noSearch();
		memObj.start();
	}
	

	

}



