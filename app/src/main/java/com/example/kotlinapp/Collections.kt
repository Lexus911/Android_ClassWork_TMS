package com.example.kotlinapp

class Collections {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            //неизменямая коллекция (unmutable)
            val  collectionList = listOf<String>(
                "Hello",
                "bye",
                "world"
            )
            //collectionList.add()  так нельзя добавить

            //изменяемая коллекция (mutable)
            val collectionList2 = mutableListOf<String>()
            collectionList2.add("Show")
            collectionList2.add("must")
            collectionList2.add("go on")

            collectionList2.forEach{ words ->
                print("$words ")
            }

            println()

            for(i in 1..10){
                print("$i ")
            }

            val arrayList = arrayListOf<String>(
                "We",
                "will",
                "rock",
                "you"
            )
            println()

            for(word in arrayList){
                print("$word " )
            }
            println()

            for (i in 10 downTo 1 step 2){
                print("$i ")
            }
            println()

            for(i in 1..10){
                if(i == 2){
                println("Go to hell")
                    continue
                }
                println("$i ")
            }

            val collections = Collections()
            repeat(4){
                println(collections.getName())
            }

        }

    }

    fun  getName():String{
        return "We will rock you"
    }

}