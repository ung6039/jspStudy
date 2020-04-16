<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
<script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
  <div class="wrapper row2">
  <div id="services" class="clear"> 
    <div class="row text-center">
     <h1>자유게시판</h1>
    </div>
  <div class="row" id="root"></div>
  <script type="text/babel">
   class BoardList extends React.Component{
      render(){
         return (
            <table className="table">
             <thead>
               <tr className="success">
                 <th className="text-center" width="10%">번호</th>
                 <th className="text-center" width="45%">제목</th>
                 <th className="text-center" width="15%">이름</th>
                 <th className="text-center" width="20%">작성일</th>
                 <th className="text-center" width="10%">조회수</th>
               </tr>
             </thead>
            </table>
         )
      }
   }
   ReactDOM.render(<BoardList/>,document.getElementById('root'))
  </script>
  </div>
  </div>
  
</body>
</html>








