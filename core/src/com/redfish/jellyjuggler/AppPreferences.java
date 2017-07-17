package com.redfish.jellyjuggler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Redfish on 7/17/2017.
 */

public class AppPreferences {
    protected Preferences getPrefs(){return Gdx.app.getPreferences("JellyJuggler");}
}
