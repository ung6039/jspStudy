<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$.ajax({
		type:'post',
		url:'movie.do',
		success:function(res)
		{
			$('#movie-list').html(res);
		}
	});
	
	$.ajax({
		type:'post',
		url:'date.do',
		success:function(res)
		{
			$('#movie-date').html(res);
		}
	})
})
</script>
</head>
<body>
  <div class="container-fluid">
   <h1 class="text-center">영화 예매</h1>
   <div class="row">
    <table class="table">
      <tr>
        <td width=20% height="500">
          <table class="table">
            <tr>
              <td bgcolor="#ccccff" class="text-center">영화선택</td>
            </tr>
          </table>
          <div style="overflow-y:scroll; height:450px;" id="movie-list">
          </div>
        </td>
        <td width=20% height="500">
          <table class="table">
            <tr>
              <td bgcolor="#ccccff" class="text-center">극장선택</td>
            </tr>
          </table>
          <div style="overflow-y:scroll; height:450px;" id="movie-theater">
           
          </div>
        </td>
        <td width=30% height="500">
          <table class="table">
            <tr>
              <td bgcolor="#ccccff" class="text-center">날짜선택</td>
            </tr>
          </table>
          <div id="movie-date"></div>
        </td>
        <td width=30% rowspan="2">
           <table class="table">
            <tr>
              <td bgcolor="#ccccff" class="text-center">예매정보</td>
            </tr>
          </table>
          <table class="table">
            <tr>
              <td class="text-center">
                <img src="def.png" width=300 height=350 id="movie-poster">
              </td>
            </tr>
            <tr>
              <td>
               <b id="movie-title"></b>
              </td>
            </tr>
            <tr>
              <td>
               <span style="color:#999">별점</span>
               <span id="movie-score"></span>
              </td>
            </tr>
            <tr>
              <td>
               <span style="color:#999">극장</span>
               <span id="movie-theater2"></span>
              </td>
            </tr>
            <tr>
              <td>
               <span style="color:#999">날짜</span>
               <span id="movie-date2"></span>
              </td>
            </tr>
            <tr>
              <td>
               <span style="color:#999">시간</span>
               <span id="movie-time2"></span>
              </td>
            </tr>
            <tr>
              <td>
               <span style="color:#999">인원</span>
               <span id="movie-inwon"></span>
              </td>
            </tr>
            <tr>
              <td>
               <span style="color:#999">금액</span>
               <span id="movie-price"></span>
              </td>
            </tr>
            <tr>
              <td class="text-center">
                <input type=button value="예매하기" class="btn btn-sm btn-danger">
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td colspan="2" height="200">
          <table class="table">
            <tr>
              <td bgcolor="#ccccff" class="text-center">시간선택</td>
            </tr>
          </table>
        </td>
        <td width=20% height="200">
          <table class="table">
            <tr>
              <td bgcolor="#ccccff" class="text-center">인원선택</td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
   </div>
  </div>
</body>
</html>













