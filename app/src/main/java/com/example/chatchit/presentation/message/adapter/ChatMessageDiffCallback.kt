package com.example.chatchit.presentation.message.adapter

import android.text.TextUtils
import androidx.recyclerview.widget.DiffUtil
import com.example.chatchit.data.roomdb.entity.MessageEntity

class ChatMessageDiffCallback : DiffUtil.ItemCallback<MessageEntity>() {
    override fun areItemsTheSame(oldItem: MessageEntity, newItem: MessageEntity): Boolean {
        return TextUtils.equals(oldItem.id, newItem.id)
    }

    override fun areContentsTheSame(oldItem: MessageEntity, newItem: MessageEntity): Boolean {
        return TextUtils.equals(oldItem.id, newItem.id) ||
                TextUtils.equals(oldItem.message, newItem.message) ||
                oldItem.time == newItem.time
    }
}