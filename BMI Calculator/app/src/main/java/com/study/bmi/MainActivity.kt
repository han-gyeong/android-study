package com.study.bmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heightEditText: EditText = findViewById(R.id.heightEditText)
        val weightEditText: EditText = findViewById(R.id.weightEditText);

        val resultButton: Button = findViewById(R.id.resultButton);

        resultButton.setOnClickListener {
            Log.d("MainActivity", "ResultButton 이 클릭되었습니다.")

            if (heightEditText.text.isEmpty() || weightEditText.text.isEmpty()) {
                Toast.makeText(this, "빈 값이 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 이 아래로는 절대 빈 값이 올수 없음.

            val height : Int = heightEditText.text.toString().toInt()
            val weight : Int = weightEditText.text.toString().toInt()

            // 어디서, 어디로 가는지를 인자로 받는다.
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("height", height);
            intent.putExtra("weight", weight);

            startActivity(intent)

//            Log.d("MainActivity", "height: $height weight: $weight")
        }
    }
}