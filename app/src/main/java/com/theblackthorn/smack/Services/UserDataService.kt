package com.theblackthorn.smack.Services

import android.graphics.Color
import java.util.*

object UserDataService {

    var id =" "
    var avatarColour = ""
    var avatarName = ""
    var email = ""
    var name = ""

    fun logOut() {
        id = ""
        avatarColour = ""
        avatarName = ""
        email = ""
        name = ""
        AuthService.authToken = ""
        AuthService.userEmail = ""
        AuthService.isLoggedIn = false
    }

    fun returnAvatarColour(components: String) : Int {

        val strippedColour = components.replace("[", "")
            .replace("]", "")
            .replace(",", "")

        var r = 0
        var g = 0
        var b = 0

        val scanner = Scanner(strippedColour)
        if (scanner.hasNext()) {
            r = ((scanner.nextDouble()) * 255).toInt()
            g = ((scanner.nextDouble()) * 255).toInt()
            b = ((scanner.nextDouble()) * 255).toInt()
        }

        return Color.rgb(r,g,b)
    }
}