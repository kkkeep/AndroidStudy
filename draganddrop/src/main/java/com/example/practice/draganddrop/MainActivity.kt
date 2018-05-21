package com.example.practice.draganddrop

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.DragEvent
import android.view.View
import android.widget.TextView
import android.support.v4.graphics.drawable.DrawableCompat.clearColorFilter
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : AppCompatActivity() {

    private val DRAG_VIEW_TAG = "DRAG_VIEW_TAG"

    lateinit var mDragView: TextView

    lateinit var  mReceiveView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDragView = findViewById(R.id.drag_view) as TextView


        mReceiveView = findViewById(R.id.receive_view) as TextView

        mDragView.tag = DRAG_VIEW_TAG

        mDragView.setOnLongClickListener { view ->


            var item = ClipData.Item(mDragView.text)

            var dragdata: ClipData = ClipData(mDragView.tag as String, arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN), item);


            val myShadow = MyDragShadShowBuilder(mDragView)

            view.startDrag(dragdata,myShadow,null,0)


            return@setOnLongClickListener true

        }



        mReceiveView.setOnDragListener {  view , event ->


            val action = event.action


            when(action){

                DragEvent.ACTION_DRAG_STARTED ->{
                    if (event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)){
                        view.setBackgroundColor(Color.BLUE)
                        view.invalidate()
                        return@setOnDragListener true

                    }

                }
                DragEvent.ACTION_DRAG_ENTERED ->{
                    view.setBackgroundColor(Color.RED)
                    view.invalidate()
                    return@setOnDragListener true


                }
                DragEvent.ACTION_DRAG_EXITED ->{
                    view.setBackgroundColor(Color.BLUE)
                    return@setOnDragListener true
                }
                DragEvent.ACTION_DRAG_LOCATION -> return@setOnDragListener true
                DragEvent.ACTION_DROP ->{
                    // Gets the item containing the dragged data
                    val item = event.clipData.getItemAt(0)

                    // Gets the text data from the item.
                    val dragData = item.text

                    // Displays a message containing the dragged data.
                    Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_LONG).show()

                    mReceiveView.text = dragData
                    // Turns off any color tints

                    view.setBackgroundColor(Color.GRAY)

                    // Invalidates the view to force a redraw
                    view.invalidate()

                    // Returns true. DragEvent.getResult() will return true.
                    return@setOnDragListener true
                }

                DragEvent.ACTION_DRAG_ENDED ->{
                    view.setBackgroundColor(Color.GRAY)

                    // Invalidates the view to force a redraw
                    view.invalidate()
                    if (event.getResult()) {
                        Toast.makeText(this, "The drop was handled.", Toast.LENGTH_LONG).show()

                    } else {
                        Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_LONG).show()

                    }
                    return@setOnDragListener true
                }
                else ->  {
                    Log.e("DragDrop Example","Unknown action type received by OnDragListener.")
                    return@setOnDragListener false
                }
            }

            return@setOnDragListener true

        }
    }
}

class MyDragShadShowBuilder( view: View): View.DragShadowBuilder(view){

    private  var shadow: Drawable = ColorDrawable(Color.GRAY)

    override fun onProvideShadowMetrics(outShadowSize: Point?, outShadowTouchPoint: Point?) {
        // Defines local variables
        val width: Int = view.width / 2
        val height: Int = view.height / 2

        // Sets the width of the shadow to half the width of the original View

        // Sets the height of the shadow to half the height of the original View

        // The drag shadow is a ColorDrawable. This sets its dimensions to be the same as the
        // Canvas that the system will provide. As a result, the drag shadow will fill the
        // Canvas.
        shadow.setBounds(0, 0, width, height)

        // Sets the size parameter's width and height values. These get back to the system
        // through the size parameter.
        outShadowSize?.set(width, height)

        // Sets the touch point's position to be in the middle of the drag shadow
        outShadowTouchPoint?.set(width / 2, height / 2)
    }

    override fun onDrawShadow(canvas: Canvas?) {
        shadow.draw(canvas)
    }


}