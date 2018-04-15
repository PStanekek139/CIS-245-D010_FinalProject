package edu.rvc.student.shoppingtracker

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.util.DisplayMetrics



class HistoryDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_detail)

        //retrieve itemName value from HistoryActivity
        val itemName: String = intent.getStringExtra("ItemName")
        val heading = findViewById<TextView>(R.id.textView)

        //set heading
        heading.text = itemName

        var success = false


        //retrieve history detail
        val historyDetailList = lookupList(itemName)

        //initialize (display) history detail list
        init(historyDetailList)

    }

    //returns list of all history list entries
    fun lookupList(entryName: String) : MutableList<ListEntry>{
        val dbHandler = DBHelper(this, null, null, 1)
        val returnedList = dbHandler.getHistoryDetailList(entryName)

        //strip off junk record
        returnedList.removeAt(0)
        return returnedList
    }

    fun init(list : MutableList<ListEntry>){
        //get screen dimensions
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenWidth = displayMetrics.widthPixels

        //bind header table layout
        //val tHeader = findViewById<TableLayout>(R.id.tHeader)
        //bind table layout
        val ll = findViewById<TableLayout>(R.id.t1)

        //clear any existing rows in table
        ll.removeAllViews()

        //variables for dynamic table content
        var tr = TableRow(this)
        var lp : TableRow.LayoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)

        var txtid = TextView(this)
        var txtname = TextView(this)
        var txtDate = TextView(this)
        var txtqty = TextView(this)
        var txtprice = TextView(this)
        var txtpurchasedate = TextView(this)

        //row index counter variable
        var counter = 0


        //add header row
        tr = TableRow(this)
        tr.setLayoutParams(lp)

        //add id label
        txtid = TextView(this)
        txtid.text = "ID"
        txtid.width = 0
        txtid.textSize = 14f
        tr.addView(txtid)

        //add date label
        txtid = TextView(this)
        txtid.text = "Date"
        if (screenWidth <= 480){
            txtid.textSize = 14f
        }
        else {
            txtid.textSize = 18f
        }
        //txtid.textSize = 14f
        txtid.width = screenWidth / 4
        txtid.setPadding(3, 3, 6, 3)
        tr.addView(txtid)

        //add qty label
        txtid = TextView(this)
        txtid.text = "QTY"
        if (screenWidth <= 480){
            txtid.textSize = 14f
        }
        else {
            txtid.textSize = 18f
        }
        //txtid.textSize = 14f
        txtid.width = screenWidth / 4
        txtid.height = 60
        txtid.setPadding(20, 3, 0, 3)
        tr.addView(txtid)

        //add price label
        txtid = TextView(this)
        txtid.text = "Price"
        if (screenWidth <= 480){
            txtid.textSize = 14f
        }
        else {
            txtid.textSize = 18f
        }
        //txtid.textSize = 14f
        txtid.width = screenWidth / 4
        txtid.height = 60
        txtid.setPadding(3, 3, 3, 3)
        tr.addView(txtid)

        //add row to table
        //tHeader.addView(tr,0)
        ll.addView(tr,0)

        counter++

        //dynamically create a row for each current list entry
        list.forEach{
            tr = TableRow(this)
            tr.setLayoutParams(lp)
            tr.tag = it.id.toString()

            //add id
            txtid = TextView(this)
            txtid.text = counter.toString()
            txtid.width = 0
            if (screenWidth <= 480){
                txtid.textSize = 10f
            }
            else {
                txtid.textSize = 16f
            }
            //txtid.textSize = 10f
            tr.addView(txtid)

            //add entry date
            txtDate = TextView(this)
            txtDate.text = it.purchasedate.toString()
            if (screenWidth <= 480){
                txtDate.textSize = 14f
            }
            else {
                txtDate.textSize = 18f
            }
            //txtDate.textSize = 14f
            txtDate.width = screenWidth / 4
            txtDate.tag = "DATE" + counter.toString()
            txtDate.setPadding(3, 0, 6, 15)
            tr.addView(txtDate)

            //add qty field
            txtqty = TextView(this)
            txtqty.tag = "QTY" + counter.toString()
            txtqty.setText("     " + it.quantity.toString())
            if (screenWidth <= 480){
                txtqty.textSize = 12f
            }
            else {
                txtqty.textSize = 18f
            }
            //txtqty.textSize = 12f
            txtqty.width = screenWidth / 4
            txtqty.height = 50
            txtqty.setPadding(20, 3, 0, 3)
            tr.addView(txtqty)

            //add price field
            txtprice = TextView(this)
            txtprice.tag = "PRICE" + counter.toString()
            txtprice.setText("$" + "%.2f".format(it.price))
            if (screenWidth <= 480){
                txtprice.textSize = 12f
            }
            else {
                txtprice.textSize = 18f
            }
            //txtprice.textSize = 12f
            txtprice.setPadding(3, 3, 3, 3)
            txtprice.width = screenWidth / 4
            txtprice.height = 50
            tr.addView(txtprice)

            ll.addView(tr,counter)
            counter++
        } //end foreach
    }
}
