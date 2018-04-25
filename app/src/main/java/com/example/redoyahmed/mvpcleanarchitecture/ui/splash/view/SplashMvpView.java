
package com.example.redoyahmed.mvpcleanarchitecture.ui.splash.view;


import com.example.redoyahmed.mvpcleanarchitecture.ui.base.MvpView;

public interface SplashMvpView extends MvpView {

    void openLoginActivity();

    void openMainActivity();

    void startSyncService();
}
