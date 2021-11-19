package ui;

import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import static ui.MainGraphicUIApp.JSON_FILE_WRITTEN_TO;

// represents the GUI for saving progress to file
public class SaveGUI extends JPanel {

    private MainGraphicUIApp mainGUI;           // main JFrame that displays the panels
    private JLabel information;                 // label that explains what each button does
    private JButton saveButton;                 // button that saves when pressed
    private JButton closeButton;                // button that closes SaveGUI when pressed
    private JsonWriter jsonWriter;              // object that writes to file
    private JLabel successSave;                 // label that indicates save was a success
    private JLabel failedToSave;                // label that indicates save was a failure

    // creates a panel for related functionality to saving to file
    public SaveGUI(MainGraphicUIApp mainGUI) {
        this.mainGUI = mainGUI;
        jsonWriter = mainGUI.getJsonWriter();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        information = new JLabel("<html>Please click \"Save\" to save the file"
                + "<br>Please click the \"Close Save Menu\" below to return to the main screen</html>");
        add(information);
        add(Box.createRigidArea(new Dimension(0, 25)));

        successSave = new JLabel("QuizManager was successfully saved to " + JSON_FILE_WRITTEN_TO);
        successSave.setBounds(10, 20, 120, 25);
        failedToSave = new JLabel("Unable to write to file: " + JSON_FILE_WRITTEN_TO);
        failedToSave.setBounds(10, 50, 120, 25);
        add(successSave);
        add(failedToSave);
        successSave.setVisible(false);
        failedToSave.setVisible(false);

        setUpSave();
        add(Box.createRigidArea(new Dimension(0, 10)));
        setUpCloseButton();
        add(Box.createRigidArea(new Dimension(0, 50)));
        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: creates close button and adds it to panel
    private void setUpCloseButton() {
        closeButton = new JButton("Close Save Menu");
        closeButton.setBounds(10, 110, 120, 25);
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.resetToWelcome();
            }
        });
        add(closeButton);
    }

    // MODIFIES: this
    // EFFECTS: creates save button that saves when pressed and adds to panel
    private void setUpSave() {
        saveButton = new JButton("Save");
        saveButton.setBounds(10, 80, 120, 25);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveProgress();
            }
        });
        add(saveButton);
    }

    // MODIFIES: this
    // EFFECTS: saves progress to file if possible
    private void saveProgress() {
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
