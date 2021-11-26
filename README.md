# Multiple Choice Quiz App

## Project Description

This is a multiple choice quiz application where you can **create** multiple choice quizzes and also 
**complete** them. A list of the application features are below:

- Create multiple choice quizzes consisting of multiple questions
- Complete created quizzes multiple times
- Track your grades for all attempted quizzes

This app could be used by students who want another way to study for courses heavy on memorization. They would be able 
to create mock questions for any of the content and quiz themselves until they feel comfortable with the content 
they're testing themselves on. It has been scientifically proven that quizzing yourself helps one retain information 
for longer, meaning users would benefit from using this for those courses intensive on memorization.

This project interests me because I'm currently taking a course that requires quite a bit of memorization, and I have 
recently started testing myself by writing questions which has significantly improved my ability to retain the 
information we're expected to know. Since writing down questions can take a bit of time, I think automating some parts 
of the process could be quite useful in saving time and focussing more on quizzing myself, 
rather than wasting more time creating the quizzes.

## User Stories

The user stories so far are:

- As a user, I want to be able to add questions to a quiz
- As a user, I want to be able to attempt and complete a quiz
- As a user, I want to be able to look at a list of all quizzes made
- As a user, I want to be able to view a progress report on all attempted quizzes so far
- As a user, I want to have the option to save all created and attempted quizzes and also get asked to save when quitting the application
- As a user, I want to be able to load all created and attempted quizzes from file

### Phase 4: Task 2

Here is an example of the logs produced from running the application:

Sun Nov 21 21:59:38 PST 2021  
Quiz "Math Quiz" was created  
Sun Nov 21 21:59:38 PST 2021  
Question: "What is 1 + 1?" was created  
Sun Nov 21 21:59:38 PST 2021  
Question: "What is 1 + 1?" added to the quiz: Math Quiz  
Sun Nov 21 21:59:50 PST 2021  
Question: "What is 3 + 3?" was created  
Sun Nov 21 21:59:50 PST 2021  
Question: "What is 3 + 3?" added to the quiz: Math Quiz  
Sun Nov 21 22:00:05 PST 2021  
Question: "What is 9 + 9?" was created  
Sun Nov 21 22:00:05 PST 2021  
Question: "What is 9 + 9?" added to the quiz: Math Quiz  
Sun Nov 21 22:00:53 PST 2021  
Quiz "Capital City Quiz" was created  
Sun Nov 21 22:00:53 PST 2021  
Question: "What is the capital of Canada?" was created  
Sun Nov 21 22:00:53 PST 2021  
Question: "What is the capital of Canada?" added to the quiz: Capital City Quiz  
Sun Nov 21 22:02:07 PST 2021  
Question: "What is the capital of France?" was created  
Sun Nov 21 22:02:07 PST 2021  
Question: "What is the capital of France?" added to the quiz: Capital City Quiz  
Sun Nov 21 22:02:36 PST 2021  
Attempt for the quiz "Math Quiz" created  
Sun Nov 21 22:03:11 PST 2021  
Attempt for the quiz "Capital City Quiz" created  

### Phase 4: Task 3

One of the things that I would refactor is the association between JsonReader, JsonWriter, LoadGUI, SaveGUI,
and MainGraphicUIApp. MainGraphicUIApp has an association to JsonReader and JsonWriter so the LoadGUI and SaveGUI also 
having that association is redundant

To fix this:  
- Remove field of JsonWriter and JsonReader
- Make all calls to JsonReader and JsonWriter from LoadGUI and SaveGUI from the MainGraphicUIApp associations by 
using a getter method

Another thing that might be able to be refactored is having both AttemptedQuizzes and a Quiz. I don't think it's the 
best design since the quizManager has to keep track of two lists instead of just one which resulted in some methods that
were extremely similar to deal with the lists differently. Therefore, it could be changed to 
one generic quiz class that has the functionality of attempting inside it, and it keeps track of all the attempted scores
too. Therefore, I would:

- combine both AttemptedQuiz and Quiz into one class
- Maybe set up a HashMap field to store grades for each attempt
- refactor QuizManager to only have to handle one list
- change GUI classes to handle the new quiz class

This approach would result in only having to worry about one list in QuizManager and makes the overall project design 
better since some arguably unnecessary classes are replaced with one class that only deals with one thing.