
package com.example.redoyahmed.mvpcleanarchitecture.ui.main.interactor;

import com.example.redoyahmed.mvpcleanarchitecture.data.db.model.Question;
import com.example.redoyahmed.mvpcleanarchitecture.data.db.repository.QuestionRepository;
import com.example.redoyahmed.mvpcleanarchitecture.data.network.ApiHelper;
import com.example.redoyahmed.mvpcleanarchitecture.data.network.model.LogoutResponse;
import com.example.redoyahmed.mvpcleanarchitecture.data.prefs.PreferencesHelper;
import com.example.redoyahmed.mvpcleanarchitecture.ui.base.BaseInteractor;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;

public class MainInteractor extends BaseInteractor implements MainMvpInteractor {

    private QuestionRepository mQuestionRepository;

    @Inject
    public MainInteractor(PreferencesHelper preferencesHelper, ApiHelper apiHelper, QuestionRepository questionRepository) {
        super(preferencesHelper, apiHelper);
        mQuestionRepository = questionRepository;
    }

    @Override
    public Observable<LogoutResponse> doLogoutApiCall() {
        return getApiHelper().doLogoutApiCall();
    }

    @Override
    public Observable<List<Question>> getAllQuestions() {
        return mQuestionRepository.getAllQuestions();
    }

    @Override
    public String getCurrentUserName() {
        return getPreferencesHelper().getCurrentUserName();
    }

    @Override
    public String getCurrentUserEmail() {
        return getPreferencesHelper().getCurrentUserEmail();
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return getPreferencesHelper().getCurrentUserProfilePicUrl();
    }
}
