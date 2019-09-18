package com.example.inventaire

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_command.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class NewCommand : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_command)

        button4.setOnClickListener {
            val idproduit = editText.text.toString()
            val quantite = editText2.text.toString()
            val post_data = "{\"boutique_id\": \"1\",\"inventaire_id\": \""+ idproduit +"\",\"quantite\": \""+ quantite +"\"}"
            if (idproduit == "" && quantite == "") {
                val toast = Toast.makeText(applicationContext, "Veuillez remplir les champs", Toast.LENGTH_LONG)
                toast.show()
                return@setOnClickListener
            }

            val t = Thread(Runnable {
                val u = URL("http://10.0.2.2:8000/commande")
                val con = u.openConnection() as HttpURLConnection
                con.requestMethod = "POST"
                con.setRequestProperty("Content-Type", "application/json; utf-8")
                con.doOutput = true

                try {
                    val os = con.outputStream
                    val input = post_data.toByteArray(Charsets.UTF_8)
                    os.write(input)
                } finally {
                    val br = BufferedReader(InputStreamReader(con.inputStream))
                    val s = br.readText()
                    println(s)
                }

                runOnUiThread {
                    val toast = Toast.makeText(applicationContext, "Commande réalisé avec succès", Toast.LENGTH_LONG)
                    toast.show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

            })
            t.start()
        }

    }
}
