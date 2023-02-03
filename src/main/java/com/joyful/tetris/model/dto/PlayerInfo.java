package com.joyful.tetris.model.dto;

public final class PlayerInfo {
    private final String playerName;
    private final int score;
    private final int lines;
    private final String rank;
    private final int level;
    private final double speed;
    private final double efficiency;

    public PlayerInfo(String playerName, int score, int lines, String rank, int level, double speed, double efficiency) {
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

    private static class PlayerInfoBuilder {
        private String playerName;
        private int score;
        private int lines;
        private String rank;
        private int level;
        private double speed;
        private double efficiency;
        
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

        public PlayerInfoBuilder speed(double speed) {
            this.speed = speed;
            return this;
        }

        public PlayerInfoBuilder efficiency(double efficiency) {
            this.efficiency = efficiency;
            return this;
        }

        public PlayerInfo build() {
            return new PlayerInfo(playerName, score, lines, rank, level, speed, efficiency);
        }
    }
}
