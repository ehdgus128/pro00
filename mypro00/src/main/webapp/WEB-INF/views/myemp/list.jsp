<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서별 직원 현황</title>
	<style>
	        table, td, tr {text-align:center; border: 1px solid lightgrey;}
	</style>
</head>
<body>


<table style="margin:auto;" >
	<tr style="background-color:#FFFF66;"> 
	    <td colspan=6>직원 목록</td>
	</tr>
	<tr>
		<td style="width:10%;">사번</td>
		<td style="width:10%;">성명</td>
		<td style="width:10%;">급여(원)</td>
		<td style="width:20%;">입사일</td>
		<td style="width:15%;">근무부서명</td>
	</tr>
	<c:forEach var="emp" items="${empList}">
		<tr>
			<td><c:out value="${emp.empId }"/></td>
			<td>${emp.empName }</td>
			<td>${emp.empSal }</td>
			<td><fmt:formatDate value="${emp.empHiredDate }" pattern="yyyy/MM/dd HH:mm:ss"/> </td>
			<td>${emp.deptName }</td>
		</tr>
	</c:forEach>	


<tr style="height:1; background-color:#99ccff"><td colspan="6"></td></tr>	
</table>




</body>
</html>