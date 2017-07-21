package com.redfish.jellyjuggler.adMob;

/**
 * Created by Redfish on 7/21/2017.
 */

public class DummyAdsController implements AdsController{
    @Override
    public void showBannerAd() {

    }

    @Override
    public void hideBannerAd() {

    }

    @Override
    public boolean isWifiConnected() {
        return false;
    }
}
