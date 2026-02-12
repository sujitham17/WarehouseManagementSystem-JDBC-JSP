package com.wipro.warehouse.service;

import java.util.Date;
import java.util.List;

import com.wipro.warehouse.bean.WarehouseBean;
import com.wipro.warehouse.dao.WarehouseDAO;
import com.wipro.warehouse.util.InvalidInputException;

public class Administrator {

    WarehouseDAO dao = new WarehouseDAO();

    public String addRecord(WarehouseBean warehouseBean) {

        try {

            if (warehouseBean == null ||
                warehouseBean.getItemName() == null ||
                warehouseBean.getReceivedDate() == null) {

                throw new InvalidInputException();
            }

            if (warehouseBean.getItemName().length() < 2) {
                return "INVALID ITEM NAME";
            }

            if (dao.recordExists(
                    warehouseBean.getItemName(),
                    warehouseBean.getReceivedDate())) {

                return "ALREADY EXISTS";
            }

            String recordId = dao.generateRecordID(
                    warehouseBean.getItemName(),
                    warehouseBean.getReceivedDate());

            warehouseBean.setRecordId(recordId);

            return dao.createRecord(warehouseBean);

        } catch (InvalidInputException e) {
            return "INVALID INPUT";
        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
    }
    
    public WarehouseBean viewRecord(String itemName, Date receivedDate) {

        return dao.fetchRecord(itemName, receivedDate);
    }
    
    public List<WarehouseBean> viewAllRecords() {

        return dao.fetchAllRecords();
    }


}
