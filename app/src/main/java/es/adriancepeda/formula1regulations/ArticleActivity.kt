package es.adriancepeda.formula1regulations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.content.ContextCompat

class ArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        val number: String=intent.getStringExtra("number").toString()
        val titleText: String =intent.getStringExtra("title").toString()
        val content: LinearLayout =findViewById(R.id.scrollArticleView)
        val text: String = intent.getStringExtra("text").toString()
        val title: Toolbar = Toolbar(this).also {
            it.setTitle(titleText)
            it.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
            it.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        }
        content.addView(title)
        val separatedText=text.split("%p")
        for(parragraph in separatedText){
            val textView: TextView = TextView(this)
            textView.setText(Html.fromHtml(parragraph))
            textView.setPadding(15,15,15,15)
            content.addView(textView)
        }
    }
}