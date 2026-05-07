package com.example.nfchostelentry

import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private var nfcAdapter: NfcAdapter? = null
    private lateinit var statusText: TextView
    private lateinit var nameText: TextView
    private lateinit var mainLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusText = findViewById(R.id.tvStatus)
        nameText = findViewById(R.id.tvName)
        mainLayout = findViewById(R.id.mainLayout)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        if (nfcAdapter == null) {
            Toast.makeText(this, R.string.nfc_not_supported, Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()

        val intent = Intent(this, javaClass).apply {
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_MUTABLE
        )

        nfcAdapter?.enableForegroundDispatch(this, pendingIntent, null, null)
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter?.disableForegroundDispatch(this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        @Suppress("DEPRECATION")
        val tag: Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)

        tag?.let {

            val uidBytes = it.id

            val uid = uidBytes.joinToString("") { byte ->
                String.format("%02X", byte)
            }

            val reversedUid = uidBytes.reversedArray().joinToString("") { byte ->
                String.format("%02X", byte)
            }

            statusText.text = getString(R.string.uid_format, uid)

            Toast.makeText(this, getString(R.string.uid_format, uid), Toast.LENGTH_LONG).show()
            Toast.makeText(this, getString(R.string.reversed_uid_format, reversedUid), Toast.LENGTH_LONG).show()

            checkUidInFirebase(uid)
        }
    }
    
    private fun checkUidInFirebase(uid: String) {

        val cleanedUid = uid.replace(":", "")

        Toast.makeText(this, getString(R.string.checking_uid, cleanedUid), Toast.LENGTH_LONG).show()

        val database = FirebaseDatabase.getInstance()
        val studentsRef = database.getReference("students")

        studentsRef.child(cleanedUid).get()
            .addOnSuccessListener { snapshot ->

                if (snapshot.exists()) {

                    Toast.makeText(this, R.string.student_found, Toast.LENGTH_LONG).show()

                    val status = snapshot.child("status").value.toString()
                    val name = snapshot.child("name").value.toString()

                    if (status == "active") {
                        statusText.text = getString(R.string.access_granted)
                        nameText.text = name
                        mainLayout.setBackgroundColor(Color.GREEN)
                    }

                } else {

                    Toast.makeText(this, R.string.student_not_found, Toast.LENGTH_LONG).show()

                    statusText.text = getString(R.string.access_denied)
                    mainLayout.setBackgroundColor(Color.RED)
                }

            }
            .addOnFailureListener {

                Toast.makeText(this, R.string.firebase_error, Toast.LENGTH_LONG).show()

                statusText.text = getString(R.string.database_error)
            }
    }
}