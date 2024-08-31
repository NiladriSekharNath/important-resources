package com.adidas.design.patterns.structural.flyweight.code;

import java.awt.*;

public class MyRect {

    private Color color;
    private int leftX, lowerY, rightX, upperY;

    public MyRect(Color color, int leftX, int lowerY, int rightX, int upperY){

        this.color = color;
        this.leftX = leftX;
        this.lowerY = lowerY;
        this.rightX = rightX;
        this.upperY = upperY;

    }

    public void draw(Graphics graphics){
        graphics.setColor(this.color);
        graphics.fillRect(leftX, lowerY, rightX, upperY);
    }
}
