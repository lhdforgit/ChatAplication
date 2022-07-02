package com.example.chatchit.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.chatchit.R
import com.example.chatchit.common.notNull
import com.example.chatchit.databinding.ActivityMainBinding
import com.example.chatchit.presentation.message.ChatMessageAct
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val roomId = "0813754950-0934497483"
    private var binding by notNull<ActivityMainBinding>()
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        startActivity(ChatMessageAct.getIntent(this, ""))
        finish()
        //val fireBaseAuth = FirebaseAuth.getInstance()
        //val currentUser = fireBaseAuth.currentUser
//        if (currentUser == null){
//            fireBaseAuth.createUserWithEmailAndPassword("danglehai2411@gmail.com","12345678")
//                .addOnSuccessListener {
//                    Log.i("===============","addOnSuccessListener")
//                }
//                .addOnFailureListener {
//                    Log.i("===============","addOnFailureListener")
//                }
//        }else{
//            val email = currentUser.email
//            Log.i("===============","Email: $email")
//            Log.i("===============","tenantId: ${currentUser.}")
//        }
//        fireBaseAuth.signInWithEmailAndPassword("danglehai2411@gmail.com","12345678")
//            .addOnSuccessListener {
//                //it?.user?.uid
//                Log.i("===============","addOnSuccessListener: ${it?.user?.toString()}")
//            }
//            .addOnFailureListener {
//                Log.i("===============","addOnFailureListener")
//            }
    }
}