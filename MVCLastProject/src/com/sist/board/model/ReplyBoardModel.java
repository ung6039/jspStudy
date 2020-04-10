package com.sist.board.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.vo.*;
@Controller
public class ReplyBoardModel {
   @RequestMapping("reply/list.do")
   public String reply_list(HttpServletRequest request,HttpServletResponse response)
   {
	   // 요청하 데이터를 가지고 온다 ==> DAO
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   Map map=new HashMap();
	   int rowSize=15;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=rowSize*curpage;
	   map.put("start", start);
	   map.put("end", end);
	   // => DAO
	   List<BoardVO> list=ReplyBoardDAO.replyListData(map);
	   int totalpage=ReplyBoardDAO.replyTotalPage();
	   
	   request.setAttribute("list", list);
	   request.setAttribute("curpage", curpage);
	   request.setAttribute("totalpage", totalpage);
	   /*
	    *   class Model
	    *   {
	    *       HttpServletRequest request;
	    *       public Model(HttpServletRequest request)
	    *       {
	    *           this.request=request;
	    *       }
	    *       public void addAttribute(String key,Object obj)
	    *       {
	    *           request.setAttribute(key,obj);
	    *       }
	    *   }
	    *   
	    *   Model model=new Model(request);
	    *   model.addAttribute("list",list)
	    *   
	    *   DispatcherServlet => Front Controller
	    *   Model => Controller
	    *   View  => JSP
	    *   Model => request
	    */
	   request.setAttribute("main_jsp", "../reply/list.jsp");
	   return "../main/main.jsp";
   }
   @RequestMapping("reply/detail.do")
   public String reply_detail(HttpServletRequest request,HttpServletResponse response)
   {
	   String no=request.getParameter("no");
	   BoardVO vo=ReplyBoardDAO.replyDetailData(Integer.parseInt(no));
	   request.setAttribute("vo", vo);
	   request.setAttribute("main_jsp", "../reply/detail.jsp");
	   return "../main/main.jsp";
   }
   
   @RequestMapping("reply/insert.do")
   public String reply_insert(HttpServletRequest request,HttpServletResponse response)
   {
	   request.setAttribute("main_jsp", "../reply/insert.jsp");
	   return "../main/main.jsp";
   }
   
   @RequestMapping("reply/insert_ok.do")
   public String reply_insert_ok(HttpServletRequest request,HttpServletResponse response)
   {
	   try
	   {
		   request.setCharacterEncoding("UTF-8");
	   }catch(Exception ex){}
	   
	   String name=request.getParameter("name");
	   String subject=request.getParameter("subject");
	   String content=request.getParameter("content");
	   String pwd=request.getParameter("pwd");
	   
	   BoardVO vo=new BoardVO();
	   vo.setName(name);
	   vo.setSubject(subject);
	   vo.setContent(content);
	   vo.setPwd(pwd);
	   
	   ReplyBoardDAO.replyInsert(vo);
	   //DAO연결
	   return "redirect:../reply/list.do";
   }
   
   @RequestMapping("reply/update.do")
   public String reply_update(HttpServletRequest request,HttpServletResponse response)
   {
	   String no=request.getParameter("no");
	   BoardVO vo=ReplyBoardDAO.replyUpdateData(Integer.parseInt(no));
	   request.setAttribute("vo", vo);
	   request.setAttribute("main_jsp", "../reply/update.jsp");
	   return "../main/main.jsp";
   }
   
   @RequestMapping("reply/password_check.do")
   public String reply_password_check(HttpServletRequest request,HttpServletResponse response)
   {
	   String no=request.getParameter("no");
	   String pwd=request.getParameter("pwd");
	   
	   String db_pwd=ReplyBoardDAO.replyGetPassword(Integer.parseInt(no));
	   int res=0;
	   if(db_pwd.equals(pwd))
	   {
		   res=1;
	   }
	   else
	   {
		   res=0;
	   }
	   request.setAttribute("result", res);
	   return "../reply/password_check.jsp";
   }
   
   @RequestMapping("reply/update_ok.do")
   public String reply_update_ok(HttpServletRequest request,HttpServletResponse response)
   {
	   try
	   {
		   request.setCharacterEncoding("UTF-8");
	   }catch(Exception ex){}
	   
	   String no=request.getParameter("no");
	   String name=request.getParameter("name");
	   String subject=request.getParameter("subject");
	   String content=request.getParameter("content");
	   String pwd=request.getParameter("pwd");
	   
	   BoardVO vo=new BoardVO();
	   vo.setName(name);
	   vo.setSubject(subject);
	   vo.setContent(content);
	   vo.setPwd(pwd);
	   vo.setNo(Integer.parseInt(no));
	   
	   ReplyBoardDAO.replyUpdate(vo);
	   return "redirect:../reply/detail.do?no="+no;
   }
}












