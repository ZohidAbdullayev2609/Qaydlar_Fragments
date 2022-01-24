package com.example.qaydlarfragments

 import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.example.qaydlarfragments.databinding.ActivitySplash2Binding
import com.example.qaydlarfragments.model.User
import com.example.qaydlarfragments.ui.FirstFragment
import com.example.qaydlarfragments.utils.MySharedPrefernce
import com.google.gson.Gson

class SplashActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivitySplash2Binding
    private lateinit var list: ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplash2Binding.inflate(layoutInflater)
        setContentView(binding.root)

//        MySharedPrefernce.init(applicationContext)
//        loadData()
        Handler(Looper.getMainLooper()).postDelayed({
//            val gson = Gson()
//            val s = gson.toJson(list)
//            MySharedPrefernce.userData = s

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, FirstFragment()).commit()

            finish()
        }, 3000)

        val images = AnimationUtils.loadAnimation(applicationContext, R.anim.images)

        val top = AnimationUtils.loadAnimation(applicationContext, R.anim.yuqori)
        val middle = AnimationUtils.loadAnimation(applicationContext, R.anim.orta)
        val pas = AnimationUtils.loadAnimation(applicationContext, R.anim.past)

        binding.notes.startAnimation(images)

        binding.splashTop.startAnimation(top)
        binding.splashMiddle.startAnimation(middle)
        binding.splashBottom.startAnimation(pas)

    }

//    private fun loadData() {
//        list = ArrayList()
//        list.add(User("Title", "Description"))
//        list.add(User("Namuna", "123456789"))
//
//    }
}