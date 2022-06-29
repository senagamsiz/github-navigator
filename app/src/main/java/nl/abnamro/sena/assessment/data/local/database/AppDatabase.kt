package nl.abnamro.sena.assessment.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import nl.abnamro.sena.assessment.data.local.dao.RemoteKeysDao
import nl.abnamro.sena.assessment.data.local.dao.RepoDao
import nl.abnamro.sena.assessment.data.local.model.RemoteKeys
import nl.abnamro.sena.assessment.data.local.model.RepoItem

@Database(entities = [RepoItem::class, RemoteKeys::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}