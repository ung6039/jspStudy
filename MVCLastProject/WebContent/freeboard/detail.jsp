<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper row2">
  <div id="services" class="clear"> 
    <div class="row text-center">
     <img src="../freeboard/jsp_board2.png" style="width:900px;height:200px;">
    </div>
    <div class="row">
     <table class="table">
       <tr>
         <th width="20%" class="success text-center">번호</th>
         <td width="30%" class="text-center">${vo.no }</td>
         <th width="20%" class="success text-center">작성일</th>
         <td width="30%" class="text-center">
           <fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/>
         </td>
       </tr>
       <tr>
         <th width="20%" class="success text-center">이름</th>
         <td width="30%" class="text-center">${vo.name }</td>
         <th width="20%" class="success text-center">조회수</th>
         <td width="30%" class="text-center">${vo.hit }</td>
       </tr>
       <tr>
         <th width="20%" class="success text-center">제목</th>
         <td colspan="3" class="text-left">${vo.subject }</td>
       </tr>
       <tr>
         <td colspan="4" class="text-left" valign="top" height="200">
           <pre style="background-color: white;border: none;white-space: pre-wrap;">${vo.content }</pre>
         </td>
       </tr>
       <tr>
         <td colspan="4" class="text-right">
           <a href="../freeboard/update.do?no=${vo.no }" class="btn btn-xs btn-danger">수정</a>
           <a href="../freeboard/delete.do?no=${vo.no }" class="btn btn-xs btn-info">삭제</a>
           <a href="../freeboard/list.do" class="btn btn-xs btn-success">목록</a>
         </td>
       </tr>
     </table>
    </div>
  </div>
 </div>
</body>
</html>











