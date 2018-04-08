package edu.rvc.student.shoppingtracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        var success = false


        //retrieve current list of entries
        val historyList = lookupList()

        //initialize (display) current list
        init(historyList)
    }

    //returns list of all current list entries
    fun lookupList() : MutableList<ListEntry>{
        val dbHandler = DBHelper(this, null, null, 1)
        val returnedList = dbHandler.getHistoryList()

        //strip off junk record
        returnedList.removeAt(0)
        return returnedList
    }
    fun init(list : MutableList<ListEntry>){
        //bind header table layout
        val tHeader = findViewById<TableLayout>(R.id.tHeader)
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
        txtid.textSize = 14f
        txtid.width = 100
        txtid.setPadding(3, 3, 6, 3)
        tr.addView(txtid)

        //add date label
        txtid = TextView(this)
        txtid.text = "Last Purchase"
        txtid.textSize = 14f
        txtid.width = 130
        txtid.setPadding(3, 3, 6, 3)
        tr.addView(txtid)

        //add qty label
        txtid = TextView(this)
        txtid.text = "Total QTY"
        txtid.textSize = 14f
        txtid.width = 60
        txtid.height = 60
        txtid.setPadding(3, 3, 0, 3)
        tr.addView(txtid)

        //add price label
        txtid = TextView(this)
        txtid.text = "Avg Price"
        txtid.textSize = 14f
        txtid.width = 60
        txtid.height = 60
        txtid.setPadding(3, 3, 3, 3)
        tr.addView(txtid)

        //add detail label
        txtid = TextView(this)
        txtid.text = "  Detail"
        txtid.textSize = 14f
        txtid.setPadding(3, 3, 30, 3)
        tr.addView(txtid)


        //add row to table
        tHeader.addView(tr,0)

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
            txtname.textSize = 14f
            txtname.width = 100
            txtname.tag = "NAME" + counter.toString()
            txtname.setPadding(3, 0, 6, 15)
            tr.addView(txtname)

            //add entry name
            txtDate = TextView(this)
            txtDate.text = it.purchasedate.toString()
            txtDate.textSize = 14f
            txtDate.width = 130
            txtDate.tag = "DATE" + counter.toString()
            txtDate.setPadding(3, 0, 6, 15)
            tr.addView(txtDate)

            //add qty field
            txtqty = TextView(this)
            txtqty.tag = "QTY" + counter.toString()
            txtqty.setText(it.quantity.toString())
            txtqty.textSize = 12f
            txtqty.width = 60
            txtqty.height = 50
            txtqty.setPadding(3, 3, 0, 3)
            tr.addView(txtqty)

            //add price field
            txtprice = TextView(this)
            txtprice.tag = "PRICE" + counter.toString()
            txtprice.setText(it.price.toString())
            txtprice.textSize = 12f
            txtprice.setPadding(3, 3, 3, 3)
            txtprice.width = 60
            txtprice.height = 50
            tr.addView(txtprice)

            //add purchase button
            btnDetail = Button(this)
            btnDetail.id = counter;
            btnDetail.text = "DETAILS"
            btnDetail.textSize = 10f
            btnDetail.tag = "D" + counter;
            btnDetail.width = 20

            btnDetail.height = TableRow.LayoutParams.WRAP_CONTENT
            tr.addView(btnDetail)

            //on-click code for Purchase buttons
            val btnD = tr.findViewWithTag<Button>("D" + counter);
            val name = tr.findViewWithTag<TextView>("NAME" + counter)
            btnD.setOnClickListener(){
                val myID = btnD.id;

                //add code to view details on an item
            }



            ll.addView(tr,counter)
            counter++
        } //end foreach
    }

}
