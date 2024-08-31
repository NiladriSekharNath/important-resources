package com.adidas.design.patterns.behavioural.interpreter.code;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class MeasurementConversion {
    public static void main(String args[]) {

        JFrame frame = new JFrame();

        String questionAsked = JOptionPane.showInputDialog(frame, "What is your Question asked ?");

        ConversionContext question = new ConversionContext(questionAsked);

        String fromConversion = question.getFromConversion();

        String toConversion = question.getToConversion();

        double quantity = question.getQuantity();

        try {


            Class tempClass = Class.forName(String.format("com.adidas.design.patterns.behavioural.interpreter.code.%s", fromConversion));

            Constructor con = tempClass.getConstructor();

            Object convertFrom = (Expression) con.newInstance();

            Class[] methodParams = new Class[]{Double.TYPE};

            Method conversionMethod = tempClass.getMethod(toConversion, methodParams);

            Object[] params = new Object[]{Double.valueOf(quantity)};

            String toQuantity = (String) conversionMethod.invoke(convertFrom, params);

            /**
             *  we are shooting out let's say we are find out the number of pints
             *
             *  1 gallon = 8 pints
             */
            String answerToQuestion = question.getResponse() + toQuantity + " " + toConversion ;

            log.info("Answer to Question {}", answerToQuestion);

            JOptionPane.showMessageDialog(null, answerToQuestion);

            frame.dispose();


        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 InstantiationException e) {

        } catch (IllegalAccessException e) {

        }

    }
}
