package com.sist.user.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
@Controller
public class MainModel {
  @RequestMapping("main/main.do")
  public String main_main(HttpServletRequest request,HttpServletResponse response)
  {
	  //System.out.println("main 수행...");
	  List<CategoryVO> list=MainDAO.mainImageData();
	  List<RecipeVO> rlist=MainDAO.mainRecipeData();
	  int count=MainDAO.recipeCount();
	  int no=(int)(Math.random()*181)+103;
	  FoodVO fvo=MainDAO.mainFoodDetailData(no);
	  request.setAttribute("main_jsp", "home.jsp");
	  request.setAttribute("clist", list);
	  request.setAttribute("rlist", rlist);
	  request.setAttribute("count", count);
	  request.setAttribute("fvo", fvo);
	  return "../main/main.jsp";
  }
}














