package ui;

import model.QuizManager;
import persistence.JsonReader;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static ui.MainGraphicUIApp.JSON_FILE_WRITTEN_TO;

public class LoadGUI extends JPanel {

    private MainGraphicUIApp mainGUI;
    private JsonReader jsonReader;
    private JButton loadButton;
    private JButton closeButton;
    private JLabel successLoad;
    private JLabel failLoad;
    private QuizManager currentManager;

    public LoadGUI(MainGraphicUIApp mainGUI) {
        this.mainGUI = mainGUI;
        jsonReader = mainGUI.getJsonReader();
        currentManager = mainGUI.getQuizManager();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(1200, 1000);

        successLoad = new JLabel("QuizManager successfully loaded from " + JSON_FILE_WRITTEN_TO);
        successLoad.setBounds(10, 20, 150, 25);
        failLoad = new JLabel("Unable to load from file: " + JSON_FILE_WRITTEN_TO);
        failLoad.setBounds(10, 50, 150, 25);
        add(successLoad);
        add(failLoad);
        successLoad.setVisible(false);
        failLoad.setVisible(false);

        setUpCloseButton();
        setUpLoad();
        setVisible(true);
    }

    public void setUpCloseButton() {
        closeButton = new JButton("Close Load Menu");
        closeButton.setBounds(10, 110, 120, 25);
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(closeButton);
    }

    public void setUpLoad() {
        loadButton = new JButton("Load");
        loadButton.setBounds(10, 80, 120, 25);
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadProgress();
            }
        });
        add(loadButton);
    }

    public void loadProgress() {
        try {
            currentManager = jsonReader.read();
            successLoad.setVisible(true);
        } catch (IOException e) {
            failLoad.setVisible(true);
        }
    }


}
