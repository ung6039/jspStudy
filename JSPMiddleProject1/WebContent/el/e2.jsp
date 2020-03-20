<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
      ${연산자}
      ==> 산술연산자  
          + , -  , *  , / (div) , % (mod)
               => ${5/2} , ${5 div 2}  , ${5%2} ${5 mod 2}
               ${"10"+1}
                =====
                Integer.parseInt("10")=> 10+1=> 11
                ${"일"+=10} => 일10(error)
                null => 0
          ==================== / (<%= 10/3 %>) (${10/3}:실수)
          + : 산술연산 , 문자열 결합
              "Hello"+10 ==> Hello10 
          + : 순수하게 산술만 가능 
              "Hello"+10 ==> error
              
              자바 : <%= "100"+"10"%>  => 10010
            ${"100"+"10"}  => 110
            ==> 문자열 결합 => (+=)
            
            <%= null+10 %>
            ${ null + 10}
               ====
                 0으로 변경
                 
                 var a='hello' ,"Hello"
                 
                 ${sessionScope.id} = null    예매,댓글 
                 
           비교 연산자  ===> true/false
           == | eq (문자열,숫자)  ==> ${requestScope.id=='admin'} ${requestScope.id eq 'admin'}
           != | ne (문자열,숫자)  ==> ${requestScope.id!='admin'} ${requestScope.id ne 'admin'}
           <  | lt  ${10<5}
           >  | gt  ${10>5}
           <= | le  ${10<=5}
           >= | ge  ${10>=5}
           
           논리연산자  ===> true/false
           && | and
           || | or
           !  | not
      
           String id; ===> null
           String id=""; ==> ""
     empty연산자 
          ${empty list}  ${ empty list}
          
         삼항연산자
         ${조건?값1:값2}
         문자열 결합 : += 
     <%= %> 
     
     ===> JSP안에서는 <% %>(X)
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <%--  <%= "Hello" %><br>
   ${"Hello" }<br>
   <%= null+10 %><br>
   ${"10"+'100' }<br>
   ${ null+10 } --%>
</body>
</html>













