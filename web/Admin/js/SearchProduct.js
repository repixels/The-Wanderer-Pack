/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function searchProducts()
{
    var productName = document.getElementById("searchInput").value;
    if(productName.length > 0)
    {
        $.get("Product?productMode=search&name="+ productName,searchHandle);
    }
    else
    {
        var resultsUL = document.getElementById("searchResults");
        resultsUL.innerHTML = "";
        resultsUL.style.display = "none";
    }
}

function searchHandle(responseTxt, statusTxt, xhr){
     if (statusTxt === "success") {
        var result = JSON.parse(responseTxt);
        printHref(result);
     }   
}

function printHref(result){
    var resultsUL = document.getElementById("searchResults");
    resultsUL.innerHTML = "";
    if(result.length > 0)
    {
        resultsUL.style.display = "block";
        for (i = 0; i < result.length; i++)
        {
            var listItem = document.createElement("li");
            var listItemRefrence = document.createElement("a");
            var productName = document.createTextNode(result[i][1]);
            
            listItemRefrence.appendChild(productName);
            listItemRefrence.href="Product?productMode=edit&id="+result[i][0];
            
            listItem.appendChild(listItemRefrence);
            resultsUL.appendChild(listItem);
        }
    }
}