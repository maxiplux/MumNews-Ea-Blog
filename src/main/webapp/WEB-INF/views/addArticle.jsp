<%--
  Created by IntelliJ IDEA.
  User: Theodros
  Date: 9/21/2018
  Time: 5:25 PM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="float" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add a Car</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>jQuery UI Datepicker - Default functionality</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function () {
            var now = new Date();

            var day = ("0" + now.getDate()).slice(-2);
            var month = ("0" + (now.getMonth() + 1)).slice(-2);

            var today = now.getFullYear() + "-" + (month) + "-" + (day);
            $("#date").datepicker().val(today);
        });
    </script>
    <style type="text/css">
        .section1 {
            color: #990000;
            text-align: center;
            float: left
        }

        .section2 {
            color: #990000;
            text-align: center;
            float: left
        }

        .section3 {
            color: #990000;
            text-align: center;
            float: left
        }

        .section4 {
            color: #990000;
            text-align: center;
            float: left
        }

        .section5 {
            color: #990000;
            text-align: center;
            float: left
        }

        .section6 {
            color: #990000;
            text-align: center;
            float: left
        }
    </style>
</head>
<body>
<form action="/create" method="post" enctype="multipart/form-data" multiple="true" name="upload"
      autocomplete="off">
    <br>
    <tr>
        <div id=".section5">
            <td>Title:</td>
            <td><p><input name="title" type="text"/></p></td>
        </div>
    </tr>
    <br>
    <tr>
        Category:
        <div id=".section3">
            <td>
                <select name="category">
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
            <td><textarea name="content" id="content" cols="30" rows="10"></textarea></td>
        </div>
    </tr>
    <br>
    <br>

    <tr>
        <td>Upload File:</td>
        <%--<form action="/add/image" method="post" enctype="multipart/form-data">--%>
        <div>
            <td id=".section4"><input type="file" name="file"/></td>
        </div>
        <%--<input type="submit" value="Upload" float:float:right/>--%>
        <%--</form>--%>
    </tr>
    </table>
    <br></br>
    <input type="submit" value="Add Article"/>

</form>
</body>
</html>
