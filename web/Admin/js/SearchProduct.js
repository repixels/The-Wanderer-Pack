/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function searchProducts()
{
    var productName = document.getElementById("searchInput").value;

$.get("Product?productMode=search&name="+ productName,searchHandle);
}

function searchHandle(responseTxt, statusTxt, xhr){
     if (statusTxt === "success") {
        var result = JSON.parse(responseTxt);
        printHref(result);
//           for (i = 0; i < result.length; i++) {
//            alert(result[i]);
//
//        }
         
     }
    
}

function printHref(result){
    var hrefDiv=document.getElementById("searchHrefDiv");
    
    for (i = 0; i < result.length; i++) {
        var a = document.createElement('a');
        
        var productName = document.createTextNode(result[i][1]);
        a.appendChild(productName);
        a.href="Product?productMode=edit&id="+result[i][0];

        hrefDiv.appendChild(a);
         var br = document.createElement("br");
        hrefDiv.appendChild(br); 
        }
}