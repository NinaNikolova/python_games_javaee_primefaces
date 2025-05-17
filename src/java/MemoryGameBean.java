
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author nina
 */
@Named("memoryGameBean")
@ViewScoped
public class MemoryGameBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<Card> cards;
    private int moves;
    private int matchedPairs;
    private int totalPairs;
    private int firstSelectedIndex;
    private int secondSelectedIndex;
    private Timer timer;
    private int elapsedTime;
    private boolean gameInProgress;
    private Difficulty difficulty;
    
    public enum Difficulty {
        BEGINNER, INTERMEDIATE
    }
    
    @PostConstruct
    public void init() {
        difficulty = Difficulty.BEGINNER;
        startNewGame();
    }
    
    public void startNewGame() {
        // Reset game stats
        moves = 0;
        matchedPairs = 0;
        elapsedTime = 0;
        firstSelectedIndex = -1;
        secondSelectedIndex = -1;
        gameInProgress = true;
        
        // Initialize cards based on difficulty
        initializeCards();
        
        // Start timer
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                elapsedTime++;
                PrimeFaces.current().ajax().update("gameForm:elapsedTime");
            }
        }, 1000, 1000);
    }
    
    private void initializeCards() {
        cards = new ArrayList<Card>();
        List<CardPair> cardPairs = new ArrayList<CardPair>();
        
        if (difficulty == Difficulty.BEGINNER) {
            // Beginner level card pairs
            cardPairs.add(new CardPair("print()", "Displays output to the console"));
            cardPairs.add(new CardPair("if:", "Conditional statement"));
            cardPairs.add(new CardPair("for:", "Loop through a sequence"));
            cardPairs.add(new CardPair("def:", "Define a function"));
            cardPairs.add(new CardPair("str", "Text data type"));
            cardPairs.add(new CardPair("int", "Integer data type"));
        } else {
            // Intermediate level card pairs
            cardPairs.add(new CardPair("print()", "Displays output to the console"));
            cardPairs.add(new CardPair("if:", "Conditional statement"));
            cardPairs.add(new CardPair("for:", "Loop through a sequence"));
            cardPairs.add(new CardPair("def:", "Define a function"));
            cardPairs.add(new CardPair("str", "Text data type"));
            cardPairs.add(new CardPair("int", "Integer data type"));
            cardPairs.add(new CardPair("list", "Ordered collection"));
            cardPairs.add(new CardPair("dict", "Key-value pairs"));
            cardPairs.add(new CardPair("try/except", "Error handling"));
            cardPairs.add(new CardPair("lambda", "Anonymous function"));
        }
        
        totalPairs = cardPairs.size();
        
        // Create two cards for each pair
        for (CardPair pair : cardPairs) {
            cards.add(new Card(pair.getKey()));
            cards.add(new Card(pair.getValue()));
        }
        
        // Shuffle the cards
        Collections.shuffle(cards);
    }
    
    public void flipCard(int index) {
        if (!gameInProgress || cards.get(index).isMatched()) {
            return;
        }
        
        // If this is the first card being flipped
        if (firstSelectedIndex == -1) {
            firstSelectedIndex = index;
            cards.get(index).setFlipped(true);
        } 
        // If this is the second card being flipped
        else if (secondSelectedIndex == -1 && firstSelectedIndex != index) {
            secondSelectedIndex = index;
            cards.get(index).setFlipped(true);
            moves++;
            
            // Check for match
            checkForMatch();
        }
    }
    
    private void checkForMatch() {
        PrimeFaces.current().executeScript("setTimeout(function() {" +
                                           "PrimeFaces.ab({s:'gameForm'});" +
                                           "}, 1000);");
        
        Card firstCard = cards.get(firstSelectedIndex);
        Card secondCard = cards.get(secondSelectedIndex);
        
        // Check if the cards match (key matches value)
        boolean isMatch = false;
        for (int i = 0; i < cards.size(); i++) {
            for (int j = 0; j < cards.size(); j++) {
                if (i != j && cards.get(i).getContent().equals(firstCard.getContent()) && 
                    cards.get(j).getContent().equals(secondCard.getContent())) {
                    // Check if the cards form a valid Python concept pair
                    isMatch = isPythonPair(firstCard.getContent(), secondCard.getContent());
                    break;
                }
            }
            if (isMatch) break;
        }
        
        if (isMatch) {
            firstCard.setMatched(true);
            secondCard.setMatched(true);
            matchedPairs++;
            
            // Check if game is complete
            if (matchedPairs == totalPairs) {
                gameInProgress = false;
                timer.cancel();
                PrimeFaces.current().executeScript("PF('gameCompleteDialog').show();");
            }
        } else {
            // No match, flip cards back
            firstCard.setFlipped(false);
            secondCard.setFlipped(false);
        }
        
        // Reset selected indices
        firstSelectedIndex = -1;
        secondSelectedIndex = -1;
    }
    
    private boolean isPythonPair(String content1, String content2) {
        // Define concept pairs (could be moved to a configuration or database)
        String[][] pairs = {
            {"print()", "Displays output to the console"},
            {"if:", "Conditional statement"},
            {"for:", "Loop through a sequence"},
            {"def:", "Define a function"},
            {"str", "Text data type"},
            {"int", "Integer data type"},
            {"list", "Ordered collection"},
            {"dict", "Key-value pairs"},
            {"try/except", "Error handling"},
            {"lambda", "Anonymous function"}
        };
        
        for (String[] pair : pairs) {
            if ((content1.equals(pair[0]) && content2.equals(pair[1])) ||
                (content1.equals(pair[1]) && content2.equals(pair[0]))) {
                return true;
            }
        }
        return false;
    }
    
    public void showHint() {
        if (!gameInProgress) return;
        
        // Find an unmatched card and briefly show it
        for (int i = 0; i < cards.size(); i++) {
            if (!cards.get(i).isMatched() && !cards.get(i).isFlipped()) {
                final int cardIndex = i;
                cards.get(cardIndex).setFlipped(true);
                
                // Schedule to flip it back
                Timer hintTimer = new Timer();
                hintTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        cards.get(cardIndex).setFlipped(false);
                        PrimeFaces.current().ajax().update("gameForm");
                    }
                }, 1000);
                
                break;
            }
        }
    }
    
    public void changeDifficulty() {
        startNewGame();
    }
    
    // Getters and setters
    public List<Card> getCards() {
        return cards;
    }
    
    public int getMoves() {
        return moves;
    }
    
    public int getMatchedPairs() {
        return matchedPairs;
    }
    
    public int getTotalPairs() {
        return totalPairs;
    }
    
    public int getElapsedTime() {
        return elapsedTime;
    }
    
    public Difficulty getDifficulty() {
        return difficulty;
    }
    
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    
    // Inner classes
    public static class Card implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private final String content;
        private boolean flipped;
        private boolean matched;
        
        public Card(String content) {
            this.content = content;
            this.flipped = false;
            this.matched = false;
        }
        
        public String getContent() {
            return content;
        }
        
        public boolean isFlipped() {
            return flipped;
        }
        
        public void setFlipped(boolean flipped) {
            this.flipped = flipped;
        }
        
        public boolean isMatched() {
            return matched;
        }
        
        public void setMatched(boolean matched) {
            this.matched = matched;
        }
    }
    
    private static class CardPair {
        private final String key;
        private final String value;
        
        public CardPair(String key, String value) {
            this.key = key;
            this.value = value;
        }
        
        public String getKey() {
            return key;
        }
        
        public String getValue() {
            return value;
        }
    }
}