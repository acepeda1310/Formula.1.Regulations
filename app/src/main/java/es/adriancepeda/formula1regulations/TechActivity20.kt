package es.adriancepeda.formula1regulations

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView

class TechActivity20 : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tech20)
        val content: LinearLayout =findViewById(R.id.scrollTech20)
        val text=resources.getStringArray(R.array.tech_20)
        val title: Toolbar = Toolbar(this).also {
            it.setTitle(R.string.techTitle20)
            it.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
            it.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        }
        content.addView(title)
        val textViewList=Array(text.size){}
        for (i in text.indices){
            val article=text[i].split("%s")
            val textView:TextView= TextView(this)
            textView.setText((i+1).toString()+". "+article[0])
            textView.setPadding(15,15,15,15)
            textView.setOnClickListener{
                val intent= Intent(this, ArticleListActivity::class.java)
                intent.putExtra("number", (i+1).toString())
                intent.putExtra("title", (i+1).toString()+". "+article[0])
                intent.putExtra("text", text[i])
                startActivity(intent)
            }
            content.addView(textView)
        }
    }
}