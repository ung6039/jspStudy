package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberVO {
   private String id;
   private String name;
   private String msg;
   private int admin;
   private String pwd;
}
