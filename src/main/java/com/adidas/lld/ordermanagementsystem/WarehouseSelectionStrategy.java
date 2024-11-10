package com.adidas.lld.ordermanagementsystem;

import java.util.List;

public abstract class WarehouseSelectionStrategy {
  public abstract Warehouse selectWarehouse(List<Warehouse> warehouseList);
}
