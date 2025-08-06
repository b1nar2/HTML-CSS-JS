<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HR 개별 사원 현황</title>
</head>

<body>
<table>
	 <tr>
		 <td width="50px" align="center">id</td>
		 <td align="center">name</td>
		 <td align="center">email</td>
		 <td align="center">phoneNumber</td>
		 <td align="center">hireDate</td>
		 <td align="center">jobId</td>
		 <td align="center">salary</td>
		 <td align="center">pct</td>
		 <td align="center">managerId</td>
		 <td align="center">departmentId</td>
	 </tr>

	 <tr>
		 <td>${member.employeeId}</td>
		 <td>${member.firstName} ${member.lastName}</td>
		 <td>${member.email}</td>
		 <td>${member.phoneNumber}</td>
		 <td>${member.hireDate}</td>
		 <td>${member.jobId}</td>
		 <td>${member.salary}</td>
		 <td>${member.commissionPct}</td>
		 <td>${member.managerId}</td>
		 <td>${member.departmentId}</td>	
	 </tr>

</table>

</body>
</html>
