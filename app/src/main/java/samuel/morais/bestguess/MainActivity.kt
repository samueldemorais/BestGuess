package samuel.morais.bestguess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var smallNumber: TextView
    private lateinit var higherNumber: TextView
    private lateinit var status: TextView
    private lateinit var answerUser: TextInputEditText
    private lateinit var answerButton: Button

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

        val guess = Guess(smallNumber.text.toString().toInt(), higherNumber.text.toString().toInt())

        this.answerButton.setOnClickListener{
            var answerText = answerUser.text.toString().toInt()
            if (guess.checkAnswer(answerText)){
                status.text = "Parabéns! Você acertou"
                //Toast.makeText(this, "Você perdeu!", Toast.LENGTH_SHORT).show()
            }
            else if(guess.checkRange(answerText)){
                guess.changeRange(answerText)
                smallNumber.text = guess.smallNumber.toString()
                higherNumber.text = guess.higherNumber.toString()
            }
            else if(guess.checkEqual()){
                status.text = "Perdeu! intervalos iguais"
            }
            else {status.text = "Perdeu! Número fora do intervalo"}

        }

    }
}