package com.adidas.design.patterns.structural.flyweight.code;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

@Slf4j
public class FlyWeightTest extends JFrame {
    JButton startDrawing;

    int windowWidth = 1750;
    int windowHeight = 500;

    Color[] shapeColor = {Color.orange, Color.red, Color.yellow, Color.blue
            , Color.pink, Color.cyan, Color.magenta, Color.black, Color.gray};


    public static void main(String args[]) {
        new FlyWeightTest();
    }

    public FlyWeightTest() {

        this.setSize(this.windowWidth, this.windowHeight);

        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Flyweight Test");

        JPanel contentPane = new JPanel();

        contentPane.setLayout(new BorderLayout());

        final JPanel drawingPanel = new JPanel();

        startDrawing = new JButton("Button 1");

        contentPane.add(drawingPanel, BorderLayout.CENTER);

        contentPane.add(startDrawing, BorderLayout.SOUTH);

        startDrawing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Graphics graphics = drawingPanel.getGraphics();

                long startTime = System.currentTimeMillis();

                for (int i = 0; i < 100_000; i++) {
                    /**
                     * normal way
                     */
                    /*graphics.setColor(getRandColor());
                    graphics.fillRect(getRandX(), getRandY(), getRandX(), getRandY());*/

                    /**
                     *  unoptimised way
                     */
                    /*MyRect rect = new MyRect(getRandColor(), getRandX(), getRandY(), getRandX(), getRandY());
                    rect.draw(graphics);*/

                    /**
                     * optimised way
                     */

                    MyRectOpt rect = RectFactory.getRect(getRandColor());

                    rect.draw(graphics, getRandX(), getRandY(), getRandX(), getRandY());

                }

                long endTime = System.currentTimeMillis();

                log.info("Shooting rectangles took : {} millis", (endTime - startTime));
            }
        });

        this.add(contentPane);
        this.setVisible(true);

    }

    private Color getRandColor() {

        Random randomGenerator = new Random();
        int randInt = randomGenerator.nextInt(9);

        return shapeColor[randInt];

    }

    private int getRandX() {
        return (int) (Math.random() * this.windowWidth);
    }

    private int getRandY() {
        return (int) (Math.random() * this.windowHeight);
    }

}
