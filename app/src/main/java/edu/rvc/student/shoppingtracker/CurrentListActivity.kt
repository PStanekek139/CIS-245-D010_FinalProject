package edu.rvc.student.shoppingtracker

import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_current_list.*

class CurrentListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_list)



        //bind text view used for testing
        val txtTest = findViewById<TextView>(R.id.txtTestList)

        //bind table layout
        val ll = findViewById<TableLayout>(R.id.t1)

        txtTest.text = ""

        //retrieve current list of entries
        val currentList = lookupList()


       // currentList.forEach {

        //}


        //not working:
        init(currentList)

        //val adapter = ArrayAdapter<ListEntry>(this, android.R.layout.simple_list_item_1,currentList)

        var entry = ListEntry(0,"",0,0f)
        currentList.forEach{
            //add to test text view at bottom
            txtTest.text = txtTest.text.toString() + "\n" + "Item: " + it.entryName
        }




    }

    //old test function that just displayed first item returned
    //fun lookupTest(): String {
    //    val dbHandler = DBHelper(this, null, null, 1)

    //    val entry = dbHandler.testCurrentListEntry()
    //    var result = ""

    //    if (entry != null) {
    //        result = entry.entryName.toString()

    //    } else {
    //        result = "No Items Found!"
    //    }
    //    return result
    //}

    //returns list of all current list entries
    fun lookupList() : MutableList<ListEntry>{
        val dbHandler = DBHelper(this, null, null, 1)
        val returnedList = dbHandler.getCurrentList()

        //strip off junk record
        returnedList.removeAt(0)
        return returnedList
    }

    //display list (not working)
    fun init(list : MutableList<ListEntry>){
        //bind header table layout
        val tHeader = findViewById<TableLayout>(R.id.tHeader)
        //bind table layout
        val ll = findViewById<TableLayout>(R.id.t1)

        //variables for dynamic table content
        var tr = TableRow(this)
        var lp : TableRow.LayoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)

        var txtid = TextView(this)
        var txtname = TextView(this)
        var txtqty = EditText(this)
        var txtprice = EditText(this)
        var btnpurchase = Button (this)
        var btnremove = Button(this)

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
        txtid.setPadding(10, 3, 50, 3)
        tr.addView(txtid)

        //add qty label
        txtid = TextView(this)
        txtid.text = "QTY"
        txtid.textSize = 14f
        txtid.setPadding(3, 3, 10, 3)
        tr.addView(txtid)

        //add price label
        txtid = TextView(this)
        txtid.text = "Price"
        txtid.textSize = 14f
        txtid.setPadding(3, 3, 10, 3)
        tr.addView(txtid)

        //add purchase label
        txtid = TextView(this)
        txtid.text = "  Purchase"
        txtid.textSize = 14f
        txtid.setPadding(3, 3, 10, 3)
        tr.addView(txtid)

        //add Remove label
        txtid = TextView(this)
        txtid.text = "  Remove"
        txtid.textSize = 14f
        txtid.setPadding(3, 3, 10, 3)
        tr.addView(txtid)

        //add row to table
        tHeader.addView(tr,0)

        //dynamically create a row for each current list entry
        list.forEach{
            tr = TableRow(this)
            tr.setLayoutParams(lp)



            //add id
            txtid = TextView(this)
            txtid.text = it.id.toString()
            txtid.width = 0
            txtid.textSize = 10f
            tr.addView(txtid)

            //add entry name
            txtname = TextView(this)
            txtname.text = it.entryName.toString()
            txtname.textSize = 14f
            txtname.setPadding(3, 3, 0, 3)
            tr.addView(txtname)

            //add qty field
            txtqty = EditText(this)
            txtqty.hint = "QTY"
            txtqty.setText(it.quantity.toString())
            txtqty.textSize = 12f
            txtqty.setPadding(3, 3, 0, 3)
            tr.addView(txtqty)

            //add price field
            txtprice = EditText(this)
            txtprice.hint = "PRICE"
            txtprice.setText(it.price.toString())
            txtprice.textSize = 12f
            txtprice.setPadding(3, 3, 3, 3)
            tr.addView(txtprice)

            //add purchase button
            btnpurchase = Button(this)
            btnpurchase.id = it.id
            btnpurchase.text = "PURCHASE" + btnpurchase.id.toString()
            btnpurchase.textSize = 10f
            btnpurchase.width = 20
            btnpurchase.height = TableRow.LayoutParams.WRAP_CONTENT
            btnpurchase.setOnClickListener{
                //need to somehow get ID
                
                //val myID = this.getID()
                //Toast.makeText(this, "Purchase: " + myID.toString(), Toast.LENGTH_LONG).show()
            }
            tr.addView(btnpurchase)

            //add remove button
            btnremove = Button (this)
            btnremove.text = "REMOVE"
            btnremove.id = it.id
            btnremove.textSize = 10f
            btnremove.width = 20
            btnremove.height = TableRow.LayoutParams.WRAP_CONTENT
            tr.addView(btnremove)

            ll.addView(tr,counter)
            counter++
        }
    }

    //add to purchase history & remove from current list
    fun purchase (id: Int) {

    }


}
