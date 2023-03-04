package com.example.iq4pics1word

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.iq4pics1word.databinding.ActivityGameBinding
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    private var currentIndex = 0
    private var optionList = mutableListOf<TextView>()
    private val resulTVList = mutableListOf<TextView>()
    private val userAnswerList = mutableListOf<UserAnswer>()
    private var coins = 400

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvCoins.text = coins.toString()

        animationImages()

        setQuestions()

        letterPlacement()

        backButton()

        animationCoin()

        btnAddCoin()
    }

    /**
     * Suwretlerdi ulkeytip kishireytiwdegi animatsiya
     */
    private fun animationImages() {
        binding.iv11.setOnClickListener {
            binding.ivBigImage.setImageResource(QuestionList.getQuestions()[currentIndex % QuestionList.getQuestions().size].pictureList[0])
            binding.ivBigImage.visibility = View.VISIBLE
            binding.ivBigImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_up_1))

            binding.ivBigImage.setOnClickListener {
                binding.ivBigImage.startAnimation(
                    AnimationUtils.loadAnimation(
                        this,
                        R.anim.scale_down_1
                    )
                )
                binding.ivBigImage.visibility = View.GONE
            }
        }

        binding.iv12.setOnClickListener {
            binding.ivBigImage.setImageResource(QuestionList.getQuestions()[currentIndex % QuestionList.getQuestions().size].pictureList[1])
            binding.ivBigImage.visibility = View.VISIBLE
            binding.ivBigImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_up_2))

            binding.ivBigImage.setOnClickListener {
                binding.ivBigImage.startAnimation(
                    AnimationUtils.loadAnimation(
                        this,
                        R.anim.scale_down_2
                    )
                )
                binding.ivBigImage.visibility = View.GONE
            }
        }

        binding.iv13.setOnClickListener {
            binding.ivBigImage.setImageResource(QuestionList.getQuestions()[currentIndex % QuestionList.getQuestions().size].pictureList[2])
            binding.ivBigImage.visibility = View.VISIBLE
            binding.ivBigImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_up_3))

            binding.ivBigImage.setOnClickListener {
                binding.ivBigImage.startAnimation(
                    AnimationUtils.loadAnimation(
                        this,
                        R.anim.scale_down_3
                    )
                )
                binding.ivBigImage.visibility = View.GONE
            }
        }

        binding.iv14.setOnClickListener {
            binding.ivBigImage.setImageResource(QuestionList.getQuestions()[currentIndex % QuestionList.getQuestions().size].pictureList[3])
            binding.ivBigImage.visibility = View.VISIBLE
            binding.ivBigImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_up_4))

            binding.ivBigImage.setOnClickListener {
                binding.ivBigImage.startAnimation(
                    AnimationUtils.loadAnimation(
                        this,
                        R.anim.scale_down_4
                    )
                )
                binding.ivBigImage.visibility = View.GONE
            }
        }
    }

    /**
     * Magliwmatlardi ornatadi
     */
    @SuppressLint("SetTextI18n")
    private fun setQuestions() {
        val question = QuestionList.getQuestions()[currentIndex % QuestionList.getQuestions().size]
        binding.tvLevel.text = (currentIndex+ 1).toString()
        binding.iv11.setImageResource(question.pictureList[0])
        binding.iv12.setImageResource(question.pictureList[1])
        binding.iv13.setImageResource(question.pictureList[2])
        binding.iv14.setImageResource(question.pictureList[3])

        val answer = question.answer
        val answerOptions = answer.toCharArray().toMutableList()

        repeat(12 - answer.length) {
            answerOptions.add(Random.nextInt(65, 91).toChar())
        }

        answerOptions.shuffle()

        fillOptionList()

        for (i in optionList.indices) {
            optionList[i].text = answerOptions[i].toString()
        }

        fillResultTVList()
        for (i in 0..7) {
            if (i < answer.length) {
                resulTVList[i].visibility = View.VISIBLE
            } else resulTVList[i].visibility = View.GONE
        }

    }

    /**
     * OptionListti toltiradi
     */
    private fun fillOptionList() {
        optionList.add(binding.tvEnter01)
        optionList.add(binding.tvEnter02)
        optionList.add(binding.tvEnter03)
        optionList.add(binding.tvEnter04)
        optionList.add(binding.tvEnter05)
        optionList.add(binding.tvEnter06)
        optionList.add(binding.tvEnter07)
        optionList.add(binding.tvEnter08)
        optionList.add(binding.tvEnter09)
        optionList.add(binding.tvEnter10)
        optionList.add(binding.tvEnter11)
        optionList.add(binding.tvEnter12)
    }

    /**
     * ResultTV listti toltiradi
     */
    private fun fillResultTVList() {
        resulTVList.add(binding.tvResult01)
        resulTVList.add(binding.tvResult02)
        resulTVList.add(binding.tvResult03)
        resulTVList.add(binding.tvResult04)
        resulTVList.add(binding.tvResult05)
        resulTVList.add(binding.tvResult06)
        resulTVList.add(binding.tvResult07)
        resulTVList.add(binding.tvResult08)
    }

    /**
     * Basqan haripti ornina tusiredi
     */
    private fun letterPlacement() {
        fillUserAnswerList()
        optionList.forEach { tv ->
            tv.setOnClickListener {
                for (index in resulTVList.indices) {
                    if (resulTVList[index].text.isEmpty() && index < QuestionList.getQuestions()[currentIndex % QuestionList.getQuestions().size].answer.length) {
                        userAnswerList[index] = UserAnswer(tv.text.toString(), tv)
                        resulTVList[index].text = tv.text.toString()
                        resulTVList[index].startAnimation(
                            AnimationUtils.loadAnimation(
                                this,
                                R.anim.letters_up
                            )
                        )
                        tv.startAnimation(AnimationUtils.loadAnimation(this, R.anim.letters_down))
                        tv.visibility = View.INVISIBLE
                        checkAnswer()
                        break
                    }
                }
            }
        }

        resulTVList.forEach { rtv ->
            rtv.setOnClickListener {
                userAnswerList[resulTVList.indexOf(rtv)].optionTV.visibility = View.VISIBLE
                userAnswerList[resulTVList.indexOf(rtv)].optionTV.startAnimation(
                    AnimationUtils.loadAnimation(
                        this,
                        R.anim.letters_up
                    )
                )
                rtv.text = ""
            }
        }

    }

    /**
     * Paydalaniwshi basqan hariplerdi saqlap turadi
     */
    private fun fillUserAnswerList() {
        repeat(8) {
            userAnswerList.add(UserAnswer("", binding.tvLevel))
        }
    }

    /**
     * Juwapti tekseredi
     */
    private fun checkAnswer() {
        val answer = QuestionList.getQuestions()[currentIndex % QuestionList.getQuestions().size].answer
        var userAnswer = ""
        repeat(answer.length) {
            userAnswer += resulTVList[it].text
        }

        if (userAnswer.length == answer.length) {
            if (userAnswer == answer) {
                binding.nextScreen.visibility = View.VISIBLE
                binding.llResult.visibility = View.VISIBLE
                try{
                    animationCircle()
                } catch (e: Exception) {
                    Toast.makeText(this, "animationCircle", Toast.LENGTH_SHORT).show()
                }
                
                binding.submitBtn.setOnClickListener {
                    forNextQuestion()
                    currentIndex++
                    setQuestions()
                    calculateCoins()
                    binding.nextScreen.visibility = View.GONE
                    binding.animationCircle.visibility = View.GONE
                }
            } else {
                binding.llResult.startAnimation(
                    AnimationUtils.loadAnimation(
                        this,
                        R.anim.shake_animation
                    )
                )
            }
        }
    }

    /**
     * Artqa qaytiw knopkasi
     */
    private fun backButton() {
        binding.ivBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Coin menen add coinnin animatsiyasi
     */
    private fun animationCoin() {
        binding.ivCoin.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_animation))
        binding.ivAdd.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation))
    }

    /**
     * Keyingi sorawga otiw ushin magliwmatlardi tazalaydi
     */
    private fun forNextQuestion() {
        optionList.forEach {
            it.visibility = View.VISIBLE
        }
        repeat(12) {
            optionList.removeAt(0)
        }
        resulTVList.forEach {
            it.text = ""
        }
        repeat(8) {
            userAnswerList.removeAt(0)
        }
        fillUserAnswerList()
    }

    /**
     * Coin jiynap baradi
     */
    private fun calculateCoins() {
        coins += 10

        binding.tvCoins.text = coins.toString()
    }

    /**
     * Add coin knopkasi
     */
    private fun btnAddCoin(){
        binding.cvAddcoin.setOnClickListener {
            binding.clWindowAd.visibility = View.VISIBLE

            binding.ivClose.setOnClickListener {
                binding.clWindowAd.visibility = View.GONE
            }
        }
    }

    private fun animationCircle(){
        binding.animationCircle.visibility = View.VISIBLE
        binding.animationCircle.setAnimation(R.raw.circle_animation)
        binding.animationCircle.repeatCount = 5
    }

}
