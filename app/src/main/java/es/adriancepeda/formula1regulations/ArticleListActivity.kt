package es.adriancepeda.formula1regulations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.content.ContextCompat

class ArticleListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)
        val number: String=intent.getStringExtra("number").toString()
        val titleText: String =intent.getStringExtra("title").toString()
        val content: LinearLayout =findViewById(R.id.scrollArticleList)
        val text: String = intent.getStringExtra("text").toString()
        val title: Toolbar = Toolbar(this).also {
            it.setTitle(titleText)
            it.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
            it.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        }
        content.addView(title)
        val list=text.split("%s")
        for (i in 1 until list.size){
            val articleDecompressed=list[i].split("%c")
            val textView: TextView = TextView(this)
            textView.setText(number+"."+i.toString()+". "+articleDecompressed[0])
            textView.setPadding(15,15,15,15)
            textView.setOnClickListener{
                val intent= Intent(this, MainActivity::class.java)
                intent.putExtra("number", (i+1).toString())
                intent.putExtra("title", number+"."+i.toString()+". "+articleDecompressed[0])
                intent.putExtra("text", text[i])
                startActivity(intent)
            }
            content.addView(textView)
        }

    }
}