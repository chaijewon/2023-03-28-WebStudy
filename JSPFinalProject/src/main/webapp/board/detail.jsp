<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
  margin: 0px auto;
  width:600px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let i=0; // 전역변수 
$(function(){
	$('#del').click(function(){
		if(i==0)
		{
			$(this).text("취소");
			$('#delTr').show();
			i=1;
		}
		else
		{
			$(this).text("수정");
			$('#delTr').hide();
			i=0;
		}
	})
	$('#delBtn').click(function(){
		let pwd=$('#pwd1').val();
		let no=$('#delBtn').attr("data-no");
		if(pwd.trim()=="")
		{
			$('#pwd1').focus();
			return;
		}
		// delete.do?no=1&pwd=1111
		$.ajax({
			type:'post',
			url:'../board/delete.do',
			data:{"no":no,"pwd":pwd},
			success:function(result)
			{
				let res=result.trim();
				if(res==='NO')
				{
					alert("비밀번호가 틀립니다")
					$('#pwd1').val("")
					$('#pwd1').focus()
				}
				else
				{
					location.href="../board/list.do"
				}
			}
		})
	})
})
</script>
</head>
<body>
<div class="wrapper row3">
  <main class="container clear">
   <h2 class="sectiontitle">글쓰기</h2>
   <table class="table">
     <tr>
      <th width=20% class="text-center">번호</th>
      <td width=30% class="text-center">${vo.no }</td>
      <th width=20% class="text-center">작성일</th>
      <td width=30% class="text-center">${vo.dbday }</td>
     </tr>
     <tr>
      <th width=20% class="text-center">이름</th>
      <td width=30% class="text-center">${vo.name }</td>
      <th width=20% class="text-center">조회수</th>
      <td width=30% class="text-center">${vo.hit }</td>
     </tr>
     <tr>
      <th width=20% class="text-center">제목</th>
      <td colspan="3">${vo.subject }</td>
     </tr>
     <tr>
       <td colspan="4" class="text-left" valign="top" height="200">
        <pre style="white-space: pre-wrap;border:none;background-color: white">${vo.content }</pre>
       </td>
     </tr>
     <tr>
       <td colspan="4" class="text-right">
         <a href="../board/update.do?no=${vo.no }" class="btn btn-xs btn-info">수정</a>
         <span class="btn btn-xs btn-success" id="del">삭제</span>
         <a href="../board/list.do" class="btn btn-xs btn-warning">목록</a>
       </td>
     </tr>
     <tr style="display:none" id="delTr">
       <td colspan="4" class="text-right inline">
       비밀번호:<input type=password name=pwd id=pwd1 size=10
              class="input-sm">
              <input type=button value="삭제" data-no="${vo.no }"
               class="btn btn-sm btn-primary" id="delBtn">
       </td>
     </tr>
   </table>
  </main>
</div>
</body>
</html>