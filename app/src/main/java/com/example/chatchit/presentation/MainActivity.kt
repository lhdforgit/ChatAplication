package com.example.chatchit.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.chatchit.R
import com.example.chatchit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val roomId = "0813754950-0934497483"
    private var binding : ActivityMainBinding? = null

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        lifecycleScope.launch {
            viewModel.getMessage(roomId).observe(this@MainActivity){
                binding?.messageTv?.text = it ?: ""
            }
        }

        binding?.sendBt?.setOnClickListener {
            binding?.inputEdt?.text?.toString()?.let {
                lifecycleScope.launch {
                    viewModel.sendMessage(it, roomId)
                }
            }
        }
    }
}