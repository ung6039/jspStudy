package com.sist.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> list=new ArrayList<String>();
	public void init(ServletConfig config) throws ServletException {
		// Path (web.xml)
		String path=config.getInitParameter("contextConfigLocation");
		HandlerMapping hm=new HandlerMapping(path);
		list=hm.getList();
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd=request.getRequestURI();// main/main.do
		
		cmd=cmd.substring(request.getContextPath().length()+1);
		System.out.println("cmd="+cmd);
		try
		{
			/*
			 *      com.sist.model.BoardModel
					com.sist.model.HomeModel
					com.sist.model.JoinModel
					com.sist.model.MainModel
					com.sist.model.Model
					com.sist.model.MovieModel
					com.sist.model.MyPageModel
					com.sist.model.ReserveModel
			 */
			for(String cls:list)
			{
				/*
				 *    @Controller
				 *    class A
				 *    
				 *    class B
				 *    
				 *    @Controller
				 *    class C
				 *    
				 *    
				 *    @Autowired
				 *    @RequestMapping("main/list.do")
				 *    메소드1
				 *    
				 *    @RequestMapping("main/update.do")
				 *    메소드2
				 *    
				 *    @RequestMapping("main/delete.do")
				 *    메소드3
				 */
				Class clsName=Class.forName(cls);
				if(clsName.isAnnotationPresent(Controller.class)==false)
					  continue;// 제외 
				Object obj=clsName.newInstance();
				
				// 메소드를 찾아서 호출 
				Method[] methods=clsName.getDeclaredMethods();
				
				for(Method m:methods)
				{
					RequestMapping rm=m.getAnnotation(RequestMapping.class);
					if(rm.value().equals(cmd))
					{
						String jsp=(String)m.invoke(obj, request,response);
						//  return "redirect:list.do"
						//  return "main/list.jsp"
						if(jsp.startsWith("redirect"))
						{
							response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
						}
						else
						{
							RequestDispatcher rd=request.getRequestDispatcher(jsp);
							rd.forward(request, response);
						}
						
						return;
					}
				}
			}
		}catch(Exception ex){}
		
	}
}
























