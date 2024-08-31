package com.adidas.design.patterns.structural.flyweight.code;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RectFactory {
    /**
     * Understanding the importance of the final keyword here
     *
     * final keyword: Prevents reassigning the reference of the rectanglesByColor map to a different map instance.
     * Map contents: The HashMap object itself is mutable. You can add, remove, or modify key-value pairs within the map.
     *
     * You can safely modify the contents of a final HashMap without violating the final constraint.
     */
    private static final Map<Color, MyRectOpt> rectanglesByColor = new HashMap<>();

    public static MyRectOpt getRect(Color color) {
        /**
         * this here is not allowed, here the reference variable "rectanglesByColor" is reassigned to a different HashMap
         *
         * rectanglesByColor = new HashMap<>();
         */

        MyRectOpt rectangle = rectanglesByColor.get(color);

        if (rectangle == null) {
            rectangle = new MyRectOpt(color);
            rectanglesByColor.put(color, rectangle);
        }

        return rectangle;
    }
}
