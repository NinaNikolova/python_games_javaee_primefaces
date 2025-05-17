/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nina
 */
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    private String text;
    private List<String> options;
    private String correctAnswer;
    private Difficulty difficulty;

       public enum Difficulty {
        EASY, MEDIUM, HARD
    }
       public Question() {
    }   
    public Question(String text, List<String> options, String correctAnswer) {
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
     public Question(String text, String correctAnswer, Difficulty difficulty, String... options) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
        this.options = Arrays.asList(options);
    }

    // Shuffles the answer options to avoid the correct answer always being in the same position
    public void shuffleOptions() {
        List<String> shuffled = Arrays.asList(options.toArray(new String[0]));
        Collections.shuffle(shuffled);
        this.options = shuffled;
    }
    public String getText() {
        return text;
    }
        public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }
        
    public void setOptions(List<String> options) {
        this.options = options;
    }
    

    public String getCorrectAnswer() {
        return correctAnswer;
    }
        public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    
    public Difficulty getDifficulty() {
        return difficulty;
    }
    
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
