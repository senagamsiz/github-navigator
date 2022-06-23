package nl.abnamro.sena.assessment.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nl.abnamro.sena.assessment.data.local.dao.RepoDao
import nl.abnamro.sena.assessment.data.local.model.RepoItem

@Database(entities = [RepoItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private const val DATABASE_NAME = "repo_database"

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}