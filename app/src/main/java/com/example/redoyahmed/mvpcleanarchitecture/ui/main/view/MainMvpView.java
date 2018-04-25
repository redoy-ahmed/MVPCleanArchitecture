
package com.example.redoyahmed.mvpcleanarchitecture.ui.main.view;


import com.example.redoyahmed.mvpcleanarchitecture.data.db.model.Question;
import com.example.redoyahmed.mvpcleanarchitecture.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

    void openLoginActivity();

    void showAboutFragment();

    void refreshQuestionnaire(List<Question> questionList);

    void reloadQuestionnaire(List<Question> questionList);

    void updateUserName(String currentUserName);

    void updateUserEmail(String currentUserEmail);

    void updateUserProfilePic(String currentUserProfilePicUrl);

    void updateAppVersion();

    /*void showRateUsDialog();

    void openMyFeedActivity();*/

    void closeNavigationDrawer();

    void lockDrawer();

    void unlockDrawer();
}
