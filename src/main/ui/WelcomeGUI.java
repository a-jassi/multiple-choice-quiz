package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeGUI extends JPanel {

    private MainGraphicUIApp mainGUI;
    private JButton createButton;
    private JButton attemptButton;
    private JButton viewButton;
    private JButton progressButton;
    private JButton saveButton;
    private JButton loadButton;
    private JLabel welcomeText;

    public WelcomeGUI(MainGraphicUIApp mainGUI) {
        this.mainGUI = mainGUI;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setUpButtons();
        setAlignmentX(WIDTH / 2);
        setVisible(true);
    }

    private void setUpButtons() {
        setUpWelcomeText();
        setUpGap();
        setUpCreate();
        setUpAttemptButton();
        setUpViewButton();
        setUpProgressButton();
        setUpSaveButton();
        setUpLoadButton();
    }

    private void setUpCreate() {
        createButton = new JButton("Create");
        createButton.setBounds(mainGUI.WIDTH / 2, 20, 80, 25);
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.setCurrentPanel(new CreateGUI(mainGUI));
            }
        });
        add(createButton);
    }

    private void setUpAttemptButton() {
        attemptButton = new JButton("Attempt");
        attemptButton.setBounds(mainGUI.WIDTH / 2, 50, 80, 25);
        attemptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!(mainGUI.getQuizManager().getAllQuizzesMade().size() == 0)) {
                    mainGUI.setCurrentPanel(new AttemptGUI(mainGUI));
                }
            }
        });
        add(attemptButton);
    }

    private void setUpViewButton() {
        viewButton = new JButton("View");
        viewButton.setBounds(mainGUI.WIDTH / 2, 80, 80, 25);
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!(mainGUI.getQuizManager().getAllQuizzesMade().size() == 0)) {
                    mainGUI.setCurrentPanel(new ViewGUI(mainGUI));
                }
            }
        });
        add(viewButton);
    }

    private void setUpProgressButton() {
        progressButton = new JButton("Progress");
        progressButton.setBounds(mainGUI.WIDTH / 2, 110, 80, 25);
        progressButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!(mainGUI.getQuizManager().getAttemptedQuizzes().size() == 0)) {
                    mainGUI.setCurrentPanel(new ProgressGUI(mainGUI));
                }
            }
        });
        add(progressButton);
    }

    private void setUpSaveButton() {
        saveButton = new JButton("Save");
        saveButton.setBounds(mainGUI.WIDTH / 2, 140, 80, 25);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.setCurrentPanel(new SaveGUI(mainGUI));
            }
        });
        add(saveButton);
    }

    private void setUpLoadButton() {
        loadButton = new JButton("Load");
        loadButton.setBounds(mainGUI.WIDTH / 2, 170, 80, 25);
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.setCurrentPanel(new LoadGUI(mainGUI));
            }
        });
        add(loadButton);
    }

    private void setUpWelcomeText() {
        String text = "<html>Welcome to the MultipleChoiceQuizApp! <br> To begin using this application, "
                + "please click one of the options below!</html>";
        welcomeText = new JLabel(text);
        add(welcomeText);
    }

    private void setUpGap() {
        add(Box.createRigidArea(new Dimension(0, 50)));
    }

}
