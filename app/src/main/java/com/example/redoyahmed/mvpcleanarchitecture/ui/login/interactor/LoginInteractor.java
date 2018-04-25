
package com.example.redoyahmed.mvpcleanarchitecture.ui.login.interactor;

import com.example.redoyahmed.mvpcleanarchitecture.data.db.repository.UserRepository;
import com.example.redoyahmed.mvpcleanarchitecture.data.network.ApiHelper;
import com.example.redoyahmed.mvpcleanarchitecture.data.network.model.LoginRequest;
import com.example.redoyahmed.mvpcleanarchitecture.data.network.model.LoginResponse;
import com.example.redoyahmed.mvpcleanarchitecture.data.prefs.PreferencesHelper;
import com.example.redoyahmed.mvpcleanarchitecture.ui.base.BaseInteractor;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LoginInteractor extends BaseInteractor implements LoginMvpInteractor {

    private UserRepository mUserRepository;

    @Inject
    public LoginInteractor(PreferencesHelper preferencesHelper, ApiHelper apiHelper, UserRepository userRepository) {
        super(preferencesHelper, apiHelper);
        mUserRepository = userRepository;
    }

    @Override
    public Observable<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {
        return getApiHelper().doServerLoginApiCall(request);
    }
}
