package ii.kamenik.mvvm.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ii.kamenik.mvvm.R
import ii.kamenik.mvvm.adapters.RecyclerViewAdapter
import ii.kamenik.mvvm.databinding.ActivityMainBinding
import ii.kamenik.mvvm.dataclass.Subscriber
import ii.kamenik.mvvm.db.SubscriberDB
import ii.kamenik.mvvm.repository.SubscriberRepository
import ii.kamenik.mvvm.viewModel.SubscriberViewModel
import ii.kamenik.mvvm.viewModel.SubscriberViewModelFactory

/**Класс View отвечает за обновление пользовательского интерфейса приложения. В соответствии с изменениями во всплывающем сообщении, предоставленном ViewModel, адаптер привязки активирует уровень представления. Установщик Toast-сообщения уведомит наблюдателя (View) об изменениях в данных. После этого View предпримет соответствующие действия.*/

class MainActivity : AppCompatActivity(

) {

    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = SubscriberDB.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this

        //сообщение об ошибках
        subscriberViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerViewAdapter({ selectedItem: Subscriber ->listItemClicked(selectedItem)})
        binding.subscriberRecyclerView.adapter = adapter
        displaySubscribersList()
    }

    private fun displaySubscribersList(){
        subscriberViewModel.getSavedSubscribers().observe(this, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(subscriber: Subscriber){
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }
}
