package ru.ucoz.rezepr_day.countforkids

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import kotlinx.android.synthetic.main.activity_about_program.*

class AboutProgramActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_program)

        //Добавление политики конфедициальности
        link_policy.movementMethod = LinkMovementMethod.getInstance()

    }
}
