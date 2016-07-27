<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
   <head>
   <title>Hello Spring MVC</title>
   </head>
   <body>
   <form:form action="/admin">
   <table>
   <tr>
   <td><form:label path="name">Name</form:label></td>
   <td><form:input path="name"/></td>
   </tr>
   </table>
   </form:form>
   </body>
</html>