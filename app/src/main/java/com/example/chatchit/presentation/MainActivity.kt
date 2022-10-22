package com.example.chatchit.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.chatchit.R
import com.example.chatchit.common.notNull
import com.example.chatchit.databinding.ActivityMainBinding
import com.example.chatchit.databinding.VolumeCustomBinding
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
        //startActivity(ChatMessageAct.getIntent(this, ""))
        //finish()
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
        var customToast = Toast(this@MainActivity)
        customToast.setGravity(Gravity.END, 0,1)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            val myToast = Toast(this)
            val myVolume = layoutInflater.inflate(R.layout.volume_custom, null)
            myToast.view = myVolume
            myToast.duration = Toast.LENGTH_LONG
            myToast.show()
            return true
        }
        else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
            Toast.makeText(this@MainActivity, "KEYCODE_VOLUME_UP", Toast.LENGTH_SHORT).show()
            return true
        }

        return super.onKeyDown(keyCode, event)
    }




    override fun onKeyLongPress(keyCode: Int, event: KeyEvent?): Boolean {

        if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){

            return true
        }
        else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
            Toast.makeText(this@MainActivity, "onKeyLongPress KEYCODE_VOLUME_UP", Toast.LENGTH_SHORT).show()
            return true
        }

        return super.onKeyLongPress(keyCode, event)
    }
}