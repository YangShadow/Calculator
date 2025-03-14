package com.boycoder.calculator

import java.util.InputMismatchException
import kotlin.system.exitProcess

fun main(){
    val cal = CalV2();
    cal.start();
}

class CalV2() {
    val help = """
    1.请输入算式，如1 + 1
    2.要用空格隔开 """.trimIndent()

    val exit = "exit"

     fun start(){
        while (true) {
            println(help)
            val input = readln() ?:continue
            val result = cal(input)
            if(result == null) {
                println("input error!")
                continue
            }
            println("$input = $result")
        }
    }

    private fun cal(input: String) : Int?{
        if(shouldExit(input )) exitProcess(0)
        val exp = parseExp(input)?:return null
        val left = exp.left
        val oprator = exp.oprator
        val right = exp.right
        return when (oprator) {
            Opration.ADD -> add(left,right)
            Opration.SUB -> sub(left,right)
            Opration.MUL -> mul(left,right)
            Opration.DIV -> div(left,right)
        }
    }

    private fun parseExp(input: String) :Exp?{
        val op = parseOpration(input)?:return null
        val list = input.split(op.value)
        if(list.size != 2) return null
        return Exp (
            left = list[0].trim(),
            oprator = op,
            right = list[1].trim()
        )
    }

    private  fun parseOpration(input: String) : Opration?{
        if(input == null) return null
        Opration.values().forEach {
            if(input.contains(it.value)) {
                return it
            }
        }
        return null
    }

    private fun add(left:String,right:String):Int{
        return left.toInt() + right.toInt()
    }

    private fun sub(left:String,right: String):Int{
        return left.toInt() - right.toInt()
    }

    private fun mul(left:String,right: String):Int{
        return left.toInt() * right.toInt()
    }

    private fun div(left:String,right: String):Int{
        return left.toInt() / right.toInt()
    }

    private fun shouldExit(input: String) :Boolean {
        return input == exit
    }

}


data class Exp(
    val left : String,
    val oprator : Opration,
    val right : String
)


enum class Opration(val value:String) {
    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/")
}