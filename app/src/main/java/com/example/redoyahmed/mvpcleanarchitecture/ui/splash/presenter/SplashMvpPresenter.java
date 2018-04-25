
package com.example.redoyahmed.mvpcleanarchitecture.ui.splash.presenter;

import com.example.redoyahmed.mvpcleanarchitecture.di.PerActivity;
import com.example.redoyahmed.mvpcleanarchitecture.ui.base.MvpPresenter;
import com.example.redoyahmed.mvpcleanarchitecture.ui.splash.interactor.SplashMvpInteractor;
import com.example.redoyahmed.mvpcleanarchitecture.ui.splash.view.SplashMvpView;

@PerActivity
public interface SplashMvpPresenter<V extends SplashMvpView, I extends SplashMvpInteractor> extends MvpPresenter<V, I> {
}
