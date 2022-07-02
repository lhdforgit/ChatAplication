package com.example.chatchit.presentation.message.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.chatchit.R
import com.example.chatchit.common.NodeConstant
import com.example.chatchit.data.roomdb.entity.MessageEntity
import com.example.chatchit.databinding.ItemMessageImageComeInBinding
import com.example.chatchit.databinding.ItemMessageTextComeInBinding
import com.example.chatchit.databinding.ItemMessageTextComeOutBinding

class ChatMessageAdapter constructor(
    diffCallback: ChatMessageDiffCallback,
    private val requestManager: RequestManager
) :
    PagingDataAdapter<MessageEntity, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == R.layout.item_message_text_come_out) {
            return MessageTextOutViewHolder.createHolder(parent)
        } else if (viewType == R.layout.item_message_image_come_in) {
            return MessageImageOutViewHolder.createHolder(parent, requestManager)
        }
        return MessageTextInViewHolder.createHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MessageTextOutViewHolder -> holder.bind(getItem(position))
            is MessageTextInViewHolder -> holder.bind(getItem(position))
            is MessageImageOutViewHolder -> holder.bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        getItem(position)?.let {
            if (it.message.startsWith("https://")) {
                return R.layout.item_message_image_come_in
            }
            if (TextUtils.equals(
                    it.userId,
                    NodeConstant.userId
                )
            ) return R.layout.item_message_text_come_out
        }
        return R.layout.item_message_text_come_in
    }
}

class MessageTextOutViewHolder(private val binding: ItemMessageTextComeOutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(message: MessageEntity?) {
        binding.messageTv.text = message?.message ?: ""
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

    fun bind(message: MessageEntity?) {
        binding.messageTv.text = message?.message ?: ""
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

class MessageImageOutViewHolder(
    private val binding: ItemMessageImageComeInBinding,
    private val requestManager: RequestManager
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(message: MessageEntity?) {
        message?.message?.let { url ->
            requestManager.load(url)
                .override(1080,720)
                .fitCenter()
                .into(binding.messageImv)
        }
    }

    companion object {
        @JvmStatic
        fun createHolder(
            parent: ViewGroup,
            requestManager: RequestManager
        ): MessageImageOutViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<ItemMessageImageComeInBinding>(
                inflater,
                R.layout.item_message_image_come_in,
                parent,
                false
            )
            return MessageImageOutViewHolder(binding, requestManager)
        }
    }
}

