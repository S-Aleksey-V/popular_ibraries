package me.tolkstudio.popularlibraries.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import me.tolkstudio.popularlibraries.mvp.model.entity.room.RoomCachedImage
import me.tolkstudio.popularlibraries.mvp.model.entity.room.RoomGithubRepository
import me.tolkstudio.popularlibraries.mvp.model.entity.room.RoomGithubUser
import me.tolkstudio.popularlibraries.mvp.model.entity.room.dao.ImageDao
import me.tolkstudio.popularlibraries.mvp.model.entity.room.dao.RepositoryDao
import me.tolkstudio.popularlibraries.mvp.model.entity.room.dao.UserDao
import java.lang.IllegalStateException

@androidx.room.Database(
    entities = [
        RoomGithubUser::class,
        RoomGithubRepository::class ,
        RoomCachedImage::class
    ],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
    abstract val imageDao: ImageDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance ?: throw IllegalStateException("Database has not been created")

        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, Database::class.java, DB_NAME).build()
            }
        }
    }
}