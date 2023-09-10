package ii.kamenik.mvvm.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ii.kamenik.mvvm.dataclass.Subscriber
import kotlinx.coroutines.flow.Flow

@Dao
interface SubscriberDAO {
    //вставляем новую строку в таблицу бд
    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber) : Long

    //обновляем строку в таблице бд
    @Update
    suspend fun updateSubscriber(subscriber: Subscriber) : Int

    //удаляем строку из таблице бд
    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber) : Int

    //удаляем всё из таблице бд
    @Query("DELETE FROM sub_data_table")
    suspend fun deleteAll() : Int

    //получить всё в таблице бд
    @Query("SELECT * FROM sub_data_table")
    fun getAllSubscribers(): Flow<List<Subscriber>>
}