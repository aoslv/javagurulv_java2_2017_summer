<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <% String data = (String) request.getAttribute("model"); %>

    <% for (int i = 0; i < 10; i++) {%>
        <p>
            <%= data %>
        </p>
    <% }%>

</body>
</html>