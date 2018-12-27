/*
 *
 *  * code:<br />Copyright (C) 2018 SIRIN LABS AG
 *
 */

package com.sirin.sdkexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.sirinlabs.sdkexample.R

import com.sirinlabs.walletconnectionsdk.WalletCommunicationManager
import com.sirinlabs.walletconnectionsdk.entities.SendRequestEntity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.experimental.and

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

        get_address_btn.setOnClickListener {
            getAddress()
        }

        get_rpc_address_btn.setOnClickListener {
            getRpcAddress()
        }

        get_chain_id_btn.setOnClickListener {
            getChainId()
        }

        get_walletid_btn.setOnClickListener {
            getWalletId()
        }

        get_publickey_btn.setOnClickListener {
            getPublicKey()
        }

        get_signed_message.setOnClickListener {
            getSignedText()
        }

        start_pairing_button.setOnClickListener {
            startPairing()
        }

        airdrop_button.setOnClickListener {
            airDrop()
        }
    }

    fun sendTransaction() {
        val data = SendRequestEntity(recipient = "tokenUpdateManager", amount = 5000000000000000.0, contractData = "5465d4s65465e4564e65e465d4",gasPrice = 60000000.0,gasLimit = 6000000000.0)
        WalletCommunicationManager.sendTransaction(data, successMethod = { hash ->
            toastValue("Transaction Succeed : $hash")
        }, failureMethod = {err ->
            toastValue("Transaction Failed : $err")
        })
    }

    fun startPairing() {
        WalletCommunicationManager.startSigningMessage(signed_message_txt.text.toString(),
                "lorem ipson lorem ipson lorem ipson lorem ipson lorem ipson lorem ipson lorem ipson lorem ipson ",
                successMethod = {signature ->
            toastValue("Transaction Succeed : ${byteArrayToHexString(signature)}")
        }, failureMethod = {err ->
            toastValue("Transaction Failed : $err")
        })
    }

    fun airDrop() {
        WalletCommunicationManager.airDrop("lorem ipson lorem ipson lorem ipson lorem ipson lorem ipson lorem ipson lorem ipson lorem ipson", successMethod = {signature ->
            toastValue("Transaction Succeed : ${byteArrayToHexString(signature)}")
            Log.d("omer", "airdrop_der = ${byteArrayToHexString(signature)}")
        }, failureMethod = {err ->
            toastValue("Transaction Failed : $err")
        })
    }


    private fun getAddress() {
        Log.d("omer", "address = ${WalletCommunicationManager.getPublicAddress(COIN_TYPE)}")
        toastValue(WalletCommunicationManager.getPublicAddress(COIN_TYPE))
    }

    fun byteArrayToHexString(bytes : ByteArray) : String{
        var hexString = ""
        bytes.map {
            hexString += String.format("%02x", it and 0xff.toByte())
        }
        return hexString
    }

    private fun getSignedText() {
        WalletCommunicationManager.startSigningMessage(signed_message_txt.text.toString(),
                "lorem ipson lorem ipson lorem ipson lorem ipson lorem ipson lorem ipson lorem ipson lorem ipson ",
                true, successMethod = {signature ->
            toastValue("Transaction Succeed : ${byteArrayToHexString(signature)}")
        }, failureMethod = {err ->
            toastValue("Transaction Failed : $err")
        })
    }

    private fun getChainId() {
        toastValue(WalletCommunicationManager.getChainId(COIN_TYPE).toString())
    }

    private fun getRpcAddress() {
        toastValue(WalletCommunicationManager.getRpcAddress(COIN_TYPE))
    }

    private fun getWalletId() {
        toastValue(WalletCommunicationManager.getWalletId())
    }

    private fun getPublicKey() {
        toastValue(WalletCommunicationManager.getPublicKey())
        Log.d("omer", "publickey ${WalletCommunicationManager.getPublicKey()}")

    }

    private fun toastValue(value : String) {
        runOnUiThread {
            Toast.makeText(this, value, Toast.LENGTH_LONG).show()
        }
    }

}
