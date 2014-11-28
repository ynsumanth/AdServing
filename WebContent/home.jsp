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
	function popup(source, resultfile) {
		alert('inside popup');
		var windowTitle = source.innerHTML.value;
		var newWindow = window.open('','_newtab');
		alert(windowTitle);
		newWindow.title = windowTitle;
		var output = '<h1>'+resultfile.title+'</h1';
		var popupbody = '<p>'+resultfile.content+'</p>';
		newWindow.document.body.innerHTML += output+popupbody;
		return true;
	}
	function popup1() {
		alert('inside popup 1');
	}
</script>
</head>
<body onload="reset();" style="background-color: silver;">
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
				<h:inputText id="searchBox" value="#{homebean.userQuery}" dir="ltr" style="border:block ; padding: 0px; margin: 0px; height: 20px; 
				width: 372px; position: relative; z-index: 20; left: 0px; outline: none;" autocomplete="off"></h:inputText>
				<h:commandButton value="Submit"	actionListener="#{homebean.listen}"></h:commandButton>
			</td>
		</tr>
	</table>
	</h:form>
	<br><br>
	<div id="resultDiv">
	<h:form>
		<c:set var="isShowResultsEnabled" scope="page" value="#{homebean.pop}"></c:set>
		<c:if test="${isShowResultsEnabled}">
			<table width="100%" style="border-left: thick 10%; border-left-color: white;border-left-style: hidden; height: 295px">
			<tr>
				<td align="center">
					<table width="70%" style="table-layout: fixed; height: 141px">
						<c:forEach var="hmap" items="#{homebean.newsResult}">
							<tr>
								<td align="justify" width="100%" style="width: 682px; " dir="ltr">
									<h:commandLink onmousedown="popup(this,hmap.key)"><c:out value="${hmap.key.title}"></c:out></h:commandLink>
									<br>
									<div id="snippet">
										<c:forEach var="value" items="${hmap.value}">
											<c:out value="${value}">..</c:out>
										</c:forEach>
									</div>
									<br>
								</td>
							</tr>
						</c:forEach>
					</table>
				</td>
				<td align="right" style="vertical-align: top;">
					<table style="width: 413px; height: 102px; table-layout: fixed; vertical-align: top;">
						<c:forEach var="hmap" items="#{homebean.newsResult}">
							<tr>
								<td dir="ltr" style="height: 49px; width: 376px; "><c:out value="${hmap.key.title}"></c:out></td>
							</tr>	
						</c:forEach>
					</table>
				</td>
			</tr>
			<tr>
				<td align="center">
					<h:commandLink>Previous</h:commandLink>
					<span style="padding: 68px">&nbsp;</span>
					<h:commandLink>Next</h:commandLink>
				</td>
			</tr>
		</table>
		
		</c:if>
	</h:form>
	</div>
</f:view>
</body>
</html>