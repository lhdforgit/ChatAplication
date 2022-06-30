package com.example.chatchit.presentation.message.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chatchit.R
import com.example.chatchit.data.roomdb.entity.MessageEntity
import com.example.chatchit.databinding.ChatMessageItemBinding

class ChatMessageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listMessage = mutableListOf<MessageEntity>()

    fun setMessage(message : MessageEntity){
        listMessage.add(message)
        notifyDataSetChanged()
    }

    fun setMessage(message :List<MessageEntity>){
        this.listMessage.clear()
        this.listMessage = message.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ChatMessageViewHolder.createHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ChatMessageViewHolder).bind(listMessage[position])
    }

    override fun getItemCount(): Int {
        return listMessage.size
    }
}

class ChatMessageViewHolder(private val binding: ChatMessageItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(message: MessageEntity) {
        binding.messageTv.text = message.message
        binding.executePendingBindings()
    }

    companion object {
        @JvmStatic
        fun createHolder(parent: ViewGroup): ChatMessageViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<ChatMessageItemBinding>(
                inflater,
                R.layout.chat_message_item,
                parent,
                false
            )
            return ChatMessageViewHolder(binding)
        }
    }
}