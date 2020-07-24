package es.adriancepeda.formula1regulations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TechActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTitle(R.string.techTitle)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tech)
        val buttonActual : Button = findViewById(R.id.techActualYear)
        buttonActual.setOnClickListener{
            val intent= Intent(this, TechActivity20::class.java)
            startActivity(intent)
        }
    }
}