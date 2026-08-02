package dev.geco.gmusic.model;

import org.jetbrains.annotations.NotNull;

import java.util.Timer;
import java.util.UUID;

public class PlayState {

	private final @NotNull UUID uuid;
	private final @NotNull PlayType playType;
	private final @NotNull Song song;
	private @NotNull Timer timer;
	private long tickPosition;
	private boolean paused = false;

	public PlayState(
			@NotNull UUID uuid,
			@NotNull PlayType playType,
			@NotNull Song song,
			@NotNull Timer timer,
			long tickPosition
	) {
		this.uuid = uuid;
		this.playType = playType;
		this.song = song;
		this.timer = timer;
		this.tickPosition = tickPosition;
	}

	public @NotNull UUID getUUID() { return uuid; }

	public @NotNull PlayType getPlayType() { return playType; }

	public @NotNull Song getSong() { return song; }

	public @NotNull Timer getTimer() { return timer; }

	public void setTimer(@NotNull Timer timer) { this.timer = timer; }

	public long getTickPosition() { return tickPosition; }

	public void setTickPosition(long tickPosition) { this.tickPosition = tickPosition; }

	public boolean isPaused() { return paused; }

	public void setPaused(boolean paused) { this.paused = paused; }

}