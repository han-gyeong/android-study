package com.study.secretdiary

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    private val numberPicker1: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker1)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }

    private val numberPicker2: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker2)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }

    private val numberPicker3: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.numberPicker3)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }

    private val openButton: AppCompatButton by lazy {
        findViewById(R.id.openButton)
    }

    private val changePasswordButton: AppCompatButton by lazy {
        findViewById(R.id.changePassword)
    }

    private var changePasswordMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // NumberPicker 를 써야 apply 가 이뤄지는데 실질적으로 쓰진 않으니 호출해주기
        numberPicker1
        numberPicker2
        numberPicker3

        openButton.setOnClickListener {
            if (changePasswordMode) {
                Toast.makeText(this, "비밀번호 변경 중입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Context.MODE_PRIVATE -> 다른앱이 아니라 내 앱에서만 사용하도록
            val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUser =
                "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if (passwordPreferences.getString("password", "000").equals(passwordFromUser)) {
                // 로그인 성공
                startActivity(Intent(this, DiaryActivity::class.java))
            } else {
                // 실패
                showErrorAlertDialog()
            }
        }

        changePasswordButton.setOnClickListener {
            val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUser =
                "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"
            if (changePasswordMode) {
                // 바꾸기 버튼 상태에서 한번 더 누르면 -> 저장하기
//                    passwordPreferences.edit {
//                        putString(
//                            "password",
//                            "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"
//                        )
//                        // 저장 방법은 commit / apply 두가지, commit 은 동기적 apply는 비동기적으로 작동
//                        commit()
//                    }
                passwordPreferences.edit(true) {
                    putString("password", passwordFromUser)
                }

                changePasswordButton.setBackgroundColor(Color.BLACK)
                Toast.makeText(this, "패스워드 변경이 완료되었습니다!", Toast.LENGTH_SHORT).show()
                changePasswordMode = false
            } else {
                // 비밀번호가 맞는지 확인해서 맞으면 비밀번호 변경 모드로 진입
                if (passwordPreferences.getString("password", "000").equals(passwordFromUser)) {
                    changePasswordMode = true
                    Toast.makeText(this, "변경할 패스워드를 입력해주세요.", Toast.LENGTH_SHORT).show()
                    changePasswordButton.setBackgroundColor(Color.RED)
                } else {
                    // 실패
                    showErrorAlertDialog()
                }
            }
        }
    }

    private fun showErrorAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("실패!")
            .setMessage("비밀번호가 옳지 않습니다.")
            .setPositiveButton("확인") { _, _ -> }
            .create()
            .show()
    }
}