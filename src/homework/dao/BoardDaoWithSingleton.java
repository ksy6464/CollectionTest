package homework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import homework.vo.boardVO;
import util.JDBCUtil2;

public class BoardDaoWithSingleton implements IBoardDao {
	
	private static IBoardDao boardDao = new BoardDaoWithSingleton();
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private BoardDaoWithSingleton() {
		
	}
	
	public static IBoardDao getInstance() {
		return boardDao;
	}

	@Override
	public int insert(boardVO bv) {
		
		int cnt = 0;
		
		try {
			conn = JDBCUtil2.getConnection();
			
			String sql = "insert into jdbc_board(board_no, board_title, board_writer, board_date, board_content)\r\n" + 
					"values(board_seq.nextval, ?, ?, sysdate,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bv.getBoardTitle());
			pstmt.setString(2, bv.getBoardWriter());
			pstmt.setString(3, bv.getBoardContent());
			
			cnt = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
			
		}
		return cnt;
	}

	@Override
	public int update(boardVO bv) {
		
		int cnt=0;
		
		try {
					
			conn = JDBCUtil2.getConnection();
			
			String sql = "update jdbc_board set board_title=?, board_writer=?, board_content=?\r\n" + 
					"where board_no=?";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoardTitle());
			pstmt.setString(2, bv.getBoardWriter());
			pstmt.setString(3, bv.getBoardContent());
			pstmt.setInt(4, bv.getBoardNo());
			
			cnt = pstmt.executeUpdate();
			System.out.println(cnt);
				
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				JDBCUtil2.close(conn, stmt, pstmt, rs);
			}
		
		return cnt;
	}

	@Override
	public int delete(int boardNo) {
		
		int cnt=0;
		
		try {
			conn = JDBCUtil2.getConnection();
			
			String sql = "delete from jdbc_board where board_no=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
			
		}
		return cnt;
	}

	@Override
	public List<boardVO> search(boardVO bv) {
		
		List<boardVO> boardList = new ArrayList<boardVO>();
		
		try {  
			conn = JDBCUtil2.getConnection();
			
			String sql = " select * from jdbc_board where 1=1 ";
			
			if(bv.getBoardTitle() != null && !bv.getBoardTitle().equals("")) {
				sql+=" and board_title = ? ";
			}
			if(bv.getBoardWriter() != null && !bv.getBoardWriter().equals("")) {
				sql+=" and board_writer = ? ";
			}
			if(bv.getBoardContent() != null && !bv.getBoardContent().equals("")) {
				sql+="  and board_content like '%'|| ? ||'%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			int paramIndex = 1;
			if(bv.getBoardTitle() != null && !bv.getBoardTitle().equals("")) {
				pstmt.setString(paramIndex++, bv.getBoardTitle());
			}
			if(bv.getBoardWriter() != null && !bv.getBoardWriter().equals("")) {
				pstmt.setString(paramIndex++, bv.getBoardWriter());
			}
			if(bv.getBoardContent() != null && !bv.getBoardContent().equals("")) {
				pstmt.setString(paramIndex++, bv.getBoardContent());
			}
			
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int boardNo = rs.getInt("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				String boardContent = rs.getString("board_content");
				
				LocalDate boardDate = rs.getTimestamp("board_date").toLocalDateTime().toLocalDate();
				
				boardVO bv2 = new boardVO();
				bv2.setBoardNo(boardNo);
				bv2.setBoardTitle(boardTitle);
				bv2.setBoardWriter(boardWriter);
				bv2.setBoardContent(boardContent);
				bv2.setBoardDate(boardDate);
				
				boardList.add(bv2);
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
			
		}
		
		
		
		return boardList;
	}

	@Override
	public List<boardVO> displayAll() {
		
		List<boardVO> boardList = new ArrayList<boardVO>();
		
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
				
				boardVO bv2 = new boardVO();
				bv2.setBoardNo(boardNo);
				bv2.setBoardTitle(boardTitle);
				bv2.setBoardWriter(boardWriter);
				bv2.setBoardContent(boardContent);
				bv2.setBoardDate(boardDate);
				
				boardList.add(bv2);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil2.close(conn, stmt, pstmt, rs);
			
		}
		return boardList;
	}
	


}
