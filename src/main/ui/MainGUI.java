package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// main class to run MultipleChoiceQuizApp with a GUI
public class MainGUI extends JFrame implements ActionListener {

    private static final int WIDTH = 700;       // width of panel
    private static final int HEIGHT = 500;      // height of panel

    // EFFECTS: creates a mainGUI object that is modeled after a JFrame
    public MainGUI() {
        super("Multiple Choice Quiz App");
        initWindow();
        initButtons();
        pack();
    }

    // The initWindow method is taken from lines 2-5 and lines 15-17 in the Test Constructor from this link:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application

    // EFFECTS: initializes the window for the gui
    public void initWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(100, 70, 100, 70));
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // the method on how to create buttons was taken from lines 6-8 from the Test Constructor from this link:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application

    // EFFECTS: creates all the buttons and adds them to the window
    public void initButtons() {
        JButton createButton = new JButton("Create");
        createButton.setActionCommand("create");
        createButton.addActionListener(this);

        JButton attemptButton = new JButton("Attempt");
        attemptButton.setActionCommand("attempt");
        attemptButton.addActionListener(this);

        JButton viewButton = new JButton("View");
        viewButton.setActionCommand("view");
        viewButton.addActionListener(this);

        JButton progressButton = new JButton("Progress");
        progressButton.setActionCommand("progress");
        progressButton.addActionListener(this);

        JButton saveButton = new JButton("Save");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);

        JButton loadButton = new JButton("Load");
        loadButton.setActionCommand("load");
        loadButton.addActionListener(this);

        addButtons(createButton, attemptButton, viewButton);
        addButtons(progressButton, saveButton, loadButton);
    }

    // EFFECTS: adds buttons to the JPanel component
    public void addButtons(JButton buttonOne, JButton buttonTwo, JButton buttonThree) {
        add(buttonOne);
        add(buttonTwo);
        add(buttonThree);
    }

    // the actionPerformed method is templated off the actionPerformed method from the link below:
    // https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application

    // EFFECTS: method that handles events
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("create")) {
            System.out.println("create");
        } else if (e.getActionCommand().equals("attempt")) {
            System.out.println("attempt");
        } else if (e.getActionCommand().equals("view")) {
            System.out.println("view");
        } else if (e.getActionCommand().equals("progress")) {
            System.out.println("progress");
        } else if (e.getActionCommand().equals("save")) {
            System.out.println("save");
        } else if (e.getActionCommand().equals("load")) {
            System.out.println("load");
        }
    }

    // EFFECTS: runs the GUI version of the MultipleChoiceQuizApp
    public static void main(String[] args) {
        new MainGUI();
    }

}
