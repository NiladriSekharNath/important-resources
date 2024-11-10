package com.adidas.lld.elevatorsystem.approach1;

import java.util.ArrayList;
import java.util.List;

public class Main {

  /**
   * https://akhileshmj.medium.com/lld-5-elevator-system-52dca7dde379
   */
  public static void main(String args[]) {

    List<Floor> floorList = new ArrayList<>();
    Floor floor1 = new Floor(1);
    Floor floor2 = new Floor(2);
    Floor floor3 = new Floor(3);
    Floor floor4 = new Floor(4);
    Floor floor5 = new Floor(5);
    floorList.add(floor1);
    floorList.add(floor2);
    floorList.add(floor3);
    floorList.add(floor4);
    floorList.add(floor5);

    Building building = new Building(floorList);

  }

}
