<%@ page import="java.util.List" %>
<%@ page import="com.wipro.warehouse.bean.WarehouseBean" %>

<!DOCTYPE html>
<html>
<head>
    <title>All Warehouse Records</title>
</head>
<body>

<h2>All Warehouse Records</h2>

<%
    List<WarehouseBean> list =
        (List<WarehouseBean>) request.getAttribute("warehouseList");

    String message = (String) request.getAttribute("message");

    if (message != null) {
%>
        <h3><%= message %></h3>
<%
    }
    else if (list != null && !list.isEmpty()) {
%>

<table border="1">
<tr>
    <th>Record ID</th>
    <th>Item Name</th>
    <th>Location</th>
    <th>Received Date</th>
    <th>Quantity</th>
    <th>Status</th>
    <th>Remarks</th>
</tr>

<%
    for (WarehouseBean bean : list) {
%>
<tr>
    <td><%= bean.getRecordId() %></td>
    <td><%= bean.getItemName() %></td>
    <td><%= bean.getLocation() %></td>
    <td><%= bean.getReceivedDate() %></td>
    <td><%= bean.getQuantity() %></td>
    <td><%= bean.getStatus() %></td>
    <td><%= bean.getRemarks() %></td>
</tr>
<%
    }
%>

</table>

<%
    }
%>

<br>
<a href="menu.html">Back to Menu</a>

</body>
</html>
