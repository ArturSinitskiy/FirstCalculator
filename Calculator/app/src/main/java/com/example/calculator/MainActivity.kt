package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isOperationCompleted = false
    private var isPointThere = false
    private var firstValue = 0.0
    private var secondValue = 0.0
    private var isDivideOperation = false
    private var isMultiplyOperation = false
    private var isPlusOperation = false
    private var isMinusOperation = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button0.setOnClickListener {
            showText(binding.inputText.text.toString(), '0')
        }
        binding.button1.setOnClickListener {
            showText(binding.inputText.text.toString(), '1')
        }
        binding.button2.setOnClickListener {
            showText(binding.inputText.text.toString(), '2')
        }
        binding.button3.setOnClickListener {
            showText(binding.inputText.text.toString(), '3')
        }
        binding.button4.setOnClickListener {
            showText(binding.inputText.text.toString(), '4')
        }
        binding.button5.setOnClickListener {
            showText(binding.inputText.text.toString(), '5')
        }
        binding.button6.setOnClickListener {
            showText(binding.inputText.text.toString(), '6')
        }
        binding.button7.setOnClickListener {
            showText(binding.inputText.text.toString(), '7')
        }
        binding.button8.setOnClickListener {
            showText(binding.inputText.text.toString(), '8')
        }
        binding.button9.setOnClickListener {
            showText(binding.inputText.text.toString(), '9')
        }
        binding.buttonDelete .setOnClickListener {
            binding.inputText.text = ""
            isOperationCompleted = false
            isPointThere = false
            isDivideOperation = false
            isMultiplyOperation = false
            isPlusOperation = false
            isMinusOperation = false
            binding.resultText.text = ""
        }
        binding.buttonPoint.setOnClickListener {
            if (isPointThere || (isOperationSymbol(binding.inputText.text.toString().last()) || binding.inputText.text.toString().last() == '.')) { }
            else {
                showText(binding.inputText.text.toString(), '.')
                isPointThere = true
            }
        }
        binding.buttonDevide.setOnClickListener {
            if ((isOperationSymbol(binding.inputText.text.toString().last())) || isOperationCompleted) { }
            else {
                firstValue = binding.inputText.text.toString().toDouble()
                showText(binding.inputText.text.toString(), '÷')
                isOperationCompleted = true
                isPointThere = false
                isDivideOperation = true
            }
        }
        binding.buttonMultyply.setOnClickListener {
            if ((isOperationSymbol(binding.inputText.text.toString().last())) || isOperationCompleted) { }
            else {
                firstValue = binding.inputText.text.toString().toDouble()
                showText(binding.inputText.text.toString(), 'x')
                isOperationCompleted = true
                isPointThere = false
                isMultiplyOperation = true
            }
        }
        binding.buttonPlus.setOnClickListener {
            if ((isOperationSymbol(binding.inputText.text.toString().last())) || isOperationCompleted) { }
            else {
                firstValue = binding.inputText.text.toString().toDouble()
                showText(binding.inputText.text.toString(), '+')
                isOperationCompleted = true
                isPointThere = false
                isPlusOperation = true
            }
        }
        binding.buttonMinus.setOnClickListener {
            if (isOperationCompleted || (isOperationSymbol(binding.inputText.text.toString().last()))) { }
            else {
                firstValue = binding.inputText.text.toString().toDouble()
                showText(binding.inputText.text.toString(), '-')
                isOperationCompleted = true
                isPointThere = false
                isMinusOperation = true
            }
        }
        binding.buttonSubmit.setOnClickListener {
            secondValue = getSecondValue(binding.inputText.text.toString())
            binding.resultText.text =
                when{
                isDivideOperation -> (firstValue / secondValue).toString()
                isMinusOperation -> (firstValue-secondValue).toString()
                isMultiplyOperation -> (firstValue * secondValue).toString()
                isPlusOperation -> (firstValue + secondValue).toString()
                    else -> 0.0.toString()
            }
        }
    }

    private fun showText(currentText: String, text: Char ){
        if(isOperationSymbol(text)) binding.inputText.text = "$currentText ${text.toString()} "
        else binding.inputText.text = "$currentText${text.toString()}"
    }
    private fun isOperationSymbol(symbol: Char): Boolean{
        return (symbol == '+' || symbol == '-' || symbol == 'x' || symbol == '÷')
    }
    private fun getSecondValue(text: String): Double{
        val index = 0
        for (i in text.indices){
            if (isOperationSymbol(text[i])) return text.substring(i+1).toDouble()
        }
        return -1.1
    }
}