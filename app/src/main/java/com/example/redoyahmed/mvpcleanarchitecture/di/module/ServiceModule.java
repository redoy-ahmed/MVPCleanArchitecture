
package com.example.redoyahmed.mvpcleanarchitecture.di.module;

import android.app.Service;

import com.example.redoyahmed.mvpcleanarchitecture.service.SyncInteractor;
import com.example.redoyahmed.mvpcleanarchitecture.service.SyncMvpInteractor;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    private final Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    SyncMvpInteractor provideSyncMvpInteractor(SyncInteractor interactor) {
        return interactor;
    }
}
