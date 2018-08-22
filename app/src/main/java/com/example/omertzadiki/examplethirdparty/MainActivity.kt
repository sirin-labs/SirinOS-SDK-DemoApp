package com.example.omertzadiki.examplethirdparty

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.example.omertzadiki.examplethirdparty.Constants.*
import com.sirinlabs.walletconnectionsdk.WalletCommunicationManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WalletCommunicationManager.addFinishInitListener {
            WalletCommunicationManager.sendTransaction(successMethod = {
                Log.d("omer","got back to app with success flag")
            }, failureMethod = {
                Log.d("omer","got back to app with failure flag")
            })
        }

        WalletCommunicationManager.init(applicationContext)
        setContentView(R.layout.activity_main)
    }

    // Catch result from send intent
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("omer", "result code : $resultCode")

        when (resultCode) {
            STATUS_BACK_FROM_SEND -> {
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }


}
