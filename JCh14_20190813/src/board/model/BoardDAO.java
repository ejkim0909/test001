package board.model;
import java.util.ArrayList;
import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;

public class BoardDAO {
	DataSource ds;
	public static final int WRITING_PER_PAGE = 10;
	
	public BoardDAO() {
		try {
			Context iniCon= (Context)new InitialContext().lookup("java:comp/env");
			ds= (DataSource)iniCon.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<BoardDTO> boardList(String curPage) {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		int iPage = Integer.parseInt(curPage);
		try {
			conn=ds.getConnection();
String sql="select * from "
		+ " (select rownum rnum, num, name, subject, content, write_date, write_time, ref, step, lev, read_cnt, child_cnt "
		+ " from "
		+ " (select * from board order by ref desc, step asc ) "
		+ " board ) "
		+ " where rnum>= ? and rnum<= ?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, WRITING_PER_PAGE*(iPage-1)+1);
			pstmt.setInt(2, WRITING_PER_PAGE*iPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO write = new BoardDTO();
				write.setNum(rs.getInt(1));
				write.setName(rs.getString(2));
				write.setPassword(rs.getString(3));
				write.setSubject(rs.getString(4));
				write.setContent(rs.getString(5));
				write.setWriteDate(rs.getDate(6));
				write.setWriteTime(rs.getTime(7));
				write.setRef(rs.getInt(8));
				write.setStep(rs.getInt(9));
				write.setLev(rs.getInt(10));
				write.setReadCnt(rs.getInt(11));
				write.setChildCnt(rs.getInt(12));
				list.add(write);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
}
























