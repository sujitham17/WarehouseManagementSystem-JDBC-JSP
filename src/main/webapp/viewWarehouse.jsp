<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
    <title>View Warehouse Record</title>
</head>
<body>

<h2>View Warehouse Record</h2>

<form action="MainServlet" method="post">

    <input type="hidden" name="operation" value="viewRecord">

    Item Name: <input type="text" name="itemName" required><br><br>

    Received Date: <input type="date" name="receivedDate" required><br><br>

    <input type="submit" value="View Record">

</form>

</body>
</html>
