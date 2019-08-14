package board.command;

import board.model.*;
import javax.servlet.http.*;
import java.util.*;

public class BoardListCmd implements BoardCmd {
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<BoardDTO> list;
		
		//DAO의 메소드를 통해서  list에 전달함.
		BoardDAO dao=new BoardDAO();
		list = dao.boardList("1");
		
		request.setAttribute("boardList", list);
		
	}
}
