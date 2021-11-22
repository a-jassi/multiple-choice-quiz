package ui;

import model.EventLog;
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
    public static final int WIDTH = 700;       // width of frame
    public static final int HEIGHT = 500;      // height of frame

    private QuizManager quizManager;            // quizManager for Multiple Choice Quiz
    private JsonWriter jsonWriter;              // object that writes to file to save progress
    private JsonReader jsonReader;              // object that reads from file to load progress
    private JPanel currentPanel;                // current panel being shown

    // EFFECTS: creates a MainGraphicUIApp object that is modeled after a JFrame
    public MainGraphicUIApp() {
        super("Multiple Choice Quiz App");
        initFields();
        initWindow();
        pack();
    }

    // MODIFIES: this
    // EFFECTS: initialize the frame
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

    // MODIFIES: this
    // EFFECTS: creates an option pane that pops up when closing; asks to save progress
    private void setUpSavePopUp() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                String[] objButtons = {"Yes","No"};
                int promptResult = JOptionPane.showOptionDialog(null,
                        "Do you want to save before exiting?", "Multiple Choice Quiz App",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, objButtons, objButtons[1]);
                if (promptResult == JOptionPane.YES_OPTION) {
                    saveProgress();
                }

                EventLog.getInstance().iterator().forEachRemaining(System.out::println);
                System.exit(0);
            }
        });
    }

    // EFFECTS: initializes the fields that don't have to do with the frame
    private void initFields() {
        jsonWriter = new JsonWriter(JSON_FILE_WRITTEN_TO);
        jsonReader = new JsonReader(JSON_FILE_WRITTEN_TO);
        quizManager = new QuizManager();
    }

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

    // MODIFIES: this
    // EFFECTS: replaces current panel with panel parameter
    public void setCurrentPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        pack();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: replaces the current panel with a welcomeUI panel
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
