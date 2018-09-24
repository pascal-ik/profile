package pamtech.com.firebase

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import pamtech.com.firebase.databinding.ItemMessagesBinding

class FireBaseAdapter(var chatMessage: ArrayList<Message> ): RecyclerView.Adapter<FireBaseAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemMessagesBinding>(LayoutInflater.from(parent.context), R.layout.item_messages, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return chatMessage.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var temp = chatMessage[position]
        holder.binding.apply {
            messageTextView.text = temp.text
            nameTextView.text = temp.name
        }
    }

    class ViewHolder(var binding: ItemMessagesBinding): RecyclerView.ViewHolder(binding.root)
}