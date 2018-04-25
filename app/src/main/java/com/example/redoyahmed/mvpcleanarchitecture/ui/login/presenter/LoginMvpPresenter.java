
package com.example.redoyahmed.mvpcleanarchitecture.ui.login.presenter;

import com.example.redoyahmed.mvpcleanarchitecture.di.PerActivity;
import com.example.redoyahmed.mvpcleanarchitecture.ui.base.MvpPresenter;
import com.example.redoyahmed.mvpcleanarchitecture.ui.login.interactor.LoginMvpInteractor;
import com.example.redoyahmed.mvpcleanarchitecture.ui.login.view.LoginMvpView;

@PerActivity
public interface LoginMvpPresenter<V extends LoginMvpView, I extends LoginMvpInteractor> extends MvpPresenter<V, I> {
    void onServerLoginClick(String email, String password);
}
