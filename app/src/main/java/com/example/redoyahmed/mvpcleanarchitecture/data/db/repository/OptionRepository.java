
package com.example.redoyahmed.mvpcleanarchitecture.data.db.repository;

import com.example.redoyahmed.mvpcleanarchitecture.data.db.model.DaoSession;
import com.example.redoyahmed.mvpcleanarchitecture.data.db.model.Option;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;

public class OptionRepository {

    private final DaoSession mDaoSession;

    @Inject
    public OptionRepository(DaoSession daoSession) {
        mDaoSession = daoSession;
    }

    public Observable<Boolean> isOptionEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !(mDaoSession.getOptionDao().count() > 0);
            }
        });
    }

    public Observable<Boolean> saveOption(final Option option) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getOptionDao().insertInTx(option);
                return true;
            }
        });
    }

    public Observable<Boolean> saveOptionList(final List<Option> optionList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getOptionDao().insertInTx(optionList);
                return true;
            }
        });
    }
}
