package com.example.im.service

import com.example.im.MainHandler
import com.example.im.model.MessageSending
import com.example.im.model.OnLogListener
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

class ImCenter(ip: String) {
    private var mClient: WebSocketClient? = null
    private var mLogListener: OnLogListener? = null

    init {
        val uri = URI.create(ip)
        try {
            mClient = object : WebSocketClient(uri) {
                override fun onOpen(serverHandshake: ServerHandshake) {
                    log(serverHandshake.toString())
                }

                override fun onMessage(s: String) {
                    log(s)
                }

                override fun onClose(i: Int, s: String, b: Boolean) {
                    log("$s|$i|$b")
                }

                override fun onError(e: Exception) {
                    log(e.message)
                }
            }
        } catch (ex: Exception) {
            log("Exception:" + ex.message)
        }

    }

    fun setLogListener(listener: OnLogListener) {
        mLogListener = listener
    }

    fun connect() {
        if (!mClient!!.isOpen) {
            mClient!!.connect()
        }
    }

    fun disconnect() {
        if (mClient!!.isOpen) {
            mClient!!.close()
        }
    }

    fun send(msg: MessageSending) {
        if (mClient!!.isOpen) {
            mClient!!.send(msg.toString())
        }
    }

    private fun log(content: String?) {
        if (content != null && mLogListener != null) {
            MainHandler.main(Runnable { mLogListener!!.log(content) })
        }
    }
}
