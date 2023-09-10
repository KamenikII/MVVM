package ii.kamenik.mvvm.repository

import ii.kamenik.mvvm.dao.SubscriberDAO
import ii.kamenik.mvvm.dataclass.Subscriber

//Локальный источник данных
class SubscriberRepository(
    private val dao: SubscriberDAO,

) {
    val subscribers = dao.getAllSubscribers()

    suspend fun insert(subscriber: Subscriber): Long {
        return dao.insertSubscriber(subscriber)
    }

    suspend fun update(subscriber: Subscriber): Int {
        return dao.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber: Subscriber): Int {
        return dao.deleteSubscriber(subscriber)
    }

    suspend fun deleteAll(): Int {
        return dao.deleteAll()
    }
}