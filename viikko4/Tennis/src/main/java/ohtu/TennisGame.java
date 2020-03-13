package ohtu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import main.java.ohtu.*;

public class TennisGame {
    
    final private static String[] scoreNames = {
        "Love",
        "Fifteen",
        "Thirty",
        "Forty"
    };

    final private static String INITIAL_SCORE_TEXT = "Love-All";
    
    private String currentScoreText;
    private int scoreBalance;
    
    private Player player1;
    private Player player2;

    private Player winner;
    private Player playerWithAdvantage;
    
    private Status gameStatus;

    public TennisGame(String player1, String player2) {
        this.player1 = new Player(player1);
        this.player2 = new Player(player2);

        this.scoreBalance = 0;
        this.currentScoreText = INITIAL_SCORE_TEXT;
        this.gameStatus = Status.NORMAL;
    }

    public void wonPoint(String playerName) {
        if ( playerName.equals(player1.getName())) {
            player1.addPoint();
        } else {
            player2.addPoint();
        }
        
        this.updateScore();
    }

    private void updateScore() {
        if ( this.player1.getPoints() >= 4 || this.player2.getPoints() >= 4 ) {            
            this.calculateScoreBalance();
            this.checkIfWinnerFound();
        } else {
            this.isGameTie();
        }
                
        this.updateCurrentScoreText();
    }

    private void calculateScoreBalance() {
        this.scoreBalance = this.player1.getPoints() - this.player2.getPoints();
    }

    private void checkIfWinnerFound() {
        if (Math.abs(this.scoreBalance) >= 2) {
            this.gameStatus = Status.WIN;
            this.setWinnerPlayer();
        } else {
            this.isGAmeDeuce();
        }
    }

    private void setWinnerPlayer() {
        if ( this.scoreBalance >= 2) {
            this.winner = this.player1;
        } else {
            this.winner = this.player2;
        }
    }

    private void isGAmeDeuce() {
        if ( this.scoreBalance == 0 ) {
            this.gameStatus = Status.DEUCE;
        } else {
            this.gameStatus = Status.ADVANTAGE;
            this.setPlayerWithAdvantage();
        }
    }

    private void setPlayerWithAdvantage() {
        if (this.scoreBalance == 1) {
            this.playerWithAdvantage = this.player1;
        } else {
            this.playerWithAdvantage = this.player2;
        }
    }

    private void isGameTie() {
        if ( this.player1.getPoints() == this.player2.getPoints() ) {
            this.gameStatus = Status.TIE;
        } else {
            this.gameStatus = Status.NORMAL;
        }
    }

    private void updateCurrentScoreText() { 
        switch (this.gameStatus) {
            case WIN:
                this.currentScoreText = "Win for " + this.winner.getName();
                break;
            case ADVANTAGE:
                this.currentScoreText = "Advantage " + this.playerWithAdvantage.getName();
                break;
            case DEUCE:
                this.currentScoreText = "Deuce"; 
                break;
            case TIE:
                this.currentScoreText = scoreNames[player1.getPoints()] + "-" + "All"; 
                break;
            default:
                this.currentScoreText = scoreNames[player1.getPoints()] + "-" + scoreNames[player2.getPoints()]; 
                break;
        }
    }

    public String getScore() {
        return this.currentScoreText;
    }
}