package com.mdgroup.zendesktestapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.mdgroup.zendesktestapplication.Constants.TOKEN
import zendesk.android.Zendesk

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.loginButton).setOnClickListener {
            ZendeskChatHelper.login(TOKEN) {}
        }

        findViewById<Button>(R.id.openChatButton).setOnClickListener {
            Zendesk.instance.messaging.showMessaging(this)
        }
    }
}