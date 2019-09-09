package org.d3ifcool.justjava;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void incrementButtonClicked_QuantityChanged() {
        onView(withId(R.id.increment_button)).perform(click());
        onView(withId(R.id.quantity_textview)).check(matches(withText("3")));
    }

    @Test
    public void decrementButtonClicked_QuantityChanged() {
        onView(withId(R.id.decrement_button)).perform(click());
        onView(withId(R.id.quantity_textview)).check(matches(withText("1")));
    }

}
