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
import java.util.*

class Commande(id: Int, produit: String, quantite: Int, date: Date = Date()) {
    val id = id
    val produit = produit
    val quantite = quantite
    val date = date
}

class history : AppCompatActivity() {

    var historique = arrayListOf<Commande>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        this.historique.add(Commande(1,"Shampoing garnier", 10))
        this.historique.add(Commande(2,"Savon marseillais", 8))
        this.historique.add(Commande(3,"Éponge de bain", 3))

        this.historique.forEach {
            val row = TableRow(this)
            val text = TextView(this)
            text.text = "ID : " + it.id + " - " + it.produit + ". Quantité : " + it.quantite
            row.gravity = Gravity.CENTER
            row.addView(text)
            tableLayout.addView(row)
        }

        val t =Thread(Runnable {
            val u = URL("http://10.0.2.2:8000/commande")
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
