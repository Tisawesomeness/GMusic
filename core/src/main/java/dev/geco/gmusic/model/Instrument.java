package dev.geco.gmusic.model;

import org.jetbrains.annotations.NotNull;

public interface Instrument {

    @NotNull String getSound();
    default int getInstrumentKey() { return 12; }
    default boolean isCustom() { return false; }

}
