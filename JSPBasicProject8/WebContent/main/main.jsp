<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	try
	{
		request.setCharacterEncoding("UTF-8");
	}catch(Exception ex){}
   String mode=request.getParameter("mode");
   if(mode==null)
	   mode="0";
   int no=Integer.parseInt(mode);
   String jsp="home.jsp";
   
   switch(no)
   {
   case 0:
	   jsp="home.jsp";
	   break;
   case 1:
	   jsp="movie.jsp";
	   break;
   case 2:
	   jsp="music.jsp";
	   break;
   case 3:
	   jsp="find.jsp";
	   break;
   case 4:
	   jsp="find_ok.jsp";
	   break;
   case 5:
	   jsp="list.jsp";
	   break;
   case 6:
	   jsp="insert.jsp";
	   break;
   }
   
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
  <%@ include file="menu.jsp" %>
  
<div class="container">
  <jsp:include page="<%=jsp %>"></jsp:include>
</div>
  
</body>
</html>