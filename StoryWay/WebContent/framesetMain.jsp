<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="EUC-KR">
</style>
<title>frame Main</title>
<FRAMESET frameborder="0" rows="25%,75%">
	<FRAME src="title.jsp" name="title" scrolling="no" noresize>
	<FRAMESET frameborder="0" cols="20%,60%,20%">
		<FRAME src="left.jsp" name="left" scrolling="no" noresize>
		<FRAME src="middle.jsp" name="middle" noresize>
		<FRAME src="right.jsp" name="right" noresize>
	</FRAMESET>
</FRAMESET>

</head>
</html>