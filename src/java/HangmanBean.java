import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nina
 */
@Named
@ViewScoped
public class HangmanBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Game categories and words
    private Map<String, List<String>> wordCategories;
    private String selectedCategory;
    private String currentWord;
    private char[] wordDisplay;
    private List<Character> guessedLetters;
    private int remainingGuesses;
    private int wrongGuesses;
    private boolean gameOver;
    private boolean gameWon;
    private int wins;
    private int losses;
    private List<Character> alphabet;
    
    @PostConstruct
    public void init() {
        initializeWordCategories();
        initializeAlphabet();
        selectedCategory = "data_types"; // Default category
        startNewGame();
    }
    
    public void startNewGame() {
        currentWord = getRandomWord(selectedCategory).toUpperCase();
        wordDisplay = new char[currentWord.length()];
        Arrays.fill(wordDisplay, '_');
        guessedLetters = new ArrayList<Character>();
        remainingGuesses = 10;
        wrongGuesses = 0;
        gameOver = false;
        gameWon = false;
    }
    
    public void guessLetter(String letterStr) {
        if (gameOver) {
            return;
        }
        
        char letter = letterStr.charAt(0);
        
        // Check if letter was already guessed
        if (guessedLetters.contains(letter)) {
            return;
        }
        
        guessedLetters.add(letter);
        
        boolean foundLetter = false;
        for (int i = 0; i < currentWord.length(); i++) {
            if (currentWord.charAt(i) == letter) {
                wordDisplay[i] = letter;
                foundLetter = true;
            }
        }
        
        if (!foundLetter) {
            wrongGuesses++;
            remainingGuesses--;
            
            if (remainingGuesses <= 0) {
                gameOver = true;
                losses++;
                // Reveal the word
                for (int i = 0; i < currentWord.length(); i++) {
                    wordDisplay[i] = currentWord.charAt(i);
                }
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Game Over", "You lost! The word was: " + currentWord));
            }
        } else {
            // Check if the word is complete
            boolean wordComplete = true;
            for (int i = 0; i < wordDisplay.length; i++) {
                if (wordDisplay[i] == '_') {
                    wordComplete = false;
                    break;
                }
            }
            
            if (wordComplete) {
                gameOver = true;
                gameWon = true;
                wins++;
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Congratulations", "You won!"));
            }
        }
    }
    
    public String getLetterClass(String letter) {
        char c = letter.charAt(0);
        if (!guessedLetters.contains(c)) {
            return "";
        }
        
        boolean isCorrect = false;
        for (int i = 0; i < currentWord.length(); i++) {
            if (currentWord.charAt(i) == c) {
                isCorrect = true;
                break;
            }
        }
        
        return isCorrect ? "guessed-correct" : "guessed-wrong";
    }
    
    public boolean isLetterGuessed(String letter) {
        return guessedLetters.contains(letter.charAt(0));
    }
    
    private String getRandomWord(String category) {
        List<String> words = wordCategories.get(category);
        if (words == null || words.isEmpty()) {
            return "ERROR";
        }
        
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }
    
private void initializeWordCategories() {
        wordCategories = new HashMap<String, List<String>>();
        
        // Data Types
        List<String> dataTypes = new ArrayList<String>();
        dataTypes.add("INTEGER");
        dataTypes.add("STRING");
        dataTypes.add("BOOLEAN");
        dataTypes.add("FLOAT");
        dataTypes.add("LIST");
        dataTypes.add("TUPLE");
        dataTypes.add("DICTIONARY");
        dataTypes.add("SET");
        dataTypes.add("NONE");
        dataTypes.add("BYTES");
        wordCategories.put("data_types", dataTypes);
        
        // Python Keywords
        List<String> keywords = new ArrayList<String>();
        keywords.add("DEF");
        keywords.add("CLASS");
        keywords.add("IF");
        keywords.add("ELSE");
        keywords.add("FOR");
        keywords.add("WHILE");
        keywords.add("TRY");
        keywords.add("EXCEPT");
        keywords.add("YIELD");
        keywords.add("LAMBDA");
        wordCategories.put("keywords", keywords);
        
        // Python Modules
        List<String> modules = new ArrayList<String>();
        modules.add("OS");
        modules.add("SYS");
        modules.add("MATH");
        modules.add("RANDOM");
        modules.add("DATETIME");
        modules.add("JSON");
        modules.add("CSV");
        modules.add("REQUESTS");
        modules.add("NUMPY");
        modules.add("PANDAS");
        wordCategories.put("modules", modules);
        
        // Python Built-in Functions
        List<String> functions = new ArrayList<String>();
        functions.add("PRINT");
        functions.add("LEN");
        functions.add("RANGE");
        functions.add("INPUT");
        functions.add("TYPE");
        functions.add("STR");
        functions.add("INT");
        functions.add("FLOAT");
        functions.add("LIST");
        functions.add("DICT");
        wordCategories.put("built_in_functions", functions);
    }
    
    private void initializeAlphabet() {
        alphabet = new ArrayList<Character>();
        for (char c = 'A'; c <= 'Z'; c++) {
            alphabet.add(c);
        }
    }
    
    // Getters and Setters
    
    public String getCurrentWord() {
        return currentWord;
    }
    
    public char[] getWordDisplay() {
        return wordDisplay;
    }
    
    public int getRemainingGuesses() {
        return remainingGuesses;
    }
    
    public int getWrongGuesses() {
        return wrongGuesses;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public boolean isGameWon() {
        return gameWon;
    }
    
    public int getWins() {
        return wins;
    }
    
    public int getLosses() {
        return losses;
    }
    
    public List<Character> getAlphabet() {
        return alphabet;
    }
    
    public String getSelectedCategory() {
        return selectedCategory;
    }
    
    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }
    
    public String getFormattedWordDisplay() {
    StringBuilder formatted = new StringBuilder();
    for (int i = 0; i < wordDisplay.length; i++) {
        formatted.append(wordDisplay[i]);
        if (i < wordDisplay.length - 1) {
            formatted.append(" "); // Add space between characters
        }
    }
    return formatted.toString();
}
}
