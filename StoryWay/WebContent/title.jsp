<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="EUC-KR"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<style type="text/css">
a.lin:link {
	color: black;
	text-decoration: none;
}

a.lin:visited {
	color: black;
	text-decoration: none;
}

a.lin:hover {
	color: red;
	text-decoration: none;
}
</style>

<title>title</title>
</head>
<body>
	<%-- set title(picture) & input box --%>
	<div id="title">
		<center>
			<a href="middle.jsp" target="middle"><img src="image\title.png"
				alt="title" width="15%" height="5%"></a>

			<form action="main.jsp" method="GET" target="middle" float="right">
				<input type="text" name="choice"
					value="방문하고싶은 가게 번호를 입력하세요. ( 콤마필수 ex. 1,2,3 )"
					style="width: 25%; height: 5%"> <input type="submit"
					value="확인" background-color:#2E64FE />
			</form>
		</center>

	</div>
</body>
</html>