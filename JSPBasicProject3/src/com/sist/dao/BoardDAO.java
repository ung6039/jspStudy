package com.sist.dao;
import java.util.*;

import java.sql.*;
public class BoardDAO {
  private Connection conn;
  private PreparedStatement ps;
  private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
  
  // 드라이버 등록
  public BoardDAO()
  {
	  try
	  {
		  Class.forName("oracle.jdbc.driver.OracleDriver");
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
  }
  // 연결
  public void getConnection()
  {
	  try
	  {
		  conn=DriverManager.getConnection(URL,"hr","happy");
	  }catch(Exception ex){}
  }
  // 연결 해제 
  public void disConnection()
  {
	  try
	  {
		  if(ps!=null) ps.close();
		  if(conn!=null) conn.close();
	  }catch(Exception ex){}
  }
  /*
   *     List
   *     add(Object o)
   *     
   *     List<BoardVO>
   *     add(BoardVO vo)
   */
  
  // 기능 => 목록 출력 
  public List<BoardVO> boardListData(int page)
  {
	  List<BoardVO> list=new ArrayList<BoardVO>();
	  try
	  {
		  getConnection();
		  String sql="SELECT no,subject,name,regdate,hit,group_tab,num "
				    +"FROM (SELECT no,subject,name,regdate,hit,group_tab,rownum as num "
				    +"FROM (SELECT no,subject,name,regdate,hit,group_tab "
				    +"FROM replyBoard ORDER BY group_id DESC,group_step ASC)) "
				    +"WHERE num BETWEEN ? AND ?";
		  // rownum은 중간에서부터 잘라낼 수 없다 Top-N (rownum은 번호가 1번부터)
		  int rowSize=10;
		  int start=(rowSize*page)-(rowSize-1);
		  int end=rowSize*page;
		  
		  // 전송 
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, start);
		  ps.setInt(2, end);
		  
		  ResultSet rs=ps.executeQuery();
		  
		  while(rs.next())
		  {
			  BoardVO vo=new BoardVO();
			  vo.setNo(rs.getInt(1));
			  vo.setSubject(rs.getString(2));
			  vo.setName(rs.getString(3));
			  vo.setRegdate(rs.getDate(4));
			  vo.setHit(rs.getInt(5));
			  vo.setGroup_tab(rs.getInt(6));
			  list.add(vo);
		  }
		  
		  rs.close();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  disConnection();
	  }
	  return list;
  }
  public int boardRowCount()
  {
	  int count=0;
	  try
	  {
		  getConnection();
		  String sql="SELECT COUNT(*) FROM replyBoard";
		  ps=conn.prepareStatement(sql);
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  count=rs.getInt(1);
		  rs.close();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  disConnection();
	  }
	  return count;
  }
  public void boardInsert(BoardVO vo)
  {
	  try
	  {
		  getConnection();
		  String sql="INSERT INTO replyBoard(no,name,subject,content,pwd,group_id) VALUES("
				    +"rb_no_seq.nextval,?,?,?,?,(SELECT NVL(MAX(group_id)+1,1) FROM replyBoard))";
		  ps=conn.prepareStatement(sql);
		  ps.setString(1, vo.getName());
		  ps.setString(2, vo.getSubject());
		  ps.setString(3, vo.getContent());
		  ps.setString(4, vo.getPwd());
		  // 실행요청
		  ps.executeUpdate();
	  }catch(Exception ex)
	  {
		  ex.printStackTrace();
	  }
	  finally
	  {
		  disConnection();
	  }
  }
}











