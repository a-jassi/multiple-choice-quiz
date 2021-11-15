//package ui;
//
//import model.QuizManager;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//// main class to run MultipleChoiceQuizApp with a GUI
//public class MainGUI extends JFrame implements ActionListener {
//
//    private static final String JSON_FILE_WRITTEN_TO = "./data/quizManager.json";
//    private static final int WIDTH = 700;       // width of panel
//    private static final int HEIGHT = 500;      // height of panel
//
//    private QuizManager quizManager;            // quizManager for Multiple Choice Quiz
//    private JsonWriter jsonWriter;              // object that writes to file to save progress
//    private JsonReader jsonReader;              // object that reads from file to load progress
//
//    // EFFECTS: creates a mainGUI object that is modeled after a JFrame
//    public MainGUI() {
//        super("Multiple Choice Quiz App");
//        initWindow();
//        initText();
//        initButtons();
//        initFields();
//    }
//
//    // The initWindow method is taken from lines 2-5 and lines 15-17 in the Test Constructor from this link:
//    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
//
//    // EFFECTS: initializes the window for the gui
//    public void initWindow() {
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setSize(new Dimension(WIDTH, HEIGHT));
//        ((JPanel) getContentPane()).setBorder(new EmptyBorder(50, 70, 50, 70));
//        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
//        setLocationRelativeTo(null);
//        setVisible(true);
//        setResizable(true);
//    }
//
//    // EFFECTS: initializes the text on the starting panel
//    public void initText() {
//        JLabel welcomeText = new JLabel();
//        welcomeText.setText("Welcome the the Multiple Choice Quiz App!");
//        JLabel instructionText = new JLabel();
//        instructionText.setText("Please choose one of the options below to get started:");
//        add(welcomeText);
//        add(Box.createRigidArea(new Dimension(0, 5)));
//        add(instructionText);
//        add(Box.createRigidArea(new Dimension(0, 5)));
//    }
//
//    // the method on how to create buttons was taken from lines 6-8 from the Test Constructor from this link:
//    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
//
//    // EFFECTS: creates all the buttons and adds them to the window
//    public void initButtons() {
//        JButton createButton = new JButton("Create");
//        createButton.setActionCommand("create");
//        createButton.addActionListener(this);
//
//        JButton attemptButton = new JButton("Attempt");
//        attemptButton.setActionCommand("attempt");
//        attemptButton.addActionListener(this);
//
//        JButton viewButton = new JButton("View");
//        viewButton.setActionCommand("view");
//        viewButton.addActionListener(this);
//
//        JButton progressButton = new JButton("Progress");
//        progressButton.setActionCommand("progress");
//        progressButton.addActionListener(this);
//
//        JButton saveButton = new JButton("Save");
//        saveButton.setActionCommand("save");
//        saveButton.addActionListener(this);
//
//        JButton loadButton = new JButton("Load");
//        loadButton.setActionCommand("load");
//        loadButton.addActionListener(this);
//
//        addButtons(createButton, attemptButton, viewButton);
//        addButtons(progressButton, saveButton, loadButton);
//    }
//
//    // EFFECTS: adds buttons to the JPanel component
//    public void addButtons(JButton buttonOne, JButton buttonTwo, JButton buttonThree) {
//        add(buttonOne);
//        add(Box.createRigidArea(new Dimension(0, 5)));
//        add(buttonTwo);
//        add(Box.createRigidArea(new Dimension(0, 5)));
//        add(buttonThree);
//        add(Box.createRigidArea(new Dimension(0, 5)));
//    }
//
//    // EFFECTS: initializes all the fields that need to be initialized
//    public void initFields() {
//        jsonWriter = new JsonWriter(JSON_FILE_WRITTEN_TO);
//        jsonReader = new JsonReader(JSON_FILE_WRITTEN_TO);
//        quizManager = new QuizManager();
//    }
//
//    // the actionPerformed method is templated off the actionPerformed method from the link below:
//    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
//
//    // EFFECTS: method that handles events
//    public void actionPerformed(ActionEvent e) {
//        if (e.getActionCommand().equals("create")) {
//            System.out.println("create");
//        } else if (e.getActionCommand().equals("attempt")) {
//            System.out.println("attempt");
//        } else if (e.getActionCommand().equals("view")) {
//            System.out.println("view");
//        } else if (e.getActionCommand().equals("progress")) {
//            System.out.println("progress");
//        } else if (e.getActionCommand().equals("save")) {
//            System.out.println("save");
//        } else if (e.getActionCommand().equals("load")) {
//            System.out.println("load");
//        }
//    }
//
//    // EFFECTS: runs the GUI version of the MultipleChoiceQuizApp
//    public static void main(String[] args) {
//        new MainGUI();
//    }
//
//}
