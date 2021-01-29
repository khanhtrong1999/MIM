package com.example.mim

class ComparePrices {
    companion object : Comparator<obj1.obj2> {

        override fun compare(a: obj1.obj2, b: obj1.obj2): Int = when {
            a.price != b.price -> a.price - b.price
            else -> a.price - b.price
        }
    }
}