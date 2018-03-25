package edu.rvc.student.shoppingtracker

/**
 * Created by pstanek on 3/25/2018.
 */
class ListEntry {
    var id: Int = 0
    var entryName: String? = null
    var quantity: Int = 0
    var price: Float = 0f

    constructor(id: Int, entryname: String, quantity: Int, price: Float) {
        this.id = id
        this.entryName = entryname
        this.quantity = quantity
        this.price = price
    }

    constructor(entryname: String, quantity: Int, price: Float) {
        this.entryName = entryname
        this.quantity = quantity
        this.price = price
    }
}