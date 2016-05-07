<%-- 
    Document   : search
    Created on : Apr 26, 2016, 9:30:57 PM
    Author     : Mohammed
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<li class="xn-search">
    <form role="form">
        <input  id="searchInput" type="text" class="search" name="search" placeholder="Search..." onkeyup="searchProducts()" onblur="setTimeout(hideResults, 100)"/>
        <ul id="searchResults" style="    display: none;
                                            color: white;
                                            background-color: black;                                            
                                            width: 200px;
                                            margin-top: 1px;
                                            opacity: 0.9;
            ">
        </ul>
    </form>
</li>   

   