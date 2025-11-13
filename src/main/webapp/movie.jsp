<%@ page import="com.politecnicllevant.firstweb.model.Movie" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Alexandra
  Date: 06/11/2025
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Movie JSP</title>
</head>
<p>
    <h1>Movie JSP</h1>
    <p>
        <c:if test="${not empty movies}">
            <c:forEach var="movie" items="${movies}">
                <p>${movie.title}</p>
            </c:forEach>
        </c:if>
    </p>
    <h1>Formulario</h1>

    <form method="post" action="${pageContext.request.contextPath}/movies">
        <input type="text" name="title" placeholder="Título" required>
        <input type="text" name="description" placeholder="Descripción" required>
        <input type="number" name="year" placeholder="Año" required>
        <button type="submit">Crear</button>
    </form>

    <c:forEach var="movie" items="${movies}">
        <p>
            <strong>${movie.title}</strong> (${movie.year}) - ${movie.description}

        <form method="post" action="${pageContext.request.contextPath}/movies" style="display:inline;">
            <input type="hidden" name="_method" value="DELETE"/>
            <input type="hidden" name="id" value="${movie.id}"/>
            <button type="submit">Borrar</button>
        </form>

        <a href="${pageContext.request.contextPath}/movies?id=${movie.id}">Ver detalle</a>
        </p>
    </c:forEach>

</body>
</html>
