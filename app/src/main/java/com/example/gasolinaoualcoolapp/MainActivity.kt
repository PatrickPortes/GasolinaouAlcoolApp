package com.example.gasolinaoualcoolapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calcularPreco(view: View) {

        //Utilizando métodos padrões:
        //val precoAlcool = findViewById<EditText>(R.id.editTextPrecoAlcool)
        //val precoGasolina = findViewById<EditText>(R.id.editTextPrecoGasolina)

        //Utilizando o plugin/extensão do kotlin para viewbinding:
        val precoAlcool = editTextPrecoAlcool.text.toString()
        val precoGasolina = editTextPrecoGasolina.text.toString()

        val validaCampos = validarCampos(precoAlcool, precoGasolina)

        if (validaCampos == true) {

            calcularMelhorPreco(precoAlcool, precoGasolina)

        } else {

            //Outra maneira que poderia ser utilizada é o método setText
            textViewResultado.text = "Preencha os Campos Primeiro!!!"
        }

    }

    fun validarCampos(precoAlcool: String, precoGasolina: String): Boolean {

        var camposValidados: Boolean = true
        if (precoAlcool == null || precoAlcool.equals("")) {
            camposValidados = false
        } else if (precoGasolina == null || precoGasolina.equals("")) {
            camposValidados = false
        }

        return camposValidados
    }

    fun calcularMelhorPreco(precoAlcool: String, precoGasolina: String) {

        //Convertendo Texto para Número para Calcular
        val valorAlcool = precoAlcool.toDouble()
        val valorGasolina = precoGasolina.toDouble()

        /*
            Realizando o Cálculo (Álcool / Gasolina)
            Se o resultado for >= 0,7 = utilizar gasolina
            Senão melhor utilizar álcool
         */
        val resultadoPreco = valorAlcool / valorGasolina
        if (resultadoPreco >= 0.7) {
            textViewResultado.text = "Melhor Utilizar Gasolina!!!"
        } else if (resultadoPreco < 0.7) {
            textViewResultado.text = "Melhor Utilizar Álcool!!!"
        }

    }

}