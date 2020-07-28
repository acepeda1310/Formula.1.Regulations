package es.adriancepeda.formula1regulations

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
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
            val images=references[0].split(" %img")
            val subArticles=images[0].split("%a1")
            val textView: TextView = TextView(this)
            textView.setText(Html.fromHtml(subArticles[0]))
            textView.setPadding(15,15,15,15)
            content.addView(textView)
            if (subArticles.size>1) showSubArticles(subArticles, content, 40)
            if (images.size>1) showImages(images, content)
            try {
                if (references.size > 1) showReferences(references, content)
            } catch (e:Exception){
                Log.e(null, "Non existent article")
            }
        }
    }

    private fun showImages(images: List<String>, content: LinearLayout) {
        Log.e(null, R.drawable.tech20_3_4_a.toString())
        Log.e(null, R.drawable.tech20_3_4_b.toString())
        Log.e(null, R.drawable.tech20_3_4_c.toString())
        Log.e(null, R.drawable.tech20_3_4_d.toString())
        for (i in 1 until images.size){
            val image=images[i].replace("\\s".toRegex(),"")
            val imageView = ImageView(this).also {
                it.setPadding(15,15,15,15)
                val layoutParams:LinearLayout.LayoutParams=LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                it.layoutParams = layoutParams
                val imageNoOther=image.split("%o")
                it.setImageResource(imageNoOther[0].toInt())
                it.adjustViewBounds=true
            }
            content.addView(imageView)
        }
    }

    private fun showSubArticles(subArticles: List<String>, content: LinearLayout, padding:Int) {
        for (i in 1 until subArticles.size) {
            val separated=subArticles[i].split("%a2")
            val tvSubArticles=TextView(this)
            tvSubArticles.setPadding(padding,15,15,15)
            tvSubArticles.text = Html.fromHtml(separated[0])
            content.addView(tvSubArticles)
            if(separated.size>1) showSubArticles(separated, content, padding+25)
        }
    }

    private fun showReferences(references: List<String>, content: LinearLayout) {
        for (i in references.indices) {
            val noSpaces=references[i].replace("\\s".toRegex(), "")
            val separatedReferences=noSpaces.split(",")
            val tvReferences = TextView(this)
            tvReferences.setPadding(15,15,15,15)
            if (i==0) tvReferences.text = Html.fromHtml("<b>"+resources.getText(R.string.references)+"</b>")
            else {
                val referenceTitle=separatedReferences[0].replace("_".toRegex()," ")
                tvReferences.text = referenceTitle
                var completeRegulations=""
                val regulation=separatedReferences[1]
                try {
                    if (regulation == "tech20") {
                        val complete = resources.getStringArray(R.array.tech_20)
                        var article = complete[separatedReferences[2].toInt() - 1]
                        val otherString=article.split("%o")
                        if(otherString.size>1){
                            for(i in 1 until otherString.size){
                                val otherData2=otherString[i].replace("\\s".toRegex(),"")
                                article=article.plus(resources.getString(otherData2.toInt()))
                            }
                        }
                        val separatedArticle = article.split("%s")
                        completeRegulations = separatedArticle[separatedReferences[3].toInt()]
                    }
                    val separatedRegulations = completeRegulations.split("%c")
                    tvReferences.setTextColor(Color.BLUE)
                    tvReferences.setOnClickListener() {
                        val intent = Intent(this, ArticleActivity::class.java)
                        intent.putExtra("number", referenceTitle)
                        intent.putExtra(
                            "title",
                            referenceTitle + ". " + separatedRegulations[0]
                        )
                        intent.putExtra("text", separatedRegulations[1])
                        startActivity(intent)
                    }
                } catch (e:Exception){
                    Log.e(null,"Non existent article")
                }
            }
            tvReferences.setBackgroundColor(Color.rgb(205,205,205))
            content.addView(tvReferences)
        }
    }
}