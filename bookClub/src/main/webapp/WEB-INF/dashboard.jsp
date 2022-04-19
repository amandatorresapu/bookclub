<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <!-- c:out ; c:forEach ; c:if -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   <!-- Formatting (like dates) -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
   <!-- form:form -->
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
   <!-- for rendering errors on PUT routes -->
 <%@ page isErrorPage="true" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Title Here</title>
  <!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
      rel="stylesheet" 
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
      crossorigin="anonymous">

</head>
<body>
    <div class="container"> <!-- Beginning of Container -->
        <h1>Welcome ${user.name} to the dashboard of books!</h1>
        
        <a href="/">Index/login</a>
        <a href="/dashboard">Dashboard</a>
        <a href="/createBook">Create new Book</a>
        
         
         <a class="btn btn-warning" href="/logout">logout</a>
           
         
         
          <c:forEach var="i" items="${allBooks}">
           <ul>
           		<li> <a href="/oneBook/${i.id }" > ${i.title} </a> ${i.author} - ${i.description } - ${i.user.name }<a href="/editBook/${i.id}">edit</a><a href="/delete/${i.id}">delete</a></li>
           		<p>------------------------------------------------------</p>
           </ul>
           
           </c:forEach>
        
    </div> <!-- End of Container -->
</body>
</html>