
package com.example.redoyahmed.mvpcleanarchitecture.ui.main.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.example.redoyahmed.mvpcleanarchitecture.BuildConfig;
import com.example.redoyahmed.mvpcleanarchitecture.R;
import com.example.redoyahmed.mvpcleanarchitecture.data.db.model.Question;
import com.example.redoyahmed.mvpcleanarchitecture.ui.base.BaseActivity;
import com.example.redoyahmed.mvpcleanarchitecture.ui.custom.QuestionCard;
import com.example.redoyahmed.mvpcleanarchitecture.ui.custom.RoundedImageView;
import com.example.redoyahmed.mvpcleanarchitecture.ui.login.view.LoginActivity;
import com.example.redoyahmed.mvpcleanarchitecture.ui.main.interactor.MainMvpInteractor;
import com.example.redoyahmed.mvpcleanarchitecture.ui.main.presenter.MainMvpPresenter;
import com.example.redoyahmed.mvpcleanarchitecture.utils.ScreenUtils;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView, MainMvpInteractor> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_view)
    DrawerLayout mDrawer;

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    @BindView(R.id.tv_app_version)
    TextView mAppVersionTextView;

    @BindView(R.id.cards_container)
    SwipePlaceHolderView mCardsContainerView;

    private TextView mNameTextView;
    private TextView mEmailTextView;
    private RoundedImageView mProfileImageView;
    private ActionBarDrawerToggle mDrawerToggle;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    public void onBackPressed() {
        /*FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(AboutFragment.TAG);
        if (fragment == null) {
            super.onBackPressed();
        } else {
            onFragmentDetached(AboutFragment.TAG);
        }*/
    }

    @Override
    public void refreshQuestionnaire(List<Question> questionList) {
        for (Question question : questionList) {
            if (question != null
                    && question.getOptionList() != null
                    && question.getOptionList().size() == 3) {
                mCardsContainerView.addView(new QuestionCard(question));
            }
        }
    }

    @Override
    public void reloadQuestionnaire(List<Question> questionList) {
        refreshQuestionnaire(questionList);
        ScaleAnimation animation = new ScaleAnimation(1.15f, 1, 1.15f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        mCardsContainerView.setAnimation(animation);
        animation.setDuration(100);
        animation.start();
    }

    @Override
    public void updateAppVersion() {
        String version = getString(R.string.version) + " " + BuildConfig.VERSION_NAME;
        mAppVersionTextView.setText(version);
    }

    @Override
    public void updateUserName(String currentUserName) {
        mNameTextView.setText(currentUserName);
    }

    @Override
    public void updateUserEmail(String currentUserEmail) {
        mEmailTextView.setText(currentUserEmail);
    }

    @Override
    public void updateUserProfilePic(String currentUserProfilePicUrl) {
        //load profile pic url into ANImageView
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onFragmentAttached() {
    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
            unlockDrawer();
        }
    }

    @Override
    public void showAboutFragment() {
        lockDrawer();
        /*getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.cl_root_view, AboutFragment.newInstance(), AboutFragment.TAG)
                .commit();*/
    }

    @Override
    public void lockDrawer() {
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void unlockDrawer() {
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Drawable drawable = item.getIcon();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
        switch (item.getItemId()) {
            case R.id.action_cut:
                return true;
            case R.id.action_copy:
                return true;
            case R.id.action_share:
                return true;
            case R.id.action_delete:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                mToolbar,
                R.string.open_drawer,
                R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        setupNavMenu();
        mPresenter.onNavMenuCreated();
        setupCardContainerView();
        mPresenter.onViewInitialized();
    }

    private void setupCardContainerView() {

        int screenWidth = ScreenUtils.getScreenWidth(this);
        int screenHeight = ScreenUtils.getScreenHeight(this);

        mCardsContainerView.getBuilder()
                .setDisplayViewCount(3)
                .setHeightSwipeDistFactor(10)
                .setWidthSwipeDistFactor(5)
                .setSwipeDecor(new SwipeDecor()
                        .setViewWidth((int) (0.90 * screenWidth))
                        .setViewHeight((int) (0.75 * screenHeight))
                        .setPaddingTop(20)
                        .setSwipeRotationAngle(10)
                        .setRelativeScale(0.01f));

        mCardsContainerView.addItemRemoveListener(new ItemRemovedListener() {
            @Override
            public void onItemRemoved(int count) {
                if (count == 0) {
                    // reload the contents again after 1 sec delay
                    new Handler(getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mPresenter.onCardExhausted();
                        }
                    }, 800);
                }
            }
        });
    }

    void setupNavMenu() {
        View headerLayout = mNavigationView.getHeaderView(0);
        mProfileImageView = headerLayout.findViewById(R.id.iv_profile_pic);
        mNameTextView = headerLayout.findViewById(R.id.tv_name);
        mEmailTextView = headerLayout.findViewById(R.id.tv_email);

        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        mDrawer.closeDrawer(GravityCompat.START);
                        switch (item.getItemId()) {
                            case R.id.nav_item_about:
                                mPresenter.onDrawerOptionAboutClick();
                                return true;
                            case R.id.nav_item_rate_us:
                                //mPresenter.onDrawerRateUsClick();
                                return true;
                            case R.id.nav_item_feed:
                                //mPresenter.onDrawerMyFeedClick();
                                return true;
                            case R.id.nav_item_logout:
                                mPresenter.onDrawerOptionLogoutClick();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
    }

    @Override
    public void openLoginActivity() {
        startActivity(LoginActivity.getStartIntent(this));
        finish();
    }

    /*@Override
    public void showRateUsDialog() {
        RateUsDialog.newInstance().show(getSupportFragmentManager());
    }*/

    /*@Override
    public void openMyFeedActivity() {
        startActivity(FeedActivity.getStartIntent(this));
    }*/

    @Override
    public void closeNavigationDrawer() {
        if (mDrawer != null) {
            mDrawer.closeDrawer(Gravity.START);
        }
    }
}
