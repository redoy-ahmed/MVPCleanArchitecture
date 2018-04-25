
package com.example.redoyahmed.mvpcleanarchitecture.ui.base;

import com.example.redoyahmed.mvpcleanarchitecture.data.network.ApiHelper;
import com.example.redoyahmed.mvpcleanarchitecture.data.prefs.PreferencesHelper;
import com.example.redoyahmed.mvpcleanarchitecture.utils.AppConstants;

public interface MvpInteractor {

    ApiHelper getApiHelper();

    PreferencesHelper getPreferencesHelper();

    void setUserAsLoggedOut();

    void setAccessToken(String accessToken);

    void updateUserInfo(String accessToken, Long userId,
                        AppConstants.LoggedInMode loggedInMode,
                        String userName,
                        String email,
                        String profilePicPath);

    void updateApiHeader(Long userId, String accessToken);
}
