
package com.example.redoyahmed.mvpcleanarchitecture.service;

import com.example.redoyahmed.mvpcleanarchitecture.data.network.ApiHelper;
import com.example.redoyahmed.mvpcleanarchitecture.data.prefs.PreferencesHelper;
import com.example.redoyahmed.mvpcleanarchitecture.ui.base.BaseInteractor;

import javax.inject.Inject;

public class SyncInteractor extends BaseInteractor implements SyncMvpInteractor {

    @Inject
    public SyncInteractor(PreferencesHelper preferencesHelper, ApiHelper apiHelper) {

        super(preferencesHelper, apiHelper);
    }
}
