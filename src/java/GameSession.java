import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nina
 */

public class GameSession implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String playerName;
    private int score;
    private int currentFloor;
    private int maxFloors;
    private Question.Difficulty difficulty;
    private boolean gameOver;
    private List<Integer> builtFloors;
    
    public GameSession() {
        this.score = 0;
        this.currentFloor = 1;
        this.maxFloors = 10; // Default tower height
        this.gameOver = false;
        this.builtFloors = new ArrayList<Integer>();
    }
    
    // Adds a floor to the tower
    public void addFloor() {
        builtFloors.add(currentFloor);
        currentFloor++;
        
        if (currentFloor > maxFloors) {
            gameOver = true;
        }
    }
    
    // Check if the player has won (completed all floors)
    public boolean isWinner() {
        return currentFloor > maxFloors;
    }
    
    // Getters and setters
    public String getPlayerName() {
        return playerName;
    }
    
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    
    public int getScore() {
        return score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public void addScore(int points) {
        this.score += points;
    }
    
    public int getCurrentFloor() {
        return currentFloor;
    }
    
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
    
    public int getMaxFloors() {
        return maxFloors;
    }
    
    public void setMaxFloors(int maxFloors) {
        this.maxFloors = maxFloors;
    }
    
    public Question.Difficulty getDifficulty() {
        return difficulty;
    }
    
    public void setDifficulty(Question.Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    
    public List<Integer> getBuiltFloors() {
        return builtFloors;
    }
    
    public void setBuiltFloors(List<Integer> builtFloors) {
        this.builtFloors = builtFloors;
    }
    
    // Reset the game session
    public void reset() {
        this.score = 0;
        this.currentFloor = 1;
        this.gameOver = false;
        this.builtFloors = new ArrayList<Integer>();
    }
}

