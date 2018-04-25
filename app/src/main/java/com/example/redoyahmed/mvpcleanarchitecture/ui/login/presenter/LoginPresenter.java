
package com.example.redoyahmed.mvpcleanarchitecture.ui.login.presenter;

import com.androidnetworking.error.ANError;
import com.example.redoyahmed.mvpcleanarchitecture.R;
import com.example.redoyahmed.mvpcleanarchitecture.data.network.model.LoginRequest;
import com.example.redoyahmed.mvpcleanarchitecture.data.network.model.LoginResponse;
import com.example.redoyahmed.mvpcleanarchitecture.ui.base.BasePresenter;
import com.example.redoyahmed.mvpcleanarchitecture.ui.login.interactor.LoginMvpInteractor;
import com.example.redoyahmed.mvpcleanarchitecture.ui.login.view.LoginMvpView;
import com.example.redoyahmed.mvpcleanarchitecture.utils.AppConstants;
import com.example.redoyahmed.mvpcleanarchitecture.utils.CommonUtils;
import com.example.redoyahmed.mvpcleanarchitecture.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;


public class LoginPresenter<V extends LoginMvpView, I extends LoginMvpInteractor> extends BasePresenter<V, I> implements LoginMvpPresenter<V, I> {

    private static final String TAG = "LoginPresenter";

    @Inject
    public LoginPresenter(I mvpInteractor, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(mvpInteractor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onServerLoginClick(String email, String password) {
        if (email == null || email.isEmpty()) {
            getMvpView().onError(R.string.empty_email);
            return;
        }
        if (!CommonUtils.isEmailValid(email)) {
            getMvpView().onError(R.string.invalid_email);
            return;
        }
        if (password == null || password.isEmpty()) {
            getMvpView().onError(R.string.empty_password);
            return;
        }
        getMvpView().showLoading();

        getCompositeDisposable().add(getInteractor()
                .doServerLoginApiCall(new LoginRequest.ServerLoginRequest(email, password))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse response) throws Exception {
                        getInteractor().updateUserInfo(
                                response.getAccessToken(),
                                response.getUserId(),
                                AppConstants.LoggedInMode.LOGGED_IN_MODE_SERVER,
                                response.getUserName(),
                                response.getUserEmail(),
                                response.getGoogleProfilePicUrl());

                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();
                        getMvpView().openMainActivity();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the login error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }
}
