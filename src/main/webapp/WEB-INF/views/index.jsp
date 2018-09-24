<%--
  Created by IntelliJ IDEA.
  User: Edwin-Cobos
  Date: 9/21/2018
  Time: 6:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<layout:extends name="base">
    <layout:put block="titlePage" type="REPLACE">
        <c:out value="Login"/>
    </layout:put>
    <layout:put block="styleLinks" type="REPLACE">

    </layout:put>
    <layout:put block="content" type="REPLACE">
        <div class="container">
            <div class="menu">
                <form:form method="GET" action="/">
                    <label for="category">Categories:</label>
                    <select id="category" name="category">
                        <option value="0">All</option>
                        <c:forEach var="cat" items="${categoryList}">
                            <option value="${cat.id}"
                                    <c:if test="${category.id == cat.id}">selected</c:if>>${fn:toLowerCase(cat.name)}</option>
                        </c:forEach>
                    </select>

                    <label for="dateOrder">Order by Date:</label>
                    <select id="dateOrder" name="dateOrder">
                        <option value="asc">Ascendant</option>
                        <option value="desc">Descendant</option>
                    </select>

                    <input type="submit" value="Filter">
                </form:form>
                <div>
                    <a href="<c:url value="/create"/>">New Article</a>
                </div>
            </div>
            <div>
                <c:forEach var="article" items="${articles}">
                    <div class="article">
                        <img src="<c:url value="${article.image}"/>" height="200">
                        <br/>
                        <a href="<c:url value="/article/${article.id}" />">${article.title}</a>
                        <br/>
                            ${article.category}
                        <br/>
                            ${article.publicationDate}
                        <br>
                            ${article.author.firstname}
                        <br>
                            ${article.content}
                        <br>
                    </div>
                </c:forEach>
            </div>
        </div>
    </layout:put>
</layout:extends>