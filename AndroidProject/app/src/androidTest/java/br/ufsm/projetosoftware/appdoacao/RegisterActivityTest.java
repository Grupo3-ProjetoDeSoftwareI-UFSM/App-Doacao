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
public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    /**
     * Ao iniciar o app o usuário clica em ainda não tem conta
     */
    @Test
    public void loginActivityTest2() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.link_signup), withText("Ainda não tem uma conta? Cadastre-se"),
                        withParent(allOf(withId(R.id.email_login_form),
                                withParent(withId(R.id.login_form))))));
        appCompatTextView.perform(scrollTo(), click());

        /**
         * Preenche os dados verficando se eles estão corretos  e se existem
         */
        ViewInteraction appCompatEditText = onView(
                withId(R.id.input_nome));
        appCompatEditText.perform(scrollTo(), click());

        ViewInteraction appCompatEditText2 = onView(
                withId(R.id.input_nome));
        appCompatEditText2.perform(scrollTo(), replaceText("Michele Giane"), closeSoftKeyboard());

        ViewInteraction editText = onView(
                allOf(withId(R.id.input_nome), withText("Michele Giane"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        editText.check(matches(withText("Michele Giane")));


        ViewInteraction appCompatEditText3 = onView(
                withId(R.id.input_email));
        appCompatEditText3.perform(scrollTo(), click());

        ViewInteraction appCompatEditText4 = onView(
                withId(R.id.input_email));
        appCompatEditText4.perform(scrollTo(), replaceText("michelep@inf.ufsm.br"), closeSoftKeyboard());


        ViewInteraction appCompatEditText5 = onView(
                withId(R.id.input_cpfcnpj));
        appCompatEditText5.perform(scrollTo(), replaceText("00000000000"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.input_cpfcnpj), withText("00000000000"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        editText2.check(matches(withText("00000000000")));


        ViewInteraction appCompatEditText6 = onView(
                withId(R.id.input_senha));
        appCompatEditText6.perform(scrollTo(), replaceText("projeto2017"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                withId(R.id.input_cep));
        appCompatEditText7.perform(scrollTo(), replaceText("22060-001"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.input_cep), withText("22060-001")));
        appCompatEditText8.perform(scrollTo(), replaceText("22060-001"), closeSoftKeyboard());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.input_cep), withText("22060-001"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        editText3.check(matches(withText("22060-001")));

        /**
         * Em campos com auto preenchimento automático  sempre vai dar erro nos testes
         * pois não é possível definir um valor fixo para um campo, que irá preencher automaticamente
         */
        ViewInteraction appCompatEditText9 = onView(
                withId(R.id.input_endereco));
        appCompatEditText.perform(scrollTo(), click());
       /* ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.input_endereco), withText("Rua Dario ")));
        appCompatEditText9.perform(scrollTo(), replaceText("Rua Dario "), closeSoftKeyboard());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.input_endereco), withText("Rua Dario "),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        editText4.check(matches(withText("Rua Dario ")));
*/
        ViewInteraction appCompatEditText10 = onView(
                withId(R.id.input_endereconumero));
        appCompatEditText10.perform(scrollTo(), replaceText("111"), closeSoftKeyboard());

        ViewInteraction appCompatEditText11 = onView(
                withId(R.id.input_complemento));
        appCompatEditText11.perform(scrollTo(), replaceText("ap 70"), closeSoftKeyboard());

        ViewInteraction appCompatEditText12 = onView(
                withId(R.id.input_bairro));
        appCompatEditText.perform(scrollTo(), click());
/*
      ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.input_bairro), withText("Boi Morto")));
        appCompatEditText12.perform(scrollTo(), replaceText("Boi Morto"), closeSoftKeyboard());

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.input_bairro), withText("Boi Morto"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        editText5.check(matches(withText("Boi Morto")));
*/

        ViewInteraction appCompatEditText13 = onView(
                withId(R.id.input_cidade));
        appCompatEditText.perform(scrollTo(), click());
        /*
        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.input_cidade), withText("Santa Maria")));
        appCompatEditText13.perform(scrollTo(), replaceText("Santa Maria"), closeSoftKeyboard());

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.input_cidade), withText("Santa Maria"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        editText6.check(matches(withText("Santa Maria")));
*/
        ViewInteraction appCompatEditText14 = onView(
                withId(R.id.input_estado));
        appCompatEditText.perform(scrollTo(), click());
        /*

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.input_estado), withText("RS")));
        appCompatEditText14.perform(scrollTo(), replaceText("RS"), closeSoftKeyboard());

        ViewInteraction editText7 = onView(
                allOf(withId(R.id.input_estado), withText("RS"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        editText7.check(matches(withText("RS")));
*/
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button_createAccount), withText("Criar Conta")));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction button = onView(
                allOf(withId(R.id.button_createAccount),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                11),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

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
