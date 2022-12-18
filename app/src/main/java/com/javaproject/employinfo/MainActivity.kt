package com.javaproject.employinfo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout

class MainActivity : AppCompatActivity() {
    var ai = "AI"
    var frontend = "Frontend"
    var backend = "Backend"
    var employType = "고용형태"
    var locaction = "지역별"
    var back = "뒤로가기"

    var fieldList = listOf<String>(ai, frontend, backend)
    var empList = listOf<String>("정규직", "인턴")
    var locList = listOf<String>("수도권", "전북권")
    var viewKeys = listOf<String>(ai, frontend, backend, employType, locaction)

    var selectedIndex: Int = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = intent.getStringExtra("view")
        var field = intent.getStringExtra("filed")
        var emp = intent.getStringExtra("emp")
        var loc = intent.getStringExtra("loc")

        if(view == ai || view == frontend || view == backend) setContentView(R.layout.activity_type)
        else if(view == employType) setContentView(R.layout.activity_employ)
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
                        var intent = Intent()

                        if(viewKeys.contains(text)){
                            intent = Intent(this, MainActivity::class.java)
                        }else{
                            intent = Intent(this, NewActivity::class.java)
                        }

                        intent.putExtra("view", text)
                        if(fieldList.contains(text)) field = text as String?
                        intent.putExtra("filed", field)
                        if(empList.contains(text))  emp = text as String?
                        intent.putExtra("emp", emp)
                        if(locList.contains(text)) loc = text as String?
                        intent.putExtra("loc", loc)

                        startActivity(intent)

                        enterCheck=0;
                    }
                }
            }else{
                viewList[i].setOnClickListener {
                    enterCheck=0

                    if(selectedIndex == i) {
                        val textId = resources.getIdentifier("v"+(i+1)+"_text", "id", this.packageName)
                        val text = viewList[i].findViewById<TextView>(textId).text
                        var intent = Intent()

                        if(text.equals(back)){
                            finish()
                        }else {
                            if (viewKeys.contains(text)) {
                                intent = Intent(this, MainActivity::class.java)

                            } else {
                                intent = Intent(this, NewActivity::class.java)
                            }

                            intent.putExtra("view", text)
                            if (fieldList.contains(text)) field = text as String?
                            intent.putExtra("filed", field)
                            if (empList.contains(text)) emp = text as String?
                            intent.putExtra("emp", emp)
                            if (locList.contains(text)) loc = text as String?
                            intent.putExtra("loc", loc)

                            startActivity(intent)
                        }

                        return@setOnClickListener
                    }

                    if(selectedIndex == i+1 && selectedIndex<3){
                        val sFrom = resources.getIdentifier("s"+(i+2), "id", this.packageName)
                        val sTo = resources.getIdentifier("s"+(i+1), "id", this.packageName)
                        motionLayout.setTransition(sFrom, sTo)
                        motionLayout.transitionToEnd()
                    }else{
                        val sFrom = resources.getIdentifier("s"+i, "id", this.packageName)
                        val sTo = resources.getIdentifier("s"+(i+1), "id", this.packageName)
                        motionLayout.setTransition(sFrom, sTo)
                        motionLayout.transitionToEnd()
                    }

                    selectedIndex = i

                }
            }
        }

    }
}
