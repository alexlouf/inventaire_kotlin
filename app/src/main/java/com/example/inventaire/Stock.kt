package com.example.inventaire

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TableRow
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_history.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL




class Stocks(id: Int, produit: String, quantite: Int) {
    val id = id
    val produit = produit
    val quantite = quantite
}

class Stock : AppCompatActivity() {

    var stock = arrayListOf<Stocks>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock)

        this.stock.add(Stocks(1,"Shampoing l'oreal", 56))
        this.stock.add(Stocks(2,"Serviette de bain", 8))
        this.stock.add(Stocks(3,"tapis de sol", 3))

        this.stock.forEach {
            val row = TableRow(this)
            val text = TextView(this)
            text.text = "ID : " + it.id + " - " + it.produit + ". Quantité : " + it.quantite
            row.gravity = Gravity.CENTER
            row.addView(text)
            tableLayout.addView(row)
        }


        val t =Thread(Runnable {
            val u = URL("http://10.0.2.2:8000/stock")
            val c = u.openConnection()
            val input = c.getInputStream()
            val reader = InputStreamReader(input)
            val buffer = BufferedReader(reader)
            val s = buffer.readText()
            println(s)
            runOnUiThread {
                // Convertir en objet et faire un foreach avec création de ligne comme ligne 34 - 40
            }
        })
        t.start()
    }
}
