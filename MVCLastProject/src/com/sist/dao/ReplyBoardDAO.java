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
   
   public static BoardVO replyUpdateData(int no)
   {
	   BoardVO vo=new BoardVO();
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
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
   
   public static String replyGetPassword(int no)
   {
	   String pwd="";
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   pwd=session.selectOne("replyGetPassword",no);
		   //System.out.println("DAO:pwd="+pwd);
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
	   finally
	   {
		   if(session!=null)
			    session.close();// 반환 => connection
	   }
	   return pwd;
   }
   
   public static void replyUpdate(BoardVO vo)
   {
	   
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession(true);
		   session.update("replyUpdate",vo);
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
   public static void replyReplyInsert(int pno,BoardVO vo)
   {
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   BoardVO pvo=session.selectOne("replyParentInfoData", pno);
		   session.update("replyGroupStepIncrement",pvo);
		   
		   // replyReplyInsert
		   vo.setGroup_id(pvo.getGroup_id());
		   vo.setGroup_step(pvo.getGroup_step()+1);
		   vo.setGroup_tab(pvo.getGroup_tab()+1);
		   vo.setRoot(pno);
		   
		   session.insert("replyReplyInsert", vo);
		   session.update("replyDepthIncrement", pno);
		   
		   session.commit();
		   
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
		   session.rollback();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
   }
   
   public static boolean replyDelete(int no,String pwd)
   {
	   boolean bCheck=false;
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession();
		   String db_pwd=session.selectOne("replyGetPassword", no);
		   if(db_pwd.equals(pwd))
		   {
			   bCheck=true;
			   BoardVO vo=session.selectOne("replyDeleteInfoData", no);
			   if(vo.getDepth()==0)
			   {
				   session.delete("replyDelete",no);
			   }
			   else
			   {
				   vo.setSubject("관리자가 삭제한 게시물입니다");
				   vo.setContent("관리자가 삭제한 게시물입니다");
				   vo.setNo(no);
				   session.update("replySubjectUpdate", vo);
			   }
			   session.update("replyDepthDecrement",vo.getRoot());
		   }
		   else
		   {
			   bCheck=false;
		   }
		   session.commit();
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
		   session.rollback();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
	   return bCheck;
   }
}













