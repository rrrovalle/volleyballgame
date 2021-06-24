package controller;

import java.util.ArrayList;
import java.util.List; 
 
import model.Referee;
import model.Set;
import model.Team;

public class GameController {
	
    private static GameController instance = null;
    
    private Set set;
    private String[] scoreList = new String[5];
    private Team team1;
    private Team team2;
    private Referee gameReferee;
    private String winner = "null";
    
    public static GameController getIntance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    private GameController() {
        team1   	= new Team();
        team2  		= new Team();
        set    		= new Set();
        gameReferee = new Referee();
    }
    
    public void setGameReferee(String name) {
    	this.gameReferee.setName(name);
    	notifyRefereeName(gameReferee.getName());
    }
    
    /** Change score points */
    public void addScore(int i) {
        switch (i) {
            case 1:
                team1.addScore();
                break;
            case 2:
                team2.addScore();
                break;
        }
        if (set.getSet() < 5) {
        	/** If set < 5 then the minimum score is 5 and the maximum is 25 */
            verifyScore(25, 30);
        } else {
        	/** set = 5 the minimum score is 15 and the maximum is 30 */
            verifyScore(15, 30);
        }
        notifyRefreshScore();
        
        verifyWonGame();
    }

    /** Sub score points */
    public void subScore(int i) {
        switch (i) {
            case 1:
                team1.subScore();
                break;
            case 2:
                team2.subScore();
                break;
        } 
        notifyRefreshScore();
    }
 
    /*
     *  check if it is possible to finish the SET
     */
     private void verifyScore(int min, int max) {

    	 /** Team 1 Score + 1> Team 2 Score */
         if (team1.getScore() >= min && team1.getScore() > team2.getScore() + 1) {
             team1.wonSet();
             saveData(team1.getScore(), team2.getScore());
             notifyWonSet(1);
             this.winner = "Team 1";
             return;
         }

         /** Team 2 Score > Team 1 Score */
         if (team2.getScore() >= min && team2.getScore() > team1.getScore() + 1) {
             team2.wonSet();
             saveData(team1.getScore(), team2.getScore());
             notifyWonSet(2);
             this.winner = "Team 2";
             return;
         }

         /** Team 1 reached maximum score */
         if (team1.getScore() == max) {
             team1.wonSet();
             saveData(team1.getScore(), team2.getScore());
             notifyWonSet(1);
             this.winner = "Team 1";
             return;
         }

         /** Team 2 reached maximum score */
         if (team2.getScore() == max) {
             team2.wonSet();
             saveData(team1.getScore(), team2.getScore());
             notifyWonSet(2);
             this.winner = "Team 2";
             return;
         } 
     }

     /** 
      * Check if any team won
      */
     private void verifyWonGame() {
         if (team1.getSetsWons() == 3) {
             notifyWhoWon(1);
             return;
         }
         if (team2.getSetsWons() == 3) {
             notifyWhoWon(2);
             return;
         }

     }
     
     /** 
      *  Returns winner team. If it is not possible to define a winner, <empty> is returned
      */
     public String returnWinner() {
    	 return this.winner;
     }

     /**
      * Save score
      * @param score1
      * @param score2
      */
     private void saveData(int score1, int score2) {
    	 scoreList[set.getSet() - 1] = "Set " + set.getSet() + " -> Team 1 | " + score1 + " X " + score2 + " | Team 2";
     }

     /**
      * Reset score
      */
     public void resetGame() {
    	 this.set.reset();
         this.team1     = new Team();
         this.team2 	= new Team();
         this.scoreList = new String[5];

         notifyRefreshScoreboard();
     }

     /**
      *  Observer Method's
      */
     private List<IGame> addGameScoreObserver = new ArrayList<>();

     public void attach(IGame obs) {
         this.addGameScoreObserver.add(obs);
     }

     public void detach(IGame obs) {
         this.addGameScoreObserver.remove(obs);
     }

     private void notifyRefreshScore() {
         for (IGame GameScore : addGameScoreObserver) {
             GameScore.refreshScore(team1.getScore(), team2.getScore());
         }
     }

     private void notifyWonSet(int i) {
         set.addSet();
         team1.setScore(0);
         team2.setScore(0);
         for (IGame GameScore : addGameScoreObserver) {
             GameScore.wonSet(i, set.getSet(), scoreList);
         }
     }

     private void notifyRefreshScoreboard() {
         for (IGame GameScore : addGameScoreObserver) {
             GameScore.resetGame();
         }

     }

     private void notifyWhoWon(int i) {
         for (IGame GameScore : addGameScoreObserver) {
             GameScore.finishGame(i);
         } 
     }
     
     private void notifyRefereeName(String name) {
         for (IGame GameScore : addGameScoreObserver) {
             GameScore.defineGameReferee(name);
         } 
     }
}
