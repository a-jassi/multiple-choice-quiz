package ui;

import model.QuizManager;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

// represents the GUI of the MultipleChoiceQuizApp
public class MainGraphicUIApp extends JFrame implements ActionListener, ItemListener {

    private static final String JSON_FILE_WRITTEN_TO = "./data/quizManager.json";
    private static final int WIDTH = 700;       // width of panel
    private static final int HEIGHT = 500;      // height of panel

    private QuizManager quizManager;            // quizManager for Multiple Choice Quiz
    private JsonWriter jsonWriter;              // object that writes to file to save progress
    private JsonReader jsonReader;              // object that reads from file to load progress
    private JPanel panels;

    // EFFECTS: creates a MainGraphicUIApp object that is modeled after a JFrame
    public MainGraphicUIApp() {
        super("Multiple Choice Quiz App");
        initWindow();
        initText();
        //initButtons();
        initFields();
        pack();
    }

    // EFFECTS: initialize the window with a CardLayout
    private void initWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
        initPanels(this.getContentPane());
    }

    // EFFECTS: initializes the welcome text
    private void initText() {
        JLabel welcomeText = new JLabel();
        welcomeText.setText("Welcome to the Multiple Choice Quiz App!");
        JLabel optionText = new JLabel();
        optionText.setText("Please pick one of the options from the drop-down box above to get started!");
        add(welcomeText);
        add(optionText);
    }

    // EFFECTS: initializes the fields
    private void initFields() {
        jsonWriter = new JsonWriter(JSON_FILE_WRITTEN_TO);
        jsonReader = new JsonReader(JSON_FILE_WRITTEN_TO);
        quizManager = new QuizManager();
    }

    // initPanels references code from the addComponentToPane method from this link below:
    // https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/CardLayoutDemoProject/src/layout/CardLayoutDemo.java

    // EFFECTS: initializes the panels for the different options
    private void initPanels(Container pane) {
        JPanel comboBoxJPanel = new JPanel();
        String[] comboBoxItems = comboBoxItems();
        JComboBox comboBox = new JComboBox(comboBoxItems);
        comboBox.setEditable(false);
        comboBox.addItemListener(this);
        comboBoxJPanel.add(comboBox);

        ArrayList<JPanel> cards = createCards();
        panels = new JPanel(new CardLayout());
        addCards(cards);

        pane.add(comboBoxJPanel, BorderLayout.PAGE_START);
        pane.add(panels, BorderLayout.CENTER);
    }

    // EFFECTS: creates the panels for all the options
    private ArrayList<JPanel> createCards() {
        JPanel createPanel = new JPanel();
        //initCreatePanel(createPanel);

        JPanel attemptPanel = new JPanel();
        //initAttemptPanel(attemptPanel);

        JPanel viewPanel = new JPanel();
        // initViewPanel(viewPanel);

        JPanel progressPanel = new JPanel();
        // initProgressPanel(progressPanel);

        JPanel savePanel = new JPanel();
        initSavePanel(savePanel);

        JPanel loadPanel = new JPanel();
        // initLoadPanel(loadPanel);

        ArrayList<JPanel> cards = new ArrayList<>();
        cards.add(createPanel);
        cards.add(attemptPanel);
        cards.add(viewPanel);
        cards.add(progressPanel);
        cards.add(savePanel);
        cards.add(loadPanel);

        return cards;
    }

    // EFFECTS: adds cards to this
    private void addCards(ArrayList<JPanel> panels) {
        this.panels.add(panels.get(0), "Create");
        this.panels.add(panels.get(1), "Attempt");
        this.panels.add(panels.get(2), "View");
        this.panels.add(panels.get(3), "Progress");
        this.panels.add(panels.get(4), "Save");
        this.panels.add(panels.get(5), "Load");
    }

    // EFFECTS: returns array for different start menu options
    private String[] comboBoxItems() {
        String[] returnArray = {"Create", "Attempt", "View", "Progress", "Save", "Load"};
        return returnArray;
    }

    private void initSavePanel(JPanel savePanel) {
        JLabel text = new JLabel("Please click the button below to save.");
        JButton saveButton = new JButton("Save Progress");
        savePanel.add(text);
        savePanel.add(saveButton);
    }

    // the itemStateChanged method is taken from the link below:
    // https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/CardLayoutDemoProject/src/layout/CardLayoutDemo.java

    @Override
    public void itemStateChanged(ItemEvent e) {
        CardLayout cardLayout = (CardLayout) (panels.getLayout());
        cardLayout.show(panels, (String) e.getItem());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Save Progress")) {
            saveProgress();
        }
    }

    // MODIFIES: this
    // EFFECTS: saves the quizManager progress to file
    private void saveProgress() {
        try {
            jsonWriter.open();
            jsonWriter.write(quizManager);
            jsonWriter.close();
            System.out.println("QuizManager was successfully saved to " + JSON_FILE_WRITTEN_TO);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_FILE_WRITTEN_TO);
        }
    }

    // EFFECTS: runs the GUI of MultipleChoiceQuiz
    public static void main(String[] args) {
        new MainGraphicUIApp();
    }

}
