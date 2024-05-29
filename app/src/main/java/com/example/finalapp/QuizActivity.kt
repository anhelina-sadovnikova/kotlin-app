package com.example.finalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.finalapp.databinding.ActivityQuizBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class QuizActivity : AppCompatActivity() {

    lateinit var quizBinding: ActivityQuizBinding

    val database = FirebaseDatabase.getInstance()
    val databaseReference = database.reference.child("questions")

    var question = ""
    var answerA = ""
    var answerB = ""
    var answerC = ""
    var answerD = ""
    var correctAnswer = ""
    var questionCount = 0
    var questionNumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quizBinding = ActivityQuizBinding.inflate(layoutInflater)
        val view = quizBinding.root
        setContentView(view)

        gameLogic()

        quizBinding.buttonNext.setOnClickListener {

            gameLogic()

        }

        quizBinding.buttonFinish.setOnClickListener {

        }
        quizBinding.textViewA.setOnClickListener {  }
        quizBinding.textViewB.setOnClickListener {  }
        quizBinding.textViewC.setOnClickListener {  }
        quizBinding.textViewD.setOnClickListener {  }
    }

    private fun gameLogic(){
        Log.d("QuizActivity", "gameLogic called")

        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("QuizActivity", "onDataChange called")

                questionCount = snapshot.childrenCount.toInt()
                Log.d("QuizActivity", "Question count: $questionCount")

                if (questionNumber <= questionCount) {

                    question = snapshot.child(questionNumber.toString()).child("q").value.toString()
                    answerA = snapshot.child(questionNumber.toString()).child("a").value.toString()
                    answerB = snapshot.child(questionNumber.toString()).child("b").value.toString()
                    answerC = snapshot.child(questionNumber.toString()).child("c").value.toString()
                    answerD = snapshot.child(questionNumber.toString()).child("d").value.toString()
                    correctAnswer = snapshot.child(questionNumber.toString()).child("answer").value.toString()

                    quizBinding.textViewQuestion.text = question
                    quizBinding.textViewA.text = answerA
                    quizBinding.textViewB.text = answerB
                    quizBinding.textViewC.text = answerC
                    quizBinding.textViewD.text = answerD

                    quizBinding.progressBarQuiz.visibility = View.INVISIBLE
                    quizBinding.linearLayoutInfo.visibility = View.VISIBLE
                    quizBinding.linearLayoutQuestion.visibility = View.VISIBLE
                    quizBinding.linearLayoutButtons.visibility = View.VISIBLE

                }else{
                    Toast.makeText(applicationContext,"You answered all the questions", Toast.LENGTH_SHORT).show()
                }

                questionNumber++
                Log.d("QuizActivity", "Next question number: $questionNumber")

            }

            override fun onCancelled(error: DatabaseError) {

                Log.d("QuizActivity", "Error: ${error.message}")
                Toast.makeText(applicationContext,error.message, Toast.LENGTH_SHORT).show()

            }
        })

    }
}