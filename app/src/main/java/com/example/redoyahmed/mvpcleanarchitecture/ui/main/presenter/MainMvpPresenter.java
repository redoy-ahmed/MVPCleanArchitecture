
package com.example.redoyahmed.mvpcleanarchitecture.ui.main.presenter;

import com.example.redoyahmed.mvpcleanarchitecture.di.PerActivity;
import com.example.redoyahmed.mvpcleanarchitecture.ui.base.MvpPresenter;
import com.example.redoyahmed.mvpcleanarchitecture.ui.main.interactor.MainMvpInteractor;
import com.example.redoyahmed.mvpcleanarchitecture.ui.main.view.MainMvpView;

@PerActivity
public interface MainMvpPresenter<V extends MainMvpView, I extends MainMvpInteractor> extends MvpPresenter<V, I> {

    void onDrawerOptionAboutClick();

    void onDrawerOptionLogoutClick();

    /*void onDrawerRateUsClick();

    void onDrawerMyFeedClick();*/

    void onViewInitialized();

    void onCardExhausted();

    void onNavMenuCreated();
}
