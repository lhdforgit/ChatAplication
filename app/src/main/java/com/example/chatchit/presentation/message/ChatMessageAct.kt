package com.example.chatchit.presentation.message

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatchit.R
import com.example.chatchit.base.AbsActivity
import com.example.chatchit.common.notNull
import com.example.chatchit.data.roomdb.entity.MessageEntity
import com.example.chatchit.databinding.ChatMessageActBinding
import com.example.chatchit.presentation.message.adapter.ChatMessageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatMessageAct : AbsActivity() {
    private val roomId = "0813754950-0934497483"

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
        adapter = ChatMessageAdapter()
        binding.messageRec.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.messageRec.setHasFixedSize(true)
        binding.messageRec.adapter = adapter

        initObserver()
        initActionView()
    }

    private fun initActionView() {
        binding.sendMessageBtn.setOnClickListener {
            binding.inputMessageEdt.text?.toString()?.let { msg ->
                lifecycleScope.launch {
                    viewModel.sendMessage(msg, roomId)
                    binding.inputMessageEdt.setText("")
                }
            }
        }
    }

    private fun initObserver() {

        lifecycleScope.launchWhenStarted {
            viewModel.getMessageList().observe(this@ChatMessageAct){
                adapter?.setMessage(it)
            }

            viewModel.getMessage(roomId).observe(this@ChatMessageAct) {
                val msg = MessageEntity(it)
                lifecycleScope.launch {
                    viewModel.insertMessage(msg)
                }
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