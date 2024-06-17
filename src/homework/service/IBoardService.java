package homework.service;

import java.util.List;

import homework.vo.boardVO;

public interface IBoardService {
	
	/**
	 * boardVO에 담겨진 게시판정보를 DB에 insert하기 위한 메서드
	 * @param bv 게시판 정보를 담은 boardVO객체
	 * @return  DB작업이 성공하면 1, 실패하면 0 반환됨.
	 */
	public int insertBoard(boardVO bv);
	
	/**
	 * boardVO에 담겨진 게시판정보를 DB에 update하기 위한 메서드
	 * @param bv 게시판 정보를 담은 boardVO객체
	 * @return  DB작업이 성공하면 1, 실패하면 0 반환됨.
	 */
	public int updateBoard(boardVO bv);
	
	/**
	 * 해당 게시판 정보를 삭제하기위한 메서드
	 * @param boardNo 삭제하고자 하는 게시판 번호
	 * @return 삭제처리가 성공하면 1, 실패하면 0 반환됨
	 */
	public int deleteBoard(int boardNo);
	
	/**
	 * 게시판 정보를 검색하기 위한 메서드
	 * @param bv 게시판 정보를 담은 boardVO객체
	 * @return 검색된 게시글 정보를 담은 List객체
	 */
	public List<boardVO> searchBoard(boardVO bv);
	
	/**
	 * DB에 존재하는 모든 게시판 정보를 가져오기 위한 메서드
	 * @return 모든 게시판 정보를 담은 List객체
	 */
	public List<boardVO> displayAllBoard();

}
