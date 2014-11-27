<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function reset() {
		document.getElementById("searchBox").value="";
		document.getElementById("resultDiv").disable=true;
	}
	function clicked() {
		alert("inside js");
		document.getElementById("resultDiv").enable=true;
	}
</script>
</head>
<body onload="reset();">
<f:view>
	<table id="finderImg" align="center">
		<tr>
			<td>
				<img alt="Finder" src="../search-icon.jpg" height="120px" width="150">
			</td>
		</tr>
	</table>
	<h:form>
		<table align="center" width="100%">
		<tr valign="middle">
			<td align="center" valign="middle" width="80%">
				<input id="searchBox" class="style1" name="box" type="text" value="" dir="ltr" style="border:block ; padding: 0px; margin: 0px; height: auto; 
				width: 50%; position: relative; z-index: 6; left: 0px; outline: none;">
				<h:commandButton value="Submit"	actionListener="#{homebean.listen}"></h:commandButton>
			</td>
		</tr>
	</table>
	</h:form>
	<div id="resultDiv">
	<h:form>
		<c:set var="isShowResultsEnabled" scope="page" value="#{homebean.pop}"></c:set>
		<c:if test="${isShowResultsEnabled}">
			<table width="100%" style="border-left: thick 10%; border-left-color: white;border-left-style: hidden;">
			<tr>
				<td align="center">
					<table width="70%">
						<c:forEach var="result" items="#{homebean.list}">
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
						
						<c:forEach var="adResult" items="#{homebean.list }">
							<tr>
								<td dir="ltr"><c:out value="${adResult }"></c:out></td>
							</tr>	
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>
		</c:if>
	</h:form>
	</div>
</f:view>
</body>
</html>