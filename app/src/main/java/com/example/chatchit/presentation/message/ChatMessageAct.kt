package com.example.chatchit.presentation.message

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.chatchit.R
import com.example.chatchit.base.AbsActivity
import com.example.chatchit.common.NodeConstant
import com.example.chatchit.common.notNull
import com.example.chatchit.data.roomdb.entity.MessageEntity
import com.example.chatchit.data.roomdb.entity.MessageUtil
import com.example.chatchit.databinding.ChatMessageActBinding
import com.example.chatchit.presentation.message.adapter.ChatMessageAdapter
import com.example.chatchit.presentation.message.adapter.ChatMessageDiffCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatMessageAct : AbsActivity() {

    private var binding by notNull<ChatMessageActBinding>()
    private val viewModel: ChatMessageViewModel by viewModels()
    private var adapter: ChatMessageAdapter? = null


    override fun initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.chat_message_act)
    }

    override fun initActionBar() {
        setSupportActionBar(binding.toolBar)
    }

    override fun initializeLayout() {
        val diffCallback = ChatMessageDiffCallback()
        adapter = ChatMessageAdapter(diffCallback, Glide.with(this))
        binding.messageRec.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        binding.messageRec.setHasFixedSize(true)
        binding.messageRec.adapter = adapter


        initActionView()
        initInsertMessage()
        initObserver()
    }

    private fun initInsertMessage() {
        lifecycleScope.launch {
            viewModel.getMessage(NodeConstant.roomId).observe(this@ChatMessageAct) {
                lifecycleScope.launch {
                    it?.let { messageEntity ->
                        viewModel.insertMessage(messageEntity)
                    }
                }
            }
        }
    }

    private fun initActionView() {
        binding.sendMessageBtn.setOnClickListener {
            binding.inputMessageEdt.text?.toString()?.let { msg ->
                lifecycleScope.launch {
                    val messageEntity = MessageUtil.createMessage(msg)
                    viewModel.sendMessage(messageEntity, NodeConstant.roomId)
                    binding.inputMessageEdt.setText("")
                }
            }
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.getMessagePaging(NodeConstant.roomId).collectLatest {
                adapter?.submitData(it)
            }
        }
    }

    companion object {
        private const val ROOM_ID = "room_id"

        @JvmStatic
        fun getIntent(context: Context, roomId: String): Intent {
            val intent = Intent(context, ChatMessageAct::class.java)
            intent.putExtra(ROOM_ID, roomId)
            return intent
        }
    }

}