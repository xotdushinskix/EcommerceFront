package com.websystique.nikita.model;

/**
 * Created by nikita on 30.08.16.
 */
public enum UserPosition {

    ACTIVE("Active"),
    LOCKED("Locked");

    private String position;

    UserPosition(String userPosition) {
        this.position = userPosition;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return this.position;
    }
}
