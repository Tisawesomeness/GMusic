package dev.geco.gmusic.util;

import dev.geco.gmusic.GMusicMain;
import dev.geco.gmusic.model.NotePart;
import dev.geco.gmusic.model.PlaySettings;
import dev.geco.gmusic.model.Song;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MusicUtil {

    private final GMusicMain gMusicMain;

    public MusicUtil(GMusicMain gMusicMain) {
        this.gMusicMain = gMusicMain;
    }

    public void playAtPlayer(@NotNull Player player, @NotNull NotePart notePart, @NotNull PlaySettings playSettings, boolean stereo) {
        play(player, notePart, player.getEyeLocation(), playSettings.getFixedVolume(), stereo);
    }
    public void playAtLocation(@NotNull Player player, @NotNull NotePart notePart, @NotNull Location origin, @NotNull PlaySettings playSettings) {
        float volume = rangeToVolume(playSettings.getRange()) * playSettings.getFixedVolume();
        play(player, notePart, origin, volume, false);
    }
    public void playAtPlayerWithDecay(@NotNull Player player, @NotNull NotePart notePart, double distanceToOrigin, @NotNull PlaySettings playSettings, boolean stereo) {
        float volume = simulateVolumeDecay(distanceToOrigin, playSettings.getRange()) * playSettings.getFixedVolume();
        play(player, notePart, player.getEyeLocation(), volume, stereo);
    }

    private void play(@NotNull Player player, @NotNull NotePart notePart, @NotNull Location origin, float fixedVolume, boolean stereo) {
        Song song = notePart.getNote().getSong();
        if(notePart.getSound() != null) {
            float volume = fixedVolume * notePart.getVolume();

            Location location = !stereo || notePart.getDistance() == 0 ? origin : gMusicMain.getSteroNoteUtil().convertToStero(origin, notePart.getDistance());

            if(!gMusicMain.getConfigService().ENVIRONMENT_EFFECTS) player.playSound(location, notePart.getSound(), song.getSoundCategory(), volume, notePart.getPitch());
            else {
                if(gMusicMain.getEnvironmentUtil().isPlayerSwimming(player)) player.playSound(location, notePart.getSound(), song.getSoundCategory(), volume > 0.4f ? volume - 0.3f : volume, notePart.getPitch() - 0.15f);
                else player.playSound(location, notePart.getSound(), song.getSoundCategory(), volume, notePart.getPitch());
            }
        } else if(notePart.getStopSound() != null) player.stopSound(notePart.getStopSound(), song.getSoundCategory());
    }

    private float rangeToVolume(double range) {
        return (float) (1.0 + (range - 16) * 0.06);
    }
    private float simulateVolumeDecay(double playerDistance, double range) {
        return (float) ((range - playerDistance) / range);
    }

}
