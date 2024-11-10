package com.adidas.lld.ordermanagementsystem;

import java.util.List;

public class WarehouseController {
  List<Warehouse> warehouseList;
  WarehouseSelectionStrategy warehouseSelectionStrategy;

  public WarehouseController(List<Warehouse> warehouseList, WarehouseSelectionStrategy warehouseSelectionStrategy){
    this.warehouseList = warehouseList;
    this.warehouseSelectionStrategy = warehouseSelectionStrategy;
  }

  public void addNewWarehouse(Warehouse warehouse){
    warehouseList.add(warehouse);
  }

  //remove warehouse
  public void removeWarehouse(Warehouse warehouse){
    warehouseList.remove(warehouse);
  }

  public Warehouse selectWarehouse(WarehouseSelectionStrategy warehouseSelectionStrategy){
    this.warehouseSelectionStrategy = warehouseSelectionStrategy;
    return warehouseSelectionStrategy.selectWarehouse(warehouseList);
  }
}
