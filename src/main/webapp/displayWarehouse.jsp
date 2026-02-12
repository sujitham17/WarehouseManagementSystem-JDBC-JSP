<%@ page import="com.wipro.warehouse.bean.WarehouseBean" %>

<!DOCTYPE html>
<html>
<head>
    <title>Display Warehouse Record</title>
</head>
<body>

<h2>Warehouse Record Details</h2>

<%
    WarehouseBean bean = (WarehouseBean) request.getAttribute("warehouse");
    String message = (String) request.getAttribute("message");

    if (message != null) {
%>
        <h3><%= message %></h3>
<%
    }
    else if (bean != null) {
%>

    Record ID: <%= bean.getRecordId() %> <br><br>
    Item Name: <%= bean.getItemName() %> <br><br>
    Location: <%= bean.getLocation() %> <br><br>
    Received Date: <%= bean.getReceivedDate() %> <br><br>
    Quantity: <%= bean.getQuantity() %> <br><br>
    Status: <%= bean.getStatus() %> <br><br>
    Remarks: <%= bean.getRemarks() %> <br><br>

<%
    }
%>

<br>
<a href="menu.html">Back to Menu</a>

</body>
</html>
