package com.redfish.jellyjuggler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.redfish.jellyjuggler.Screens.PreferencesScreen;

/**
 * Created by Redfish on 7/17/2017.
 */

public class AppPreferences {
    private Preferences preferences;

    protected Preferences getPrefs(){
        if(preferences==null){
            preferences = Gdx.app.getPreferences("JellyJuggler");
        }
        return preferences;
    }

    public void enableAds(boolean adsEnable){
        getPrefs().putBoolean("ads.enabled",adsEnable);
        getPrefs().flush();
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
    public boolean isAdsEnabled(){return getPrefs().getBoolean("ads.enabled", true);}
}
