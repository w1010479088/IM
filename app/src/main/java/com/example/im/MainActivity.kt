package com.example.im

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.im.model.MessageSending
import com.example.im.model.OnLogListener
import com.example.im.service.ImCenter
import com.example.im.widget.ViewHelper

class MainActivity : AppCompatActivity() {
    private val IP = "wss://50.lrlz.com:8080"
    private val mBuilder = StringBuilder()
    private var mHelper: ViewHelper? = null

    private val contentView: View
        get() = this.window.decorView.findViewById(android.R.id.content)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val im = ImCenter(IP)
        im.setLogListener(object : OnLogListener {
            override fun log(content: String) {
                logContent(content)
            }
        })
        mHelper = ViewHelper(this, contentView)
        mHelper!!.setClick(R.id.disconnect) { _ -> im.disconnect() }
        mHelper!!.setClick(R.id.connect) { _ -> im.connect() }
        mHelper!!.setClick(R.id.clear) { _ -> clear() }
        mHelper!!.setClick(R.id.send) { _ -> im.send(MessageSending("测试一下!")) }
        logContent("大俊子来了!")
    }

    private fun logContent(content: String) {
        mBuilder.append(content + "\n")
        mHelper!!.setText(R.id.log_content, mBuilder.toString())
    }

    private fun clear() {
        mBuilder.delete(0, mBuilder.length)
        logContent("已清除完毕!")
    }
}
