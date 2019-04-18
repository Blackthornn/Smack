package com.theblackthorn.smack.Controller

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.theblackthorn.smack.R
import com.theblackthorn.smack.Services.AuthService
import com.theblackthorn.smack.Services.UserDataService
import kotlinx.android.synthetic.main.activity_create_user.*
import java.util.*

class CreateUserActivity : AppCompatActivity() {

    var userAvatar = "profileDefault"
    var avatarColour = "[0.5, 0.5, 0.5, 1]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
    }

    fun generateUserAvatar(view: View) {
        val random = Random()
        val colour = random. nextInt(2)
        val avatar = random.nextInt(28)

        if (colour == 0){
            userAvatar = "light$avatar"
        } else {
            userAvatar = "dark$avatar"
        }
        val resourceId = resources.getIdentifier(userAvatar, "drawable", packageName)
        createAvatarImageView.setImageResource(resourceId)
    }

    fun createUserClicked(view: View) {

        val userName = createUserNameText.text.toString()
        val email = createEmailText.text.toString()
        val password = createPasswordText.text.toString()

        AuthService.registerUser(this, email, password) {registerSuccess ->
            if (registerSuccess) {
                AuthService.loginUser(this, email, password) {loginSuccess ->
                    if(loginSuccess) {
                        AuthService.createUser(this, userName, email, userAvatar, avatarColour) {createSuccess ->
                            if(createSuccess) {
                                println(UserDataService.avatarName)
                                println(UserDataService.avatarColour)
                                println(UserDataService.name)
                                finish()
                            }
                        }
                    }
                }
            }

        }
    }

    fun generateColourClicked(view: View) {
        val random = Random()
        val r = random.nextInt(255)
        val g = random.nextInt(255)
        val b = random.nextInt(255)

        createAvatarImageView.setBackgroundColor(Color.rgb(r,g,b))

        val savedR = r.toDouble() / 255
        val savedG = g.toDouble() / 255
        val savedB = b.toDouble() / 255

        avatarColour = "[$savedR, $savedG, $savedB, 1]"
        println(avatarColour)
    }
}
