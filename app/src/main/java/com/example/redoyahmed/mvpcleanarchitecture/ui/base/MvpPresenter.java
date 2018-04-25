
package com.example.redoyahmed.mvpcleanarchitecture.ui.base;

import com.androidnetworking.error.ANError;

public interface MvpPresenter<V extends MvpView, I extends MvpInteractor> {

    void onAttach(V mvpView);

    void onDetach();

    V getMvpView();

    I getInteractor();

    boolean isViewAttached();

    void checkViewAttached() throws BasePresenter.MvpViewNotAttachedException;

    void handleApiError(ANError error);

    void setUserAsLoggedOut();
}
