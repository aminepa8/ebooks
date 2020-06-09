<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<style type = "text/css">
    table, td,tr,th {
      border: 1px solid black;
    } 
    td{
    padding:8px;
    }
    #MyTable{
    	border-collapse: collapse;
    }
    h1{
    color:green;
    }
    </style>
<title>E-commerce Livre</title>
</head>
<body>
<div align="center">
	<h1>Liste des livres</h1>
	<table id="MyTable">
		<tr>
        	<th>Description</th>
        	<th>Prix</th>
        	<th></th>
       </tr>
       <c:forEach var="livre" items="${LivresList}">
	       <form action="cart" method="post">
	       	<tr>
	       		<td>${livre.getTitreLivre()}</td>
	       		<td>${livre.getPrixLivre()}</td>
	 
	       		<input type="hidden"  value="${livre.getCodeLivre()}" name="CodeLivre">
	       		<td ><input type="submit" value="Ajouter au chariot"></td>
	       	</tr>
	       	</form>
		</c:forEach>
      
	</table>
</div>
</body>
</html>