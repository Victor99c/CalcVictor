package com.example.calcvictor


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.calcvictor.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var firstValue: Double = 0.0
    var operator: Char = '0'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val op = binding.root
        setContentView(op)

        binding.btnLimpiar.setOnClickListener {
            binding.editTextNumber.text.clear()
        }

        binding.btnBorrar.setOnClickListener {
            binding.editTextNumber.setText(binding.editTextNumber.text.dropLast(1))
        }
        binding.btnResultado.setOnClickListener {
            when(operator){
                '*' -> {
                    binding.editTextNumber.setText("${firstValue * binding.editTextNumber.text.toString().toDouble()}")
                }
                '/' -> {
                    binding.editTextNumber.setText("${firstValue / binding.editTextNumber.text.toString().toDouble()}")
                }
                '+' -> {
                    binding.editTextNumber.setText("${firstValue + binding.editTextNumber.text.toString().toDouble()}")
                }
                '-' -> {
                    binding.editTextNumber.setText("${firstValue - binding.editTextNumber.text.toString().toDouble()}")
                }
                else -> {
                    binding.editTextNumber.setText("SYNTAX ERROR")
                }
            }
            operator = '0'
        }
        binding.btnPorcentaje.setOnClickListener {
            binding.editTextNumber.setText(percentage(operator))
            operator = '0'
        }

    }

    fun percentage(OperatorToUse: Char): String {
        when(OperatorToUse){
            '+' -> {
                return "${firstValue + (firstValue*binding.editTextNumber.text.toString().toDouble()/100)}"
            }
            '-' -> {
                return "${firstValue - (firstValue*binding.editTextNumber.text.toString().toDouble()/100)}"
            }
            '0' -> {
                return "${binding.editTextNumber.text.toString().toDouble()/100}"
            }
            else -> {
                return "SYNTAX ERROR"
            }
        }
    }

    fun numberButtonClicked(view: View){
        val btn = view as Button

        if(btn.id.equals(binding.btnPunto.id)){
            if(!binding.editTextNumber.text.contains('.')){
                binding.editTextNumber.text.append(btn.text)
            }
        }else{
            binding.editTextNumber.text.append(btn.text)
        }
    }

    fun operatorButtonClicked(view: View){
        val btn = view as Button

        operator = when(btn.id){
            binding.btnMultiplicacion.id -> {'*'}
            binding.btnDivision.id -> {'/'}
            binding.btnSuma.id -> {'+'}
            binding.btnResta.id -> {'-'}
            else -> {'0'}
        }

        firstValue = binding.editTextNumber.text.toString().toDouble()
        binding.editTextNumber.text.clear()
    }

}
