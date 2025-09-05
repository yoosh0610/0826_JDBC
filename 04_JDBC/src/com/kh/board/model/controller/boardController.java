package com.kh.board.model.controller;

import java.util.List;

import com.kh.board.model.dto.BoardDTO;
import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;

public class boardController {
	
	public int insertBoard(BoardDTO bd) {
		
		int result = new BoardService().insertBoard(bd);
		return result;
		//return new boardService().insertBoard(bd);
	}
	
	public List<Board> selectBoardList(){
		List<Board> boards = new BoardService().selectBoardList();
		
		return boards;
	}
	
	public Board selectBoard(int boardNo) {
		return new BoardService().selectBoard(boardNo);
	}
	
	public int deleteBoard(int boardNo) {
		
		return new BoardService().deleteBoard(boardNo);
		
		
	}
		
}
