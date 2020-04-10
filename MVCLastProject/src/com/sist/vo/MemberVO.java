package com.sist.vo;
/*
 *   ID       NOT NULL VARCHAR2(20)  
	PWD      NOT NULL VARCHAR2(10)  
	NAME     NOT NULL VARCHAR2(34)  
	EMAIL             VARCHAR2(100) 
	SEX               VARCHAR2(10)  
	BIRTHDAY NOT NULL VARCHAR2(20)  
	POST     NOT NULL VARCHAR2(7)   
	ADDR1    NOT NULL VARCHAR2(200) 
	ADDR2             VARCHAR2(200) 
	TEL               VARCHAR2(20)  
	CONTENT  NOT NULL CLOB          
	REGDATE           DATE          
	ADMIN             CHAR(1) 
 */

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class MemberVO {
  private String id;
  private String pwd;
  private String name;
  private String email;
  private String sex;
  private String birthday;
  private String post;
  private String addr1;
  private String addr2;
  private String tel;
  private String content;
  private Date regdate;
  private String admin;
  private String msg;
}
