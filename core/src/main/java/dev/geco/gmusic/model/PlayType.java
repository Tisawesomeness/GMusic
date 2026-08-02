package dev.geco.gmusic.model;

public enum PlayType {

    DEFAULT(0),
    JUKEBOX(1),
    RADIO(2);

    private final int id;

    PlayType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static PlayType byId(int id) {
        for(PlayType playType : values()) if(playType.getId() == id) return playType;
        return null;
    }

}