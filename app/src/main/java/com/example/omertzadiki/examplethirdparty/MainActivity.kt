package com.example.omertzadiki.examplethirdparty

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.example.omertzadiki.examplethirdparty.Constants.*
import com.sirinlabs.walletconnectionsdk.WalletCommunicationManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val COIN_TYPE: String = "ETH"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WalletCommunicationManager.addFinishInitListener {
            initButtons()
        }

        WalletCommunicationManager.init(applicationContext)
        setContentView(R.layout.activity_main)
    }

    fun initButtons() {
        send_transaction_btn.setOnClickListener {
            sendTransaction()
        }

        sign_transaction_btn.setOnClickListener {
            signTransaction()
        }

        get_address_btn.setOnClickListener {
            getAddress()
        }

        get_rpc_address_btn.setOnClickListener {
            getRpcAddress()
        }

        get_chain_id_btn.setOnClickListener {
            getChainId()
        }
    }

    fun sendTransaction() {
        WalletCommunicationManager.sendTransaction(successMethod = {
            Log.d("omer","got back to app with success flag")
        }, failureMethod = {
            Log.d("omer","got back to app with failure flag")
        })
    }

    fun signTransaction() {
        WalletCommunicationManager.sendTransaction(successMethod = {
            Log.d("omer","got back to app with success flag")
        }, failureMethod = {
            Log.d("omer","got back to app with failure flag")
        })
    }


    fun getAddress() {
        toastValue(WalletCommunicationManager.getPublicAddress(COIN_TYPE))
    }

    fun getChainId() {
        toastValue(WalletCommunicationManager.getChainId(COIN_TYPE).toString())
    }

    fun getRpcAddress() {
        toastValue(WalletCommunicationManager.getRpcAddress(COIN_TYPE))
    }

    fun toastValue(value : String) {
        Toast.makeText(applicationContext, value, Toast.LENGTH_LONG).show()
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
