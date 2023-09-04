package samuel.morais.bestguess
import kotlin.random.Random

class Guess(
    var smallNumber: Int,
    var higherNumber: Int
){
    val randomNumber: Int = Random.nextInt(smallNumber, higherNumber)

    fun checkAnswer(answerUser: Int): Boolean{
        return answerUser == randomNumber
    }

    fun checkEqual() :Boolean{
        return smallNumber == higherNumber
    }

    fun checkRange(answerUser: Int): Boolean{
        return answerUser in smallNumber..higherNumber
    }

    fun changeRange(answerUser: Int) {
         when {
           answerUser < randomNumber -> {
                smallNumber = answerUser + 1
            }
            else ->  {
                higherNumber = answerUser - 1
            }
        }
    }



}