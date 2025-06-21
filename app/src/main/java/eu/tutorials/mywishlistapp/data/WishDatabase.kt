package eu.tutorials.mywishlistapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

/*
You always need to update version of the database whenever you change anything in the entity class and then uninstall the app and install it again else it will give IllegalStateException error.
 */
@Database(
    entities = [Wish::class],
    version = 2,
    exportSchema = false
)
abstract class WishDatabase : RoomDatabase() {
    abstract fun wishDao(): WishDao
}