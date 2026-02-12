package com.wipro.warehouse.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.wipro.warehouse.bean.WarehouseBean;
import com.wipro.warehouse.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    Administrator admin = new Administrator();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        try {

            if ("newRecord".equals(operation)) {

                WarehouseBean bean = new WarehouseBean();

                bean.setItemName(request.getParameter("itemName"));
                bean.setLocation(request.getParameter("location"));

                String dateStr = request.getParameter("receivedDate");
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                bean.setReceivedDate(date);

                bean.setQuantity(Integer.parseInt(request.getParameter("quantity")));
                bean.setStatus(request.getParameter("status"));
                bean.setRemarks(request.getParameter("remarks"));

                String result = admin.addRecord(bean);

                if ("FAIL".equals(result) || "INVALID INPUT".equals(result)) {
                    response.sendRedirect("error.html");
                } else {
                    response.sendRedirect("success.html");
                }
            }

            else if ("viewRecord".equals(operation)) {

                String itemName = request.getParameter("itemName");

                String dateStr = request.getParameter("receivedDate");
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);

                WarehouseBean bean = admin.viewRecord(itemName, date);

                if (bean == null) {
                    request.setAttribute("message",
                            "No matching records exists! Please try again!");
                } else {
                    request.setAttribute("warehouse", bean);
                }

                request.getRequestDispatcher("displayWarehouse.jsp")
                        .forward(request, response);
            }

            else if ("viewAllRecords".equals(operation)) {

                List<WarehouseBean> list = admin.viewAllRecords();

                if (list.isEmpty()) {
                    request.setAttribute("message", "No records available!");
                } else {
                    request.setAttribute("warehouseList", list);
                }

                request.getRequestDispatcher("displayAllWarehouses.jsp")
                        .forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}
