package com.sist.controller;
import java.io.*;
import java.util.*;
import java.net.*;
public class ComponentScan {
   public List<String> getClassNameRead(String pack)
   {
	   List<String> list=new ArrayList<String>();
	   try
	   {
	     String path="C:\\Users\\sist181\\jspStudy\\MVCProject5\\src";
		 URL url=this.getClass().getResource(".");
		 String path1=url.getPath();
		 System.out.println("path1="+path1);
		 // 
		 //path1=path1.substring(1,path1.lastIndexOf("/build"));
		 //System.out.println("path1="+path1);
	     path=path+"\\"+pack.replace(".", "\\");
	     File dir=new File(path);
	     File[] files=dir.listFiles();
	     for(File f:files)
	     {
	    	 String name=f.getName();
	    	 if(name.endsWith("java"))
	    	 {
	    		 String s=pack+"."+name.substring(0,name.indexOf("."));
	    		 // name="BoardModel.java"
	    		 // s="com.sist.model"+"."+"BoardModel"
	    		 // com.sist.model.BoardModel
	    		 list.add(s);
	    	 }
	     }
	   }catch(Exception ex){ex.printStackTrace();}
	   return list;
   }
}











