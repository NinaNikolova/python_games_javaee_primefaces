
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.faces.bean.ManagedBean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nina
 */
@ManagedBean(name = "questionService")
@ApplicationScoped
public class QuestionService implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Question> questions;
    private Random random = new Random();

    @PostConstruct
    public void init() {
        questions = new ArrayList<Question>();
        loadQuestions();
    }

    // Initialize with some predefined questions
    private void loadQuestions() {
        // Easy questions
        questions.add(new Question("Which keyword is used to define a function in Python?", "def", Question.Difficulty.EASY,
                "func", "define", "def", "function"));
        questions.add(new Question("What is the correct file extension for Python files?", ".py", Question.Difficulty.EASY,
                ".py", ".python", ".pt", ".pyt"));
        questions.add(new Question("Which of these is used to output text in Python?", "print()", Question.Difficulty.EASY,
                "write()", "echo()", "print()", "output()"));
        questions.add(new Question("Which symbol is used to comment a single line in Python?", "#", Question.Difficulty.EASY,
                "//", "/*", "#", "<!--"));
        questions.add(new Question("What is the result of: 3 + 2 * 2?", "7", Question.Difficulty.EASY,
                "10", "7", "8", "9"));
        questions.add(new Question("Which data type is used to store True or False values?", "bool", Question.Difficulty.EASY,
                "boolean", "bool", "logical", "int"));
        questions.add(new Question("Which keyword is used to create a loop in Python?", "for", Question.Difficulty.EASY,
                "loop", "for", "repeat", "iterate"));
        questions.add(new Question("What is used to store multiple values in one variable?", "list", Question.Difficulty.EASY,
                "string", "list", "int", "char"));
        questions.add(new Question("What is the value of: len('Hello')?", "5", Question.Difficulty.EASY,
                "4", "5", "6", "None"));
        questions.add(new Question("What is the correct way to start a function?", "def myFunc():", Question.Difficulty.EASY,
                "function myFunc():", "start myFunc():", "def myFunc():", "func myFunc():"));
        questions.add(new Question("Which of these is a valid Python variable name?", "my_var", Question.Difficulty.EASY,
                "my-var", "2var", "my_var", "var!"));
        questions.add(new Question("What will 'int(\"5\")' return?", "5", Question.Difficulty.EASY,
                "Error", "5", "'5'", "0"));
        questions.add(new Question("How do you get user input in Python?", "input()", Question.Difficulty.EASY,
                "scan()", "input()", "get()", "read()"));
        questions.add(new Question("Which value represents 'false' in Python?", "False", Question.Difficulty.EASY,
                "None", "0", "False", "'false'"));
        questions.add(new Question("Which built-in type is used for decimal numbers?", "float", Question.Difficulty.EASY,
                "int", "float", "decimal", "real"));
        questions.add(new Question("How do you write a string in Python?", "\"text\"", Question.Difficulty.EASY,
                "`text`", "<text>", "\"text\"", "@text"));
        questions.add(new Question("What symbol is used for multiplication in Python?", "*", Question.Difficulty.EASY,
                "x", "**", "*", "×"));
        questions.add(new Question("Which function can convert a string to an integer?", "int()", Question.Difficulty.EASY,
                "toInt()", "int()", "strToInt()", "convert()"));
        questions.add(new Question("How to write a block of code in Python?", "Using indentation", Question.Difficulty.EASY,
                "Using brackets", "Using indentation", "Using begin-end", "Using semicolons"));
        questions.add(new Question("What does 'print(3 == 3)' output?", "True", Question.Difficulty.EASY,
                "False", "True", "3", "None"));

        // Medium questions
        questions.add(new Question("Which of the following is a mutable data type in Python?", "list", Question.Difficulty.MEDIUM,
                "tuple", "string", "list", "int"));
        questions.add(new Question("How do you create a dictionary in Python?", "Using curly braces {}", Question.Difficulty.MEDIUM,
                "Using square brackets []", "Using parentheses ()", "Using curly braces {}", "Using angle brackets <>"));
        questions.add(new Question("What is the output of: len([1, 2, 3])?", "3", Question.Difficulty.MEDIUM,
                "2", "3", "4", "1"));
        questions.add(new Question("Which operator is used for exponentiation in Python?", "**", Question.Difficulty.MEDIUM,
                "^", "*", "**", "//"));
        questions.add(new Question("What is the output of: '5' + '3'?", "53", Question.Difficulty.MEDIUM,
                "8", "53", "Error", "None"));
        questions.add(new Question("Which loop type is not available in Python?", "do-while", Question.Difficulty.MEDIUM,
                "while", "for", "do-while", "nested for"));
        questions.add(new Question("Which method adds an element to a list?", "append()", Question.Difficulty.MEDIUM,
                "add()", "insert()", "append()", "extend()"));
        questions.add(new Question("How do you declare a set?", "Using curly braces", Question.Difficulty.MEDIUM,
                "Using brackets", "Using quotes", "Using curly braces", "Using commas"));
        questions.add(new Question("What is the default return value of a function without return statement?", "None", Question.Difficulty.MEDIUM,
                "0", "null", "None", "empty"));
        questions.add(new Question("What will 'type([])' return?", "<class 'list'>", Question.Difficulty.MEDIUM,
                "<class 'set'>", "<class 'dict'>", "<class 'list'>", "<list>"));
        questions.add(new Question("What does 'is' keyword compare?", "Object identity", Question.Difficulty.MEDIUM,
                "Value", "Object identity", "Data type", "Length"));
        questions.add(new Question("Which method removes the last element from a list?", "pop()", Question.Difficulty.MEDIUM,
                "remove()", "del()", "pop()", "clear()"));
        questions.add(new Question("Which keyword is used for conditional execution?", "if", Question.Difficulty.MEDIUM,
                "when", "if", "switch", "case"));
        questions.add(new Question("How do you handle multiple exceptions?", "Using multiple except blocks", Question.Difficulty.MEDIUM,
                "Using try-catch", "Multiple try blocks", "Using multiple except blocks", "With errors[]"));
        questions.add(new Question("What does 'break' do in a loop?", "Exits the loop", Question.Difficulty.MEDIUM,
                "Restarts loop", "Skips next iteration", "Exits the loop", "Does nothing"));
        questions.add(new Question("Which function sorts a list in place?", "sort()", Question.Difficulty.MEDIUM,
                "order()", "sort()", "sorted()", "arrange()"));
        questions.add(new Question("What is slicing used for in Python?", "Extracting parts of sequences", Question.Difficulty.MEDIUM,
                "Sorting data", "Copying values", "Extracting parts of sequences", "Creating variables"));
        questions.add(new Question("Which character separates dictionary keys from values?", ":", Question.Difficulty.MEDIUM,
                "=", ":", ",", "->"));
        questions.add(new Question("What is 'None' in Python?", "A special constant representing null", Question.Difficulty.MEDIUM,
                "An integer", "A string", "A special constant representing null", "A type error"));
        questions.add(new Question("What does 'continue' do in a loop?", "Skips to next iteration", Question.Difficulty.MEDIUM,
                "Exits loop", "Repeats current", "Skips to next iteration", "Ignores condition"));

        // Hard questions
        questions.add(new Question("What does the 'pass' statement do in Python?", "Does nothing", Question.Difficulty.HARD,
                "Stops the loop", "Raises an error", "Does nothing", "Skips iteration"));
        questions.add(new Question("How can you handle exceptions in Python?", "Using try and except", Question.Difficulty.HARD,
                "Using catch and handle", "Using try and catch", "Using try and except", "Using error and fix"));
        questions.add(new Question("What is a lambda function?", "An anonymous function", Question.Difficulty.HARD,
                "A function with no return", "An anonymous function", "A class method", "A loop function"));
        questions.add(new Question("What is the output of: list(range(2, 10, 2))?", "[2, 4, 6, 8]", Question.Difficulty.HARD,
                "[2, 3, 4, 5]", "[2, 4, 6, 8]", "[2, 4, 6, 8, 10]", "[2, 4, 6, 8, 9]"));
        questions.add(new Question("Which built-in function returns the memory address of an object?", "id()", Question.Difficulty.HARD,
                "mem()", "ref()", "id()", "loc()"));
        questions.add(new Question("How do you import the 'math' module in Python?", "import math", Question.Difficulty.HARD,
                "include math", "require 'math'", "import math", "use math"));
        questions.add(new Question("What is a generator in Python?", "A function that yields values", Question.Difficulty.HARD,
                "A random number function", "A function that returns a list", "A function that yields values", "An iterator object"));
        questions.add(new Question("What does *args do?", "Collects extra positional arguments", Question.Difficulty.HARD,
                "Collects keyword arguments", "Unpacks lists", "Collects extra positional arguments", "Defines variables"));
        questions.add(new Question("What is the use of '**kwargs'?", "Pass multiple keyword arguments", Question.Difficulty.HARD,
                "Pass strings", "Pass multiple values", "Pass multiple keyword arguments", "Break from function"));
        questions.add(new Question("Which decorator is used to define a class method?", "@classmethod", Question.Difficulty.HARD,
                "@static", "@classmethod", "@method", "@class"));
        questions.add(new Question("Which keyword defines an anonymous function?", "lambda", Question.Difficulty.HARD,
                "func", "lambda", "def", "anon"));
        questions.add(new Question("What is the output of bool([])?", "False", Question.Difficulty.HARD,
                "True", "None", "False", "Error"));
        questions.add(new Question("Which method is called when an object is created?", "__init__", Question.Difficulty.HARD,
                "__create__", "__start__", "__init__", "__object__"));
        questions.add(new Question("What does the 'with' statement manage?", "Context managers", Question.Difficulty.HARD,
                "Loops", "Functions", "Context managers", "Classes"));
        questions.add(new Question("What does 'yield' do?", "Returns value and pauses function", Question.Difficulty.HARD,
                "Returns value and ends function", "Throws error", "Returns value and pauses function", "Declares a variable"));
        questions.add(new Question("What will 'a is b' check for?", "Same object in memory", Question.Difficulty.HARD,
                "Same type", "Equal values", "Same object in memory", "Same address"));
        questions.add(new Question("How do you open a file in read mode?", "open('file.txt', 'r')", Question.Difficulty.HARD,
                "read('file.txt')", "open('file.txt')", "open('file.txt', 'r')", "file.open('r')"));
        questions.add(new Question("What does 'assert' do?", "Tests if a condition is true", Question.Difficulty.HARD,
                "Raises an error", "Exits loop", "Tests if a condition is true", "Ignores exceptions"));
        questions.add(new Question("What does the 'global' keyword do?", "Declares a global variable inside a function", Question.Difficulty.HARD,
                "Creates a variable", "Declares a local variable", "Declares a global variable inside a function", "Deletes global vars"));
        questions.add(new Question("What is a docstring?", "A string that documents code", Question.Difficulty.HARD,
                "A variable name", "A loop", "A string that documents code", "A list of strings"));

    }

    // Get a random question based on difficulty
    public Question getRandomQuestion(Question.Difficulty difficulty) {
        List<Question> filteredQuestions = new ArrayList<Question>();
        for (Question q : questions) {
            if (q.getDifficulty() == difficulty) {
                filteredQuestions.add(q);
            }
        }

        if (filteredQuestions.isEmpty()) {
            return null;
        }

        Question question = filteredQuestions.get(random.nextInt(filteredQuestions.size()));
        question.shuffleOptions();
        return question;
    }

    // Get all questions
    public List<Question> getAllQuestions() {
        return Collections.unmodifiableList(questions);
    }

    // Add a new question
    public void addQuestion(Question question) {
        questions.add(question);
    }
}
