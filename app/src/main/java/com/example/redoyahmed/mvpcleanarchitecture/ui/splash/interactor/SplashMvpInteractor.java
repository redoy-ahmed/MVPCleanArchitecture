
package com.example.redoyahmed.mvpcleanarchitecture.ui.splash.interactor;

import com.example.redoyahmed.mvpcleanarchitecture.ui.base.MvpInteractor;
import io.reactivex.Observable;

public interface SplashMvpInteractor extends MvpInteractor {

    Observable<Boolean> seedDatabaseQuestions();

    Observable<Boolean> seedDatabaseOptions();

    int getCurrentUserLoggedInMode();
}
