package com.example.gasolinaoualcoolapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.gasolinaoualcoolapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.buttonCalcular

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var etGasolina: EditText
    private lateinit var etAlcool: EditText
    private lateinit var btCalcular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializando por meio da viewBinding Manualmente (usando private lateinit):
        startingComponents()

        btCalcular.setOnClickListener { calcularPreco() }

        //Inicializando as Views de Forma Padrão pelo findViewById:
        /*val precoAlcool = findViewById<EditText>(R.id.editTextPrecoAlcool)
        val precoGasolina = findViewById<EditText>(R.id.editTextPrecoGasolina)*/

        //Inicializando as Views por Meio da Extensão/Plugin do Kotlin para viewbinding:
        /*val precoAlcool = editTextPrecoAlcool.text.toString()
        val precoGasolina = editTextPrecoGasolina.text.toString()*/
    }

    fun calcularPreco() {

        val precoGasolina = etGasolina.text.toString()
        val precoAlcool = etAlcool.text.toString()

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
            Senão melhor utilizar álcool:
            (0,7 seria 70% na lógica)
         */
        val resultadoPreco = valorAlcool / valorGasolina
        if (resultadoPreco >= 0.7) {
            textViewResultado.text = "Melhor Utilizar Gasolina!!!"
        } else if (resultadoPreco < 0.7) {
            textViewResultado.text = "Melhor Utilizar Álcool!!!"
        }

    }

    private fun startingComponents(){
        etGasolina = binding.editTextPrecoGasolina
        etAlcool = binding.editTextPrecoAlcool
        btCalcular = binding.buttonCalcular
    }

}