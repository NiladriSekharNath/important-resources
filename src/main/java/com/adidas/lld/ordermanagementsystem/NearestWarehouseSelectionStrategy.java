package com.adidas.lld.ordermanagementsystem;

import java.util.List;

public class NearestWarehouseSelectionStrategy extends WarehouseSelectionStrategy{
  @Override
  public Warehouse selectWarehouse(List<Warehouse> warehouseList) {
    /**
     * algo to pick the nearest warehouse, for now we are just returning the 0-th warehouse;
     */
    return warehouseList.get(0);
  }
}
