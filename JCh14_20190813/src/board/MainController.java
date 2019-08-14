package board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.command.*;

/**
 * Servlet implementation class MainController
 */
@WebServlet("*.bbs")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String rURI = request.getRequestURI();
		String rPath = request.getContextPath();
		String cmdURI = rURI.substring(rPath.length());
		String viewPage = null;
		BoardCmd cmd = null;

		if (cmdURI.contentEquals("/boardList.bbs")
				|| cmdURI.contentEquals("/*.bbs") ) {
			cmd = new BoardListCmd();
			cmd.excute(request, response);
			viewPage="list.jsp";
		}


		// 
		if(cmdURI.equals("/boardWriteForm.bbs")){
			viewPage = "boardWrite.jsp";
		}
		
		// 글 작성 처리
		if(cmdURI.equals("/boardWrite.bbs")){
			cmd = new BoardWriteCmd();
			cmd.excute(request, response);
			viewPage = "boardList.bbs";
		}
		
		// 글 열람 처리
		if(cmdURI.equals("/boardRead.bbs")) {
			cmd = new BoardReadCmd();
			cmd.excute(request, response);
			viewPage = "boardRead.jsp";
		}
		
		// 글 수정 비밀번호 확인 화면 제공
		if(cmdURI.equals("/boardUpdatePassword.bbs")) {
			cmd = new BoardUpdatePasswordCmd();
			cmd.excute(request, response);			
			viewPage = "boardUpdatePassword.jsp";
		}
		
		// 글 수정 비밀번호 확인 처리
		if(cmdURI.equals("/boardUpdateCheck.bbs")) {
			cmd = new BoardUpdateCheckCmd();
			cmd.excute(request, response);
			
			BoardUpdateCheckCmd checkCmd = (BoardUpdateCheckCmd) cmd;
			if(checkCmd.password_check) {
				viewPage = "boardUpdateForm.bbs";
			} else {
				viewPage = "boardUpdateError.bbs";
			}
		}
		
		// �� ���� ��й�ȣ ���� ȭ�� ����		
		if(cmdURI.equals("/boardUpdateError.bbs")){
			viewPage = "boardUpdateError.jsp";
		}
		
		// �� ���� ȭ�� ����
		if(cmdURI.equals("/boardUpdateForm.bbs")){
			cmd = new BoardUpdateFormCmd();
			cmd.excute(request, response);
			viewPage = "boardUpdateForm.jsp";
		}
		
		// �� ���� ó��
		if(cmdURI.equals("/boardUpdate.bbs")){
			cmd = new BoardUpdateCmd();
			cmd.excute(request, response);
			viewPage = "boardList.bbs";
		}
		
		// �� ���� ��й�ȣ Ȯ�� ȭ�� ����
		if(cmdURI.equals("/boardDeletePassword.bbs")){
			cmd = new BoardDeletePasswordCmd();
			cmd.excute(request, response);			
			viewPage = "boardDeletePassword.jsp";
		}
		
		// 글 삭제 비밀번호 확인 처리
		if(cmdURI.equals("/boardDeleteCheck.bbs")){
			cmd = new BoardDeleteCheckCmd();
			cmd.excute(request, response);
			
			BoardDeleteCheckCmd checkCmd = (BoardDeleteCheckCmd) cmd;
			if (checkCmd.password_check && checkCmd.reply_check){
				viewPage = "boardDelete.bbs";				
			} else {
				viewPage = "boardDeleteError.bbs";
			}
		}
		
		// 글 삭제 비밀번호 오류 화면 제공
		if(cmdURI.equals("/boardDeleteError.bbs")){
			viewPage = "boardDeleteError.jsp";
		}
		
		// �� ���� ó��
		if(cmdURI.equals("/boardDelete.bbs")){
			cmd = new BoardDeleteCmd();
			cmd.excute(request, response);
			viewPage = "boardList.bbs";
		}
		
		// �� �˻� ó��
		if(cmdURI.equals("/boardSearch.bbs")){
			cmd = new BoardSearchCmd();
			cmd.excute(request, response);
			viewPage = "boardSearchList.jsp";
		}
		
		// ��� �ۼ� ȭ�� ����
		if(cmdURI.equals("/boardReplyForm.bbs")){
			cmd = new BoardReplyFormCmd();
			cmd.excute(request, response);
			viewPage = "boardReply.jsp";
		}
		
		// ��� �ۼ� ó��
		if(cmdURI.equals("/boardReply.bbs")){
			cmd = new BoardReplyCmd();
			cmd.excute(request, response);
			viewPage = "boardList.bbs";
		}
				

		RequestDispatcher rDis = request.getRequestDispatcher(viewPage);
		rDis.forward(request, response);
	}

}


















