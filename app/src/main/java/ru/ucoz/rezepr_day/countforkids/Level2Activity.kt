package ru.ucoz.rezepr_day.countforkids

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_level1.*
import java.util.*

class Level2Activity : AppCompatActivity() {

    var count_progress:Int = 0//Отсчет игры - счетчик игры
    val process_max_count: Int = 10//Максимальное количество ходов

    var max_number : Int = 10
    var rand = Random()

    var answer : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level2)

        val screenSize:Int = resources.configuration.screenLayout ; Configuration.SCREENLAYOUT_SIZE_MASK
        if(screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE
            || screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE){
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }else{
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        //Установить заголовок в ActionBar
        val actionbar = supportActionBar
        actionbar!!.setTitle("Level 2")

        //Начать игру
        game()

        //При изменении текста в EditText
        edit_text_answer.addTextChangedListener(textWatcher)

        //При нажатии кнопки
        btn_play.setOnClickListener { v ->
            if(edit_text_answer.text.toString() == answer)
            {
                edit_text_answer.setText("")
                if(count_progress < process_max_count - 1) {
                    count_progress++
                    game()
                    progress_bar.progress = count_progress

                } else {
                    count_progress++
                    progress_bar.progress = count_progress
                    win_game()
                }
            }else{
                lose_game()
            }
        }

        btn_restart.setOnClickListener { v ->
            edit_text_answer.setText("")
            edit_text_answer.isEnabled = true
            count_progress = 0
            game()
        }
    }

    private fun lose_game() {
        btn_restart.isEnabled = true
        btn_restart.background = resources.getDrawable(R.drawable.back)
        tw_count.setText(R.string.message_lose)
        edit_text_answer.setText(answer)
        btn_play.isEnabled = false
        btn_play.background = resources.getDrawable(R.drawable.back_false)
        edit_text_answer.isEnabled = false
    }

    private fun game() {
        //Делаем недоступным кнопку Проверить
        btn_play.isEnabled = false
        btn_play.background = resources.getDrawable(R.drawable.back_false)
        //Отключить кнопку рестарта
        btn_restart.isEnabled = false
        btn_restart.background = resources.getDrawable(R.drawable.back_false)
        //Приравниваем прогресс бару счетчика игры
        progress_bar.progress = count_progress

        var number_a: Int = rand.nextInt(max_number + 1)
        var number_b: Int = rand.nextInt(max_number + 1)

        //Чтобы не было отрицательных ответов
        if (number_a < number_b) {
            val x = number_a
            number_a = number_b
            number_b = x
        }

        //Показ примера в TextView
        tw_count.text = number_a.toString() + " - " + number_b.toString()
        //Вычисление правильного ответа
        answer = (number_a - number_b).toString()
    }

    private fun win_game() {
        btn_play.isEnabled = false;
        edit_text_answer.isEnabled = false;
        btn_play.background = resources.getDrawable(R.drawable.back_false)
        tw_count.setText(R.string.message_win)
    }

    //Метод проверки измеений в EditText
    private val textWatcher = object : TextWatcher {

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            //after text changed
            btn_play.isEnabled = true
            btn_play.background = resources.getDrawable(R.drawable.back)
            //Toast.makeText(Level1Activity,"change text", Toast.LENGTH_SHORT).show()
        }

        override fun beforeTextChanged(
            s: CharSequence, start: Int, count: Int,
            after: Int
        ) {
        }

        override fun afterTextChanged(s: Editable) {

        }
    }
}