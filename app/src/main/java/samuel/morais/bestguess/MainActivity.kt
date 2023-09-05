package samuel.morais.bestguess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var smallNumber: TextView
    private lateinit var higherNumber: TextView
    private lateinit var status: TextView
    private lateinit var answerUser: TextInputEditText
    private lateinit var answerButton: Button

    private fun restartGame(guess: Guess): Guess {
        smallNumber.text = "1"
        higherNumber.text = "100"
        val newGuess = Guess(smallNumber.text.toString().toInt(), higherNumber.text.toString().toInt())
        Toast.makeText(this, "Jogo reiniciado!", Toast.LENGTH_SHORT).show()
        return newGuess
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        smallNumber = findViewById(R.id.smallNumber)
        higherNumber = findViewById(R.id.higherNumber)
        status = findViewById(R.id.status)
        answerButton = findViewById(R.id.answerButton)
        answerUser = findViewById(R.id.answerUser)

        smallNumber.text = "1"
        higherNumber.text = "100"

        var guess = Guess(smallNumber.text.toString().toInt(), higherNumber.text.toString().toInt())


        this.answerButton.setOnClickListener{
            var answerText = answerUser.text.toString().toInt()
            answerUser.text = null
            if (guess.checkAnswer(answerText)){
                status.text = "Parabéns! Você acertou"
                Toast.makeText(this, "Você ganhou!", Toast.LENGTH_SHORT).show()
            }
            else if(guess.checkRange(answerText)){
                guess.changeRange(answerText)
                smallNumber.text = guess.smallNumber.toString()
                higherNumber.text = guess.higherNumber.toString()
                if(guess.checkEqual()){
                    status.text = "Perdeu! intervalos iguais"
                    Toast.makeText(this, "Perdeu! intervalos iguais", Toast.LENGTH_SHORT).show()
                    guess = restartGame(guess)
                }
                else{status.text = "Continue tentando"
                Toast.makeText(this, "Continue tentando", Toast.LENGTH_SHORT).show()}
            }
            else {status.text = "Perdeu! Número fora do intervalo"
                Toast.makeText(this, "Perdeu! Número fora do intervalo", Toast.LENGTH_SHORT).show()
                guess = restartGame(guess)}

        }

        this.status.setOnLongClickListener(View.OnLongClickListener {
            guess = restartGame(guess)
            true
        })

    }




}