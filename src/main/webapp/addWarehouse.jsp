<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
    <title>Add Warehouse Record</title>
</head>
<body>

<h2>Add Warehouse Record</h2>

<form action="MainServlet" method="post">

    <input type="hidden" name="operation" value="newRecord">

    Item Name: <input type="text" name="itemName" required><br><br>

    Location: <input type="text" name="location" required><br><br>

    Received Date: <input type="date" name="receivedDate" required><br><br>

    Quantity: <input type="number" name="quantity" required><br><br>

    Status:
    <select name="status">
        <option value="In-Stock">In-Stock</option>
        <option value="Dispatched">Dispatched</option>
        <option value="Damaged">Damaged</option>
    </select>
    <br><br>

    Remarks: <input type="text" name="remarks"><br><br>

    <input type="submit" value="Add Record">

</form>

</body>
</html>
