package ii.kamenik.mvvm.adapters

import androidx.recyclerview.widget.RecyclerView
import ii.kamenik.mvvm.databinding.ListItemBinding
import ii.kamenik.mvvm.dataclass.Subscriber

class ViewHolder (
    val binding: ListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(subscriber: Subscriber, clickListener: (Subscriber) -> Unit) {
        binding.nameTextView.text = subscriber.name
        binding.emailTextView.text = subscriber.email
        binding.listItemLayout.setOnClickListener {
            clickListener(subscriber)
        }
    }
}