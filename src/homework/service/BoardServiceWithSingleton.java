package homework.service;

import java.util.List;

import homework.dao.BoardDaoWithSingleton;
import homework.dao.IBoardDao;
import homework.vo.boardVO;

public class BoardServiceWithSingleton implements IBoardService {
	
	private static IBoardService boardService = new BoardServiceWithSingleton();
	
	private IBoardDao boardDao;
	
	private BoardServiceWithSingleton() {
		boardDao = BoardDaoWithSingleton.getInstance();
	}
	
	public static IBoardService getInstance() {
		return boardService;
	}
	

	@Override
	public int insertBoard(boardVO bv) {
		int cnt = boardDao.insert(bv);
		return cnt;
	}

	@Override
	public int updateBoard(boardVO bv) {
		int cnt = boardDao.update(bv);
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = boardDao.delete(boardNo);
		return cnt;
	}

	@Override
	public List<boardVO> searchBoard(boardVO bv) {
//		List<boardVO> boardList = boardDao.search(bv);
//		return boardList;
		return boardDao.search(bv);
	}

	@Override
	public List<boardVO> displayAllBoard() {
		List<boardVO> boardList = boardDao.displayAll();
		return boardList;
	}

}
