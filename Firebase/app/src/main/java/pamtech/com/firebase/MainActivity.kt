package pamtech.com.firebase

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.view.*
import pamtech.com.firebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var database: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    lateinit var binding: ActivityMainBinding
    lateinit var valueEventListener: ValueEventListener
    lateinit var firebaseLayoutManager: LinearLayoutManager
    lateinit var firebaseAdapter: FireBaseAdapter
    lateinit var firebaseAuth: FirebaseAuth


    val Tag = "MainActivity"
    var userName: String = Constants.ANONYMOUS
    var messages: ArrayList<Message> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initializeRecyclerViewAdapter()
        initializeFirebaseComponents()
        startFirebaseSignIn()
        addTextWatchers()
    }

    override fun onResume() {
        super.onResume()
        addReadListeners()
    }

    override fun onPause() {
        super.onPause()
        removeReadListeners()
    }

    fun sendMessage(view: View) {
        var message = Message(binding.messageEditText.text.toString(), userName)
        databaseReference.push().setValue(message)
        //Clear input box
        binding.messageEditText.setText("")
    }

    fun signOut(view: View) {
        AuthUI.getInstance().signOut(this)
                .addOnCompleteListener {
                    onSignedOutCleanup()
                }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.RC_SIGN_IN
                && resultCode == Activity.RESULT_OK) {
            firebaseAuth.currentUser?.displayName?.let { onSignedInInitialize(it) }
            Toast.makeText(this, "Signed In", Toast.LENGTH_SHORT).show()
        } else {
            var response = IdpResponse.fromResultIntent(data)
            Toast.makeText(this, response?.providerType, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initializeFirebaseComponents() {
        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference.child("messages")
        firebaseAuth = FirebaseAuth.getInstance()
    }

    private fun onSignedInInitialize(userName: String) {
        this.userName = userName
    }

    private fun onSignedOutCleanup() {
        userName = Constants.ANONYMOUS
        firebaseAdapter.clear()
        startFirebaseSignIn()
    }

    private fun startFirebaseSignIn() {
        startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(false)
                //list of sign in options
                .setAvailableProviders(listOf(AuthUI.IdpConfig.EmailBuilder().build(),
                        AuthUI.IdpConfig.GoogleBuilder().build(),
                        AuthUI.IdpConfig.FacebookBuilder().build()))
                .build(), Constants.RC_SIGN_IN)
    }

    private fun initializeRecyclerViewAdapter() {
        firebaseLayoutManager = LinearLayoutManager(this)
        firebaseAdapter = FireBaseAdapter(messages)
        binding.recyclerView.apply {
            adapter = firebaseAdapter
            layoutManager = firebaseLayoutManager
        }
    }

    private fun addReadListeners() {
        valueEventListener = object : ValueEventListener {

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("MainActivity", databaseError.message)
            }

            override fun onDataChange(dataSnapshots: DataSnapshot) {
                messages.clear()
                dataSnapshots.children.forEach { dataSnapshot ->
                    var message = dataSnapshot.getValue(Message::class.java)
                    message?.let {
                        messages.add(it)
                    }
                }
                firebaseAdapter.notifyDataSetChanged()
            }
        }
        databaseReference.addValueEventListener(valueEventListener)
    }

    private fun removeReadListeners() {
        databaseReference.removeEventListener(valueEventListener)
    }
    private fun addTextWatchers() {
        binding.messageEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(charSequence: CharSequence, p1: Int, p2: Int, p3: Int) {
                binding.sendButton.isEnabled = charSequence.toString().isNotBlank()
            }
        })
    }

    private fun FireBaseAdapter.clear() {
        chatMessage.clear()
        notifyDataSetChanged()
    }

    private fun Any?.isNotNull(): Boolean {
        return this != null
    }
}

