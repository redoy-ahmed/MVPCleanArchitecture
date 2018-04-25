
package com.example.redoyahmed.mvpcleanarchitecture.ui.base;

import com.example.redoyahmed.mvpcleanarchitecture.data.network.ApiHelper;
import com.example.redoyahmed.mvpcleanarchitecture.data.prefs.PreferencesHelper;
import com.example.redoyahmed.mvpcleanarchitecture.utils.AppConstants;

import javax.inject.Inject;


public class BaseInteractor implements MvpInteractor {

    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public BaseInteractor(PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public ApiHelper getApiHelper() {
        return mApiHelper;
    }

    @Override
    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    @Override
    public void setAccessToken(String accessToken) {
        getPreferencesHelper().setAccessToken(accessToken);
        getApiHelper().getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public void updateUserInfo(String accessToken, Long userId, AppConstants.LoggedInMode loggedInMode, String userName, String email, String profilePicPath) {

        getPreferencesHelper().setAccessToken(accessToken);
        getPreferencesHelper().setCurrentUserId(userId);
        getPreferencesHelper().setCurrentUserLoggedInMode(loggedInMode);
        getPreferencesHelper().setCurrentUserName(userName);
        getPreferencesHelper().setCurrentUserEmail(email);
        getPreferencesHelper().setCurrentUserProfilePicUrl(profilePicPath);

        updateApiHeader(userId, accessToken);
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                null,
                null,
                AppConstants.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                null,
                null,
                null);
    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {
        getApiHelper().getApiHeader()
                .getProtectedApiHeader().setUserId(userId);
        getApiHelper().getApiHeader()
                .getProtectedApiHeader().setAccessToken(accessToken);
    }
}
