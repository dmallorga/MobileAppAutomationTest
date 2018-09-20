package com.mytaxi.android_demo.tests;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.utils.idle_resources.LoginIdleManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.mytaxi.android_demo.core.Properties.MYTAXI_LOGOUT;
import static com.mytaxi.android_demo.core.Properties.MYTAXI_PASSWORD;
import static com.mytaxi.android_demo.core.Properties.MYTAXI_USERNAME;

@RunWith(AndroidJUnit4.class)
public class LoginTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(LoginIdleManager.getIdlingResource());
    }

    @Test
    public void loginSuccess(){
        Log.println(Log.INFO, "Test", "Performing login success test");
        Espresso.onView(ViewMatchers.withId(R.id.edt_username)).perform(ViewActions.typeText(MYTAXI_USERNAME));
        Espresso.onView(ViewMatchers.withId(R.id.edt_password)).perform(ViewActions.typeText(MYTAXI_PASSWORD));
        Espresso.onView(ViewMatchers.withId(R.id.btn_login)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Espresso.onView(ViewMatchers.withId(R.id.nav_username)).check(ViewAssertions.matches(ViewMatchers.withText(MYTAXI_USERNAME)));
        Espresso.onView(ViewMatchers.withText(MYTAXI_LOGOUT)).perform(ViewActions.click());
    }
}
