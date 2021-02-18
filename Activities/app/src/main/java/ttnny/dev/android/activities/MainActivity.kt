package ttnny.dev.android.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    companion object {
        private const val FIRST_ACTIVITY_REQUEST_CODE = 1

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1: Button = findViewById(R.id.btn_launch_activity_first)
        val tv1: TextView = findViewById(R.id.tv_first_activity_result)

        val button2 = findViewById<Button>(R.id.btn_launch_activity_second)
        val tv2 = findViewById<TextView>(R.id.tv_second_activity_result)

        button1.setOnClickListener {
            val intent = Intent(this, FirstActivity::class.java)
            startActivityForResult(intent, FIRST_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == FIRST_ACTIVITY_REQUEST_CODE) {

            }
        }
    }

}