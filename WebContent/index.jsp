<!-- Author: Vlad Ciobanu -->
<!-- Student ID: C15716369 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
<title>Lotto Servlet</title>
</head>
<body>	
	 <form action="LottoServlet" method="post">
       	<input type="number" name="number1"/> First Number<br>        
        <input type="number" name="number2"/> Second Number<br>  
        <input type="number" name="number3"/> Third Number<br>  
        <input type="number" name="number4"/> Fourth Number<br>        
        <input type="number" name="number5"/> Fifth Number<br>  
        <input type="number" name="number6"/> Sixth Number<br> 
        <input type="text" name="username"/> Name<br>
        <input type="submit" value="submit">            
    </form>    

</body>
</html>