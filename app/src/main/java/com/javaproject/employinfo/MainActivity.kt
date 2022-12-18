package com.javaproject.employinfo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout

class MainActivity : AppCompatActivity() {
    var employType = "고용형태"
    var locaction = "지역별"
    var back = "뒤로가기"

    var selectedIndex: Int = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = intent.getStringExtra("view")

        if(view == employType) setContentView(R.layout.activity_employ)
        else if(view == locaction) setContentView(R.layout.activity_local)
        else setContentView(R.layout.activity_main)

        val motionLayout = findViewById<MotionLayout>(R.id.motion_container)

        var enterCheck = 0

        val lastChildIdx = motionLayout.childCount - 2
        val viewList = arrayListOf<View>()

        for(i : Int in 0..lastChildIdx){
            viewList.add(findViewById<View>(resources.getIdentifier("v"+(i+1), "id", this.packageName)))

            if(i==0){
                viewList[i].setOnClickListener{
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
                        val textId = resources.getIdentifier("v"+(i+1)+"_text", "id", this.packageName)
                        val text = viewList[i].findViewById<TextView>(textId).text

                        if(text.equals(employType) or text.equals(locaction)){
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("view", text)
                            startActivity(intent)
                        }else{
                            val intent = Intent(this, NewActivity::class.java)
                            intent.putExtra("view", text)
                            startActivity(intent)
                        }

                        enterCheck=0;
                    }
                }
            }else{
                viewList[i].setOnClickListener {
                    enterCheck=0

                    if (selectedIndex == i){
                        val textId = resources.getIdentifier("v"+(i+1)+"_text", "id", this.packageName)
                        val text = viewList[i].findViewById<TextView>(textId).text

                        if(text.equals(back)){
                            finish()
                        }else if(text.equals(employType) or text.equals(locaction)){
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("view", text)
                            startActivity(intent)
                        }else{
                            val intent = Intent(this, NewActivity::class.java)
                            intent.putExtra("view", text)
                            startActivity(intent)
                        }

                        return@setOnClickListener
                    }

                    val sFrom = resources.getIdentifier("s"+i, "id", this.packageName)
                    val sTo = resources.getIdentifier("s"+(i+1), "id", this.packageName)
                    motionLayout.setTransition(sFrom, sTo)
                    motionLayout.transitionToEnd()

                    selectedIndex = i
                }
            }
        }

    }
    /*fun start(layoutFileId: Int) {
        val intent = Intent(this, CarouselActivity::class.java).apply {
            putExtra("layout_file_id", layoutFileId)
        }
        startActivity(intent)
    }*/
}
