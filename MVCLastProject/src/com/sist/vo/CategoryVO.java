package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

/*
 *   CATENO  NOT NULL NUMBER        
	TITLE   NOT NULL VARCHAR2(200) 
	SUBJECT NOT NULL VARCHAR2(200) 
	POSTER  NOT NULL VARCHAR2(200) 
	LINK    NOT NULL VARCHAR2(200)
	
	 #{no}
	 ${}
	 WHERE ${name}  
	 WHERE subject
	 WHERE content
	 
	 WHERE AND title like #{title}
	 
	  <if test="state != null">
	    state = #{state}
	  </if>
	  <if test="title != null">
	    AND title like #{title}
	  </if>
	  <if test="author != null and author.name != null">
	    AND author_name like #{author.name}
	  </if>
 */
@Getter
@Setter
public class CategoryVO {
   private int cateno;
   private String title;
   private String subject;
   private String poster;
   private String link;
   
}











