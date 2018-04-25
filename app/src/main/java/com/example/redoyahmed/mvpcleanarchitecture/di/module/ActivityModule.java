
package com.example.redoyahmed.mvpcleanarchitecture.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.redoyahmed.mvpcleanarchitecture.di.ActivityContext;
import com.example.redoyahmed.mvpcleanarchitecture.di.PerActivity;
import com.example.redoyahmed.mvpcleanarchitecture.ui.login.interactor.LoginInteractor;
import com.example.redoyahmed.mvpcleanarchitecture.ui.login.interactor.LoginMvpInteractor;
import com.example.redoyahmed.mvpcleanarchitecture.ui.login.presenter.LoginMvpPresenter;
import com.example.redoyahmed.mvpcleanarchitecture.ui.login.presenter.LoginPresenter;
import com.example.redoyahmed.mvpcleanarchitecture.ui.login.view.LoginMvpView;
import com.example.redoyahmed.mvpcleanarchitecture.ui.main.interactor.MainInteractor;
import com.example.redoyahmed.mvpcleanarchitecture.ui.main.interactor.MainMvpInteractor;
import com.example.redoyahmed.mvpcleanarchitecture.ui.main.presenter.MainMvpPresenter;
import com.example.redoyahmed.mvpcleanarchitecture.ui.main.presenter.MainPresenter;
import com.example.redoyahmed.mvpcleanarchitecture.ui.main.view.MainMvpView;
import com.example.redoyahmed.mvpcleanarchitecture.ui.splash.interactor.SplashInteractor;
import com.example.redoyahmed.mvpcleanarchitecture.ui.splash.interactor.SplashMvpInteractor;
import com.example.redoyahmed.mvpcleanarchitecture.ui.splash.presenter.SplashMvpPresenter;
import com.example.redoyahmed.mvpcleanarchitecture.ui.splash.presenter.SplashPresenter;
import com.example.redoyahmed.mvpcleanarchitecture.ui.splash.view.SplashMvpView;
import com.example.redoyahmed.mvpcleanarchitecture.utils.rx.AppSchedulerProvider;
import com.example.redoyahmed.mvpcleanarchitecture.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView, SplashMvpInteractor> provideSplashPresenter(SplashPresenter<SplashMvpView, SplashMvpInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView, LoginMvpInteractor> provideLoginPresenter(LoginPresenter<LoginMvpView, LoginMvpInteractor> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView, MainMvpInteractor> provideMainPresenter(MainPresenter<MainMvpView, MainMvpInteractor> presenter) {
        return presenter;
    }

    /*@Provides
    AboutMvpPresenter<AboutMvpView, AboutMvpInteractor> provideAboutPresenter(AboutPresenter<AboutMvpView, AboutMvpInteractor> presenter) {
        return presenter;
    }

    @Provides
    RatingDialogMvpPresenter<RatingDialogMvpView, RatingDialogMvpInteractor> provideRateUsPresenter(RatingDialogPresenter<RatingDialogMvpView, RatingDialogMvpInteractor> presenter) {
        return presenter;
    }

    @Provides
    FeedMvpPresenter<FeedMvpView, FeedMvpInteractor> provideFeedPresenter(FeedPresenter<FeedMvpView, FeedMvpInteractor> presenter) {
        return presenter;
    }

    @Provides
    OpenSourceMvpPresenter<OpenSourceMvpView, OpenSourceMvpInteractor> provideOpenSourcePresenter(OpenSourcePresenter<OpenSourceMvpView, OpenSourceMvpInteractor> presenter) {
        return presenter;
    }

    @Provides
    BlogMvpPresenter<BlogMvpView, BlogMvpInteractor> provideBlogMvpPresenter(BlogPresenter<BlogMvpView, BlogMvpInteractor> presenter) {
        return presenter;
    }

    @Provides
    FeedPagerAdapter provideFeedPagerAdapter(AppCompatActivity activity) {
        return new FeedPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    OpenSourceAdapter provideOpenSourceAdapter() {
        return new OpenSourceAdapter(new ArrayList<OpenSourceResponse.Repo>());
    }

    @Provides
    BlogAdapter provideBlogAdapter() {
        return new BlogAdapter(new ArrayList<BlogResponse.Blog>());
    }*/

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    @PerActivity
    SplashMvpInteractor provideSplashMvpInteractor(SplashInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    LoginMvpInteractor provideLoginMvpInteractor(LoginInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    MainMvpInteractor provideMainMvpInteractor(MainInteractor interactor) {
        return interactor;
    }

   /* @Provides
    @PerActivity
    AboutMvpInteractor provideAboutMvpInteractor(AboutInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    RatingDialogMvpInteractor provideRatingDialogMvpInteractor(RatingDialogInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    FeedMvpInteractor provideFeedMvpInteractor(FeedInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    OpenSourceMvpInteractor provideOpenSourceMvpInteractor(OpenSourceInteractor interactor) {
        return interactor;
    }

    @Provides
    @PerActivity
    BlogMvpInteractor provideBlogMvpInteractor(BlogInteractor interactor) {
        return interactor;
    }*/
}
