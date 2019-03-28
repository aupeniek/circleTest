package com.mytaxi.android_demo.common;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.Rule;

import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class BaseInstrumentedTest {
    public String user;
    public String password;

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule(MainActivity.class);

    public void login() {
        try {
            RandomUserGenerator generator = new RandomUserGenerator();
            this.user = generator.getUsername();
            this.password = generator.getPassword();

            System.out.println("login in progress..  " + this.user +" : "+ this.password);

            onView(withId(R.id.edt_username))
                    .perform(typeText(this.user), closeSoftKeyboard());
            onView(withId(R.id.edt_password))
                    .perform(typeText(this.password), closeSoftKeyboard());
            onView(withId(R.id.btn_login))
                    .perform(click());

        } catch (NoMatchingViewException e) {
            System.out.println("Already logged in.");
        }


    }


}
