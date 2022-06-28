package nl.abnamro.sena.assessment.ui.view.overview

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import nl.abnamro.sena.assessment.R
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OverviewFragmentTest {

    @Test
    fun test_navToDetailsScreen() {

        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        val reposOverviewScenario = launchFragmentInContainer<ReposOverviewFragment>()

        reposOverviewScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.navigation)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(ViewMatchers.withId(R.id.reposOverviewFragment)).perform(ViewActions.click())
        ViewMatchers.assertThat(
            navController.currentDestination?.id,
            CoreMatchers.`is`(R.id.action_reposOverviewFragment_to_detailsFragment))
    }

}




