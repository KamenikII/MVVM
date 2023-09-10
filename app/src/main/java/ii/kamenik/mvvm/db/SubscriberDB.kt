package ii.kamenik.mvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ii.kamenik.mvvm.dao.SubscriberDAO
import ii.kamenik.mvvm.dataclass.Subscriber

@Database(entities = [Subscriber::class], version = 1)
abstract class SubscriberDB: RoomDatabase() {
    abstract val subscriberDAO: SubscriberDAO

    companion object {
        //Пример БД
        @Volatile
        private var INSTANCE: SubscriberDB ?= null
        fun getInstance(context: Context): SubscriberDB{
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscriberDB::class.java,
                        "subscriber_data_database"
                    ).build()
                }
                return instance
            }
        }
    }
}