package com.example.inventaire

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            showHistory()
        }

        button2.setOnClickListener {
            showNewCommand()
        }

        button3.setOnClickListener {
            showStock()
        }
    }

    fun showHistory(){
        val intent = Intent(this, history::class.java)
        startActivity(intent)
    }

    fun showNewCommand(){
        val intent = Intent(this, NewCommand::class.java)
        startActivity(intent)
    }

    fun showStock(){
        val intent = Intent(this, Stock::class.java)
        startActivity(intent)
    }
}
