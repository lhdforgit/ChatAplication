package com.example.chatchit.presentation.message.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chatchit.R
import com.example.chatchit.common.NodeConstant
import com.example.chatchit.data.roomdb.entity.MessageEntity
import com.example.chatchit.databinding.ItemMessageTextComeInBinding
import com.example.chatchit.databinding.ItemMessageTextComeOutBinding

class ChatMessageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listMessage = mutableListOf<MessageEntity>()

    fun setMessage(message :List<MessageEntity>){
        this.listMessage.clear()
        this.listMessage = message.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == R.layout.item_message_text_come_out){
            return MessageTextOutViewHolder.createHolder(parent)
        }
        return MessageTextInViewHolder.createHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MessageTextOutViewHolder -> holder.bind(listMessage[position])
            is MessageTextInViewHolder -> holder.bind(listMessage[position])
        }
    }

    override fun getItemCount(): Int {
        return listMessage.size
    }

    override fun getItemViewType(position: Int): Int {
        listMessage[position].let {
            if (TextUtils.equals(it.userId, NodeConstant.userId)) return R.layout.item_message_text_come_out
        }
        return R.layout.item_message_text_come_in
    }
}

class MessageTextOutViewHolder(private val binding: ItemMessageTextComeOutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(message: MessageEntity) {
        binding.messageTv.text = message.message
        binding.executePendingBindings()
    }

    companion object {
        @JvmStatic
        fun createHolder(parent: ViewGroup): MessageTextOutViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<ItemMessageTextComeOutBinding>(
                inflater,
                R.layout.item_message_text_come_out,
                parent,
                false
            )
            return MessageTextOutViewHolder(binding)
        }
    }
}

class MessageTextInViewHolder(private val binding: ItemMessageTextComeInBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(message: MessageEntity) {
        binding.messageTv.text = message.message
        binding.executePendingBindings()
    }

    companion object {
        @JvmStatic
        fun createHolder(parent: ViewGroup): MessageTextInViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<ItemMessageTextComeInBinding>(
                inflater,
                R.layout.item_message_text_come_in,
                parent,
                false
            )
            return MessageTextInViewHolder(binding)
        }
    }
}