/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function addToCart(){
    var cartSpan=$('.simpleCart_total');
    
    var productId= document.getElementById("productId").value;
    
    
     $.get("OrderServlet?orderMode=add&prod="+productId+"&user=1",addHandle);
    
}

function addHandle(responseTxt, statusTxt, xhr){
     if (statusTxt === "success") {
        
        alert(responseTxt);
     }   
}

function checkIfProductExistInCart(){

    $.get("OrderServlet?orderMode=check&prod=1&user=1",checkHandle);
    return true;
    
}

function checkHandle(responseTxt, statusTxt, xhr){
     if (statusTxt === "success") {
        if(responseTxt==="true"){
            var link= document.getElementById("addToCart");
            link.style.display = 'none';
        }
        
     }   
}


window.onload=function onLoad(){
    checkIfProductExistInCart();
    
};