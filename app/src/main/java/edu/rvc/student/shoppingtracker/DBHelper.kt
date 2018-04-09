package edu.rvc.student.shoppingtracker

/**
 * Created by pstanek on 3/25/2018.
 */

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues
import java.lang.System.currentTimeMillis
import java.text.DateFormat
import java.time.LocalDateTime
import java.text.SimpleDateFormat

class DBHelper(context: Context, name: String?,
               factory: SQLiteDatabase.CursorFactory?, version: Int) :
        SQLiteOpenHelper(context, DATABASE_NAME,
                factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {

        //create 'CurrentList' table
        val CREATE_ENTRY_TABLE = ("CREATE TABLE " +
                TABLE_CURRENTLIST + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_ENTRYNAME
                + " TEXT," + COLUMN_QUANTITY + " INTEGER," +
                COLUMN_PRICE + " REAL" + ")")
        db.execSQL(CREATE_ENTRY_TABLE)

        //create 'HistoryList' table
        val CREATE_HISTORY_TABLE = ("CREATE TABLE " +
                TABLE_HISTORYLIST + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_ENTRYNAME
                + " TEXT," + COLUMN_QUANTITY + " INTEGER," +
                COLUMN_PRICE + " REAL," + COLUMN_PURCHASEDATE + " TEXT)")
        db.execSQL(CREATE_HISTORY_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                           newVersion: Int) {

    }

    companion object {

        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "ShoppingTrackerDB.db"
        val TABLE_CURRENTLIST = "CurrentList"
        val TABLE_HISTORYLIST = "HistoryList"

        val COLUMN_ID = "_id"
        val COLUMN_ENTRYNAME = "entryname"
        val COLUMN_QUANTITY = "quantity"
        val COLUMN_PRICE = "price"
        val COLUMN_PURCHASEDATE = "purchasedate"

    }

    //add to CurrentList
    fun addCurrentListEntry(listentry: ListEntry){
        val values = ContentValues()
        values.put(COLUMN_ENTRYNAME, listentry.entryName)
        values.put(COLUMN_QUANTITY, listentry.quantity)
        values.put(COLUMN_PRICE, listentry.price)

        val db = this.writableDatabase

        db.insert(TABLE_CURRENTLIST, null, values)
        db.close()
    }

    //return all items in CurrentList
    //must skip first returned result!
    fun getCurrentList():MutableList<ListEntry>{
        var entry = ListEntry(0,"SKIP",0,0f)
        var result : MutableList<ListEntry> = mutableListOf(entry)

        val query = "SELECT * FROM $TABLE_CURRENTLIST ORDER BY ENTRYNAME"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()){
            cursor.moveToFirst()
            entry = ListEntry(Integer.parseInt(cursor.getString(0)),cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getFloat(3))
            result.add(entry)
            while (cursor.moveToNext()){
                entry = ListEntry(Integer.parseInt(cursor.getString(0)),cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getFloat(3))
                result.add(entry)
            }
        }
        return result
    }

    fun getHistoryList():MutableList<ListEntry>{
        var entry = ListEntry(0,"SKIP", 0, 0f, "NEVER")
        var result: MutableList<ListEntry> = mutableListOf(entry)

        val query = "SELECT $COLUMN_ENTRYNAME, SUM($COLUMN_QUANTITY), AVG($COLUMN_PRICE), MAX($COLUMN_PURCHASEDATE)" +
                " FROM $TABLE_HISTORYLIST  GROUP BY $COLUMN_ENTRYNAME ORDER BY $COLUMN_ENTRYNAME"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()){
            cursor.moveToFirst()
            entry = ListEntry(0,cursor.getString(0), Integer.parseInt(cursor.getString(1)), cursor.getFloat(2), cursor.getString(3))
            result.add(entry)
            while (cursor.moveToNext()){
                entry = ListEntry(0,cursor.getString(0), Integer.parseInt(cursor.getString(1)), cursor.getFloat(2), cursor.getString(3))
                result.add(entry)
            }
        }

        return result

    }

    fun getHistoryDetailList(entryName: String):MutableList<ListEntry>{
        var entry = ListEntry(0,"SKIP", 0, 0f, "NEVER")
        var result: MutableList<ListEntry> = mutableListOf(entry)



        val query = "SELECT * FROM $TABLE_HISTORYLIST WHERE ENTRYNAME = '" + entryName + "' ORDER BY $COLUMN_PURCHASEDATE DESC"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()){
            cursor.moveToFirst()
            entry = ListEntry(0,cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getFloat(3), cursor.getString(4))
            result.add(entry)
            while (cursor.moveToNext()){
                entry = ListEntry(0,cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getFloat(3), cursor.getString(4))
                result.add(entry)
            }
        }


        return result
    }


    //return first item in CurrentList (for testing purposes)
    fun testCurrentListEntry():ListEntry?{
        val query = "SELECT * FROM $TABLE_CURRENTLIST"
        val db = this.writableDatabase
        val cursor  =db.rawQuery(query, null)

        var entry: ListEntry? = null

        if (cursor.moveToFirst()){
            cursor.moveToFirst()
            val id = Integer.parseInt(cursor.getString(0))
            val name = cursor.getString(1)
            val quantity = Integer.parseInt(cursor.getString(2))
            val price = cursor.getFloat(3)
            entry = ListEntry(id, name, quantity, price)
            cursor.close()
        }
        db.close()
        return entry
    }


    //delete from CurrentList (by ID)
    fun deleteCurrentListEntry(listID: Int): Boolean {
        var result = false
        val id = listID

        val db = this.writableDatabase
        db.delete(TABLE_CURRENTLIST, COLUMN_ID + " = ?",
                                        arrayOf(id.toString()))
        result = true
        db.close()

        return result
    }

    //add to HistoryList
    fun addHistoryListEntry(listentry: ListEntry){
        val values = ContentValues()
        values.put(COLUMN_ENTRYNAME, listentry.entryName)
        values.put(COLUMN_QUANTITY, listentry.quantity)
        values.put(COLUMN_PRICE, listentry.price)
        var thisTime = currentTimeMillis()
        values.put(COLUMN_PURCHASEDATE, DateFormat.getDateInstance(DateFormat.SHORT).format(thisTime).toString())




        val db = this.writableDatabase

        db.insert(TABLE_HISTORYLIST, null, values)
        db.close()
    }

    //delete from CurrentList (by ID)
    fun deleteHistoryListEntry(listID: Int): Boolean {
        var result = false
        val id = listID

        val db = this.writableDatabase
        db.delete(TABLE_HISTORYLIST, COLUMN_ID + " = ?",
                arrayOf(id.toString()))
        result = true
        db.close()

        return result
    }

    //delete all saved data - no going back!
    fun reinitializeDatabase():Boolean{
        var result = false

        val db = this.writableDatabase
        db.execSQL("DELETE FROM " + TABLE_CURRENTLIST)
        db.execSQL("DELETE FROM " + TABLE_HISTORYLIST)
        db.close()

        result = true

        return result
    }
}