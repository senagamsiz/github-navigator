package nl.abnamro.sena.assessment.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import nl.abnamro.sena.assessment.data.local.dao.RepoDao
import nl.abnamro.sena.assessment.data.local.database.AppDatabase
import nl.abnamro.sena.assessment.data.local.model.RepoItem
import nl.abnamro.sena.assessment.getOrAwaitValue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@SmallTest
class RepoDaoTest {


    private lateinit var database: AppDatabase
    private lateinit var dao: RepoDao

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.repoDao()
    }

    @After
    @Throws(IOException::class)
    fun teardown() {
        database.close()
    }

    @Test
    fun verifyClearRepos() = runBlocking{
        val repoItem1 = RepoItem(1, "sena", "senagamsiz", "repo1", "url","public",false,"url2")
        dao.insertAllRepos(repoItem1)
        val repoItem2 = RepoItem(2, "elcin", "elcingamsiz", "repo2", "url","public",false,"url3")
        dao.insertAllRepos(repoItem2)
        val repoItem3 = RepoItem(3, "elcinsena", "elcinsenagamsiz", "repo3", "url","public",false,"url4")
        dao.insertAllRepos(repoItem2)
        dao.clearRepos()
        val allRepoItems = dao.getRepoById(1).asLiveData().getOrAwaitValue()
        assertThat(allRepoItems).isNotEqualTo(repoItem3)
    }

    @Test
    fun verifyCheckId() = runBlocking{
        val repoItem1 = RepoItem(1, "sena", "senagamsiz", "repo1", "url","public",false,"url2")
        dao.insertAllRepos(repoItem1)
        val allRepoItems = dao.getRepoById(2).asLiveData().getOrAwaitValue()
        assertThat(allRepoItems).isNotEqualTo(repoItem1)
    }

    @Test
    fun verifyGetRepoById() = runBlocking {
        val repoItem1 = RepoItem(1, "sena", "senagamsiz", "repo1", "url","public",false,"url2")
        dao.insertAllRepos(repoItem1)

        val allRepoItems = dao.getRepoById(1).asLiveData().getOrAwaitValue()
        assertThat(allRepoItems).isEqualTo(repoItem1)
    }

}