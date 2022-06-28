package nl.abnamro.sena.assessment.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nl.abnamro.sena.assessment.data.local.model.RepoItem

@Dao
interface RepoDao {

    @Query("SELECT * FROM RepoItem")
    fun getAllRepos(): PagingSource<Int, RepoItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRepos(vararg repo: RepoItem)

    @Query("SELECT * FROM RepoItem WHERE id=:repoId")
    fun getRepoById(repoId: Int): Flow<RepoItem>

    @Query("DELETE FROM repoitem")
    suspend fun clearRepos()
}