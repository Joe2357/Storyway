<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="story_way.Main"%>
<%@ page import="story_way.Restaurant"%>
<%@ page import="story_way.Path"%>
<%@ page import="story_way.GetInput"%>
<%@ page import="story_way.Algorithm"%>

<!DOCTYPE html>
<%
	request.setCharacterEncoding("UTF-8");
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<style type="text/css">
h3 {
	font-family: 나눔바른펜;
}
</style>
</head>
<body>

	<%
		String user_choice = request.getParameter("choice");
		String path = Main.main(user_choice);
		String[] arr = path.split("/");
		int time = Integer.parseInt(arr[1]);
		int hour = 0, minute = 0;
		hour = time / 60;
		time = time % 60;
		minute = time;
	%>

	<p>
		<br> <br> <img src="image/bestWay.png" width="35%"
			height="10%" alt="Best Way :" />
	<h3>
		&nbsp; &nbsp; &nbsp; &nbsp;
		<%=arr[0]%></h3>
	<img src="image/time.png" width="35%" height="10%" alt="예상 소요시간 :" />
	<h3>
		&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<%=hour%>
		시
		<%=minute%>
		분
	</h3>
	</p>

</body>
</html>