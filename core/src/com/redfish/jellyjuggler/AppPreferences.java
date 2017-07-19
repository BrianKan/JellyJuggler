package com.redfish.jellyjuggler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Redfish on 7/17/2017.
 */

public class AppPreferences {

    protected Preferences getPrefs(){
        return Gdx.app.getPreferences("JellyJuggler");
    }

    public void enableSounds(boolean soundEnable){
        getPrefs().putBoolean("sound.enabled",soundEnable);
        getPrefs().flush();
    }

    public void enableMusic(boolean musicEnable){
        getPrefs().putBoolean("music.enabled",musicEnable);
        getPrefs().flush();
    }

    public boolean isMusicEnabled() {
        return getPrefs().getBoolean("music.enabled", true);
    }
    public boolean isSoundEnabled() {
        return getPrefs().getBoolean("sound.enabled", true);
    }
}
