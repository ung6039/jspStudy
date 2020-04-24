package com.sist.board.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.BoardReplyVO;
import com.sist.vo.BoardVO;

@Controller
/*
 *                       Model => DAO,Service,VO
 *    사용자 요청 (페이지) =======> Model (DAO) =======================> JSP출력 
 *                         ==========
 *                         @RequestMapping(사용자 요청한 URI주소)
 */
public class FreeBoardModel {
   @RequestMapping("freeboard/list.do")
   public String freeboard_list(HttpServletRequest request,HttpServletResponse response)
   {
	   //page
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   FreeBoardDAO dao=new FreeBoardDAO();
	   List<BoardVO> list=dao.freeboardListData(curpage);
	   for(BoardVO vo:list)
	   {
		   vo.setCount(FreeBoardReplyDAO.replyCount(vo.getNo()));
	   }
	   int totalpage=dao.freeboardTotalPage();
	   
	   request.setAttribute("curpage", curpage);
	   request.setAttribute("totalpage", totalpage);
	   request.setAttribute("list", list);
	   request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	   request.setAttribute("main_jsp", "../freeboard/list.jsp");
	   return "../main/main.jsp";
   }
   
   @RequestMapping("freeboard/insert.do")
   public String freeboard_insert(HttpServletRequest request,HttpServletResponse response)
   {
	   request.setAttribute("main_jsp", "../freeboard/insert.jsp");
	   return "../main/main.jsp";
   }
   
   @RequestMapping("freeboard/insert_ok.do")
   public String freeboard_insert_ok(HttpServletRequest request,HttpServletResponse response)
   {
	   // 메소드 => 매개변수가 3개 이상이면 클래스로 묶어서 전송 
	   //                         ========== ~VO(구조체(여러변수를 묶어준다)=>클래스)
	   // https://www.google.co.kr/search?source=hp&ei=UxGZXtvpMdKDoASN3oioCw&q=%EC%9E%90%EB%B0%94
	   // 사용자 보낸 데이터를 받는다 
	   try
	   {
		   request.setCharacterEncoding("UTF-8");// 디코딩 ( 송신:인코딩 , 수신:디코딩 )
		   // ASC (1byte) => Unicode(2byte)
		   // %A%ACC%
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
	   // DAO로 전송 => DAO에서 오라클로 보내준다 
	   FreeBoardDAO dao=new FreeBoardDAO();
	   // insert요청 
	   dao.freeboardInsert(vo);
	   return "redirect:../freeboard/list.do";
   }
   
   @RequestMapping("freeboard/detail.do")
   public String freeboard_detail(HttpServletRequest request,HttpServletResponse response)
   {
	   String no=request.getParameter("no");//bno
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   Map map=new HashMap();
	   int rowSize=10;
	   int start=(curpage*rowSize)-(rowSize-1);
	   int end=curpage*rowSize;
	   map.put("pStart", start);
	   map.put("pEnd", end);
	   map.put("pBno", Integer.parseInt(no));
	   List<BoardReplyVO> list=FreeBoardReplyDAO.replyListData(map);
	   
	   map=new HashMap();
	   map.put("pBno", Integer.parseInt(no));
	   int totalpage=FreeBoardReplyDAO.replyTotalPage(map);
	   // no주고 => vo를 받는다 
	   FreeBoardDAO dao=new FreeBoardDAO();
	   // vo를 받아온다 
	   BoardVO vo=dao.freeboardInfoData(Integer.parseInt(no), 1);
	   // jsp로 보내준다 
	   request.setAttribute("list", list);
	   request.setAttribute("curpage", curpage);
	   request.setAttribute("totalpage", totalpage);
	   request.setAttribute("vo", vo);
	   request.setAttribute("main_jsp", "../freeboard/detail.jsp");
	   return "../main/main.jsp";
   }
   
   @RequestMapping("freeboard/update.do")
   public String freeboard_update(HttpServletRequest request,HttpServletResponse response)
   {
	   String no=request.getParameter("no");
	   // no주고 => vo를 받는다 
	   FreeBoardDAO dao=new FreeBoardDAO();
	   // vo를 받아온다 
	   BoardVO vo=dao.freeboardInfoData(Integer.parseInt(no), 2);
	   // jsp로 보내준다 
	   request.setAttribute("vo", vo);
	   request.setAttribute("main_jsp", "../freeboard/update.jsp");
	   return "../main/main.jsp";
   }
   
   @RequestMapping("freeboard/update_ok.do")
   public String freeboard_update_ok(HttpServletRequest request,HttpServletResponse response)
   {
	   // 메소드 => 매개변수가 3개 이상이면 클래스로 묶어서 전송 
	   //                         ========== ~VO(구조체(여러변수를 묶어준다)=>클래스)
	   // https://www.google.co.kr/search?source=hp&ei=UxGZXtvpMdKDoASN3oioCw&q=%EC%9E%90%EB%B0%94
	   // 사용자 보낸 데이터를 받는다 
	   try
	   {
		   request.setCharacterEncoding("UTF-8");// 디코딩 ( 송신:인코딩 , 수신:디코딩 )
		   // ASC (1byte) => Unicode(2byte)
		   // %A%ACC%
	   }catch(Exception ex){}
	   
	   String name=request.getParameter("name");
	   String subject=request.getParameter("subject");
	   String content=request.getParameter("content");
	   String pwd=request.getParameter("pwd");
	   String no=request.getParameter("no");
	   
	   BoardVO vo=new BoardVO();
	   vo.setName(name);
	   vo.setSubject(subject);
	   vo.setContent(content);
	   vo.setPwd(pwd);
	   vo.setNo(Integer.parseInt(no));
	   // DAO로 전송 => DAO에서 오라클로 보내준다 
	   FreeBoardDAO dao=new FreeBoardDAO();
	   // update요청 
	   boolean bCheck=dao.freeboardUpdate(vo);
	   request.setAttribute("bCheck", bCheck);
	   request.setAttribute("no", no);
	   return "../freeboard/update_ok.jsp";
   }
   /*
    *   사용자 
    *    => delete.do?no=10   ===> Model   ==============> 요청한 JSP로 값을 전송 (화면에 출력)
    *                             ======
    *                             사용자 보내준 값을 받는다 
    */
   @RequestMapping("freeboard/delete.do")
   public String freeboard_delete(HttpServletRequest request,HttpServletResponse response)
   {
	   String no=request.getParameter("no");
	   request.setAttribute("no", no);
	   request.setAttribute("main_jsp", "../freeboard/delete.jsp");
	   return "../main/main.jsp";
   }
   
   @RequestMapping("freeboard/delete_ok.do")
   public String freeboard_delete_ok(HttpServletRequest request,HttpServletResponse response)
   {
	   String no=request.getParameter("no");
	   String pwd=request.getParameter("pwd");
	   // DAO연결 
	   FreeBoardDAO dao=new FreeBoardDAO();
	   boolean bCheck=dao.freeboardDelete(Integer.parseInt(no), pwd);
	   request.setAttribute("bCheck", bCheck);
	   return "../freeboard/delete_ok.jsp";
   }
   
   @RequestMapping("freeboard/reply_insert.do")
   public String freeboard_reply_insert(HttpServletRequest request,HttpServletResponse response)
   {
	   try
	   {
		   request.setCharacterEncoding("UTF-8");
	   }catch(Exception ex){}
	   String bno=request.getParameter("bno");
	   String msg=request.getParameter("msg");
	   HttpSession session=request.getSession();// request=>Session,Cookie
	   String id=(String)session.getAttribute("id");
	   String name=(String)session.getAttribute("name");
	   
	   // DAO => Map에 묶어서 전송
	   Map<String,Object> map=new HashMap<String,Object>();
	   map.put("pBno", Integer.parseInt(bno));
	   map.put("pId", id);
	   map.put("pName", name);
	   map.put("pMsg", msg);
	   // insert 처리 
	   FreeBoardReplyDAO.replyInsert(map);
	   return "redirect:../freeboard/detail.do?no="+bno;
   }
   
   @RequestMapping("freeboard/reply_update.do")
   public String freeboard_reply_update(HttpServletRequest request,HttpServletResponse response)
   {
	   try
	   {
		   request.setCharacterEncoding("UTF-8");
	   }catch(Exception ex){}
	   String bno=request.getParameter("bno");
	   String no=request.getParameter("no");
	   String msg=request.getParameter("msg");
	   
	   Map map=new HashMap();
	   map.put("pNo", Integer.parseInt(no));
	   map.put("pMsg", msg);
	   
	   FreeBoardReplyDAO.replyUpdate(map);
	   return "redirect:../freeboard/detail.do?no="+bno;
   }
   
   @RequestMapping("freeboard/reply_reply_insert.do")
   public String freeboard_reply_reply_insert(HttpServletRequest request,HttpServletResponse response)
   {
	   try
	   {
		  request.setCharacterEncoding("UTF-8");   
	   }catch(Exception ex){}
	   
	   String bno=request.getParameter("bno");
	   String pno=request.getParameter("pno");
	   String msg=request.getParameter("msg");
	   HttpSession session=request.getSession();
	   String id=(String)session.getAttribute("id");
	   String name=(String)session.getAttribute("name");
	   
	   Map map=new HashMap();
	   map.put("pBno", Integer.parseInt(bno));
	   map.put("pPno", Integer.parseInt(pno));
	   map.put("pId", id);
	   map.put("pName", name);
	   map.put("pMsg", msg);
	   // DAO
	   FreeBoardReplyDAO.replyReplyInsert(map);
	   return "redirect:../freeboard/detail.do?no="+bno;
   }
   @RequestMapping("freeboard/reply_delete.do")
   public String freeboard_reply_delete(HttpServletRequest request,HttpServletResponse response)
   {
	   String no=request.getParameter("no");
	   String bno=request.getParameter("bno");
	   Map map=new HashMap();
	   map.put("pNo", Integer.parseInt(no));
	   // DAO
	   FreeBoardReplyDAO.replyDelete(map);
	   return "redirect:../freeboard/detail.do?no="+bno;
   }
}













