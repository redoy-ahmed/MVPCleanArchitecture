
package com.example.redoyahmed.mvpcleanarchitecture.data.db.repository;

import com.example.redoyahmed.mvpcleanarchitecture.data.db.model.DaoSession;
import com.example.redoyahmed.mvpcleanarchitecture.data.db.model.Question;
import com.example.redoyahmed.mvpcleanarchitecture.data.db.model.User;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UserRepository {

    private final DaoSession mDaoSession;

    @Inject
    public UserRepository(DaoSession daoSession) {
        mDaoSession = daoSession;
    }

    public Observable<Long> insertUser(final User user) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mDaoSession.getUserDao().insert(user);
            }
        });
    }

    public Observable<List<User>> getAllUsers() {
        return Observable.fromCallable(new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                return mDaoSession.getUserDao().loadAll();
            }
        });
    }

    public Observable<List<Question>> getAllQuestions() {
        return Observable.fromCallable(new Callable<List<Question>>() {
            @Override
            public List<Question> call() throws Exception {
                return mDaoSession.getQuestionDao().loadAll();
            }
        });
    }
}
