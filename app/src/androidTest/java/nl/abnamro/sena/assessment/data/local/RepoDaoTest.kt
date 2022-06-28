package nl.abnamro.sena.assessment.data.local

import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import nl.abnamro.sena.assessment.data.local.dao.RepoDao
import nl.abnamro.sena.assessment.data.local.database.AppDatabase
import nl.abnamro.sena.assessment.data.local.model.RepoItem
import org.junit.After
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest
import nl.abnamro.sena.assessment.getOrAwaitValue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@SmallTest
class RepoDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: RepoDao

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
    fun insertAllRepos() = runBlockingTest {
        val repoItem1 = RepoItem(1, "sena", "senagamsiz", "repo1", "url","public",false,"url2")
        dao.insertAllRepos(repoItem1)

        val allRepoItems = dao.getAllRepos().asLiveData().getOrAwaitValue()

        assertThat(allRepoItems).contains(repoItem1)
    }

}