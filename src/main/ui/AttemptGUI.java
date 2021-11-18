package ui;

import model.AttemptedQuiz;
import model.Question;
import model.Quiz;
import model.QuizManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// represents the GUI for attempting quizzes
public class AttemptGUI extends JPanel {

    private MainGraphicUIApp mainGUI;
    private JTextField inputQuizName;
    private JButton enter;
    private JLabel quizName;

    public AttemptGUI(MainGraphicUIApp mainGUI) {
        this.mainGUI = mainGUI;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setUpLabels();
        setUpTextField();
        setUpEnterButton();
        chooseQuiz();
        setVisible(true);
    }

    private void setUpLabels() {
        List<Quiz> quizzes = mainGUI.getQuizManager().getAllQuizzesMade();
        for (Quiz quiz : quizzes) {
            JLabel label = new JLabel(quiz.getName());
            add(label);
        }

        JLabel text = new JLabel("<html><br><br>Input the quiz to attempt:</html>");
        add(text);
    }

    private void setUpTextField() {
        inputQuizName = new JTextField();
        add(inputQuizName);
    }

    private void setUpEnterButton() {
        enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseQuiz();
            }
        });
        add(enter);
    }

    private void chooseQuiz() {
        if (!inputQuizName.getText().equals("")) {
            QuizManager quizManager = mainGUI.getQuizManager();
            Quiz quiz = quizManager.getQuizFromName(inputQuizName.getText());
            AttemptedQuiz attemptedQuiz = new AttemptedQuiz(quiz);
            quizManager.addToAttemptedQuizzes(attemptedQuiz);
            goIntoQuiz(attemptedQuiz, 0);
            setVisible(false);
        }
    }

    private void goIntoQuiz(AttemptedQuiz attemptedQuiz, int index) {
        JPanel attemptPanel = new JPanel();
        attemptPanel.setLayout(new BoxLayout(attemptPanel, BoxLayout.Y_AXIS));

        Quiz quizFromAttempted = attemptedQuiz.getQuiz();
        quizName = new JLabel("<html>Currently attempting: " + quizFromAttempted.getName() + "<br><br></html>");
        attemptPanel.add(quizName);

        Question questionWorkedOn = getQuestionFromIndex(quizFromAttempted, index);

        JLabel question = new JLabel(questionWorkedOn.toStringAsHtml());
        attemptPanel.add(question);

        JTextField inputTextField = new JTextField();
        attemptPanel.add(inputTextField);

        JButton submitAnswer = new JButton("Submit Answer");
        submitAnswer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!inputTextField.getText().equals("")) {
                    attemptedQuiz.checkAnswer(index, inputTextField.getText());
                }
                try {
                    goIntoQuiz(attemptedQuiz, index + 1);
                } catch (IndexOutOfBoundsException exception) {
                    displayProgressPanel(attemptedQuiz);
                }
                // TODO: fix bug with incorrect progress report; maybe create separate method that listens for submit button
            }
        });
        attemptPanel.add(submitAnswer);
        attemptPanel.setVisible(true);
        mainGUI.setCurrentPanel(attemptPanel);
    }

    private void displayProgressPanel(AttemptedQuiz attemptedQuiz) {
        JPanel progressPanel = new JPanel();
        progressPanel.setLayout(new BoxLayout(progressPanel, BoxLayout.Y_AXIS));

        JLabel progressLabel = new JLabel("<html>Here is how you did on "
                + attemptedQuiz.getQuiz().getName() + "<br><br></html>");

        progressPanel.add(progressLabel);

        JLabel passedLabel = new JLabel("Passed: " + attemptedQuiz.checkIfPassed());
        progressPanel.add(passedLabel);
        int attemptedQuizSize = attemptedQuiz.getQuiz().getQuestions().size();
        JLabel scoreLabel = new JLabel("Score: " + attemptedQuiz.getGrade() + "/" + attemptedQuizSize);
        progressPanel.add(scoreLabel);
        JLabel percentageLabel = new JLabel("Percentage: " + attemptedQuiz.getGradeAsPercent() + "%");
        progressPanel.add(passedLabel);
        progressPanel.add(scoreLabel);
        progressPanel.add(percentageLabel);

        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainGUI.resetToWelcome();
            }
        });
        progressPanel.add(doneButton);
        progressPanel.setVisible(true);
        mainGUI.setCurrentPanel(progressPanel);
    }

    public Question getQuestionFromIndex(Quiz quiz, int index) {
        return quiz.getQuestions().get(index);
    }

}
