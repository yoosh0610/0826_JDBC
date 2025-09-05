package com.kh.board.view;

import java.util.List;
import java.util.Scanner;

import com.kh.board.model.controller.boardController;
import com.kh.board.model.dto.BoardDTO;
import com.kh.board.model.vo.Board;

public class BoardView {
	private Scanner sc = new Scanner(System.in);
	private boardController bc = new boardController();
	
	public void mainMenu() {
		while(true) {
			System.out.println("게시판 서비스입니다!");
			// 전체 게시글 목록 조회
			selectBoardList();
			
			System.out.println("\n===========================");
			System.out.println("1. 게시글 상세조회");
			System.out.println("2. 게시글 작성하기");
			System.out.println("3. 게시글 삭제하기");
			System.out.println("9. 회원 메뉴로 돌아가기");
			System.out.println("메뉴를 선택해주세요. > ");
			int menuNo = sc.nextInt();
			sc.nextLine();
			
			switch(menuNo) {
			case 1: selectBoard(); break;
			case 2: insertBoard(); break;
			case 3: deleteBoard(); break;
			case 9: System.out.println("잘 가시오~~"); return;
			
			}
			
		}
	}
	
	/*
	 * SELECT / INSERT / UPDATE / DELETE
	 * 
	 * BOARD를 예시로 들어보겠음
	 * 
	 * INSERT : insertBoard()
	 * UPDATE : updatetBoard
	 * DELETE : deleteBoard
	 * SELECT(다수행) : selectBoardList()
	 * SELECT(단수행) : selectBoard()
	 * -----------------------------------------------
	 * INSERT : save()
	 * UPDATE : update()
	 * DELETE : deleteByXXX()
	 * SELECT(다수행) : findAll()
	 * SELECT(단수행) : findbyXXX()
	 */
	
	
	
	
	public void	insertBoard() {
		
		System.out.println("게시글 작성 서비스입니다.");
		
		System.out.println("아이디를 입력해주세요. > ");
		String userId = sc.nextLine();
		System.out.println("제목을 입력해주세요. > ");
		String boardTitle = sc.nextLine();
		System.out.println("본문을 입력해주세요. > ");
		String boardContent = sc.nextLine();
		
		// 뷰 1절 끝!
		int result =  bc.insertBoard(new BoardDTO(boardTitle, boardContent, userId));
		
		System.out.println();
		System.out.println();
		
		if(result > 0) {
			System.out.println("게시글 작성 성공!");
		} else {
		System.out.println("게시글 작성 실패..ㅠ");
		}
		
		System.out.println();
		System.out.println();
	}
	
	private void selectBoardList() {
		
		System.out.println();
		
		List<Board> boards = bc.selectBoardList();
		
		if(!boards.isEmpty()) {
			boards.stream().map(b -> "\n게시글번호 : " + b.getBoardNo()
									+ "\t제목 : " + b.getBoardTitle()
									+ "\t\t작성자 : " + b.getBoardWriter()
									+ "\t작성일 : " + b.getCreateDate())
							.forEach(System.out::print);
			
		} else {
			System.out.println("게시글이 존재하지 않습니다.");
			System.out.println("첫 게시글의 주인공이 되어보세요!");
		}
	}
	
	private void selectBoard() {
		
		System.out.println("게 시 글 상 세 조 회 서 비 스 입 니 다.");
		System.out.println("조회할 게시글 번호를 입력하세요. > ");
		int boardNo = sc.nextInt();
		sc.nextLine();
		
		Board board = bc.selectBoard(boardNo);
		
		if(board != null) {
			System.out.println("\n\n제목 : " + board.getBoardTitle());
			System.out.println("\n작성자 : " + board.getBoardWriter());
			System.out.println("\n작성일 : " + board.getCreateDate());
			System.out.println("\n본문 : ");
			System.out.println("-----------------------------------");
			System.out.println(board.getBoardContent());
			System.out.println("-----------------------------------");
			
		}else {
			System.out.println("존재하지 않는 게시글 번호입니다.");
			
		}
		
		while(true) {
			System.out.print("목록으로 돌아가려면 돌아가기를 입력하세요. > ");
			String exit = sc.nextLine();
			if("돌아가기".equals(exit)) {
				return;
			}
		}
		
	}
	
	private void deleteBoard() {
		System.out.println("주세요 보드번호 > ");
		int boardNo = sc.nextInt();
		sc.nextLine();
		
		if(bc.deleteBoard(boardNo) > 0) {
			System.out.println("지우기 성공!");
		} else {
			System.out.println("못지움 까비~");
		}
		
		
	}
}
