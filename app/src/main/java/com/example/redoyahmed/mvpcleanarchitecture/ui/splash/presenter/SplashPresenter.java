
package com.example.redoyahmed.mvpcleanarchitecture.ui.splash.presenter;

import com.example.redoyahmed.mvpcleanarchitecture.R;
import com.example.redoyahmed.mvpcleanarchitecture.ui.base.BasePresenter;
import com.example.redoyahmed.mvpcleanarchitecture.ui.splash.interactor.SplashMvpInteractor;
import com.example.redoyahmed.mvpcleanarchitecture.ui.splash.view.SplashMvpView;
import com.example.redoyahmed.mvpcleanarchitecture.utils.AppConstants;
import com.example.redoyahmed.mvpcleanarchitecture.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class SplashPresenter<V extends SplashMvpView, I extends SplashMvpInteractor> extends BasePresenter<V, I> implements SplashMvpPresenter<V, I> {

    @Inject
    public SplashPresenter(I mvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        getMvpView().startSyncService();

        getCompositeDisposable().add(getInteractor()
                .seedDatabaseQuestions()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .concatMap(new Function<Boolean, ObservableSource<Boolean>>() {
                    @Override
                    public ObservableSource<Boolean> apply(Boolean aBoolean) throws Exception {
                        return getInteractor().seedDatabaseOptions();
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        decideNextActivity();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().onError(R.string.some_error);
                        decideNextActivity();
                    }
                }));


    }

    private void decideNextActivity() {
        if (getInteractor().getCurrentUserLoggedInMode() == AppConstants.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
            getMvpView().openLoginActivity();
        } else {
            getMvpView().openMainActivity();
        }
    }
}
