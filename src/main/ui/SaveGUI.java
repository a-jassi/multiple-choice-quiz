package ui;

import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import static ui.MainGraphicUIApp.JSON_FILE_WRITTEN_TO;

public class SaveGUI extends JPanel {

    private MainGraphicUIApp mainGUI;
    private JButton saveButton;
    private JButton closeButton;
    private JsonWriter jsonWriter;
    private JLabel successSave;
    private JLabel failedToSave;

    public SaveGUI(MainGraphicUIApp mainGUI) {
        this.mainGUI = mainGUI;
        jsonWriter = mainGUI.getJsonWriter();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        successSave = new JLabel("QuizManager was successfully saved to " + JSON_FILE_WRITTEN_TO);
        successSave.setBounds(10, 20, 120, 25);
        failedToSave = new JLabel("Unable to write to file: " + JSON_FILE_WRITTEN_TO);
        failedToSave.setBounds(10, 50, 120, 25);
        add(successSave);
        add(failedToSave);
        successSave.setVisible(false);
        failedToSave.setVisible(false);

        setUpCloseButton();
        setUpSave();
        setVisible(true);
    }

    public void setUpCloseButton() {
        closeButton = new JButton("Close Save Menu");
        closeButton.setBounds(10, 110, 120, 25);
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(closeButton);
    }

    public void setUpSave() {
        saveButton = new JButton("Save");
        saveButton.setBounds(10, 80, 120, 25);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveProgress();
            }
        });
        add(saveButton);
    }

    public void saveProgress() {
        try {
            jsonWriter.open();
            jsonWriter.write(mainGUI.getQuizManager());
            jsonWriter.close();
            successSave.setVisible(true);
        } catch (FileNotFoundException e) {
            failedToSave.setVisible(true);
        }
    }

}
