
package com.example.redoyahmed.mvpcleanarchitecture.data.network;

import com.example.redoyahmed.mvpcleanarchitecture.data.network.model.BlogResponse;
import com.example.redoyahmed.mvpcleanarchitecture.data.network.model.LoginRequest;
import com.example.redoyahmed.mvpcleanarchitecture.data.network.model.LoginResponse;
import com.example.redoyahmed.mvpcleanarchitecture.data.network.model.LogoutResponse;
import com.example.redoyahmed.mvpcleanarchitecture.data.network.model.OpenSourceResponse;

import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public interface ApiHelper {

    ApiHeader getApiHeader();

    Observable<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

    Observable<LogoutResponse> doLogoutApiCall();

    Observable<BlogResponse> getBlogApiCall();

    Observable<OpenSourceResponse> getOpenSourceApiCall();
}
