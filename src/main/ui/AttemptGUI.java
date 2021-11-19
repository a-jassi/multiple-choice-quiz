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

// represents the GUI for attempting quizzes
public class AttemptGUI extends JPanel implements ActionListener {

    private MainGraphicUIApp mainGUI;
    private JTextField inputQuizName;
    private JButton enter;
    private JLabel quizName;

    private JTextField inputTextField;
    private AttemptedQuiz attemptedQuiz;
    private int index;

    public AttemptGUI(MainGraphicUIApp mainGUI) {
        this.mainGUI = mainGUI;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        index = 0;

        setUpLabels();
        setUpTextField();
        setUpEnterButton();
        // chooseQuiz();
        add(Box.createRigidArea(new Dimension(20, 50)));
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
            this.attemptedQuiz = attemptedQuiz;
            goIntoQuiz(attemptedQuiz, index);
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

        inputTextField = new JTextField();
        attemptPanel.add(inputTextField);

        JButton submitAnswer = new JButton("Submit Answer");
        submitAnswer.addActionListener(this);
        attemptPanel.add(submitAnswer);
        attemptPanel.add(Box.createRigidArea(new Dimension(50, 50)));
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
        int attemptedQuizSize = attemptedQuiz.getQuiz().getQuestions().size();
        JLabel scoreLabel = new JLabel("Score: " + attemptedQuiz.getGrade() + "/" + attemptedQuizSize);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!inputTextField.getText().equals("")) {
            this.attemptedQuiz.checkAnswer(index + 1, inputTextField.getText());
        }
        try {
            index++;
            goIntoQuiz(attemptedQuiz, index);
        } catch (IndexOutOfBoundsException exception) {
            displayProgressPanel(attemptedQuiz);
        }
    }
}
