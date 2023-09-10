package ii.kamenik.mvvm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ii.kamenik.mvvm.repository.SubscriberRepository


/**Наш класс ViewModel имеет параметры конструктора. Следовательно, чтобы создать его экземпляр, мы должны получить поддержку фабричного класса.*/

class SubscriberViewModelFactory(
    private val repository: SubscriberRepository
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
    //fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SubscriberViewModel::class.java)){
            return SubscriberViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}