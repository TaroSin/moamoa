package com.javaproject.employinfo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout

class LocalActivity : AppCompatActivity() {

    var selectedIndex: Int = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local)

        val motionLayout = findViewById<MotionLayout>(R.id.motion_container)

        val v1 = findViewById<View>(R.id.v1)
        val v2 = findViewById<View>(R.id.v2)
        val v3 = findViewById<View>(R.id.v3)
        val v4 = findViewById<View>(R.id.v4)
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
//                val intent = Intent(this, CarouselActivity::class.java)
                startActivity(intent)
                enterCheck=0;
            }

        }

        v2.setOnClickListener {
            enterCheck=0
            if (selectedIndex == 1) return@setOnClickListener

            if (selectedIndex == 2) {
                motionLayout.setTransition(R.id.s3, R.id.s2)
            } else {
                motionLayout.setTransition(R.id.s1, R.id.s2)
            }
            motionLayout.transitionToEnd()
            selectedIndex = 1


        }
        v3.setOnClickListener {
            if (selectedIndex == 2){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return@setOnClickListener
            }

            if (selectedIndex == 3) {
                motionLayout.setTransition(R.id.s1, R.id.s3)
            } else {
                motionLayout.setTransition(R.id.s2, R.id.s3)
            }
            motionLayout.transitionToEnd()
            selectedIndex = 2;

        }
    }
    /*fun start(layoutFileId: Int) {
        val intent = Intent(this, CarouselActivity::class.java).apply {
            putExtra("layout_file_id", layoutFileId)
        }
        startActivity(intent)
    }*/
}
