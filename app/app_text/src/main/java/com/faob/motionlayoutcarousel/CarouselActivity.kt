package com.faob.motionlayoutcarousel

import android.content.Intent
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CarouselActivity : AppCompatActivity() {
//    private var selectedIndex: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = intent.getIntExtra("layout_file_id", R.layout.demo1)
        setContentView(layout)

        val layoutList = arrayListOf(R.layout.demo1, R.layout.demo1, R.layout.demo1,R.layout.demo1)
        val demoList = arrayListOf<DemoAdapter.Demo>()
        for (i in 0 until layoutList.size) {
            demoList.add(DemoAdapter.Demo("영상 ${i + 1}", layoutList[i]))
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerView.layoutManager =
            LinearLayoutManager(this)
        recyclerView.adapter = DemoAdapter(demoList)
    }

    fun start(layoutFileId: Int) {
        val intent = Intent(this, CarouselActivity::class.java).apply {
            putExtra("layout_file_id", layoutFileId)
        }
        startActivity(intent)
    }

//        val layout = intent.getIntExtra("layout_file_id", R.layout.main)
//        setContentView(layout)
//
//        val motionLayout = findViewById<MotionLayout>(R.id.motion_container)
//
//        val v1 = findViewById<View>(R.id.v1)
//        val v2 = findViewById<View>(R.id.v2)
//        val v3 = findViewById<View>(R.id.v3)
//
//        v1.setOnClickListener {
//            if (selectedIndex == 0) return@setOnClickListener
//
//            motionLayout.setTransition(R.id.s2, R.id.s1) //orange to blue transition
//            motionLayout.transitionToEnd()
//            selectedIndex = 0;
//        }
//        v2.setOnClickListener {
//            if (selectedIndex == 1) return@setOnClickListener
//
//            if (selectedIndex == 2) {
//                motionLayout.setTransition(R.id.s3, R.id.s2)  //red to orange transition
//            } else {
//                motionLayout.setTransition(R.id.s1, R.id.s2) //blue to orange transition
//            }
//            motionLayout.transitionToEnd()
//            selectedIndex = 1;
//        }
//        v3.setOnClickListener {
//            if (selectedIndex == 2) return@setOnClickListener
//
//            motionLayout.setTransition(R.id.s2, R.id.s3) //orange to red transition
//            motionLayout.transitionToEnd()
//            selectedIndex = 2;
//        }
}