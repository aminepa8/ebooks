<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
        
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script
			  src="https://code.jquery.com/jquery-3.5.1.min.js"
			  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
			  crossorigin="anonymous"></script>
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

     .QuantiteBox{
     	width:20px;
     }
     h1{
     color:green;
     }
    </style>
    <script type="text/javascript"> 
    function increment_quantity(codeLivre_id) {
        var inputQuantityElement = parseFloat($("#input-quantity-"+codeLivre_id).val());
        if(inputQuantityElement > 0){
        	console.log(inputQuantityElement);
            var livre_prix = parseFloat($("#prix-"+codeLivre_id).val());
            $("#input-quantity-"+codeLivre_id).attr("value", inputQuantityElement);//this
         	// Store Quantity
            sessionStorage.setItem("quantity-of-book-"+codeLivre_id, inputQuantityElement);
            console.log(livre_prix);
            var newMontant = inputQuantityElement * livre_prix;
          //Store total Price
            sessionStorage.setItem("montant-of-book-"+codeLivre_id, newMontant.toFixed(2));
            console.log(newMontant);
            $('#montant-'+codeLivre_id).text(newMontant.toFixed(2));//avoir juste deux numero apres virgule
        }else{
        	alert("Wrong input");
        }
        
    }
    
    function remove(rowId){
        $('#item-' + rowId).fadeOut('slow', function(){
        	sessionStorage.removeItem('quantity-of-book-'+rowId);
        	sessionStorage.removeItem('montant-of-book-'+rowId);
            $('#item-' + rowId).remove();
        });
        $.ajax({
        	  type: "GET",
        	  url: "chario?supprimerLivreId="+rowId,
        	  //success: alert("Livre retirer"),
        	});
    }
    //
   $(window).on('load', function()  {
    	console.log("Document Ready");
  	// Run code
  	var bookNumbers = 4;
  	var i;//codeLivre
	  	for (i = 1; i <= bookNumbers; i++) {
	  		if ('quantity-of-book-'+i in sessionStorage) {//sessionStorage.getItem('quantity-of-book-'+i).length == 0
	  			console.log("Qantite");
	  			console.log(sessionStorage.getItem('quantity-of-book-'+i));
	  			console.log("montant");
	  			console.log(sessionStorage.getItem('montant-of-book-'+i));
	  			$("#input-quantity-"+i).attr("value", sessionStorage.getItem('quantity-of-book-'+i));//this
	  			$('#montant-'+i).text(sessionStorage.getItem('montant-of-book-'+i));
	  		}
	  		console.log("hello");
		}
	});
    </script>
<title>E-commerce Livre</title>
</head>
<body>
<div align="center">
	<h1>Liste des livres</h1>
	<table  id="MyTable">
		<tr>
        	<th>Quantité</th>
			<th>Description</th>
        	<th>Prix</th>
        	<th>Montant</th>
       </tr>
        <c:forEach var="livre" items="${LivresList}">
	   
	       	<tr id="item-${livre.getCodeLivre()}">
	       		<td>
	       		<input  class="QuantiteBox" type="text" name="input-quantity-${livre.getCodeLivre()}" id="input-quantity-${livre.getCodeLivre()}" value="1">
	       		<button class="button" onClick="increment_quantity(${livre.getCodeLivre()})">Modifier</button>
	       		</td>
	       		<td>${livre.getTitreLivre()}</td>
	       		<td >
	       			<span>${livre.getPrixLivre()}</span> ?
	       		</td>
	       		<input type="hidden"  value="${livre.getPrixLivre()}" name="prix-${livre.getCodeLivre()}" id="prix-${livre.getCodeLivre()}">
	 			<td > 
	 			<span id="montant-${livre.getCodeLivre()}"> ${livre.getPrixLivre()}</span>
	 				?
	 			</td>
	       		<input type="hidden"  value="${livre.getCodeLivre()}" name="CodeLivre">
	       		<td ><button  id="DeleteButton" onclick="remove(${livre.getCodeLivre()})">Supprimer</button></td>
	       		
	       	</tr>
	    
		</c:forEach>
	</table>
	
	<p><strong>pour changer la quantité,</strong> entrez la nouvelle quantité puis appuyer sue le bouton Modifier.</p>
	<button onclick="window.location.href='/ELibrarie'">Continuer vos achats</button>
	<br>
	<button onclick="window.location.href='/ELibrarie/cart'">Commander/Payer</button>
</div>
</body>
</html>