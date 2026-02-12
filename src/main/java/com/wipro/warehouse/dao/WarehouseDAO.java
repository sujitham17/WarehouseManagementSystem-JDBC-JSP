package com.wipro.warehouse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.wipro.warehouse.util.DBUtil;

public class WarehouseDAO {

    public boolean recordExists(String itemName, Date receivedDate) {

        boolean exists = false;

        try {
            Connection con = DBUtil.getDBConnection();

            String query = "SELECT * FROM WAREHOUSE_TB WHERE ITEMNAME = ? AND RECEIVED_DATE = ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, itemName);
            ps.setDate(2, new java.sql.Date(receivedDate.getTime()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                exists = true;
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }
    
    public String generateRecordID(String itemName, Date receivedDate) {

        String recordId = null;

        try {
            Connection con = DBUtil.getDBConnection();

            // Convert date to YYYYMMDD
            java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMMdd");
            String datePart = format.format(receivedDate);

            // First two uppercase letters of item name
            String itemPart = itemName.substring(0, 2).toUpperCase();

            // Get sequence value
            String seqQuery = "SELECT WAREHOUSE_SEQ.NEXTVAL FROM DUAL";
            PreparedStatement ps = con.prepareStatement(seqQuery);
            ResultSet rs = ps.executeQuery();

            int seqNumber = 0;

            if (rs.next()) {
                seqNumber = rs.getInt(1);
            }

            // Make sure sequence is 2 digit
            String seqPart = String.format("%02d", seqNumber);

            recordId = datePart + itemPart + seqPart;

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return recordId;
    }
    
    public String createRecord(com.wipro.warehouse.bean.WarehouseBean warehouseBean) {

        String status = "FAIL";

        try {
            Connection con = DBUtil.getDBConnection();

            String query = "INSERT INTO WAREHOUSE_TB "
                    + "(RECORDID, ITEMNAME, LOCATION, RECEIVED_DATE, QUANTITY, STATUS, REMARKS) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, warehouseBean.getRecordId());
            ps.setString(2, warehouseBean.getItemName());
            ps.setString(3, warehouseBean.getLocation());
            ps.setDate(4, new java.sql.Date(warehouseBean.getReceivedDate().getTime()));
            ps.setInt(5, warehouseBean.getQuantity());
            ps.setString(6, warehouseBean.getStatus());
            ps.setString(7, warehouseBean.getRemarks());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = warehouseBean.getRecordId();
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
    
    public com.wipro.warehouse.bean.WarehouseBean fetchRecord(String itemName, Date receivedDate) {

        com.wipro.warehouse.bean.WarehouseBean bean = null;

        try {
            Connection con = DBUtil.getDBConnection();

            String query = "SELECT * FROM WAREHOUSE_TB WHERE ITEMNAME = ? AND RECEIVED_DATE = ?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, itemName);
            ps.setDate(2, new java.sql.Date(receivedDate.getTime()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                bean = new com.wipro.warehouse.bean.WarehouseBean();

                bean.setRecordId(rs.getString("RECORDID"));
                bean.setItemName(rs.getString("ITEMNAME"));
                bean.setLocation(rs.getString("LOCATION"));
                bean.setReceivedDate(rs.getDate("RECEIVED_DATE"));
                bean.setQuantity(rs.getInt("QUANTITY"));
                bean.setStatus(rs.getString("STATUS"));
                bean.setRemarks(rs.getString("REMARKS"));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }
    
    public java.util.List<com.wipro.warehouse.bean.WarehouseBean> fetchAllRecords() {

        java.util.List<com.wipro.warehouse.bean.WarehouseBean> list =
                new java.util.ArrayList<com.wipro.warehouse.bean.WarehouseBean>();

        try {
            Connection con = DBUtil.getDBConnection();

            String query = "SELECT * FROM WAREHOUSE_TB";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                com.wipro.warehouse.bean.WarehouseBean bean =
                        new com.wipro.warehouse.bean.WarehouseBean();

                bean.setRecordId(rs.getString("RECORDID"));
                bean.setItemName(rs.getString("ITEMNAME"));
                bean.setLocation(rs.getString("LOCATION"));
                bean.setReceivedDate(rs.getDate("RECEIVED_DATE"));
                bean.setQuantity(rs.getInt("QUANTITY"));
                bean.setStatus(rs.getString("STATUS"));
                bean.setRemarks(rs.getString("REMARKS"));

                list.add(bean);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }




}
