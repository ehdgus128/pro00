<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<% request.setCharacterEncoding("utf-8") ; %>

<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>년도별 채용 직원 수</title>
	<style>
	        table, td, tr {text-align:center; border: 1px solid lightgrey;}
	</style>
</head>
<body>


<table style="margin:auto;" >
	<tr style="background-color:#FFFF66;"> 
	    <td colspan=2>년도별 직원 채용 현황</td>
	</tr>
	<tr>
		<td style="width:30%;">년도</td>
		<td style="width:30%;">채용 직원 수(명)</td>
		
	</tr>
	<c:forEach var="yearEmpNo" items="${requestScope.hiredEmpCntYear}">
		<tr>
			<td><c:out value="${yearEmpNo.hiredYear }"/></td>
			<td>${yearEmpNo.empCount }</td>
		</tr>
	</c:forEach>	


<tr style="height:1; background-color:#99ccff"><td colspan="2"></td></tr>	
</table>




</body>
</html>