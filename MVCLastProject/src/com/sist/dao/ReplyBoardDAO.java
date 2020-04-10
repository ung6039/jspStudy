package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
/*
 *     프로그램 
 *     ======
 *       공통모듈 : 중복제거 
 *       핵심모듈 ==========> 코딩이 가능하게 만든다 (Spring)
 *       =========================================== AOP
 */
import java.util.*;
import com.sist.vo.*;
public class ReplyBoardDAO {
   private static SqlSessionFactory ssf;
   static
   {
	   ssf=CreateSqlSessionFactory.getSsf();
   }
   
   public static List<BoardVO> replyListData(Map map)
   {
	   List<BoardVO> list=new ArrayList<BoardVO>();
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   list=session.selectList("replyListData", map);
		   /*
		    *     1) SQL
		    *     2) PreparedStatement => 채우기
		    *        ps.setString(1,"")
		    *     3) ~VO
		    */
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
		   /*
		    *   Connection , PreparedStatement 
		    *   if(ps!=null) ps.close()
		    *   if(conn!=null) conn.close()
		    */
	   }
	   return list;
   }
   
   public static int replyTotalPage()
   {
	   int total=0;
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   total=session.selectOne("replyTotalPage");
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
	   finally
	   {
		   if(session!=null)
			    session.close();// 반환 => connection
	   }
	   return total;
   }
   // replyHitIncrement
   
   public static BoardVO replyDetailData(int no)
   {
	   BoardVO vo=new BoardVO();
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   session.update("replyHitIncrement",no);
		   session.commit();
		   vo=session.selectOne("replyDetailData", no);
		   /*
		    *     1) SQL
		    *     2) PreparedStatement => 채우기
		    *        ps.setString(1,"")
		    *     3) ~VO
		    */
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
		   /*
		    *   Connection , PreparedStatement 
		    *   if(ps!=null) ps.close()
		    *   if(conn!=null) conn.close()
		    */
	   }
	   return vo;
   }
   
   public static void replyInsert(BoardVO vo)
   {
	   
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession(true);
		   session.insert("replyInsert",vo);
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
	   finally
	   {
		   if(session!=null)
			    session.close();// 반환 => connection
	   }
	   
   }
}













