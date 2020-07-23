package es.adriancepeda.formula1regulations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTitle(R.string.sportTitle)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sport)
    }
}