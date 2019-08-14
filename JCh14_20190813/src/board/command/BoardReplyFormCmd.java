package board.command;

import javax.servlet.http.*;
import board.model.*;

public class BoardReplyFormCmd implements BoardCmd{

	public void excute(HttpServletRequest request, HttpServletResponse response) {
		String inputNum = request.getParameter("num");
		BoardDAO dao = new BoardDAO();
		
		BoardDTO writing = dao.boardReplyForm(inputNum);
		request.setAttribute("boardReplyForm", writing);
	}
}
