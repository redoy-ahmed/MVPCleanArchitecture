
package com.example.redoyahmed.mvpcleanarchitecture.ui.main.interactor;

import com.example.redoyahmed.mvpcleanarchitecture.data.db.model.Question;
import com.example.redoyahmed.mvpcleanarchitecture.data.network.model.LogoutResponse;
import com.example.redoyahmed.mvpcleanarchitecture.ui.base.MvpInteractor;
import java.util.List;
import io.reactivex.Observable;

public interface MainMvpInteractor extends MvpInteractor {

    Observable<LogoutResponse> doLogoutApiCall();

    Observable<List<Question>> getAllQuestions();

    String getCurrentUserName();

    String getCurrentUserEmail();

    String getCurrentUserProfilePicUrl();
}
