import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class GameBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private QuestionService questionService;

    private GameSession gameSession;
    private Question currentQuestion;
    private String selectedAnswer;
    private String playerName;
    private Question.Difficulty difficulty;
    private int maxFloors;

    @PostConstruct
    public void init() {
        gameSession = new GameSession();
        difficulty = Question.Difficulty.EASY;
        maxFloors = 10;
        playerName = null; // Reset to show welcome panel
        selectedAnswer = null;
        currentQuestion = null;
        System.out.println("GameBean initialized, playerName: " + playerName + ", gameOver: " + (gameSession != null ? gameSession.isGameOver() : "null"));
    }

    public void startGame() {
        if (playerName == null || playerName.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Please enter a player name"));
            return;
        }

        gameSession.reset();
        gameSession.setPlayerName(playerName);
        gameSession.setDifficulty(difficulty);
        gameSession.setMaxFloors(maxFloors);

        loadNextQuestion();

        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Game Started",
                          "Welcome " + playerName + "! Build your Python tower!"));
    }

    private void loadNextQuestion() {
        currentQuestion = questionService.getRandomQuestion(gameSession.getDifficulty());
        selectedAnswer = null;
        System.out.println("Loaded question: " + (currentQuestion != null ? currentQuestion.getText() : "null"));
    }

    public void submitAnswer() {
        if (selectedAnswer == null || selectedAnswer.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Please select an answer"));
            return;
        }

        if (currentQuestion == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No question available"));
            return;
        }

        boolean correct = selectedAnswer.equals(currentQuestion.getCorrectAnswer());

        if (correct) {
            int points;
            switch (difficulty) {
                case EASY:
                    points = 10;
                    break;
                case MEDIUM:
                    points = 20;
                    break;
                case HARD:
                    points = 30;
                    break;
                default:
                    points = 0;
            }

            gameSession.addScore(points);
            gameSession.addFloor();

            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct!",
                              "You earned " + points + " points. Your tower grows!"));

            if (gameSession.isGameOver()) {
                if (gameSession.isWinner()) {
                    FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Congratulations!",
                                      "You completed your tower with " + gameSession.getScore() + " points!"));
                }
            } else {
                loadNextQuestion();
            }
        } else {
            gameSession.setGameOver(true);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect!",
                              "The correct answer was: " + currentQuestion.getCorrectAnswer() +
                              ". Your tower construction has stopped."));
        }
    }

    public void restart() {
        init();
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Game Restarted", "Start a new game!"));
    }

    public boolean isGameInProgress() {
        boolean inProgress = !gameSession.isGameOver() && playerName != null && !playerName.isEmpty() && currentQuestion != null;
        System.out.println("isGameInProgress: " + inProgress + ", playerName: " + playerName + ", gameOver: " + gameSession.isGameOver() + ", currentQuestion: " + (currentQuestion != null));
        return inProgress;
    }

    public boolean isGameOver() {
        boolean gameOver = gameSession.isGameOver();
        System.out.println("isGameOver: " + gameOver);
        return gameOver;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Question.Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Question.Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getMaxFloors() {
        return maxFloors;
    }

    public void setMaxFloors(int maxFloors) {
        this.maxFloors = maxFloors;
    }

    public int getScore() {
        return gameSession.getScore();
    }

    public int getCurrentFloor() {
        return gameSession.getCurrentFloor();
    }

    public List<Integer> getBuiltFloors() {
        return gameSession.getBuiltFloors();
    }
}