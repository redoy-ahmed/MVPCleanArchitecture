
package com.example.redoyahmed.mvpcleanarchitecture.ui.login.interactor;

import com.example.redoyahmed.mvpcleanarchitecture.data.network.model.LoginRequest;
import com.example.redoyahmed.mvpcleanarchitecture.data.network.model.LoginResponse;
import com.example.redoyahmed.mvpcleanarchitecture.ui.base.MvpInteractor;

import io.reactivex.Observable;

public interface LoginMvpInteractor extends MvpInteractor {
    Observable<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);
}
