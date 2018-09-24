<%--
  Created by IntelliJ IDEA.
  User: Theodros
  Date: 9/21/2018
  Time: 5:25 PM
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
            <form action="/create" method="post" enctype="multipart/form-data" multiple="true" name="upload"
                  autocomplete="off">
                <br>
                <tr>
                    <div id=".section5">
                        <td>Title:</td>
                        <td><p><input name="title" type="text" required="required" /></p></td>
                    </div>
                </tr>
                <br>
                <tr>
                    Category:
                    <div id=".section3">
                        <td>
                            <select name="category" required="required">
                                <c:forEach var="cat" items="${cateogories}">
                                    <option value="${cat.id}">${cat.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </div>
                </tr>
                <br>
                <tr>
                    <td>Content:</td>
                    <div id=".section2">
                        <td><textarea name="content" id="content" cols="30" rows="10" required="required" ></textarea></td>
                    </div>
                </tr>
                <br>
                <br>

                <tr>
                    <td>Upload File:</td>
                        <%--<form action="/add/image" method="post" enctype="multipart/form-data">--%>
                    <div>
                        <td id=".section4"><input required="required" type="file" name="file"/></td>
                    </div>
                        <%--<input type="submit" value="Upload" float:float:right/>--%>
                        <%--</form>--%>
                </tr>
                </table>
                <br></br>
                <input type="submit" value="Add Article"/>

            </form>
        </div>
    </layout:put>
</layout:extends>
