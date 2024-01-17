package com.example.components.core.extensions

object NumbersDehault {
    const val MASK_CEP  = "#####-###"
    const val MASK_CNPJ = "##.###.###/####-##"
    const val MASK_CPF  = "###.###.###-##"

    const val INPUT_LENGTH_CEP  = 8
    const val INPUT_LENGTH_CNPJ = MASK_CNPJ.length - 4
    const val INPUT_LENGTH_CPF  = MASK_CPF.length  - 3
}