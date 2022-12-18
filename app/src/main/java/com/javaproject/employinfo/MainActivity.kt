package com.javaproject.employinfo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout

class MainActivity : AppCompatActivity() {

    var selectedIndex: Int = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val motionLayout = findViewById<MotionLayout>(R.id.motion_container)

        val v1 = findViewById<View>(R.id.v1)
        val v2 = findViewById<View>(R.id.v2)
        val v3 = findViewById<View>(R.id.v3)
        val v4 = findViewById<View>(R.id.v4)
        val v1Text = findViewById<TextView>(R.id.v1_text);
        val v2Text = findViewById<TextView>(R.id.v2_text);
//        val v3Text = findViewById<TextView>(R.id.v3_text);
//        val v4Text = findViewById<TextView>(R.id.v4_text);

        var enterCheck = 0
        v1.setOnClickListener {
            if (enterCheck==0){
                if (selectedIndex == 0) {
                    enterCheck=1
                    return@setOnClickListener
                }

                motionLayout.setTransition(R.id.s2, R.id.s1)
                motionLayout.transitionToEnd()
                selectedIndex = 0;
            }
            else{
                val intent = Intent(this, EmploActivity::class.java)
                startActivity(intent)
                enterCheck=0;
            }

        }

        v2.setOnClickListener {
            enterCheck=0
            if (selectedIndex == 1){
                val intent = Intent(this, LocalActivity::class.java)
                startActivity(intent)
                return@setOnClickListener
            }


                motionLayout.setTransition(R.id.s1, R.id.s2)
            motionLayout.transitionToEnd()
            selectedIndex = 1


        }

    }
    /*fun start(layoutFileId: Int) {
        val intent = Intent(this, CarouselActivity::class.java).apply {
            putExtra("layout_file_id", layoutFileId)
        }
        startActivity(intent)
    }*/
}
