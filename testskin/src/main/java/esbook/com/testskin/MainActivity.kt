package esbook.com.testskin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IntDef
import android.support.v4.view.LayoutInflaterCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        LayoutInflaterCompat.setFactory2() //监听布局的创建


        val testAutoInsertNullnessAnnotations = TestAutoInsertNullnessAnnotations()

        testAutoInsertNullnessAnnotations.test1()

        testAutoInsertNullnessAnnotations.test2()

    }


}

