package com.mdgroup.zendesktestapplication

import android.app.Application
import com.mdgroup.zendesktestapplication.Constants.ZENDESK_CHANNEL_KEY
import timber.log.Timber
import zendesk.android.Zendesk
import zendesk.android.ZendeskUser
import zendesk.logger.Logger
import zendesk.messaging.BuildConfig
import zendesk.messaging.android.DefaultMessagingFactory

object ZendeskChatHelper {

    fun init(app: Application) {
        Timber.d("Zendesk: Initialize...")
        Zendesk.initialize(
            context = app,
            channelKey = ZENDESK_CHANNEL_KEY,
            successCallback = {
                Timber.d("Zendesk: Initialize chat success")
            },
            failureCallback = { error ->
                Timber.e("Zendesk: Initialize chat failure: ${error.message}")
            },
            messagingFactory = DefaultMessagingFactory()
        )
        Logger.setLoggable(BuildConfig.DEBUG)
    }

    fun login(jwtToken: String, callback: (user: ZendeskUser) -> Unit) {
        Zendesk.instance.loginUser(
            jwt = jwtToken,
            successCallback = { user ->
                Timber.d("Zendesk: Login success id: ${user.id}; externalId: ${user.externalId}")
                callback(user)
            },
            failureCallback = { error ->
                Timber.e("Zendesk: Error login chat by jwt: ${error.message}")
            }
        )
    }

    fun logout() {
        Zendesk.instance.logoutUser(
            successCallback = {
                Timber.d("Zendesk: User logout chat success")
            },
            failureCallback = { error ->
                Timber.e(error)
            }
        )
    }

}