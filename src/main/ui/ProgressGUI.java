package ui;

import model.AttemptedQuiz;
import model.Question;
import model.Quiz;
import model.QuizManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProgressGUI extends JPanel {

    private MainGraphicUIApp mainGUI;
    private QuizManager quizManager;
    private JTextField inputQuizName;

    public ProgressGUI(MainGraphicUIApp mainGUI) {
        this.mainGUI = mainGUI;
        quizManager = mainGUI.getQuizManager();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setUpIndividualOrOverall();
        mainGUI.setCurrentPanel(this);
    }

    private void setUpIndividualOrOverall() {
        JLabel info = new JLabel("Would you like to view Individual or Overall statistics?");
        add(info);
        add(Box.createRigidArea(new Dimension(0, 50)));
        JButton individual = new JButton("Individual");
        individual.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setUpIndividualPanel();
            }
        });
        JButton overall = new JButton("Overall");
        overall.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setUpOverallPanel();
            }
        });
        add(individual);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(overall);
    }

    private void setUpOverallPanel() {
        JPanel overallPanel = new JPanel();
        overallPanel.setLayout(new BoxLayout(overallPanel, BoxLayout.Y_AXIS));

        JLabel overallInfo = new JLabel("Here are your overall statistics on attempted quizzes:");
        overallPanel.add(overallInfo);
        overallPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        JLabel passedLabel = new JLabel("Passed: " + (quizManager.overallAttemptedPercentCorrect() >= 50));
        JLabel scoreLabel = new JLabel("Score: " + quizManager.overallAttemptedQuestionsCorrect()
                + "/" + quizManager.overallPotentialQuestionsCorrect());
        JLabel percentageLabel = new JLabel("Percentage: " + quizManager.overallAttemptedPercentCorrect() + "%");
        overallPanel.add(passedLabel);
        overallPanel.add(scoreLabel);
        overallPanel.add(percentageLabel);

        overallPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        JButton goToMainPage = new JButton("Done");
        goToMainPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.resetToWelcome();
            }
        });

        overallPanel.add(goToMainPage);
        mainGUI.setCurrentPanel(overallPanel);
    }

    private void setUpIndividualPanel() {
        JPanel individualChoosePanel = new JPanel();
        individualChoosePanel.setLayout(new BoxLayout(individualChoosePanel, BoxLayout.Y_AXIS));

        setUpLabels(individualChoosePanel);
        setUpTextField(individualChoosePanel);
        setUpEnterButton(individualChoosePanel);

        mainGUI.setCurrentPanel(individualChoosePanel);
    }

    private void setUpLabels(JPanel panel) {
        JLabel info = new JLabel("Here is a list of all attempted quizzes:");
        panel.add(info);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));

        List<AttemptedQuiz> quizzes = mainGUI.getQuizManager().getAttemptedQuizzes();
        for (AttemptedQuiz attempted : quizzes) {
            JLabel label = new JLabel(attempted.getQuiz().getName());
            panel.add(label);
        }

        JLabel input = new JLabel("<html><br><br>Input the attempted quiz to view:</html>");
        panel.add(input);
    }

    private void setUpTextField(JPanel panel) {
        inputQuizName = new JTextField();
        panel.add(inputQuizName);
    }

    private void setUpEnterButton(JPanel panel) {
        JButton enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!inputQuizName.getText().equals("")) {
                    showPanelWithIndividualStats();
                }
            }
        });

        panel.add(enter);
    }

    private void showPanelWithIndividualStats() {
        JPanel individualPanel = new JPanel();
        individualPanel.setLayout(new BoxLayout(individualPanel, BoxLayout.Y_AXIS));

        JLabel info = new JLabel("Here are your overall statistics for the selected attempted quiz:");
        individualPanel.add(info);
        individualPanel.add(Box.createRigidArea(new Dimension(0, 50)));

        AttemptedQuiz attemptedQuiz = quizManager.getAttemptedQuizFromName(inputQuizName.getText());
        JLabel passed = new JLabel("Passed: " + attemptedQuiz.checkIfPassed());
        List<Question> questions = attemptedQuiz.getQuiz().getQuestions();
        JLabel score = new JLabel("Score: " + attemptedQuiz.getGrade() + "/" + questions.size());
        JLabel percentage = new JLabel("Percentage: " + attemptedQuiz.getGradeAsPercent() + "%");
        individualPanel.add(passed);
        individualPanel.add(score);
        individualPanel.add(percentage);

        individualPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        JButton returnToMenu = new JButton("Done");
        returnToMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.resetToWelcome();
            }
        });
        individualPanel.add(returnToMenu);

        mainGUI.setCurrentPanel(individualPanel);
    }

}
