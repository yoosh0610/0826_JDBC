package com.kh.board.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.board.model.dao.BoardDAO;
import com.kh.board.model.dto.BoardDTO;
import com.kh.board.model.vo.Board;
import com.kh.common.JDBCTemplate;
import com.kh.statement.model.dao.MemberDao;
import com.kh.statement.model.vo.Member;

public class BoardService {
	private Connection conn = null;
	
	public BoardService() {
		conn = JDBCTemplate.getConnection();
	}
	
	public int insertBoard(BoardDTO bd) {
		// 내가 입력한 값을 가지고
		// BOARD테이블에 한 행 INSERT해줘~
		int result = 0;
		
		
		// 1. 값의 유효성 검증
		if("".equals(bd.getBoardTitle().trim())) {
			return result;
		}
		// 제목 : 안녕하세요, 내용 : 반갑습니다, 아이디 : admin
		// 2. 인증 / 인가
		Member member = new MemberDao().findById(conn, bd.getBoardWriter());
		//조회된게 있으면 Member의 값의 주소값, 없으면 null
		if(member != null) {
			
			// 3. 데이터 가공
			int userNo = member.getUserNo();
			Board board = new Board(0,
									bd.getBoardTitle(),
									bd.getBoardContent(),
									String.valueOf(userNo),
									null,
									null);
			result = new BoardDAO().insertBoard(conn, board);
		}
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public List<Board> selectBoardList(){
		
		List<Board> board = new BoardDAO().selectBoardList(conn);
		
		new BoardDAO().outputHTML(conn);
		JDBCTemplate.close(conn);
		
		return board;
	}
	
	public Board selectBoard(int boardNo) {
		// 보드넘버 시퀀스 가지고 만든건데
		// 직접 만든게 아닌데
		// 1부터 시작인데 숫자 0이하값 들어오면 갈 필요 없는데
		// DB가면 돈드는데
		Board boards = new BoardDAO().selectBoard(conn, boardNo);

		JDBCTemplate.close(conn);
		
		return boards;
	}
	public int deleteBoard(int boardNo) {
		
		int result = new BoardDAO().deleteBoard(conn, boardNo);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	
	
	
}
