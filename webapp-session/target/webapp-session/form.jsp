<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*, org.aguzman.apiservlet.webapp.headers.models.*"%>
<%
  List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
  Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
%>

<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="UTF-8">
      <title>Formulario De Productos</title>
  </head>
  <body>
    <h1>Formulario De Productos</h1>
    <form action="<%=request.getContextPath()%>/productos/form" method="post">

      <div>
        <label for="nombre">Nombre</label>
        <div>
          <input type="text" name="nombre" id="nombre">
        </div>
        <% if(errores != null && errores.containsKey("nombre")) { %>
          <div style="color:red"><%=errores.get("nombre")%></div>
        <%}%>
      </div>

      <div>
        <label for="precio">Precio</label>
        <div>
          <input type="number" name="precio" id="precio">
        </div>
        <% if(errores != null && errores.containsKey("precio")) { %>
          <div style="color:red"><%=errores.get("precio")%></div>
        <%}%>
      </div>

      <div>
        <label for="sku">Sku</label>
        <div>
          <input type="text" name="sku" id="sku">
        </div>
        <% if(errores != null && errores.containsKey("sku")) { %>
          <div style="color:red"><%=errores.get("sku")%></div>
        <%}%>
      </div>

      <div>
        <label for="fecha_registro">Fecha Registro</label>
        <div>
          <input type="date" name="fecha_registro" id="fecha_registro">
        </div>
        <% if(errores != null && errores.containsKey("fecha_registro")) { %>
          <div style="color:red"><%=errores.get("fecha_registro")%></div>
        <%}%>
      </div>

      <div>
        <label for="categoria">Categoria</label>
        <div>
          <select name="categoria" id="categoria">
            <option value="">--- Seleccionar ---</option>
            <%for(Categoria c : categorias) {%>
              <option value="<%=c.getId()%>"><%=c.getNombre()%></option>
            <%}%>
          </select>
        </div>
        <% if(errores != null && errores.containsKey("categoria")) { %>
          <div style="color:red"><%=errores.get("categoria")%></div>
        <%}%>
      </div>

      <div>
        <input type="submit" value="Crear">
      </div>

    </form>
  </body>
</html>