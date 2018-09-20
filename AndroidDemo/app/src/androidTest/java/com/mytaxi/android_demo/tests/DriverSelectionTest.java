package com.mytaxi.android_demo.tests;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.RootMatchers;
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

import static com.mytaxi.android_demo.generics.utils.MyTaxiConstants.MYTAXI_DRIVER_TO_SELECT;
import static com.mytaxi.android_demo.generics.utils.MyTaxiConstants.MYTAXI_PASSWORD;
import static com.mytaxi.android_demo.generics.utils.MyTaxiConstants.MYTAXI_USERNAME;


@RunWith(AndroidJUnit4.class)
public class DriverSelectionTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(LoginIdleManager.getIdlingResource());
    }

    @Test
    public void selectSecondResult() {
        Log.println(Log.INFO,"@Test","Performing login success test");
        Espresso.onView(ViewMatchers.withId(R.id.edt_username)).perform(ViewActions.typeText(MYTAXI_USERNAME));
        Espresso.onView(ViewMatchers.withId(R.id.edt_password)).perform(ViewActions.typeText(MYTAXI_PASSWORD));
        Espresso.onView(ViewMatchers.withId(R.id.btn_login)).perform(ViewActions.click());

        Log.println(Log.INFO,"@Test","Search driver sa");
        Espresso.onView(ViewMatchers.withId(R.id.textSearch)).perform(ViewActions.typeText("sa"));

        Log.println(Log.INFO,"@Test","Select second driver from the list by name");
        Espresso.onView(ViewMatchers.withText(MYTAXI_DRIVER_TO_SELECT))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(ViewActions.click());
        Log.println(Log.INFO,"@Test","Call driver selected");
        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click());
    }
}
