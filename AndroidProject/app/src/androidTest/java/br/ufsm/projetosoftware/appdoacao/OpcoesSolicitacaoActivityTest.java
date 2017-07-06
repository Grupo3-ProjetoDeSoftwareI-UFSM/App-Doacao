package br.ufsm.projetosoftware.appdoacao;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class OpcoesSolicitacaoActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void loginActivityTest255() {

        /**
         * Verifica os campos se estão ma posição certa
         */
        ViewInteraction button3 = onView(
                allOf(withId(R.id.btAvaliacoes),
                        childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1), isDisplayed()));


        ViewInteraction button4 = onView(allOf(withId(R.id.btChat),
                childAtPosition(childAtPosition(withId(android.R.id.content), 0), 2), isDisplayed()));

        ViewInteraction button5 = onView(allOf(withId(R.id.btConcluiDoacao),
                        childAtPosition(childAtPosition(withId(android.R.id.content), 0), 3),
                        isDisplayed()));

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.btChat), withText("iniciar chat"), isDisplayed()));

        ViewInteraction frameLayout6 = onView(
                allOf(childAtPosition(childAtPosition(
                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class), 0), 0), isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
