<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<tr>
<td colspan ="7" align="right"><a href="#">[새글쓰기지롱]</a></td>
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
    <td>${aaa.num}</td>
    <td>
      <c:forEach begin="1" end="${aaa.lev}">
        <%= "&nbsp;&nbsp;" %>
      </c:forEach>
      ${aaa.subject}
    </td>
    <td>${aaa.name}</td>
    <td>${aaa.writeDate}</td>
    <td>${aaa.writeTime}</td>
    <td>${aaa.readCnt}</td>
    <td>${aaa.childCnt}</td>  
  </tr>
</c:forEach>



</table>
</body>
</html>
















