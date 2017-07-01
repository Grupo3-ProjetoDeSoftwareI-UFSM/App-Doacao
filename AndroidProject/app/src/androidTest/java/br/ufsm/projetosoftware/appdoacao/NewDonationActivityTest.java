package br.ufsm.projetosoftware.appdoacao;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import br.ufsm.projetosoftware.appdoacao.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NewDonationActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);


    /**
     * Esse teste verfifca se os campos existem e se corresponde com que o usuário irá cadastar
     */
    @Test
    public void NewDonationActivityTest() {


        ViewInteraction appCompatButton2 = onView(
        allOf(withId(R.id.btNewDonation), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatSpinner = onView(
        allOf(withId(R.id.spType), isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction listView = onView(
        allOf(childAtPosition(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class), 0), isDisplayed()));
        listView.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
        allOf(withId(android.R.id.text1), withText("Material de contrução"),
        childAtPosition(
        childAtPosition(
        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class), 0), 0), isDisplayed()));
        textView.check(matches(withText("Material de contrução")));

        ViewInteraction appCompatTextView = onView(allOf(withId(android.R.id.text1), withText("Material de contrução"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
        allOf(withId(R.id.spCategory), isDisplayed()));
        appCompatSpinner2.perform(click());

        ViewInteraction listView2 = onView(
        allOf(childAtPosition(
        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
        0),
        isDisplayed()));
        listView2.check(matches(isDisplayed()));

        ViewInteraction appCompatTextView2 = onView(
        allOf(withId(android.R.id.text1), withText("Material elétrico"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatEditText2 = onView(
        allOf(withId(R.id.etTitle), isDisplayed()));
        appCompatEditText2.perform(click());

        ViewInteraction appCompatEditText3 = onView(
        allOf(withId(R.id.etTitle), isDisplayed()));
        appCompatEditText3.perform(replaceText("lâmpada"), closeSoftKeyboard());

        ViewInteraction editText = onView(
        allOf(withId(R.id.etTitle), withText("lâmpada"),
        childAtPosition(
        childAtPosition(
        withId(android.R.id.content), 0), 2), isDisplayed()));
        editText.check(matches(withText("lâmpada")));

        ViewInteraction appCompatEditText4 = onView(allOf(withId(R.id.etDescription), isDisplayed()));
        appCompatEditText4.perform(replaceText("uma lâmpada queimado"), closeSoftKeyboard());


      ViewInteraction appCompatButton3 = onView(allOf(withId(R.id.btSelectImage), withText("Selecionar foto"), isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction imageView = onView(allOf(withId(R.id.ivImage), childAtPosition(childAtPosition(withId(android.R.id.content),
                0),
            5),
         isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction appCompatButton4 = onView(allOf(withId(R.id.btRegisterDonation), withText("Cadastrar doação"), isDisplayed()));
        appCompatButton4.perform(click());

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
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
