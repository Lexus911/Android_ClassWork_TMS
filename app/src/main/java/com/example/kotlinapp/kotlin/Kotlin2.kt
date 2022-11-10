package com.example.kotlinapp.kotlin

class Kotlin2 {

    val nullable: Int? = null

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {

        val first = First()
        val first2 = First()

        val second = Second
        val second2 = Second

        println("${first.hashCode()} ${first2.hashCode()}")
        println("${first.navigate().hashCode()} ${first2.navigate().hashCode()}")

        println("${second.hashCode()} ${second2.hashCode()}")

        val third = Third().navigate()
        val third2 = Third.walk()

        val values = Values(0, "name")

        println(values.name)

            val kotlin2 = Kotlin2()
            var nonNullableValue: Int = 0 // не null переменная

            val nullableValue: Int? = kotlin2.nullable  // null переменная

            //1 способ
            if(nullableValue != null){ // проверка что наша null переменная не null
                nonNullableValue = nullableValue
            }else{
    //            nonNullableValue = nullableValue // невозможно присвоить null не null-переменной
            }

            //2 способ
            nullableValue?.let{
                nonNullableValue = it
            }

            //3 способ
            nonNullableValue = nullableValue ?: 0 // если значение переменной nullable - null, то выведт 0

            //4 способ
            nonNullableValue = nullableValue!!

            //5 способ
            val outer = Outer(Inner("value in inner"))
            val value: String = outer.inner?.value ?: "" // умолчанию выдаст пустую строку вместо ошибки
        }
    }
}

class First{
    fun navigate(){
        println("navigating...")
    }
}

object Second{

}

class Third{
    fun navigate(){
        println("navigating...")
    }

    companion object{
        fun walk(){
            println("I'm walking")
        }
    }
}

data class Values(
    val number: Int,
    val name: String? = "have no string"
)

data class Outer(val inner: Inner?)
data class Inner(val value: String)