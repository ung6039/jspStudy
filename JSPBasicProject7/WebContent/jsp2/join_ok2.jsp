<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.member.*"%>
<%
    try
    {
    	request.setCharacterEncoding("UTF-8");
    }catch(Exception ex){}
%>
<%--
      MemberBean mb=new MemberBean();
 --%>
<jsp:useBean id="mb" class="com.sist.member.MemberBean">
  <jsp:setProperty name="mb" property="*"/>
</jsp:useBean>

<%--
    String name=request.getParameter("name"); // name  <input type=text name=name>
    String sex=request.getParameter("sex");
    String addr=request.getParameter("addr");
    String tel=request.getParameter("tel");
    String age=request.getParameter("age");
    
    MemberBean mb=new MemberBean();
    mb.setName(request.getParameter("name"));
    mb.setSex(request.getParameter("sex"));
    mb.setAddr(request.getParameter("addr"));
    mb.setTel(request.getParameter("tel"));
    mb.setAge(Integer.parseInt(request.getParameter("age")));
 --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>전송받은 데이터 출력</h1>
   이름:<jsp:getProperty property="name" name="mb"/><br>
   성별:<jsp:getProperty property="sex" name="mb"/><br>
   주소:<%=mb.getAddr() %><br>
   전화:<%=mb.getTel() %><br>
   나이:<%=mb.getAge() %>
</body>
</html>










