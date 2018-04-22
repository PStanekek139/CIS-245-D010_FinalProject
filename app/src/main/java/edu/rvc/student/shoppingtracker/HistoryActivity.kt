package edu.rvc.student.shoppingtracker

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.*

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        var success = false


        //retrieve history list of entries
        val historyList = lookupList()

        //initialize (display) history list
        init(historyList)
    }

    //returns list of all history list entries
    fun lookupList() : MutableList<ListEntry>{
        val dbHandler = DBHelper(this, null, null, 1)
        val returnedList = dbHandler.getHistoryList()

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
        var btnDetail = Button(this)

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

        //add name label
        txtid = TextView(this)
        txtid.text = "Item"
        if (screenWidth == 480){
            txtid.textSize = 12f
        }
        else if (screenWidth < 480){
            txtid.textSize = 10f
        }
        else {
            txtid.textSize = 16f
        }
        //txtid.textSize = 12f
        txtid.width = screenWidth / 5
        //txtid.width = 100
        txtid.setPadding(3, 3, 6, 3)
        tr.addView(txtid)

        //add date label
        txtid = TextView(this)
        txtid.text = "Last"
        if (screenWidth == 480){
            txtid.textSize = 12f
        }
        else if (screenWidth < 480){
            txtid.textSize = 10f
        }
        else {
            txtid.textSize = 16f
        }
        //txtid.textSize = 12f
        txtid.width = screenWidth / 6
        //txtid.width = 130
        txtid.setPadding(3, 3, 6, 3)
        tr.addView(txtid)

        //add qty label
        txtid = TextView(this)
        txtid.text = "Total QTY"
        if (screenWidth == 480){
            txtid.textSize = 12f
        }
        else if (screenWidth < 480){
            txtid.textSize = 10f
        }
        else {
            txtid.textSize = 16f
        }
        //txtid.textSize = 12f
        txtid.width = screenWidth / 8
        //txtid.width = 60
        txtid.height = 60
        txtid.setPadding(3, 3, 0, 3)
        tr.addView(txtid)

        //add price label
        txtid = TextView(this)
        txtid.text = "Avg Price"
        if (screenWidth == 480){
            txtid.textSize = 12f
        }
        else if (screenWidth < 480){
            txtid.textSize = 10f
        }
        else {
            txtid.textSize = 16f
        }
        //txtid.textSize = 12f
        txtid.width = screenWidth / 6
        //txtid.width = 60
        txtid.height = 60
        txtid.setPadding(3, 3, 3, 3)
        tr.addView(txtid)

        //add detail label
        txtid = TextView(this)
        txtid.text = "Detail"
        txtid.width = screenWidth / 5
        if (screenWidth == 480){
            txtid.textSize = 12f
        }
        else if (screenWidth < 480){
            txtid.textSize = 10f
        }
        else {
            txtid.textSize = 16f
        }
        //txtid.textSize = 12f
        txtid.setPadding(3, 3, 30, 3)
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
            txtid.textSize = 10f
            tr.addView(txtid)

            //add entry name
            txtname = TextView(this)
            txtname.text = it.entryName.toString()
            if (screenWidth == 480){
                txtname.textSize = 12f
            }
            else if (screenWidth < 480){
                txtname.textSize = 10f
            }
            else {
                txtname.textSize = 16f
            }
            //txtname.textSize = 12f
            txtname.width = screenWidth / 5
            //txtname.width = 100
            txtname.tag = "NAME" + counter.toString()
            txtname.setPadding(3, 0, 6, 15)
            tr.addView(txtname)

            //add entry name
            txtDate = TextView(this)
            txtDate.text = it.purchasedate.toString()
            if (screenWidth == 480){
                txtDate.textSize = 12f
            }
            else if (screenWidth < 480){
                txtDate.textSize = 10f
            }
            else {
                txtDate.textSize = 16f
            }
            //txtDate.textSize = 12f
            txtDate.width = screenWidth / 6
            //txtDate.width = 130
            txtDate.tag = "DATE" + counter.toString()
            txtDate.setPadding(3, 0, 6, 15)
            tr.addView(txtDate)

            //add qty field
            txtqty = TextView(this)
            txtqty.tag = "QTY" + counter.toString()
            txtqty.setText(it.quantity.toString())
            if (screenWidth == 480){
                txtqty.textSize = 10f
            }
            else if (screenWidth < 480){
                txtqty.textSize = 8f
            }
            else {
                txtqty.textSize = 16f
            }
            //txtqty.textSize = 10f
            txtqty.width = screenWidth / 8
            //txtqty.width = 60
            txtqty.height = 50
            txtqty.setPadding(3, 3, 0, 3)
            tr.addView(txtqty)

            //add price field
            txtprice = TextView(this)
            txtprice.tag = "PRICE" + counter.toString()
            txtprice.setText("$" + "%.2f".format(it.price))
            if (screenWidth == 480){
                txtprice.textSize = 10f
            }
            else if (screenWidth < 480){
                txtprice.textSize = 8f
            }
            else {
                txtprice.textSize = 16f
            }
            //txtprice.textSize = 10f
            txtprice.setPadding(3, 3, 3, 3)
            txtprice.width = screenWidth / 6
            //txtprice.width = 60
            txtprice.height = 50
            tr.addView(txtprice)

            //add purchase button
            btnDetail = Button(this)
            btnDetail.id = counter;
            btnDetail.text = "DETAILS"
            btnDetail.tag = txtname.text
            //btnDetail.setBackgroundColor(Color.parseColor("#D69500"))
            if (screenWidth == 480){
                btnDetail.textSize = 10f
            }
            else if (screenWidth < 480){
                btnDetail.textSize = 8f
            }
            else {
                btnDetail.textSize = 16f
            }
            btnDetail.textSize = 10f
            btnDetail.width = screenWidth / 5
            //btnDetail.width = 20

            btnDetail.height = TableRow.LayoutParams.WRAP_CONTENT
            tr.addView(btnDetail)

            //on-click code for History Detail button
            val btnD = tr.findViewById<Button>(counter);
            val name = tr.findViewWithTag<TextView>("NAME" + counter)
            btnD.setOnClickListener(){
                val myTag = btnD.tag;

                //view details on an item
                val intent = Intent(this, HistoryDetail::class.java)
                //send data to history detail activity
                intent.putExtra("ItemName", myTag.toString())
                //start history detail activity
                startActivity(intent)
            }



            ll.addView(tr,counter)
            counter++
        } //end foreach
    }

}
