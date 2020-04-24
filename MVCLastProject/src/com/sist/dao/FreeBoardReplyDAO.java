package com.sist.dao;
import java.sql.ResultSet;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sist.vo.BoardReplyVO;
import com.sist.vo.ReplyVO;
public class FreeBoardReplyDAO {
   private static SqlSessionFactory ssf;
   static
   {
	   ssf=CreateSqlSessionFactory.getSsf();
   }
   
   public static List<BoardReplyVO> replyListData(Map map)
   {
	   List<BoardReplyVO> list=new ArrayList<BoardReplyVO>();
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   session.update("replyListData2",map);
		   list=(ArrayList<BoardReplyVO>)map.get("pResult");// CURSOR
		   // mybatis => CURSOR => List
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
	   return list;
   }
   // Model => DAO(매개변수로 전송)
   // 매개변수 => 클래스 => 값변경,추가가 가능하다 (주소에 의한 전달)
   public static void replyInsert(Map map)
   {
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   session.update("replyInsert2",map); //프로시저 호출 
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
   }
   
   public static int replyTotalPage(Map map)
   {
	   int total=0;
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   session.update("replyTotalPage2",map); //프로시저 호출 
		   total=(int)map.get("pTotal");
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
	   return total;
   }
   
   public static void replyUpdate(Map map)
   {
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   session.update("replyUpdate2",map); //프로시저 호출 
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
   }
   
   public static void replyReplyInsert(Map map)
   {
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   session.update("replyReplyInsert2",map); //프로시저 호출 
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
   }
   
   public static void replyDelete(Map map)
   {
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   session.update("replyDelete2",map); //프로시저 호출 
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
   }
   
   public static int replyCount(int bno)
   {
	   SqlSession session=null;
	   int count=0;
	   try
	   {
		   session=ssf.openSession();
		   count=session.selectOne("replyCount", bno);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
	   
	   return count;
   }
  
}










