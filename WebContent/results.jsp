<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your Query Results</title>
</head>
<body>
<form id="searchForm">
	<table align="center" width="50%">
		<tr valign="middle" align="right">
			<td align="center" valign="middle" width="40%">
				<input id="searchBox" class="style1" name="box" type="text" value="" dir="ltr" style="border:block ; padding: 0px; margin: 0px; height: auto; 
				width: 80%; position: relative; z-index: 6; left: 0px; outline: none;">
				<input type="submit" style="width: 60px;" value="Submit">
			</td>
		</tr>
	</table>
</form>
<br><br>
<div id="resultsDiv">
	<form id="resultsForm">
		<table width="100%" style="border-left: thick 10%; border-left-color: white;border-left-style: hidden;">
			<tr>
				<td align="center">
					<table width="70%">
						<c:forEach var="result" items=<%= request.getAttribute("queryResults") %>>
							<tr>
								<td align="center">
									<c:out value="${result }"></c:out>
								</td>
							</tr>
						</c:forEach>
					</table>
				</td>
				<td align="right">
					<table width="30%">
						
						<c:forEach var="adResult" items=<%=request.getAttribute("adResults") %>>
							<tr>
								<td dir="ltr"><c:out value="${adResult }"></c:out></td>
							</tr>	
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>