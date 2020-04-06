package com.sist.recipe.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.RecipeDAO;

import java.util.*;
import com.sist.vo.*;
@Controller
public class RecipeModel {
   @RequestMapping("recipe/recipe.do")
   public String recipe_recipe(HttpServletRequest request,HttpServletResponse response)
   {
	   
	   // 요청 => 처리 => DAO
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   int rowSize=20;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=rowSize*curpage;
	   
	   // Map
	   Map map=new HashMap();
	   map.put("start", start);
	   map.put("end", end);
	   
	   List<RecipeVO> list=RecipeDAO.recipeListData(map);
	   for(RecipeVO vo:list)
	   {
		   String title=vo.getTitle();
		   if(title.length()>12)
		   {
			   title=title.substring(0,12).concat("...");
			   vo.setTitle(title);
		   }
	   }
	   int totalpage=RecipeDAO.recipeTotalPage();
	   
	   final int BLOCK=10;
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1; 
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	   // 1~10  , 11~20  85  => endPage= 81~90
	   
	   int allPage=totalpage;
	   if(endPage>allPage)
		   endPage=allPage;
	   
	   request.setAttribute("list", list);
	   request.setAttribute("curpage", curpage);
	   request.setAttribute("totalpage", totalpage);
	   request.setAttribute("BLOCK", BLOCK);
	   request.setAttribute("startPage", startPage);
	   request.setAttribute("endPage", endPage);
	   request.setAttribute("allPage", allPage);
	   request.setAttribute("main_jsp", "../recipe/recipe.jsp");
	   return "../main/main.jsp";
   }
   
   @RequestMapping("recipe/chef.do")
   public String recipe_chef(HttpServletRequest request,HttpServletResponse response)
   {
	   String page=request.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   int rowSize=30;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=rowSize*curpage;
	   
	   // Map
	   Map map=new HashMap();
	   map.put("start", start);
	   map.put("end", end);
	   
	   List<ChefVO> list=RecipeDAO.chefListData(map);
	   int totalpage=RecipeDAO.chefTotalPage();
	   
	   final int BLOCK=5;
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1; 
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	   // 1~10  , 11~20  85  => endPage= 81~90
	   
	   int allPage=totalpage;
	   if(endPage>allPage)
		   endPage=allPage;
	   
	   request.setAttribute("list", list);
	   request.setAttribute("curpage", curpage);
	   request.setAttribute("totalpage", totalpage);
	   request.setAttribute("BLOCK", BLOCK);
	   request.setAttribute("startPage", startPage);
	   request.setAttribute("endPage", endPage);
	   request.setAttribute("allPage", allPage);
	   
	   request.setAttribute("main_jsp", "../recipe/chef.jsp");
	   return "../main/main.jsp";
   }
}














