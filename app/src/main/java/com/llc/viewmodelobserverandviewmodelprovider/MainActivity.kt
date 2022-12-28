package com.llc.realtimechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.llc.viewmodelobserverandviewmodelprovider.MainViewModelFactory
import com.llc.viewmodelobserverandviewmodelprovider.R
import com.llc.viewmodelobserverandviewmodelprovider.databinding.ActivityMainBinding

//if you use observer, extend " Observer<List<Chat>> "
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val chatRecyclerViewAdapter = ChatRecyclerViewAdapter()

    private lateinit var auth: FirebaseAuth

    private val viewModelFactory by lazy {
        MainViewModelFactory()
    }

    //for observer
    /* private val viewModel:MainViewModel by lazy {
         MainViewModel()
     }*/

    //for factoroy
    private val viewModel:MainViewModel by lazy {
       viewModelFactory.create(MainViewModel()::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //for observer
        //viewModel.chatListObservable.attchObserver(this)

        //for factoroy
        viewModel.chatListLiveData.observe(this, Observer{chatList->
            chatRecyclerViewAdapter.submitList(chatList)
        })

        auth = Firebase.auth
        if (auth.currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.rvChat.apply {
            adapter = chatRecyclerViewAdapter
            layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        }

        binding.btnSend.setOnClickListener {
            val message = binding.etMessage.text.toString()
            viewModel.sendMessage(message)
        }
    }

    private fun logOut(){
        auth.signOut()
        val intent= Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId== R.id.action_logout){
            logOut()
        }
        return super.onOptionsItemSelected(item)
    }

    //for observer
  /*  override fun onDestroy() {
        viewModel.chatListObservable.detchObserver(this)
        super.onDestroy()
    }
*/
    //for observer
    //Every time a new value comes in, it will be submit on the adapter
   /* override fun observe(newValue: List<Chat>) {
        chatRecyclerViewAdapter.submitList(newValue)
    }*/
}