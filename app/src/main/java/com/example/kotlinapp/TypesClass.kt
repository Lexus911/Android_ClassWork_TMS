package com.example.kotlinapp

class TypesClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val child: Child = Child()
            if (child is Parent2) {
                child.sleep()
                child.walk()
            }

            if (child is Child) {
                child.sleep()
                child.walk()
            }

            val parent2 = child as Parent2 //строгое приведение типа
            if (parent2 is Parent2) {
                //parent2.sleep()
                parent2.walk()
            }

            if (parent2 is Child) {//проверка типа
                parent2.sleep()
                parent2.walk()
            }
        }
    }

    open class Parent2 {
        open fun walk() {
            println("Parent is walking...")
        }
    }

    class Child : Parent2() {

        override fun walk() {
            println("Child is walking...")
        }

        fun sleep() {
            println("Child is sleeping...")
        }
    }
}