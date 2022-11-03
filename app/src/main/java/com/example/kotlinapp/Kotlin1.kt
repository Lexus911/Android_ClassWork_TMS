package com.example.kotlinapp

private const val ZERO = 0

fun outerFun(){ //является по умолчанию статической, т.к. находится вне класса
    println("I'm an outer fun")
}

class Kotlin1 {
    val country: String = "Poland"
    var city = "Warsaw"
    lateinit var address: String

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            println("Show must go on")

            val kotlinClass = KotlinClass("Alex", 31)
            val kotlinClass2 = KotlinClass("Belarus", "Brest", "street")

            val string = kotlinClass.getNameAndAge()
            println(string)
            println("${kotlinClass2.country} ${kotlinClass2.city} ${kotlinClass2.address}")


            val kotlin1 = Kotlin1()
//            kotlin1.country = " can't change"
            kotlin1.city = "Brestchester"
            kotlin1.address = "street"
            println("${kotlin1.address}")


        }
    }
    fun emptyFun():Unit{
        println("I'm an empty fun")
    }

    fun returnBoolean():Boolean = true
}

class KotlinClass(val name: String, var age: Int): Parent(), Behavior {
    var country: String = ""
    var city: String = ""
    var address: String = ""

    constructor() : this("", 1)
    constructor(country: String, city: String, address: String) : this() {
        this.country = country
        this.city = city
        this.address = address
    }



    fun getNameAndAge():String{
        return "$name $age"
    }

    override fun getHairColor() {
        super.getHairColor()
    }

    override fun getEyeColor() {
        TODO("Not yet implemented")
    }

}

open class Parent{
    open fun getHairColor(){

    }
}

interface Behavior{
    fun getEyeColor()
}