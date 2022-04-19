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
        <h1>Hello ${user.name } created a new book</h1>
        
       
        <a href="/dashboard">Dashboard</a>
       
         
         <form:form action="/makingBook" method="post" modelAttribute="book">
            <input type="hidden" name="user" value="${user.id}">
            
            
                <div class="form-group">
                    <label>title:</label>
                    <form:input path="title" class="form-control" />
                    <form:errors path="title" class="text-danger" />
                </div>
                <div class="form-group">
                    <label>author:</label>
                    <form:input path="author" class="form-control" />
                    <form:errors path="author" class="text-danger" />
                </div>
                <div class="form-group">
                    <label>Description:</label>
                    <form:input path="description" class="form-control" />
                    <form:errors path="description" class="text-danger" />
                </div>
   
                <input type="submit" value="Make new book" class="btn btn-primary" />
            </form:form>
         
         
         
    </div> <!-- End of Container -->
</body>
</html>