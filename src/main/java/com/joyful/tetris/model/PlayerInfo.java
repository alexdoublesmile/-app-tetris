package com.joyful.tetris.model;

public final class PlayerInfo {
    private String playerName;
    private final int score;
    private final int lines;
    private final String rank;
    private final int level;
    private final String speed;
    private final String efficiency;

    public PlayerInfo(String playerName, int score, int lines, String rank, int level, String speed, String efficiency) {
        this.playerName = playerName;
        this.score = score;
        this.lines = lines;
        this.rank = rank;
        this.level = level;
        this.speed = speed;
        this.efficiency = efficiency;
    }

    public static PlayerInfoBuilder builder() {
        return new PlayerInfoBuilder();
    }

    public static class PlayerInfoBuilder {
        private String playerName;
        private int score;
        private int lines;
        private String rank;
        private int level;
        private String speed;
        private String efficiency;
        
        public PlayerInfoBuilder playerName(String playerName) {
            this.playerName = playerName;
            return this;
        }

        public PlayerInfoBuilder score(int score) {
            this.score = score;
            return this;
        }

        public PlayerInfoBuilder lines(int lines) {
            this.lines = lines;
            return this;
        }

        public PlayerInfoBuilder rank(String rank) {
            this.rank = rank;
            return this;
        }

        public PlayerInfoBuilder level(int level) {
            this.level = level;
            return this;
        }

        public PlayerInfoBuilder speed(String speed) {
            this.speed = speed;
            return this;
        }

        public PlayerInfoBuilder efficiency(String efficiency) {
            this.efficiency = efficiency;
            return this;
        }

        public PlayerInfo build() {
            return new PlayerInfo(playerName, score, lines, rank, level, speed, efficiency);
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public int getLines() {
        return lines;
    }

    public String getRank() {
        return rank;
    }

    public int getLevel() {
        return level;
    }

    public String getSpeed() {
        return speed;
    }

    public String getEfficiency() {
        return efficiency;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
