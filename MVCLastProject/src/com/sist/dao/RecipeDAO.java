package com.sist.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
/*
 *         request                         request
 *                   service(request)                recipeListData(request)
 *     요청 =========> DispatcherServlet  ===========> Model <=====================> DAO
 *                     request를 받는다         <============  DAO를 받은 값을 request에 추가
 *                                                      request.setAttribute()
 *                  ====================   request
 *                        |
 *                        |  forward(request)
 *                      해당 JSP로 request를 전송
 *                      JSP => request에 담긴 데이터를 출력 
 *                      =============================> 메모리에 출력 =====> 사용자가 브라우저에서 읽기
 */
import java.util.*;
import com.sist.vo.*;
public class RecipeDAO {
   private static SqlSessionFactory ssf;
   static
   {
	   ssf=CreateSqlSessionFactory.getSsf();
   }
   
   public static List<RecipeVO> recipeListData(Map map)
   {
	    SqlSession session=null;
	    List<RecipeVO> list=new ArrayList<RecipeVO>();
	    try
	    {
	    	session=ssf.openSession();
	    	list=session.selectList("recipeListData",map);
	    }catch(Exception ex)
	    {
	    	System.out.println(ex.getMessage());
	    }
	    finally
	    {
	    	if(session!=null)
	    		session.close();
	    }
	    return list;
   }
   
   public static int recipeTotalPage()
   {
	    SqlSession session=null;
	    int total=0;
	    try
	    {
	    	session=ssf.openSession();
	    	total=session.selectOne("recipeTotalPage");
	    }catch(Exception ex)
	    {
	    	System.out.println(ex.getMessage());
	    }
	    finally
	    {
	    	if(session!=null)
	    		session.close();
	    }
	    return total;
   }
   
   
   public static List<ChefVO> chefListData(Map map)
   {
	    SqlSession session=null;
	    List<ChefVO> list=new ArrayList<ChefVO>();
	    try
	    {
	    	session=ssf.openSession();
	    	list=session.selectList("chefListData",map);
	    }catch(Exception ex)
	    {
	    	System.out.println(ex.getMessage());
	    }
	    finally
	    {
	    	if(session!=null)
	    		session.close();
	    }
	    return list;
   }
   
   public static int chefTotalPage()
   {
	    SqlSession session=null;
	    int total=0;
	    try
	    {
	    	session=ssf.openSession();
	    	total=session.selectOne("chefTotalPage");
	    }catch(Exception ex)
	    {
	    	System.out.println(ex.getMessage());
	    }
	    finally
	    {
	    	if(session!=null)
	    		session.close();
	    }
	    return total;
   }
}













