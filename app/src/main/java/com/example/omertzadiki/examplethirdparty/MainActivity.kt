package com.example.omertzadiki.examplethirdparty

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.sirinlabs.walletconnectionsdk.WalletCommunicationManager
import com.sirinlabs.walletconnectionsdk.entities.SendRequestEntity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val COIN_TYPE: String = "ETH"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WalletCommunicationManager.addFinishInitListener {
            initButtons()
        }

        WalletCommunicationManager.init(applicationContext)
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
        val data = SendRequestEntity(recipient = "0x008023500DfB949b8854C329C6237bFC3c060Fd6", amount = 0.001)
        WalletCommunicationManager.sendTransaction(data, successMethod = { hash ->
            toastValue("Transaction Succeed : $hash")
        }, failureMethod = {err ->
            toastValue("Transaction Failed : $err")
        })
    }

    fun signTransaction() {
        val data = SendRequestEntity(recipient = "0x008023500DfB949b8854C329C6237bFC3c060Fd6", amount = 0.001)
        WalletCommunicationManager.sendTransaction(data, successMethod = {hash ->
            toastValue("Transaction Succeed : $hash")
        }, failureMethod = {err ->
            toastValue("Transaction Failed : $err")
        })
    }


    private fun getAddress() {
        toastValue(WalletCommunicationManager.getPublicAddress(COIN_TYPE))
    }

    private fun getChainId() {
        toastValue(WalletCommunicationManager.getChainId(COIN_TYPE).toString())
    }

    private fun getRpcAddress() {
        toastValue(WalletCommunicationManager.getRpcAddress(COIN_TYPE))
    }

    private fun toastValue(value : String) {
        runOnUiThread {
            Toast.makeText(this, value, Toast.LENGTH_LONG).show()
        }
    }

}
