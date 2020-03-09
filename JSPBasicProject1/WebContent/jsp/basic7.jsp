<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
    List<MusicVO> list=MovieDAO.musicAllData();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
   <div class="container">
     <h1 class="text-center">Music Top 50</h1>
     <div class="row">
       <table class="table table-hover">
         <tr class="danger">
           <th class="text-center">순위</th>
           <th class="text-center"></th>
           <th class="text-center">곡명</th>
           <th class="text-center">가수명</th>
           <th class="text-center">앨범</th>
         </tr>
         <%
            for(MusicVO vo:list)
            {
         %>
                <tr>
		           <td class="text-center"><%= vo.getRank() %></td>
		           <td class="text-center"><img src=<%=vo.getPoster() %> width=35 height=35></td>
		           <td><%=vo.getTitle()%></td>
		           <td><%=vo.getSinger() %></td>
		           <td><%=vo.getAlbum() %></td>
		         </tr> 
         <%
            }
         %>
       </table>
     </div>
   </div>
</body>
</html>








