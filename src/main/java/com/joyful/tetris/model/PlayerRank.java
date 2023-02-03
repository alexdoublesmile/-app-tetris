package com.joyful.tetris.model;

import com.joyful.tetris.Launcher;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public enum PlayerRank {
    NOOB("Noob", 2000) {
        @Override
        public PlayerRank getHigherRank() {
            Launcher.playBeginner();
            return BEGINNER;
        }

        @Override
        public List<Color> getColors() {
            return Arrays.asList(
                    new Color(200, 200, 200)
            );
        }
    },
    BEGINNER("Beginner", 5000) {
        @Override
        public PlayerRank getHigherRank() {
            Launcher.playExperienced();
            return EXPERIENCED;
        }

        @Override
        public List<Color> getColors() {
            return Arrays.asList(
                    new Color(255, 255, 150),
                    new Color(200, 200, 200)
            );
        }
    },
    EXPERIENCED("Experienced", 10000) {
        @Override
        public PlayerRank getHigherRank() {
            Launcher.playExpert();
            return EXPERT;
        }

        @Override
        public List<Color> getColors() {
            return Arrays.asList(
                    new Color(50, 180, 100),
                    new Color(255, 255, 150),
                    new Color(200, 200, 200)
            );
        }
    },
    EXPERT("Expert", 15000) {
        @Override
        public PlayerRank getHigherRank() {
            Launcher.playMaster();
            return MASTER;
        }

        @Override
        public List<Color> getColors() {
            return Arrays.asList(
                    new Color(255, 155, 50),
                    new Color(50, 180, 100),
                    new Color(255, 255, 150),
                    new Color(200, 200, 200)
            );
        }
    },
    MASTER("Master", 20000) {
        @Override
        public PlayerRank getHigherRank() {
            Launcher.playSenior();
            return SENIOR;
        }

        @Override
        public List<Color> getColors() {
            return Arrays.asList(
                    new Color(50, 200, 200),
                    new Color(50, 200, 200),
                    new Color(50, 180, 100),
                    new Color(255, 255, 150),
                    new Color(200, 200, 200)
            );
        }
    },
    SENIOR("Senior", 25000) {
        @Override
        public PlayerRank getHigherRank() {
            return SENIOR;
        }

        @Override
        public List<Color> getColors() {
            return Arrays.asList(
                    new Color(0, 100, 200),
                    new Color(50, 180, 100),
                    new Color(50, 200, 200),
                    new Color(255, 255, 150),
                    new Color(255, 155, 50),
                    new Color(200, 100, 100),
                    new Color(200, 200, 200)
            );
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
    public abstract List<Color> getColors();
    
    public PlayerRank getRankByScore(int score) {
        return score >= nextRankScore ? getHigherRank() : this;
    }
}
