<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function reset() {
		document.getElementById("resultDiv").disable = true;
	}
	function clicked() {
		document.getElementById("resultDiv").enable = true;
	}
	function popup(title, author, place, date, content) {
		var newWindow = window
				.open(
						"",
						"",
						config = "height=500, width=600, location=no, menubar=no, status=no, toolbar=no, scrollbars=no, resizable=0, "
								+ "directories=no");
		newWindow.title = "News Article";
		newWindow.document.write("<h1>" + title + "</h1>" + "<br>");
		if (author.trim())
			newWindow.document.write("Author: " + author);
		if (date.trim())
			newWindow.document.write("\t Date: " + date);
		if (place.trim())
			newWindow.document.write("\t Place: " + place);
		if (content.trim())	
		newWindow.document.write("<p>" + content + "</p>");
	}
	function updateClicks(count) {
		$.ajax({
			url: 'Faces Servlet',
			data: count,
			type: 'get',
			success: function(data) {
				console.log("success");
			}
		})
	}
</script>
</head>
<body onload="reset();">
	<f:view>
		<table id="finderImg" align="center">
			<tr>
				<td><img alt="Finder" src="../search-icon.jpg" height="120px"
					width="150"></td>
			</tr>
		</table>
		<h:form>
		
			<table align="center" width="100%">
				<tr valign="middle">
					<td align="center" valign="middle" width="80%"><h:inputText
							id="searchBox" value="#{homebean.userQuery}" dir="ltr"
							style="border:block ; padding: 0px; margin: 0px; height: 20px; 
				width: 372px; position: relative; z-index: 20; left: 0px; outline: none;"
							autocomplete="on"></h:inputText> <h:commandButton value="Search"
							actionListener="#{homebean.listen}"></h:commandButton></td>
				</tr>
				<tr valign = "middle">
					<td align="center" valign="middle" width="80%">
					<h:selectManyCheckbox value="#{homebean.selectedCheckboxes}">
					<f:selectItem itemValue="author" itemLabel="Author"/>
					<f:selectItem itemValue="title" itemLabel="Title"/>
					<f:selectItem itemValue="place" itemLabel="Place"/>
					<f:selectItem itemValue="content" itemLabel="Content"/>					
					</h:selectManyCheckbox>
					</td>
				<tr valign="baseline">
					<td valign="baseline" align="center" width="50%"><c:set
							var="spellcheck" scope="page"
							value="#{homebean.spellCheckResults}"></c:set> <c:if
							test="${spellcheck}">
							<c:out value="Did you mean:" />&nbsp;
							<i><h:commandLink style="color: blue;"	actionListener="#{homebean.queryForSpellChecker}">
									<c:forEach var="spelling" items="#{homebean.spellCorrections}">
										<c:out value="${spelling}"/>&nbsp;
									</c:forEach>
							</h:commandLink></i>
						</c:if></td>
				</tr>
			</table>
		</h:form>
		<br>
		<br>
		<div id="resultDiv">
			<h:form>
				<c:set var="isShowResultsEnabled" scope="page"
					value="#{homebean.pop}"></c:set>
				<c:if test="${isShowResultsEnabled}">
					<table width="100%"
						style="border-left: thick 10%; border-left-color: white; border-left-style: hidden; height: 295px">
						<tr>
							<td align="center">
								<table width="70%" style="table-layout: fixed; height: 141px">
									<c:forEach var="hmap" items="#{homebean.newsResult}">
										<tr>
											<td align="justify" width="100%" style="width: 682px;"
												dir="ltr"><a
												style="color: blue; cursor: pointer; cursor: hand;"
												onclick="popup('${hmap.key.title}','${hmap.key.author}','${hmap.key.place}','${hmap.key.date}','${hmap.key.content}')">
													<c:out value="${hmap.key.title}"></c:out>
											</a> <br>
												<div id="snippet">
													<c:forEach var="value" items="${hmap.value}">
														"${value}"..&nbsp;
													</c:forEach>
												</div> <br></td>
										</tr>
									</c:forEach>
								</table>
							</td>
							<td align="right" style="vertical-align: top;">
								<table
									style="width: 413px; height: 102px; table-layout: fixed; vertical-align: top;">
									<c:forEach var="adver" items="#{homebean.adResult}">
										<tr>
											<td dir="ltr" style="height: 49px; width: 376px;">
												<h:commandLink actionListener="#{homebean.adClickListener}">
													<a href="<c:url value="${adver.url}"></c:url>" target="_blank">${adver.title}</a>
												</h:commandLink>
											</td>
										</tr>
									</c:forEach>
								</table>
							</td>
						</tr>
						<tr>
							<td align="center"><h:commandButton actionListener="#{homebean.previous}" value="Previous"></h:commandButton> <span
								style="padding: 68px">&nbsp;</span> <h:commandButton actionListener="#{homebean.next}" value="Next"></h:commandButton>
							</td>
						</tr>
					</table>

				</c:if>
			</h:form>
		</div>
	</f:view>
</body>
</html>