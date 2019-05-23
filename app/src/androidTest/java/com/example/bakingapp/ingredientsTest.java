package com.example.bakingapp;




import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import com.example.bakingapp.UI.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;


@RunWith(AndroidJUnit4.class)
public class ingredientsTest {
    @Rule public ActivityTestRule<MainActivity> mainActivityActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void chckingifthefirstingredentisright(){
        Espresso.onView(ViewMatchers.withId(R.id.brownies))
                .perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.text)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }



}
