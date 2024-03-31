package com.name.a10androidkotlincatchkenny

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.name.a10androidkotlincatchkenny.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var textViewTime: TextView
    private lateinit var textViewScore: TextView
    private var score : Int = 0
    private lateinit var gridLayout: GridLayout
    private  var imageArray = ArrayList<ImageView>()
    var runnable= Runnable{}
    var handler: Handler= Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        textViewTime=binding.textViewTime
        textViewScore=binding.textViewScore
        gridLayout=binding.gridLayout

        imageArray.add(binding.imageView1)
        imageArray.add(binding.imageView10)
        imageArray.add(binding.imageView11)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView12)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)

        hideImages()

        //CountDown Timer

        object: CountDownTimer (15700,1000){
            override fun onTick(millisUntilFinished: Long) {
                textViewTime.text="Time: ${millisUntilFinished/1000}"
            }

            override fun onFinish() {
                textViewTime.text="Finish!!"
                handler.removeCallbacks(runnable)

                for (image in imageArray){
                    image.visibility=View.INVISIBLE
                }
                //alert dialog
                val alert=AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart the Game")
                alert.setPositiveButton("Yes",DialogInterface.OnClickListener { dialog, which ->
                    //restart
                    val intentFromMain=intent
                    finish()
                    startActivity(intentFromMain)

                })
                alert.setNegativeButton("No",DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this@MainActivity,"Game Over", Toast.LENGTH_SHORT).show()
                })
                alert.show()

            }

        }.start()


    }

    fun hideImages(){

       runnable= object : Runnable {
            override fun run() {
                for(image in imageArray){
                    image.visibility=View.INVISIBLE
                }

                val random= Random()
                val randomIndex=random.nextInt(12)
                imageArray[randomIndex].visibility=View.VISIBLE
                handler.postDelayed(runnable,850)
            }
        }
        handler.post(runnable)
        /*
        git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/mesutKoc77/AndroidKotlinCatachKenny.git
git push -u origin main
         */

    }



    fun increaseScore (view :View){
        score++
        textViewScore.text="Score : ${score}"

    }





}