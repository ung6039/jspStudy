<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*,java.net.*"%>
<%
    String fn=request.getParameter("fn");
    response.setHeader("Content-Disposition", "attachment;filename="
    		          +URLEncoder.encode(fn, "UTF-8"));
    File file=new File("c:\\upload\\"+fn);
    response.setContentLength((int)file.length());
    
    try
    {
    	BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
    	BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
    	int i=0;
    	byte[] buffer=new byte[1024];
    	while((i=bis.read(buffer, 0, 1024))!=-1)
    	{
    		bos.write(buffer, 0, i);
    	}
    	out.clear();
    	out=pageContext.pushBody();
    	bis.close();
    	bos.close();
    }catch(Exception ex){}
%>