/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nina
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ManagedBean
@SessionScoped
public class QuizBean implements Serializable {

    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private String selectedAnswer;
    private String feedback;
    private boolean gameOver;
    private boolean waitingForNext = false;

    public QuizBean() {
        initializeQuestions();
        currentQuestionIndex = 0;
        score = 0;
        feedback = "";
        gameOver = false;

    }

    private void initializeQuestions() {
        questions = new ArrayList<Question>();
        questions.add(new Question(
                "What is the output of print(2 ** 3)?",
                Arrays.asList("6", "8", "9", "12"),
                "8"
        ));
        questions.add(new Question(
                "Which keyword is used to define a function in Python?",
                Arrays.asList("func", "def", "function", "lambda"),
                "def"
        ));
        questions.add(new Question(
                "What is the correct file extension for Python files?",
                Arrays.asList(".py", ".pt", ".python", ".pyt"),
                ".py"
        ));
        questions.add(new Question(
                "Which function is used to output text in Python?",
                Arrays.asList("echo()", "print()", "write()", "out()"),
                "print()"
        ));
        questions.add(new Question(
                "Which symbol is used to start a comment in Python?",
                Arrays.asList("#", "//", "/*", "--"),
                "#"
        ));
        questions.add(new Question(
                "How do you create a list in Python?",
                Arrays.asList( "list = (1, 2, 3)",
            "list = {1, 2, 3}",
            "list = [1, 2, 3]",
            "list = <1, 2, 3>"),
                "list = [1, 2, 3]"
        ));
        questions.add(new Question(
                "What is the output of len([1, 2, 3])?",
                Arrays.asList("2", "3", "1", "Error"),
                "3"
        ));
        questions.add(new Question(
                "Which keyword is used to start a loop in Python?",
                Arrays.asList("repeat", "loop", "for", "iterate"),
                "for"
        ));
        questions.add(new Question(
                "What is the result of 10 / 3?",
                Arrays.asList("3", "3.33", "3.0", "4"),
                "3"
        ));
        questions.add(new Question(
                "Which of these is a Boolean value in Python?",
                Arrays.asList("yes", "True", "on", "enabled"),
                "True"
        ));
        questions.add(new Question(
                "Which function returns the data type of an object?",
                Arrays.asList("typeof()", "type()", "gettype()", "classof()"),
                "type()"
        ));
    }

    public void submitAnswer() {
         if (gameOver || waitingForNext) return; 
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (selectedAnswer != null && selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            score += 10;
            feedback = "Correct!";
        } else {
            feedback = "Incorrect. The correct answer is: " + currentQuestion.getCorrectAnswer();
        }
        if (currentQuestionIndex >= questions.size()) {
            gameOver = true;
        }
        selectedAnswer = null;
         waitingForNext = true;
    }

    public void restartGame() {
        currentQuestionIndex = 0;
        score = 0;
        feedback = "";
        gameOver = false;
         waitingForNext = false;
    }

    // Getters and Setters
    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    public int getScore() {
        return score;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public String getFeedback() {
        return feedback;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public int getTotalQuestions() {
        return questions != null ? questions.size() : 0;
    }

    public boolean isWaitingForNext() {
        return waitingForNext;
    }

    public void setWaitingForNext(boolean waitingForNext) {
        this.waitingForNext = waitingForNext;
    }
    public void nextQuestion() {
    if (waitingForNext && !gameOver) {
        currentQuestionIndex++;
        feedback = "";
        waitingForNext = false;

        if (currentQuestionIndex >= questions.size()) {
            gameOver = true;
        }
    }
}

}


