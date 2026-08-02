package dev.geco.gmusic.model;

import org.jetbrains.annotations.NotNull;

public class CustomInstrument implements Instrument {

    private final String sound;
    private final int key;

    public CustomInstrument(String sound, int key) {
        this.sound = sound;
        this.key = key;
    }

    @Override
    public @NotNull String getSound() { return sound; }

    @Override
    public int getInstrumentKey() { return key; }

    @Override
    public boolean isCustom() { return true; }

}