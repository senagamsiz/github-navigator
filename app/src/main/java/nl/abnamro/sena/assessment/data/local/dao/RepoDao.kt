package nl.abnamro.sena.assessment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nl.abnamro.sena.assessment.data.model.Repo

@Dao
interface RepoDao {

    @Query("SELECT * FROM repo_table ORDER BY name")
    fun getAllReposByName(): Flow<List<Repo.RepoItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRepos(vararg repo: Repo)
}