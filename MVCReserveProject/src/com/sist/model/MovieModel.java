package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.MovieDAO;
import com.sist.dao.MovieVO;
import com.sist.dao.ReserveTheaterVO;

import java.text.SimpleDateFormat;
import java.util.*;
@Controller
public class MovieModel {
   @RequestMapping("movie/reserve.do")
   public String movie_reserve(HttpServletRequest request,HttpServletResponse response)
   {
	   return "reserve.jsp";
   }
   @RequestMapping("movie/movie.do")
   public String movie_movie(HttpServletRequest request,HttpServletResponse response)
   {
	   List<MovieVO> list=MovieDAO.movieListData();
	   request.setAttribute("mList", list);
	   
	   return "movie.jsp";
   }
   
   @RequestMapping("movie/theater.do")
   public String movie_theater(HttpServletRequest request,HttpServletResponse response)
   {
	   String tno=request.getParameter("tno");
	   // DAO연결 
	   // 결과값을 theater.jsp
	   StringTokenizer st=new StringTokenizer(tno,",");
	   List<ReserveTheaterVO> list=new ArrayList<ReserveTheaterVO>();
	   while(st.hasMoreTokens())
	   {
		   ReserveTheaterVO vo=MovieDAO.movieTheaterData(Integer.parseInt(st.nextToken()));
		   list.add(vo);
	   }
	   
	   request.setAttribute("tList", list);
	   
	   return "theater.jsp";
   }
   
   @RequestMapping("movie/date.do")
   public String movie_date(HttpServletRequest request,HttpServletResponse response)
   {
	   String strYear=request.getParameter("year");
	   String strMonth=request.getParameter("month");
	   
	   String reserve_date=request.getParameter("rdate");
	   
	   String today=new SimpleDateFormat("yyyy-M-d").format(new Date());
	   StringTokenizer st=new StringTokenizer(today,"-");
	   
	   String sy=st.nextToken();
	   String sm=st.nextToken();
	   String sd=st.nextToken();
	   
	   if(strYear==null)
		   strYear=sy;
	   if(strMonth==null)
		   strMonth=sm;
	   
	   int year=Integer.parseInt(strYear);
	   int month=Integer.parseInt(strMonth);
	   int day=Integer.parseInt(sd);
	   
	   
	   String[] strWeek={"일","월","화","수","목","금","토"};
	   
	   int total=(year-1)*365
			    +(year-1)/4
			    -(year-1)/100
			    +(year-1)/400;
	   
	   int[] lastDay={31,28,31,30,31,30,
			          31,31,30,31,30,31};
	   
	   if((year%4==0 && year%100!=0)||(year%400==0))
		   lastDay[1]=29;
	   else
		   lastDay[1]=28;
		
	   for(int i=0;i<month-1;i++)
	   {
		   total+=lastDay[i];
	   }
	   
	   total++;
	   
	   int week=total%7;
	   
	   int[] days=new int[31];
	   if(reserve_date!=null)
	   {
		   // 1,2,3,7,8,10...
		   StringTokenizer st1=new StringTokenizer(reserve_date,",");
		   while(st1.hasMoreTokens())
		   {
			  int p=Integer.parseInt(st1.nextToken());// 31
			  if(p>=day)
			  {
			     days[p-1]=p;
			  }
		   }
	   }
	   request.setAttribute("days", days);
	   request.setAttribute("lastday", lastDay[month-1]);
	   request.setAttribute("week", week);
	   request.setAttribute("year", year);
	   request.setAttribute("month", month);
	   request.setAttribute("day", day);
	   request.setAttribute("strWeek", strWeek);
	   return "date.jsp";
   }
}
















