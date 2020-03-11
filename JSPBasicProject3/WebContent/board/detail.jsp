<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
    // http://localhost/JSPBasicProject3/board/detail.jsp?no=15
    String no=request.getParameter("no");
    String strPage=request.getParameter("page");
    // no=> DAO로 전송 
    BoardDAO dao=new BoardDAO();
    // DAO에서 요청한 => VO를 받아 온다 
    BoardVO vo=dao.boardDetailData(Integer.parseInt(no), 0);
    // VO에 저장된 데이터를 HTML를 이용해서 출력 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<style type="text/css">
.row {
   margin: 0px auto;
   width:600px;
}
h2 {
  text-align: center;
}
</style>
</head>
<body>
    <div class="container">
      <h2>내용보기</h2>
      <div class="row">
        <table class="table table-hover">
          <tr>
           <th width=20% class="text-center success">번호</th>
           <td width=30% class="text-center"><%=vo.getNo() %></td>
           <th width=20% class="text-center success">작성일</th>
           <td width=30% class="text-center"><%=vo.getRegdate() %></td>
          </tr>
          <tr>
           <th width=20% class="text-center success">이름</th>
           <td width=30% class="text-center"><%=vo.getName() %></td>
           <th width=20% class="text-center success">조회수</th>
           <td width=30% class="text-center"><%=vo.getHit() %></td>
          </tr>
          <tr>
           <th width=20% class="text-center success">제목</th>
           <td colspan="3" class="text-left"><%=vo.getSubject() %></td>
          </tr>
          <tr>
            <td colspan="4" class="text-left" valign="top" height="200">
             <pre style="white-space: pre-wrap;background-color: white;border: none "><%=vo.getContent() %></pre>
            </td>
          </tr>
          <tr>
            <td colspan="4" class="text-right">
              <a href="reply.jsp?no=<%=vo.getNo() %>&page=<%=strPage %>" class="btn btn-xs btn-success">답변</a>
              <a href="update.jsp?no=<%=vo.getNo() %>&page=<%=strPage %>" class="btn btn-xs btn-primary">수정</a>
              <a href="#" class="btn btn-xs btn-danger">삭제</a>
              <a href="list.jsp?page=<%=strPage %>" class="btn btn-xs btn-info">목록</a>
            </td>
          </tr>
        </table>
      </div>
    </div>
</body>
</html>






















