package kkk.com.testspanneblestring

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by taofu
 *on 2018/5/21.
 */
class MainActivity : AppCompatActivity() {

    private val spannableString = SpannableStringBuilder("0123456789")
    val foregroundColorSpan = ForegroundColorSpan(Color.BLUE)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        testSpanedIncludeAndExclude()
        btn_insert.setOnClickListener {
            val backgroundColorSpan = BackgroundColorSpan(Color.RED)
            //spannableString.insert(1,"adb")
            var start = spannableString.length-1
            spannableString.append("abddddddd")
            //spannableString.insert(,"abd")
            spannableString.setSpan(backgroundColorSpan,start,spannableString.length -3,Spanned.SPAN_EXCLUSIVE_INCLUSIVE)

            tv_Span.text = spannableString

        }
    }


    fun testSpanedIncludeAndExclude(){

        spannableString.setSpan(foregroundColorSpan,1,1,Spanned.SPAN_EXCLUSIVE_INCLUSIVE)

        tv_Span.text = spannableString

        val spannableString: SpannableString? = null;
    }


    class BtnOnClickListener : View.OnClickListener{
        override fun onClick(v: View?) {
        }

    }

}