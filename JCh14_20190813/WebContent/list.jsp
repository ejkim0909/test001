<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록 조회</title>
</head>
<body>
		<h3>게시판 목록 조회</h3>
<table border="1">
<tr>
<td colspan ="7" align="right"><a href="boardWriteForm.bbs">[새글쓰기지롱]</a></td>
</tr>
<tr>
<th>글번호</th>
<th>제목</th>
<th>작성자</th>
<th>작성일</th>
<th>작성시간</th>
<th>조회수</th>
<th>답글수</th>
</tr>
<c:forEach items="${boardList }" var="aaa">
  <tr>
    <td><a href="boardRead.bbs?num=${aaa.num }">${aaa.num}</a></td>
    <td>
      <c:forEach begin="1" end="${aaa.lev}">
        <%= "&nbsp;&nbsp;" %>
      </c:forEach>
      <a href="boardRead.bbs?num=${aaa.num }">${aaa.subject}</a>
    </td>
    <td>${aaa.name}</td>
    <td>${aaa.writeDate}</td>
    <td>${aaa.writeTime}</td>
    <td>${aaa.readCnt}</td>
    <td>${aaa.childCnt}</td>  
  </tr>
</c:forEach>

			<tr>
				<td colspan="7">
				<a href="boardList.bbs">[첫 페이지로]</a>
					<c:forEach var ="i" begin="1" end ="${pageCnt}">
						<a href="boardList.bbs?curPage=${i}">[${i}]</a>
					</c:forEach>
				</td>
			
			</tr>
			
			<tr>
				<td colspan="7" align="center">
					<form action="boardSearch.bbs" method="post">
						<select name="searchOption">
							<option value="subject">제목</option>
							<option value="content">본문</option>
							<option value="both">제목+본문</option>
							<option value="name">작성자</option>
						</select>
						<input type="text" name="searchWord">
						<input type="submit" value="검색">
					</form>
				</td>
			</tr>
</table>
</body>
</html>
















