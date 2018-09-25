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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<layout:extends name="base">
    <layout:put block="titlePage" type="REPLACE">
        <c:out value="Article | ${article.title}"/>
    </layout:put>
    <layout:put block="styleLinks" type="REPLACE">

    </layout:put>
    <layout:put block="content" type="REPLACE">
        <div class="container">
            <div>

                <img src="<c:url value="${article.image}"/>" height="200">
                <br/>
                <strong>${article.title}</strong>
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

            <br>
            <strong>New Comment:</strong>
            <form:form method="post" action="/createComment/${article.id}" modelAttribute="comment">
                <form:textarea cols="100" path="comment" required="required" title="New Comment"/>
                <br>
                <input type="submit" value="Add Comment"/>
            </form:form>
            <c:forEach var="com" items="${comments}">
                <div class="comment">
                        ${com.comment}
                    <br>
                            <span>${com.user.firstname}</span>
                    <br>
                            <span>${com.creationDate}</span>
                    <br>
                </div>
            </c:forEach>
        </div>
    </layout:put>
</layout:extends>