package com.example.redoyahmed.mvpcleanarchitecture.ui.splash.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.redoyahmed.mvpcleanarchitecture.R;
import com.example.redoyahmed.mvpcleanarchitecture.service.SyncService;
import com.example.redoyahmed.mvpcleanarchitecture.ui.base.BaseActivity;
import com.example.redoyahmed.mvpcleanarchitecture.ui.login.view.LoginActivity;
import com.example.redoyahmed.mvpcleanarchitecture.ui.splash.interactor.SplashMvpInteractor;
import com.example.redoyahmed.mvpcleanarchitecture.ui.splash.presenter.SplashMvpPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements SplashMvpView {

    @Inject
    SplashMvpPresenter<SplashMvpView, SplashMvpInteractor> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(SplashActivity.this);
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openMainActivity() {
        //Intent intent = MainActivity.getStartIntent(SplashActivity.this);
        //startActivity(intent);
        //finish();
    }

    @Override
    public void startSyncService() {
        //SyncService.start(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }
}
