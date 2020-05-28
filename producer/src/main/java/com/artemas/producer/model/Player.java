package com.artemas.producer.model;

public class Player {
    private Long id;
    private String playerName;
    private String playerSurname;
    private String position;

    public Player(Long id, String playerName, String playerSurname, String position) {
        this.id = id;
        this.playerName = playerName;
        this.playerSurname = playerSurname;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerSurname() {
        return playerSurname;
    }

    public String getPosition() {
        return position;
    }
}
