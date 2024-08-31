package com.adidas.design.patterns.structural.flyweight.code;

import java.awt.*;

public class MyRectOpt {

    private Color color;

    /**
     *
     * @param color
     * this color is the intrinsic property that we are sharing and keeping in the hashMap
     */
    public MyRectOpt(Color color){
        this.color = color;
    }

    public void draw(Graphics graphics, int leftX, int lowerY, int rightX, int upperY) {
        graphics.setColor(this.color);
        graphics.fillRect(leftX, lowerY, rightX, upperY);
    }

}
