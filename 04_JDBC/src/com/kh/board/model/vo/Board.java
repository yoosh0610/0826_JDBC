package com.kh.board.model.vo;

import java.sql.Date;
import java.util.Objects;

public class Board {
	private int boardNo; 		 // BOARD_NO NUMBER NUMBER
	private String boardTitle; 	 // BOARD_TITLE VARCHAR2
	private String boardContent; // BOARD_CONTENT VARCHAR2
	private String boardWriter;  // BOARD_WRITER NUMBER FOREIGN KEY(USERNO)
	private Date createDate; 	 // CREATE_DATE DATE
	private String deleteStatus; // DELETE_STATUS CHAR(1)
	
	// 모든 필드에 대한 매개변수생성자
	public Board(int boardNo, String boardTitle, String boardContent, String boardWriter, Date createDate,
			String deleteStatus) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.createDate = createDate;
		this.deleteStatus = deleteStatus;
	}

	// getter
	public int getBoardNo() {
		return boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	// equals, hashCode
	@Override
	public int hashCode() {
		return Objects.hash(boardContent, boardNo, boardTitle, boardWriter, createDate, deleteStatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		return Objects.equals(boardContent, other.boardContent) && boardNo == other.boardNo
				&& Objects.equals(boardTitle, other.boardTitle) && Objects.equals(boardWriter, other.boardWriter)
				&& Objects.equals(createDate, other.createDate) && Objects.equals(deleteStatus, other.deleteStatus);
	}
	
	
	
}
