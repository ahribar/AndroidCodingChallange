package si.development.ahill.beeniusdemo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.ortiz.touchview.TouchImageView
import org.hamcrest.CoreMatchers.allOf
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import si.development.ahill.beeniusdemo.presentation.albums.AlbumsAdapter
import si.development.ahill.beeniusdemo.presentation.main.MainActivity
import si.development.ahill.beeniusdemo.presentation.photos.PhotosAdapter
import si.development.ahill.beeniusdemo.presentation.users.UsersAdapter

/**
 * Instrumented test, which will execute on an Android device.
 * It uses [Thread.sleep] which, I know, is not optimal... But it's good enough for this test.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class FlowInstrumentedTest {

    @Rule
    @JvmField
    val rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun navigate_to_users() {
        Thread.sleep(200L)
        onView(allOf(withId(R.id.recyclerUsers), isDisplayed())).perform(
            actionOnItemAtPosition<UsersAdapter.UsersViewHolder>(
                1,
                click()
            )
        )
    }

    @Test
    fun navigate_to_album() {
        navigate_to_users()
        Thread.sleep(200L)
        onView(allOf(withId(R.id.recyclerAlbums), isDisplayed())).perform(
            actionOnItemAtPosition<AlbumsAdapter.AlbumsViewHolder>(
                1,
                click()
            )
        )
    }

    @Test
    fun navigate_to_photo() {
        navigate_to_album()
        Thread.sleep(5000L)
        onView(allOf(withId(R.id.recyclerPhotos), isDisplayed())).perform(
            actionOnItemAtPosition<PhotosAdapter.PhotosViewHolder>(
                0,
                click()
            )
        )
    }

    @Test
    fun check_if_details_are_present() {
        navigate_to_photo()
        onView(withId(R.id.image)).check { view, noViewFoundException ->
            noViewFoundException?.run { throw this }
            assertTrue(view is TouchImageView)
        }
    }
}