package es.adriancepeda.formula1regulations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import java.lang.Exception

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
            val references=parragraph.split("%r")
            val textView: TextView = TextView(this)
            textView.setText(Html.fromHtml(references[0]))
            textView.setPadding(15,15,15,15)
            content.addView(textView)
            try {
                if (references.size > 1) showReferences(references, content)
            } catch (e:Exception){
                Log.e(null, "Non existent article")
            }
        }
    }

    private fun showReferences(references: List<String>, content: LinearLayout) {
        for (i in references.indices) {
            val noSpaces=references[i].replace("\\s".toRegex(), "")
            val separatedReferences=noSpaces.split(",")
            val tvReferences = TextView(this)
            tvReferences.setPadding(15,15,15,15)
            if (i==0) tvReferences.setText(Html.fromHtml("<b>"+R.string.references+"</b>"))
            else {
                tvReferences.setText(separatedReferences[0])
                var completeRegulations=""
                val regulation=separatedReferences[1]
                if (regulation=="tech20") {
                    val complete=resources.getStringArray(R.array.tech_20)
                    val article=complete[separatedReferences[2].toInt()-1]
                    val separatedArticle=article.split("%s")
                    completeRegulations=separatedArticle[separatedReferences[3].toInt()-1]
                }
                val separatedRegulations=completeRegulations.split("%c")
                tvReferences.setOnClickListener(){
                    val intent= Intent(this, ArticleActivity::class.java)
                    intent.putExtra("number", separatedReferences[0])
                    intent.putExtra("title", separatedReferences[0]+". "+separatedRegulations[0])
                    intent.putExtra("text", separatedRegulations[1])
                    startActivity(intent)
                }
            }
            content.addView(tvReferences)
        }
    }
}