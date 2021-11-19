package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents the main menu GUI
public class WelcomeGUI extends JPanel {

    private MainGraphicUIApp mainGUI;           // main JFrame that displays the panels
    private JButton createButton;               // button that leads to creating a quiz
    private JButton attemptButton;              // button that leads to attempting a quiz
    private JButton viewButton;                 // button that leads to viewing a quiz
    private JButton progressButton;             // button that leads to viewing stats on attempted quiz
    private JButton saveButton;                 // button that leads to saving progress
    private JButton loadButton;                 // button that leads to loading progress
    private JLabel welcomeText;                 // label that has basic instructions

    // creates a new WelcomeGUI panel for the main menu
    public WelcomeGUI(MainGraphicUIApp mainGUI) {
        this.mainGUI = mainGUI;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setUpButtons();
        setVisible(true);

    }

    // EFFECTS: creates and adds all buttons to panel
    private void setUpButtons() {
        setUpIcon();
        setUpGap();
        setUpWelcomeText();
        setUpGap();
        setUpCreate();
        setUpAttemptButton();
        setUpViewButton();
        setUpProgressButton();
        setUpSaveButton();
        setUpLoadButton();
    }

    // setUpIcon references code from the link below: (answer with 109 up-votes)
    // https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon

    // link for image used: https://www.clipartmax.com/middle/m2i8A0G6G6m2N4m2_multiple-choice-icon-graphic-design/

    // EFFECTS: adds image of logo and name of app on welcome panel
    private void setUpIcon() {
        ImageIcon image = new ImageIcon("./data/mcqImage2.jpeg");
        Image imageAsImage = image.getImage();
        Image newImage = imageAsImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        image = new ImageIcon(newImage);
        JLabel label = new JLabel();
        label.setText("Multiple Choice Quiz App");
        label.setIcon(image);
        add(label);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds create button to panel
    private void setUpCreate() {
        createButton = new JButton("Create");
        createButton.setBounds(MainGraphicUIApp.WIDTH / 2, 20, 80, 25);
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.setCurrentPanel(new CreateGUI(mainGUI));
            }
        });
        add(createButton);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds attempt button to panel
    private void setUpAttemptButton() {
        attemptButton = new JButton("Attempt");
        attemptButton.setBounds(MainGraphicUIApp.WIDTH / 2, 50, 80, 25);
        attemptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!(mainGUI.getQuizManager().getAllQuizzesMade().size() == 0)) {
                    mainGUI.setCurrentPanel(new AttemptGUI(mainGUI));
                }
            }
        });
        add(attemptButton);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds view button to panel
    private void setUpViewButton() {
        viewButton = new JButton("View");
        viewButton.setBounds(MainGraphicUIApp.WIDTH / 2, 80, 80, 25);
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!(mainGUI.getQuizManager().getAllQuizzesMade().size() == 0)) {
                    mainGUI.setCurrentPanel(new ViewGUI(mainGUI));
                }
            }
        });
        add(viewButton);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds progress button to panel
    private void setUpProgressButton() {
        progressButton = new JButton("Progress");
        progressButton.setBounds(MainGraphicUIApp.WIDTH / 2, 110, 80, 25);
        progressButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!(mainGUI.getQuizManager().getAttemptedQuizzes().size() == 0)) {
                    mainGUI.setCurrentPanel(new ProgressGUI(mainGUI));
                }
            }
        });
        add(progressButton);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds save button to panel
    private void setUpSaveButton() {
        saveButton = new JButton("Save");
        saveButton.setBounds(MainGraphicUIApp.WIDTH / 2, 140, 80, 25);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.setCurrentPanel(new SaveGUI(mainGUI));
            }
        });
        add(saveButton);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds load button to panel
    private void setUpLoadButton() {
        loadButton = new JButton("Load");
        loadButton.setBounds(MainGraphicUIApp.WIDTH / 2, 170, 80, 25);
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.setCurrentPanel(new LoadGUI(mainGUI));
            }
        });
        add(loadButton);
    }

    // MODIFIES: this
    // EFFECTS: initializes and adds intro text to panel
    private void setUpWelcomeText() {
        String text = "<html>Welcome to the MultipleChoiceQuizApp! <br> To begin using this application, "
                + "please click one of the options below!</html>";
        welcomeText = new JLabel(text);
        add(welcomeText);
    }

    // EFFECTS: adds a vertical gap to panel 50 pixels high
    private void setUpGap() {
        add(Box.createRigidArea(new Dimension(0, 50)));
    }

}
