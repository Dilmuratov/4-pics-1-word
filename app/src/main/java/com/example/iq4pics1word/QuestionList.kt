package com.example.iq4pics1word

object QuestionList {

    fun getQuestions(): MutableList<Question> {
        val question = mutableListOf<Question>()

        question.add(
            Question(
                id = 0,
                pictureList = mutableListOf(
                    R.drawable.photo1_1,
                    R.drawable.photo1_2,
                    R.drawable.photo1_3,
                    R.drawable.photo1_4
                ),
                answer = "SUWIQ"
            )
        )

        question.add(
            Question(
                id = 1,
                pictureList = mutableListOf(
                    R.drawable.photo2_1,
                    R.drawable.photo2_2,
                    R.drawable.photo2_3,
                    R.drawable.photo2_4
                ),
                answer = "SHAWQIM"
            )
        )

        question.add(
            Question(
                id = 2,
                pictureList = mutableListOf(
                    R.drawable.photo3_1,
                    R.drawable.photo3_2,
                    R.drawable.photo3_3,
                    R.drawable.photo3_4
                ),
                answer = "ISSI"
            )
        )

        question.add(
            Question(
                id = 3,
                pictureList = mutableListOf(
                    R.drawable.photo4_1,
                    R.drawable.photo4_2,
                    R.drawable.photo4_3,
                    R.drawable.photo4_4
                ),
                answer = "MUZIKA"
            )
        )

        question.add(
            Question(
                id = 4,
                pictureList = mutableListOf(
                    R.drawable.photo5_1,
                    R.drawable.photo5_2,
                    R.drawable.photo5_3,
                    R.drawable.photo5_4
                ),
                answer = "NOQAT"
            )
        )

        return question
    }
}