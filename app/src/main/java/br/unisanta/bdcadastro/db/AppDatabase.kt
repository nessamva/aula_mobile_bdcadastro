package br.unisanta.bdcadastro.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.unisanta.bdcadastro.dao.UserDao
import br.unisanta.bdcadastro.model.User


@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_room.db"
            ).allowMainThreadQueries().build()
        }
    }
}