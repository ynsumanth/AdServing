<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get confused</title>
<style type="text/css">
.style1{
	color: #222;
	font: 16px arial,sans-serif;
}
.mainBorder {
	height: 15px;
	border-left-width: 15;
	border-left-style: hidden;
	border-left-color: white;
	border-right-width: 15%;
	border-right-style: hidden;
	border-right-color: white;
}
</style>
<script type="text/javascript">
	function reset() {
		var searchString = document.getElementById("searchBox").value;
		if(searchstring=='') {
			document.getElementById("showResults").disable=true;
		}
		else {
			document.forms[0].submit();
		}
	}
</script>
</head>
<!-- body starts -->
<body onload="reset();">

<form id="searchForm" action="search" method="get">
<div id="div1" align="center">
	<table id="finderImg">
		<tr>
			<td>
				<img alt="Finder" src="../search-icon.jpg" height="120px" width="150">
			</td>
		</tr>
	</table>
	<table align="center" width="100%">
		<tr valign="middle">
			<td align="center" valign="middle" width="80%">
				<input id="searchBox" class="style1" name="box" type="text" value="" dir="ltr" style="border:block ; padding: 0px; margin: 0px; height: auto; 
				width: 50%; position: relative; z-index: 6; left: 0px; outline: none;">
				<h:commandButton value="Submit"	action="#{homebean.action}"></h:commandButton>
			</td>
		</tr>
	</table>
</div>
</form>
</body>
</html>