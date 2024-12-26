<%--
  Created by IntelliJ IDEA.
  User: israellcid
  Date: 18/01/24
  Time: 12:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, org.icid.apiservlet.webapp.models.*"%>

<%
  List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");
  String titulo = (String) request.getAttribute("titulo");
%>

<html>
  <head>
    <title><%=titulo%></title>
  </head>
  <body>
    <h1><%=titulo%></h1>
    <form action="<%=request.getContextPath()%>/cursos/buscar" method="post">
      <input type="text" name="nombre">
      <input type="submit" value="buscar">
    </form>

    <table>
      <tr>
        <th>id</th>
        <th>nombre</th>
        <th>descripcion</th>
        <th>instructor</th>
        <th>duracion</th>
      </tr>
      <% for(Curso c: cursos) {%>
        <tr>
          <td><%=c.getId()%></td>
          <td><%=c.getNombre()%></td>
          <td><%=c.getDescripcion()%></td>
          <td><%=c.getInstructor()%></td>
          <td><%=c.getDuracion()%></td>
        </tr>
      <%}%>
    </table>
  </body>
</html>
