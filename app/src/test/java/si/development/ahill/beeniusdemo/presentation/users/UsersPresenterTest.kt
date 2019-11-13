package si.development.ahill.beeniusdemo.presentation.users

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import si.development.ahill.beeniusdemo.dependency.DependencyProvider
import si.development.ahill.beeniusdemo.dependency.GlobalComponent
import si.development.ahill.beeniusdemo.domain.interactors.user.GetUsersInteractor
import si.development.ahill.beeniusdemo.domain.repositories.UserRepository

/**
 * Created by Andraž Hribar on 12. 11. 2019.
 * andraz.hribar@gmail.com
 */
@ExperimentalCoroutinesApi // Used for coroutines testing
class UsersPresenterTest {

    @get:Rule
    val coroutineRule = UsersPresenterTestCoroutineRule()

    private lateinit var presenter: UsersPresenter

    @Mock
    private lateinit var viewModel: UsersContract.ViewModel
    @Mock
    private lateinit var globalComponent: GlobalComponent
    @Mock
    private lateinit var userDataRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        DependencyProvider.globalComponent = globalComponent
        presenter = UsersPresenter()
        presenter.getUsersInteractor = GetUsersInteractor(userDataRepository)
        presenter.bindViewModel(viewModel)
    }

    @Test
    fun testFetchUsers() {
        coroutineRule.run {
            presenter.getUsersInteractor(null, coroutineScope, coroutineScope) {
                assert(it.isRight) // Check if the presenter gets a positive data set from the repo
            }
        }
    }

    class UsersPresenterTestCoroutineRule : TestRule {

        val coroutineDispatcher = TestCoroutineDispatcher()
        val coroutineScope = TestCoroutineScope(coroutineDispatcher)

        override fun apply(base: Statement, description: Description?) = object : Statement() {

            @Throws(Throwable::class)
            override fun evaluate() {
                Dispatchers.setMain(coroutineDispatcher)
                base.evaluate()
                Dispatchers.resetMain()
                coroutineScope.cleanupTestCoroutines()
            }
        }
    }
}