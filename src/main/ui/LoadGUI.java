package ui;

import persistence.JsonReader;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static ui.MainGraphicUIApp.JSON_FILE_WRITTEN_TO;

// represents the GUI for loading data from file
public class LoadGUI extends JPanel {

    private MainGraphicUIApp mainGUI;       // JFrame that displays the panels
    private JsonReader jsonReader;          // reader that reads from file
    private JButton loadButton;             // button that executes read from file
    private JButton closeButton;            // button that closes LoadGUI and returns to main screen
    private JLabel successLoad;             // label that indicates load was successful
    private JLabel failLoad;                // label that indicates load failed
    private JLabel information;             // instructions on what to do in LoadGUI

    // creates a LoadGUI panel for functionality related to loading data from file
    public LoadGUI(MainGraphicUIApp mainGUI) {
        this.mainGUI = mainGUI;
        jsonReader = mainGUI.getJsonReader();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(1200, 1000);

        information = new JLabel("<html>Please click \"Load\" to load the file"
                + "<br>Please click the \"Close Load Menu\" below to return to the main screen</html>");
        add(information);
        add(Box.createRigidArea(new Dimension(0, 25)));

        successLoad = new JLabel("QuizManager successfully loaded from " + JSON_FILE_WRITTEN_TO);
        successLoad.setBounds(10, 20, 150, 25);
        failLoad = new JLabel("Unable to load from file: " + JSON_FILE_WRITTEN_TO);
        failLoad.setBounds(10, 50, 150, 25);
        add(successLoad);
        add(failLoad);
        successLoad.setVisible(false);
        failLoad.setVisible(false);

        setUpLoad();
        add(Box.createRigidArea(new Dimension(0, 10)));
        setUpCloseButton();
        add(Box.createRigidArea(new Dimension(0, 50)));
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates close button and adds it to panel
    private void setUpCloseButton() {
        closeButton = new JButton("Close Load Menu");
        closeButton.setBounds(10, 110, 120, 25);
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.resetToWelcome();
            }
        });
        add(closeButton);
    }

    // MODIFIES: this
    // EFFECTS: creates load button and adds it to the panel
    private void setUpLoad() {
        loadButton = new JButton("Load");
        loadButton.setBounds(10, 80, 120, 25);
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadProgress();
            }
        });
        add(loadButton);
    }

    // MODIFIES: this
    // EFFECTS: tries to load data from file
    private void loadProgress() {
        try {
            mainGUI.setQuizManager(jsonReader.read());
            successLoad.setVisible(true);
        } catch (IOException e) {
            failLoad.setVisible(true);
        }
    }


}
