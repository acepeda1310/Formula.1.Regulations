package es.adriancepeda.formula1regulations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTitle(R.string.finTitle)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fin)
    }
}