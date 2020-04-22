<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	
	$('.rdate').hover(function(){
		$(this).css("cursor","pointer");
	},function(){
		$(this).css("cursor","none");
	})
	
	$('#year').change(function(){
		var year=$(this).val();
		var month=$('#month').val();
		//alert(year+"|"+month)
		$.ajax({
			type:'post',
			url:'date.do',
			data:{"year":year,"month":month},
			success:function(res)
			{
				$('#movie-date').html(res);
			}
		})
	})
	$('#month').change(function(){
		var month=$(this).val();
		var year=$('#year').val();
		//alert(year+"|"+month)
		$.ajax({
			type:'post',
			url:'date.do',
			data:{"year":year,"month":month},
			success:function(res)
			{
				$('#movie-date').html(res);
			}
		})
	})
	
	$('.rdate').click(function(){
		var year=$('#year').val();
		var month=$('#month').val();
		var day=$(this).text();
		
		var rday=year+"년 "+month+"월 "+day+"일";
		$('#movie-date2').text(rday);
		
		$.ajax({
			type:'post',
			url:'time.do',
			data:{"tno":day},
			success:function(res)
			{
				$('#movie-time').html(res);
			}
		})
	});
	
});
</script>
</head>
<body>
  <div class="row" style="margin: 0px auto;width:450px;">
    <h3 class="text-center">${year }년 ${month }월</h3>
    <table class="table">
      <tr>
        <td>
          <select name=year id="year">
           <c:forEach var="i" begin="2020" end="2030">
            <c:if test="${i==year }">
              <option selected>${i }</option>
            </c:if>
            <c:if test="${i!=year }">
              <option>${i }</option>
            </c:if>
           </c:forEach>
          </select>년도&nbsp;
          <select name=month id="month">
           <c:forEach var="i" begin="1" end="12">
            <c:if test="${i==month }">
              <option selected>${i }</option>
            </c:if>
            <c:if test="${i!=month }">
              <option>${i }</option>
            </c:if>
           </c:forEach>
          </select>월
        </td>
      </tr>
    </table>
    <table class="table">
      <tr style="height:45px">
        <c:forEach var="sw" items="${strWeek }">
          <th class="text-center danger">
            ${sw }
          </th>
        </c:forEach>
      </tr>
      <%--
         for(int i=0;i<3;i++)
                     i<=3
       --%>
      <c:set var="week" value="${week }"/>
      <%-- int week=3 --%>
      <c:forEach var="i" begin="1" end="${lastday }">
        <c:if test="${i==1 }">
          <tr style="height:45px">
          <c:forEach var="j" begin="1" end="${week }">
            <td>&nbsp;</td>
          </c:forEach>
        </c:if>
        <%--
               days[i]
         --%>
        <c:if test="${i==days[i-1]}">
          <td class="text-center success rdate">${i }</td>
        </c:if>
        <c:if test="${i!=days[i-1]}">
          <td class="text-center">${i }</td>
        </c:if>
        <%--
              week=week+1
         --%>
        <c:set var="week" value="${week+1 }"/>
        <c:if test="${week>6 }">
          <%--
             week=0
           --%>
          <c:set var="week" value="0"/>
          </tr>
          <tr style="height:45px">
        </c:if>
      </c:forEach>
    </tr>
    </table>
    
  </div>
</body>
</html>
















