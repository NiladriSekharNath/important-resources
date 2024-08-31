package com.adidas.design.patterns.behavioural.memento.code;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Slf4j
public class TestMemento extends JFrame {

    public static void main(String[] args){
        new TestMemento();
    }

    private JButton saveButton, undoButton, redoButton;

    private JTextArea theArticle = new JTextArea(40, 60);

    CareTaker careTaker = new CareTaker();

    Originator originator = new Originator();

    int saveFiles = 0 , currentArticle = 0;

    public TestMemento(){
        this.setSize(750, 780);
        this.setTitle("Memento Design Pattern ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();

        panel1.add(new JLabel("Article"));

        panel1.add(theArticle);

        ButtonListener saveListener = new ButtonListener();
        ButtonListener undoListener = new ButtonListener();
        ButtonListener redoListener = new ButtonListener();

        saveButton = new JButton("Save");
        saveButton.addActionListener(saveListener);

        undoButton = new JButton("Undo");
        undoButton.addActionListener(undoListener);

        redoButton = new JButton("Redo");
        redoButton.addActionListener(redoListener);

        panel1.add(saveButton);
        panel1.add(undoButton);
        panel1.add(redoButton);

        this.add(panel1);
        this.setVisible(true);

    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == saveButton){
                String textInTextArea = theArticle.getText();
                originator.set(textInTextArea);
                careTaker.addMemento(originator.storeInMemento());

                /**
                 *  here we are incrementing the number of savedFiles so once we save one file we
                 *  increment it
                 *
                 *  also similarly for the count of the Articles as well
                 */
                saveFiles++;
                currentArticle++;

                log.info("Save files : {}", saveFiles);

                undoButton.setEnabled(true);
            } else{
                if(e.getSource() == undoButton){
                    if(currentArticle >= 1){
                        currentArticle--;

                        String textBoxInString = originator.restoreFromMemento(careTaker.getMemento(currentArticle));

                        theArticle.setText(textBoxInString);

                        redoButton.setEnabled(true);
                    }
                    else{
                        undoButton.setEnabled(false);
                    }
                }
                else {
                    if(e.getSource() == redoButton){
                        if((saveFiles - 1) > currentArticle){
                            currentArticle++;

                            String textBoxInString = originator.restoreFromMemento(careTaker.getMemento(currentArticle));

                            theArticle.setText(textBoxInString);

                            undoButton.setEnabled(true);
                        } else{
                            redoButton.setEnabled(false);
                        }
                    }

                }
            }
        }
    }
}
