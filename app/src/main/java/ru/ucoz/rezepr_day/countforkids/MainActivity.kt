package ru.ucoz.rezepr_day.countforkids

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val screenSize:Int = resources.configuration.screenLayout ; Configuration.SCREENLAYOUT_SIZE_MASK
        if(screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE
            || screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE){
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }else{
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        btn_level_one.setOnClickListener { v ->
            val i = Intent(this, Level1Activity::class.java)
            startActivity(i)
        }
        btn_level_two.setOnClickListener { v ->
            val j = Intent(this, Level2Activity::class.java)
            startActivity(j)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_settings -> {
                val i = Intent(this, AboutProgramActivity::class.java)
                startActivity(i);
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}

