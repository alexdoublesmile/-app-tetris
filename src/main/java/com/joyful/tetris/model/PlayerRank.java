package com.joyful.tetris.model;

import com.joyful.tetris.Launcher;

public enum PlayerRank {
    NOOB("Noob", 200) {
        @Override
        public PlayerRank getHigherRank() {
            Launcher.playBeginner();
            return BEGINNER;
        }
    },
    BEGINNER("Beginner", 500) {
        @Override
        public PlayerRank getHigherRank() {
            Launcher.playExperienced();
            return EXPERIENCED;
        }
    },
    EXPERIENCED("Experienced", 1000) {
        @Override
        public PlayerRank getHigherRank() {
            Launcher.playExpert();
            return EXPERT;
        }
    },
    EXPERT("Expert", 1500) {
        @Override
        public PlayerRank getHigherRank() {
            Launcher.playMaster();
            return MASTER;
        }
    },
    MASTER("Master", 2000) {
        @Override
        public PlayerRank getHigherRank() {
            Launcher.playSenior();
            return SENIOR;
        }
    },
    SENIOR("Senior", 2500) {
        @Override
        public PlayerRank getHigherRank() {
            return SENIOR;
        }
    };
    
    private final String title;
    private final int nextRankScore;

    private PlayerRank(String title, int score) {
        this.title = title;
        this.nextRankScore = score;
    }

    public String getTitle() {
        return title;
    }

    public int getNextRankScore() {
        return nextRankScore;
    }
    
    public abstract PlayerRank getHigherRank();
    
    public PlayerRank getRankByScore(int score) {
        return score >= nextRankScore ? getHigherRank() : this;
    }
}
