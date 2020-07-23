package es.adriancepeda.formula1regulations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    internal lateinit var buttonTech:Button
    internal lateinit var buttonSport:Button
    internal lateinit var buttonFin:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        setTitle(R.string.app_name)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonTech = findViewById(R.id.buttonTech)
        buttonTech.setOnClickListener{
            val intent=Intent(this, TechActivity::class.java)
            startActivity(intent)
        }
        buttonSport = findViewById(R.id.buttonSport)
        buttonSport.setOnClickListener{
            val intent=Intent(this, SportActivity::class.java)
            startActivity(intent)
        }
        buttonFin = findViewById(R.id.buttonFin)
        buttonFin.setOnClickListener{
            val intent=Intent(this, FinActivity::class.java)
            startActivity(intent)
        }

    }
}