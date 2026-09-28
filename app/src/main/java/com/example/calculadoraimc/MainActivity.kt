package com.example.calculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraIMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IMCScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun IMCScreen(modifier: Modifier = Modifier) {
    val alturaFieldValue = remember { mutableStateOf("") }
    val pesoFieldValue = remember { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier.fillMaxSize() ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // -- header --
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .height(160.dp)
                        .background(
                            color = colorResource(id = R.color.cor_app)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(
                            R.drawable.bmi
                        ),
                        contentDescription = "IMC",
                        modifier = Modifier.size(80.dp)
                            .padding(vertical = 16.dp)
                    )
                    Text(
                        text = "Calculadora IMC",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                }
                // -- formulário --
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 32.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .height(300.dp)
                            .fillMaxWidth()
                            .offset(y = (-32).dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F6F6)),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                            Text(
                                text = "Seus Dados",
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                color = colorResource(R.color.cor_app),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            OutlinedTextField(
                                value = alturaFieldValue.value,
                                onValueChange = {novoValor -> alturaFieldValue.value = novoValor},
                                modifier = Modifier.width(300.dp).align(Alignment.CenterHorizontally),
                                label = {Text("Altura")},
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = colorResource(R.color.cor_app),
                                    unfocusedBorderColor = colorResource(R.color.cor_app)
                                ),
                                singleLine = true,
                                shape = RoundedCornerShape(15.dp)
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            OutlinedTextField(
                                value = pesoFieldValue.value,
                                onValueChange = {novoValor -> pesoFieldValue.value = novoValor},
                                modifier = Modifier.width(300.dp).align(Alignment.CenterHorizontally),
                                label = {Text("Peso")},
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = colorResource(R.color.cor_app),
                                    unfocusedBorderColor = colorResource(R.color.cor_app)
                                ),
                                singleLine = true,
                                shape = RoundedCornerShape(15.dp)
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            Button(
                                onClick = {},
                                modifier = Modifier.width(300.dp),
                                colors = ButtonDefaults.buttonColors(colorResource(R.color.cor_app))

                            ) {
                                Text(text = "Calcular")
                            }

                        }

                    }

                }

            }
        }
        // -- card resultado --

    }
}

@Composable
fun Result(pesoStr: String, alturaStr : String){
    val peso = pesoStr.toFloatOrNull()
    val altura = alturaStr.toFloatOrNull()
    val mensagem : String
    if (peso != null && altura != null && altura > 0){
        mensagem = calcularImc(peso, altura)
    }else{
        mensagem = ""
    }


    Row() {
        Text(mensagem, fontSize = 20.sp)
    }
}

fun calcularImc(peso: Float, altura: Float): String{
    val imc = peso / (altura*altura)
    var resultado: String
    if (imc < 18.5f){
        resultado = "Abaixo do peso"
    }else if(imc in 18.5f..<25.0f){
        resultado = "Peso Ideal"
    }else if(imc in 25.0f..<30.0f){
        resultado = "Levemente Acima do Peso"
    }else if(imc in 30.0f..<35.0f){
        resultado = "Obesidade Grau I"
    }else if (imc in 35.0f..<40.0f){
        resultado = "Obesidade Grau II"
    }else if(imc >= 40.0f){
        resultado = "Obesidade Grau III"
    }else{
        resultado = "Dados Inválidos"
    }
    return resultado
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculadoraIMCTheme {
        IMCScreen()
    }
}


