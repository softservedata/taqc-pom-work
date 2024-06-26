package com.softserve.edu

import java.util.Arrays

//class First {
//}

fun main() {
    println("Hello, Fifteen!")
    // /*
    //val someList : List<String> = listOf("I", "Am", "Definitely", "Immutable")
    //println(someList.joinToString(" "))
    //
    //val array = arrayOf(1, 2, 3, 4, 5, 6, "_")
    //val array = arrayOf<String>("__").forEach { i -> (i + 1).toString() }
    //val array = arrayOf(1, 2, 3, 4, 5)
    //val array = Array<Int>(16, { i -> i + 1 })
    //val array = IntArray(16) { i -> i + 1 }
    //val array = Array<String>(16, { i -> (i + 1).toString() })
    val array = Array<String>(16) { i -> (i + 1).toString() }
    array[15] = "__"
    //array.shuffle()
    //
    val arr = array.copyOf()
    //val arr = Array<String>(16, { i -> (i + 1).toString() })
    arr.shuffle()
    //println(Arrays.toString(array))
    //println(array.contentToString())
    println("origin: " + array.joinToString(" "))
    println("shuffl: " + arr.joinToString(" "))
    arr.sortWith(compareBy(String::length).then(String::compareTo))
    println("sorted: " + arr.joinToString(" "))
    println("Compare: " + arr.equals(array))
    println("Compare: " + array.contentDeepEquals(arr))
    // */
    /*
    //val arr = Array<String>(16) { i -> (i + 1).toString() }
    //val arr = arrayOf<String>("__")
    //(0..15).forEach { i -> arr += i }
    println(arr)
    */
    //
    val nearRow = arrayOf<Int>(-1, 1, 0, 0)
    val nearColumn = arrayOf<Int>(0, 0, -1, 1)
    var box = arrayOf<Array<String>>()
    for (i in 0..3) {
        var row = arrayOf<String>()
        for (j in 0..3) {
            //row[j] = arr[i * 4 + j]
            row += arr[i * 4 + j]
        }
        box += row
    }
    //
    val boxOriginal = box.copyOf()
    println("equals box: " + box.equals(boxOriginal))
    println("contentDeepEquals box: " + box.contentDeepEquals(boxOriginal))
    //
    /*
    var count = 0;
    var isWin = false
    while (!isWin && count < 10) {
        //(1..20).forEach { print("-") }
        println("-".repeat(20))
        for (row in box) {
            print("|")
            for (value in row) {
                //print("$value")
                print(String.format("%4s", value))
                //var str = String.Companion.format("%4s", value)
                //print(str)
            }
            println("  |")
        }
        println("-".repeat(20))
        //
        var num = 0
        var isMove = false
        do {
            do {
                print("num = ")
                num = Integer.valueOf(readLine()!!) // readln  to int or null ?:
                //println("\tnum = " + num)
            } while ((num < 1) || (num > 15)) // num ! in 1..15
            //
            // Find position
            var row = -1
            var column = -1
            for (i in 0..3) {
                for (j in 0..3) {
                    if (box[i][j].equals(num.toString())) {
                        row = i
                        column = j
                    }
                }
            }
            //
            //println("\trow = " + row + "  column = " + column)
            // Check move
            for (k in 0..3) {
                val i = nearRow[k];
                val j = nearColumn[k];
                //println("\ti = " + i + "  j = " + j + "  rewiew row = " + (row + i) + "  review column = " + (column + j))
                if (((row + i) >= 0) && ((row + i) < 4)
                        && ((column + j) >= 0) && ((column + j) < 4)
                        && (box[row + i][column + j].equals("__"))) {
                    box[row + i][column + j] = num.toString();
                    box[row][column] = "__"
                    isMove = true
                }
            }
            //println("\tisMove = " + isMove)
            if (!isMove) {
                println("\tNO MOVE! Select a valid number")
            }
        } while (!isMove)
        // New move
        count++
        //
        //isWin = box.contentDeepEquals(boxOriginal)
        //println("\tbox.contentDeepEquals(boxOriginal) = $isWin")
        //
//        isWin = true
//        for (i in 0..3) {
//            for (j in 0..3) {
//                if (box[i][j].)
//            }
//        }
    }
    println("\tNumber of moves = $count")
    */
}