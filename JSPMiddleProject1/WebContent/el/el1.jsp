<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
        EL : 화면에 출력하는 언어 
        => 사용법 
           ${출력물} => {} 일반 자바변수(X)
           
           <%= 일반 자바변수 %>
           ${} => getParameter("id") => ${param.id}
           ${} => request.getAttribute("id")
                  => ${requestScope.id} => ${id}
           ${} => session.getAttribute("name","홍길동")
                  => ${sessionScope.name} => ${name}
                    예)
                String id="admin";
                ${id}(X)
                request.setAttribute("id",id)
                ${id}
                
                
                request.setAttribute("id1","admin");
                session.setAttribute("id2","hong");
                
                ${id1} => admin  ${id2} => hong
                ${sessionScope.id} ==> hong
                
 --%>
<%
     String name="홍길동";
     request.setAttribute("name", name);
     
     String n=(String)request.getAttribute("name");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  이름 : ${name }
  <%=n %>
</body>
</html>






