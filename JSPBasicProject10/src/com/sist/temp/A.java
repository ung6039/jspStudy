package com.sist.temp;

public class A {
   @RequestMapping("a1")
   public void display()
   {
	   System.out.println("A:display Call...");
   }
   @RequestMapping("a2")
   public void display2()
   {
	   System.out.println("A:display2 Call...");
   }
}
