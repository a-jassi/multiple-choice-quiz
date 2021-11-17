package ui;

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

    public void setUpLabels() {
        List<Quiz> quizzes = mainGUI.getQuizManager().getAllQuizzesMade();
        for (Quiz quiz : quizzes) {
            JLabel label = new JLabel(quiz.getName());
            add(label);
        }

        JLabel text = new JLabel("Input the quiz to attempt:");
        add(text);
    }

    public void setUpTextField() {
        inputQuizName = new JTextField();
        add(inputQuizName);
    }

    public void setUpEnterButton() {
        enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseQuiz();
            }
        });
        add(enter);
    }

    public void chooseQuiz() {
        if(!inputQuizName.getText().equals("")) {
            QuizManager quizManager = mainGUI.getQuizManager();
            Quiz quiz = quizManager.getQuizFromName(inputQuizName.getText());
            goIntoQuiz(quiz);
            setVisible(false);
        }
    }

    public void goIntoQuiz(Quiz quiz) {
        JPanel attemptPanel = new JPanel();
        attemptPanel.setLayout(new BoxLayout(attemptPanel, BoxLayout.Y_AXIS));

        quizName = new JLabel("Currently attempting: " + quiz.getName());


        JLabel question = new JLabel("<html>" + "</html>");
    }

}
