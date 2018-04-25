
package com.example.redoyahmed.mvpcleanarchitecture.di.component;

import com.example.redoyahmed.mvpcleanarchitecture.di.PerActivity;
import com.example.redoyahmed.mvpcleanarchitecture.di.module.ActivityModule;
import com.example.redoyahmed.mvpcleanarchitecture.ui.login.view.LoginActivity;
import com.example.redoyahmed.mvpcleanarchitecture.ui.main.view.MainActivity;
import com.example.redoyahmed.mvpcleanarchitecture.ui.splash.view.SplashActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(LoginActivity activity);

    void inject(SplashActivity activity);

    //void inject(FeedActivity activity);

    //void inject(AboutFragment fragment);

    //void inject(OpenSourceFragment fragment);

    //void inject(BlogFragment fragment);

    //void inject(RateUsDialog dialog);
}
