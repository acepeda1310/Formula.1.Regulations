package es.adriancepeda.formula1regulations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TechActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTitle(R.string.techTitle)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tech)
    }
}