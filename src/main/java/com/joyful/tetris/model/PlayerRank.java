package com.joyful.tetris.model;

public enum PlayerRank {
    NOOB("Noob", 5000) {
        @Override
        public PlayerRank getHigherRank() {
            return EXPERIENCED;
        }
    },
    EXPERIENCED("Experienced", 10000) {
        @Override
        public PlayerRank getHigherRank() {
            return EXPERT;
        }
    },
    EXPERT("Expert", 15000) {
        @Override
        public PlayerRank getHigherRank() {
            return MASTER;
        }
    },
    MASTER("Master", 20000) {
        @Override
        public PlayerRank getHigherRank() {
            return SENIOR;
        }
    },
    SENIOR("Senior", 25000) {
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
