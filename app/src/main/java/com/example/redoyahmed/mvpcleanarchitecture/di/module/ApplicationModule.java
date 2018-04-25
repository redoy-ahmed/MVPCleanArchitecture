
package com.example.redoyahmed.mvpcleanarchitecture.di.module;

import android.app.Application;
import android.content.Context;


import com.example.redoyahmed.mvpcleanarchitecture.BuildConfig;
import com.example.redoyahmed.mvpcleanarchitecture.R;
import com.example.redoyahmed.mvpcleanarchitecture.data.db.DbOpenHelper;
import com.example.redoyahmed.mvpcleanarchitecture.data.db.model.DaoMaster;
import com.example.redoyahmed.mvpcleanarchitecture.data.db.model.DaoSession;
import com.example.redoyahmed.mvpcleanarchitecture.data.network.ApiHeader;
import com.example.redoyahmed.mvpcleanarchitecture.data.network.ApiHelper;
import com.example.redoyahmed.mvpcleanarchitecture.data.network.AppApiHelper;
import com.example.redoyahmed.mvpcleanarchitecture.data.prefs.AppPreferencesHelper;
import com.example.redoyahmed.mvpcleanarchitecture.data.prefs.PreferencesHelper;
import com.example.redoyahmed.mvpcleanarchitecture.di.ApiInfo;
import com.example.redoyahmed.mvpcleanarchitecture.di.ApplicationContext;
import com.example.redoyahmed.mvpcleanarchitecture.di.DatabaseInfo;
import com.example.redoyahmed.mvpcleanarchitecture.di.PreferenceInfo;
import com.example.redoyahmed.mvpcleanarchitecture.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey, PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(apiKey, preferencesHelper.getCurrentUserId(), preferencesHelper.getAccessToken());
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession(DbOpenHelper dbOpenHelper) {
        return new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }
}
