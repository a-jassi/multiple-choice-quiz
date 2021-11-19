package ui;

import model.QuizManager;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

// represents the GUI of the MultipleChoiceQuizApp
public class MainGraphicUIApp extends JFrame {

    public static final String JSON_FILE_WRITTEN_TO = "./data/quizManager.json";
    public static final int WIDTH = 700;       // width of panel
    public static final int HEIGHT = 500;      // height of panel

    private QuizManager quizManager;            // quizManager for Multiple Choice Quiz
    private JsonWriter jsonWriter;              // object that writes to file to save progress
    private JsonReader jsonReader;              // object that reads from file to load progress
    private JPanel currentPanel;                // current panel being shown

    // have one JPanel pointing to current panel and discard others, don't have visibility

    // EFFECTS: creates a MainGraphicUIApp object that is modeled after a JFrame
    public MainGraphicUIApp() {
        super("Multiple Choice Quiz App");
        initFields();
        initWindow();
        pack();
    }

    // EFFECTS: initialize the window with a CardLayout
    private void initWindow() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setUpSavePopUp();
        setSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(50, 50, 50, 50));
        currentPanel = new WelcomeGUI(this);
        add(currentPanel, BorderLayout.CENTER);
    }

    // EFFECTS: returns quizManager
    public QuizManager getQuizManager() {
        return quizManager;
    }

    // EFFECTS: returns jsonWriter
    public JsonWriter getJsonWriter() {
        return jsonWriter;
    }

    // EFFECTS: returns jsonReader
    public JsonReader getJsonReader() {
        return jsonReader;
    }

    // MODIFIES: this
    // EFFECTS: sets new quizManager
    public void setQuizManager(QuizManager quizManager) {
        this.quizManager = quizManager;
    }

    // the code for the save pop-up window was taken from the link below:
    // https://stackoverflow.com/questions/15449022/show-prompt-before-closing-jframe

    private void setUpSavePopUp() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                String[] objButtons = {"Yes","No"};
                int promptResult = JOptionPane.showOptionDialog(null,
                        "Do you want to save before exiting?","Multiple Choice Quiz App",
                        JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,
                        null,objButtons,objButtons[1]);
                if (promptResult == JOptionPane.YES_OPTION) {
                    saveProgress();
                }
                System.exit(0);
            }
        });
    }

    // EFFECTS: initializes the fields
    private void initFields() {
        jsonWriter = new JsonWriter(JSON_FILE_WRITTEN_TO);
        jsonReader = new JsonReader(JSON_FILE_WRITTEN_TO);
        quizManager = new QuizManager();
    }

    // initPanels references code from the addComponentToPane method from this link below:
    // https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/CardLayoutDemoProject/src/layout/CardLayoutDemo.java

    // the itemStateChanged method is taken from the link below:
    // https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/CardLayoutDemoProject/src/layout/CardLayoutDemo.java

    // MODIFIES: this
    // EFFECTS: saves the quizManager progress to file
    private void saveProgress() {
        try {
            jsonWriter.open();
            jsonWriter.write(quizManager);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // setCurrentPanel references code from the link below:
    // https://stackoverflow.com/questions/218155/how-do-i-change-jpanel-inside-a-jframe-on-the-fly

    public void setCurrentPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        pack();
        repaint();
    }

    public void resetToWelcome() {
        getContentPane().removeAll();
        getContentPane().add(new WelcomeGUI(this));
        pack();
        repaint();
    }

    // EFFECTS: runs the GUI of MultipleChoiceQuiz
    public static void main(String[] args) {
        new MainGraphicUIApp();
    }

}
