<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,com.oreilly.servlet.multipart.*,com.oreilly.servlet.*"%>
<%@ page import="java.io.*" %>
<%
    try
    {
    	 request.setCharacterEncoding("UTF-8");
    }catch(Exception ex){}

    String path="c:\\upload";
    int maxSize=100*1024*1024;
    String enctype="UTF-8";
    MultipartRequest mr=new MultipartRequest(request,path,maxSize,enctype,
    		     new DefaultFileRenamePolicy());
    String name=mr.getParameter("name");
    String subject=mr.getParameter("subject");
    String content=mr.getParameter("content");
    String pwd=mr.getParameter("pwd");
    String filename=mr.getOriginalFileName("upload");// 원래 (사용자 보내준 파일명 a.jpg)
    String fn=mr.getFilesystemName("upload");// 변경된 파일명 
    FileBoardVO vo=new FileBoardVO();
    vo.setName(name);
    vo.setSubject(subject);
    vo.setContent(content);
    vo.setPwd(pwd);
    
    if(filename==null)
    {
    	vo.setFilename("");
    	vo.setFilesize(0);
    }
    else
    {
    	File file=new File(path+"\\"+fn);
    	vo.setFilename(fn);
    	vo.setFilesize((int)file.length());
    }
    
    FileBoardDAO dao=new FileBoardDAO();
    // insert
    dao.boardInsert(vo);
    // 이동 
    response.sendRedirect("list.jsp");
    
%>









