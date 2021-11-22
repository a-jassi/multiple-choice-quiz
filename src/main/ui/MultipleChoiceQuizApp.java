package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Multiple Choice Quiz App
public class MultipleChoiceQuizApp {

    private static final String JSON_FILE_WRITTEN_TO = "./data/quizManager.json";
    private QuizManager quizManager;        // quizManager
    private Scanner input;                  // scanner to get input from user
    private JsonWriter jsonWriter;          // saves data to file
    private JsonReader jsonReader;          // loads data from file

    // EFFECTS: runs the multiple choice quiz application
    public MultipleChoiceQuizApp() throws FileNotFoundException {
        runMultipleChoiceQuiz();
    }

    // The method runMultipleChoiceQuiz() references code from the TellerApp example,
    // specifically runTeller() in the TellerApp Class (ui package)
    // Therefore, the idea for initialize(), displayMenu(), and processCommand() are also obtained through this
    // reference (as they are helper methods to runTeller)
    // Link Below:
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp/blob/master/src/main/ca/ubc/cpsc210/bank/ui/TellerApp.java

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMultipleChoiceQuiz() {
        boolean keepGoing = true;
        String command = null;

        initialize();

        while (keepGoing) {
            displayMenu();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                askForSaveProgress();
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThanks for using the MultipleChoiceQuizApp!\nGoodbye!");
        System.out.println("Event Logs: \n\n");
        EventLog.getInstance().iterator().forEachRemaining(System.out::println);
    }

    // MODIFIES: this
    // EFFECTS: initializes a QuizManager to manage quizzes
    private void initialize() {
        jsonWriter = new JsonWriter(JSON_FILE_WRITTEN_TO);
        jsonReader = new JsonReader(JSON_FILE_WRITTEN_TO);
        quizManager = new QuizManager();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of all options the user can execute
    private void displayMenu() {
        System.out.println("Choose one of the following:");
        System.out.println("\tcreate    -> create a new quiz");
        System.out.println("\tattempt   -> attempt a quiz");
        System.out.println("\tview      -> view all created quizzes");
        System.out.println("\tprogress  -> view a progress report of all attempted quizzes");
        System.out.println("\tsave      -> save your created and attempted quizzes");
        System.out.println("\tload      -> load previously saved progress");
        System.out.println("\tquit      -> quit the application");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("create")) {
            createQuiz();
        } else if (command.equals("attempt")) {
            attemptQuiz();
        } else if (command.equals("view")) {
            viewQuizzes();
        } else if (command.equals("progress")) {
            viewProgress();
        } else if (command.equals("save")) {
            saveProgress();
        } else if (command.equals("load")) {
            loadSavedProgress();
        } else {
            System.out.println("Invalid command! Please input a command stated above.\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new quiz
    private void createQuiz() {
        System.out.println("What would you like to name the quiz?");
        String name = input.nextLine();
        for (Quiz next : quizManager.getAllQuizzesMade()) {
            if (next.getName().equalsIgnoreCase(name) || name.isEmpty()) {
                System.out.println("Name is either empty, or already taken by a previous quiz, please input a new one");
            }
        }

        Quiz quiz = new Quiz(name);
        quizManager.addToAllQuizzesMade(quiz);
        quizEditor(quiz);
    }

    // MODIFIES: this
    // EFFECTS: adds questions to quiz based on user input
    private void quizEditor(Quiz quiz) {
        while (true) {
            System.out.println("What is the question you would like to ask?");
            System.out.println("Type \"done\" if you are done adding questions (must have added at least one))");
            String question = input.nextLine();
            if (!question.isEmpty()) {
                if (question.equalsIgnoreCase("done")) {
                    if (quiz.numberOfQuestions() <= 0) {
                        System.out.println("You must ask at least one question!\n");
                        continue;
                    } else {
                        break;
                    }
                }
            } else {
                System.out.println("No empty inputs!");
            }

            List<String> answers = new ArrayList<>();
            inputCorrectAnswer(answers, quiz, question);
        }
    }

    // MODIFIES: this
    // EFFECTS: asks for correct answer and creates quiz from inputs
    private void inputCorrectAnswer(List<String> answers, Quiz quiz, String question) {
        System.out.println("What is the correct answer?");
        String answer = input.nextLine();
        if (!answer.isEmpty()) {
            answers.add(answer);
            inputFakeAnswers(answers);
            quiz.addQuestion(new Question(question, answer, answers));
        } else {
            System.out.println("No empty inputs!");
        }
    }

    // MODIFIES: this
    // EFFECTS: asks for wrong answers and adds them to answers
    private void inputFakeAnswers(List<String> answers) {
        int i = 1;

        while (answers.size() < 4) {
            System.out.println("Give wrong answer " + i);
            String fakeAnswer = input.nextLine();
            if (answers.contains(fakeAnswer) || answers.isEmpty()) {
                System.out.println("Invalid input! No repetition or empty strings!\n");
                continue;
            }
            answers.add(fakeAnswer);
            i++;
        }
    }

    // MODIFIES: this
    // EFFECTS: attempt a quiz where user can answer questions
    private void attemptQuiz() {
        if (!quizManager.getAllQuizzesMade().isEmpty()) {
            printListOfAllQuizzes();

            Quiz quizFromName = accessQuiz("attempt");
            AttemptedQuiz quizToAttempt = new AttemptedQuiz(quizFromName);
            quizManager.addToAttemptedQuizzes(quizToAttempt);

            quizToAttempt.shuffleAnswers();
            List<Question> questionList = quizToAttempt.getQuiz().getQuestions();
            for (int i = 1; i <= questionList.size(); i++) {
                System.out.println(quizToAttempt.getQuiz().stringOfQuestion(i));
                System.out.println("\nWrite the answer you believe is true (not the letter)");
                String answer = input.nextLine();
                if (!answer.isEmpty()) {
                    if (quizToAttempt.checkAnswer(i, answer)) {
                        System.out.println("Congratulations! You got it right!\n");
                    }
                } else {
                    System.out.println("Can't input an empty answer!");
                }

            }

            printStats(quizToAttempt);
        } else {
            System.out.println("Create some quizzes before accessing attempt!\n");
        }
    }

    // EFFECTS: prints stats on an attempted quiz
    private void printStats(AttemptedQuiz attemptedQuiz) {
        List<Question> questions = attemptedQuiz.getQuiz().getQuestions();
        System.out.println("Your grades for the quiz are below:");
        System.out.println("Passed: " + attemptedQuiz.checkIfPassed());
        System.out.println("Score: " + attemptedQuiz.getGrade() + "/" + questions.size());
        System.out.println("Percentage: " + attemptedQuiz.getGradeAsPercent() + "%\n\n");
    }

    // EFFECTS: prints list off all created quizzes
    private void printListOfAllQuizzes() {
        System.out.println("These are all of the quizzes you have made:");
        for (Quiz next : quizManager.getAllQuizzesMade()) {
            System.out.println("\t" + next.getName());
        }
    }

    // EFFECTS: returns quiz corresponding to inputted name
    private Quiz accessQuiz(String attemptOrView) {
        System.out.println("\nEnter the name of the quiz you would like to " + attemptOrView + ".");
        String quizName = input.nextLine();
        boolean noSuchQuizName = true;
        for (Quiz next : quizManager.getAllQuizzesMade()) {
            if (next.getName().equalsIgnoreCase(quizName)) {
                noSuchQuizName = false;
            }
        }
        if (noSuchQuizName) {
            System.out.println("This quiz name is not found anywhere\n");
        }
        return quizManager.getQuizFromName(quizName);
    }

    // EFFECTS: view list of all quizzes and print a specific one to look at
    private void viewQuizzes() {
        if (!quizManager.getAllQuizzesMade().isEmpty()) {
            printListOfAllQuizzes();
            System.out.println(accessQuiz("view"));
        } else {
            System.out.println("Create 1 quiz minimum before accessing view\n");
        }
    }

    // EFFECTS: view statistics of all attempted quizzes and look at overall stats
    private void viewProgress() {
        if (!quizManager.getAttemptedQuizzes().isEmpty()) {
            System.out.println("Would you like to see individual or overall statistics?");
            String individualOrOverall = input.nextLine();
            if (individualOrOverall.equalsIgnoreCase("individual")) {
                printListOfAllAttemptedQuizzes();
                AttemptedQuiz attempted = accessAttemptedQuiz();
                printStats(attempted);
            } else if (individualOrOverall.equalsIgnoreCase("overall")) {
                printOverallStats();
            } else {
                System.out.println("Please enter \"individual\" or \"overall\"");
            }
        } else {
            System.out.println("Complete a quiz before accessing progress!\n");
        }
    }

    // EFFECTS: returns attempted quiz that is entered
    private AttemptedQuiz accessAttemptedQuiz() {
        System.out.println("Enter the Attempted Quiz you would like a progress report on:");
        String attemptedName = input.nextLine();
        return quizManager.getAttemptedQuizFromName(attemptedName);

    }

    // EFFECTS: prints list of all attempted quizzes
    private void printListOfAllAttemptedQuizzes() {
        System.out.println("These are all of the quizzes you have attempted:");
        for (AttemptedQuiz next : quizManager.getAttemptedQuizzes()) {
            System.out.println("\t" + next.getQuiz().getName());
        }
    }

    // EFFECTS: prints the statistics for all attempted quizzes combined
    private void printOverallStats() {
        System.out.println("Here are your overall statistics:\n\n");
        boolean passedQuizzes = (quizManager.overallAttemptedPercentCorrect()) >= 50;
        System.out.println("Passed: " + passedQuizzes);
        System.out.println("Score: " + quizManager.overallAttemptedQuestionsCorrect()
                + "/" + quizManager.overallPotentialQuestionsCorrect());
        System.out.println("Percentage: " + quizManager.overallAttemptedPercentCorrect() + "%\n");
    }

    // saveProgress is referenced from the saveProgress() method from the WorkRoomApp class in JsonSerializationDemo
    // link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java

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

    // EFFECTS: asks the user if they want to save data before quitting
    private void askForSaveProgress() {
        while (true) {
            System.out.println("Would you like to save your progress before quitting?");
            String inputtedAnswer = input.nextLine();
            if (inputtedAnswer.equalsIgnoreCase("yes")) {
                saveProgress();
                break;
            } else if (inputtedAnswer.equalsIgnoreCase("no")) {
                System.out.println("Okay, progress will not be saved.");
                break;
            } else {
                System.out.println("Invalid input. Please input \"yes\" or \"no\"");
            }
        }
    }

    // loadSavedProgress is templated from the loadWorkRoom() method from WorkRoomApp class in JsonSerializationDemo
    // link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java

    // MODIFIES: this
    // EFFECTS: loads saved progress from file
    private void loadSavedProgress() {
        try {
            quizManager = jsonReader.read();
            System.out.println("Successfully loaded saved progress from " + JSON_FILE_WRITTEN_TO);
        } catch (IOException e) {
            System.out.println("Unable to read data from file " + JSON_FILE_WRITTEN_TO);
        }
    }
}
