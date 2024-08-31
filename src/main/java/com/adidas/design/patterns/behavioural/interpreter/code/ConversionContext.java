package com.adidas.design.patterns.behavioural.interpreter.code;

public class ConversionContext {

    private String conversionQuestion  = "";

    private String conversionResponse = "";

    private String fromConversion = "";

    private String toConversion = "";

    private Double quantity;
    String [] partsOfQues;

    public ConversionContext (String input){
        this.conversionQuestion = input;
        this.partsOfQues = getInput().split(" ");

        this.fromConversion = getCapitalized(partsOfQues[1]);

        /**
         * 1 gallons to pints
         */
        this.toConversion = getLowerCase(partsOfQues[3]);

        this.quantity = Double.valueOf(partsOfQues[0]);

        /**
         * if the question is 1 gallons to pints
         *
         * this below conversionQuestion is going to return
         *
         * 1 gallons =
         */
        this.conversionQuestion = partsOfQues[0] + " " + partsOfQues[1] + " equals " ;
    }

    public String getInput(){
        return this.conversionQuestion;
    }

    public String getCapitalized(String wordToCapitalize){
        wordToCapitalize = wordToCapitalize.toLowerCase();
        wordToCapitalize = Character.toUpperCase(wordToCapitalize.charAt(0)) + wordToCapitalize.substring(1);

        int lengthOfWord = wordToCapitalize.length();

        if((wordToCapitalize.charAt(lengthOfWord - 1)) != 's'){
            wordToCapitalize = new StringBuffer(wordToCapitalize).insert(lengthOfWord, "s").toString();
        }

        return wordToCapitalize;
    }

    public String getLowerCase(String wordToLowerCase){
        return wordToLowerCase.toLowerCase();
    }

    public String getFromConversion() { return this.fromConversion; }

    public String getToConversion() { return this.toConversion; }

    public String getResponse() { return this.conversionResponse; }

    public double getQuantity() { return this.quantity; }
}
