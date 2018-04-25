
package com.example.redoyahmed.mvpcleanarchitecture.di.component;

import android.app.Application;
import android.content.Context;

import com.example.redoyahmed.mvpcleanarchitecture.MvpApp;
import com.example.redoyahmed.mvpcleanarchitecture.data.db.model.DaoSession;
import com.example.redoyahmed.mvpcleanarchitecture.data.network.ApiHelper;
import com.example.redoyahmed.mvpcleanarchitecture.data.prefs.PreferencesHelper;
import com.example.redoyahmed.mvpcleanarchitecture.di.ApplicationContext;
import com.example.redoyahmed.mvpcleanarchitecture.di.module.ApplicationModule;
import com.example.redoyahmed.mvpcleanarchitecture.service.SyncService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MvpApp app);

    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    PreferencesHelper preferencesHelper();

    ApiHelper apiHelper();

    DaoSession daoSession();
}