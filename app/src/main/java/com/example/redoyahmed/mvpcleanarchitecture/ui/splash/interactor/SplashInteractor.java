
package com.example.redoyahmed.mvpcleanarchitecture.ui.splash.interactor;

import android.content.Context;

import com.example.redoyahmed.mvpcleanarchitecture.data.db.model.Option;
import com.example.redoyahmed.mvpcleanarchitecture.data.db.model.Question;
import com.example.redoyahmed.mvpcleanarchitecture.data.db.repository.OptionRepository;
import com.example.redoyahmed.mvpcleanarchitecture.data.db.repository.QuestionRepository;
import com.example.redoyahmed.mvpcleanarchitecture.data.network.ApiHelper;
import com.example.redoyahmed.mvpcleanarchitecture.data.prefs.PreferencesHelper;
import com.example.redoyahmed.mvpcleanarchitecture.di.ApplicationContext;
import com.example.redoyahmed.mvpcleanarchitecture.ui.base.BaseInteractor;
import com.example.redoyahmed.mvpcleanarchitecture.utils.AppConstants;
import com.example.redoyahmed.mvpcleanarchitecture.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;


public class SplashInteractor extends BaseInteractor implements SplashMvpInteractor {

    private QuestionRepository mQuestionRepository;
    private OptionRepository mOptionRepository;
    private Context mContext;

    @Inject
    public SplashInteractor(@ApplicationContext Context context, PreferencesHelper preferencesHelper, ApiHelper apiHelper, QuestionRepository questionRepository, OptionRepository optionRepository) {
        super(preferencesHelper, apiHelper);
        mContext = context;
        mQuestionRepository = questionRepository;
        mOptionRepository = optionRepository;
    }

    @Override
    public Observable<Boolean> seedDatabaseQuestions() {

        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        return mQuestionRepository.isQuestionEmpty()
                .concatMap(new Function<Boolean, ObservableSource<? extends Boolean>>() {
                    @Override
                    public ObservableSource<? extends Boolean> apply(Boolean isEmpty)
                            throws Exception {
                        if (isEmpty) {
                            Type type = $Gson$Types.newParameterizedTypeWithOwner(
                                    null,
                                    List.class,
                                    Question.class);
                            List<Question> questionList = gson.fromJson(
                                    FileUtils.loadJSONFromAsset(
                                            mContext,
                                            AppConstants.SEED_DATABASE_QUESTIONS),
                                    type);

                            return mQuestionRepository.saveQuestionList(questionList);
                        }
                        return Observable.just(false);
                    }
                });
    }

    @Override
    public Observable<Boolean> seedDatabaseOptions() {

        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();

        return mOptionRepository.isOptionEmpty()
                .concatMap(new Function<Boolean, ObservableSource<? extends Boolean>>() {
                    @Override
                    public ObservableSource<? extends Boolean> apply(Boolean isEmpty)
                            throws Exception {
                        if (isEmpty) {
                            Type type = new TypeToken<List<Option>>() {
                            }.getType();
                            List<Option> optionList = gson.fromJson(FileUtils.loadJSONFromAsset(mContext, AppConstants.SEED_DATABASE_OPTIONS), type);
                            return mOptionRepository.saveOptionList(optionList);
                        }
                        return Observable.just(false);
                    }
                });
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return getPreferencesHelper().getCurrentUserLoggedInMode();
    }
}
