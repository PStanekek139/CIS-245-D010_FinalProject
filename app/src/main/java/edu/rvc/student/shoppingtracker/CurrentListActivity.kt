package edu.rvc.student.shoppingtracker

import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_current_list.*
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.util.DisplayMetrics
import android.widget.LinearLayout
import android.R.attr.button





class CurrentListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_list)

        val btnAdd = findViewById<Button>(R.id.btnAddNew)
        val txtNewName = findViewById<EditText>(R.id.txtNewName)
        val txtNewQty = findViewById<EditText>(R.id.txtNewQty)
        var success = false


        //retrieve current list of entries
        val currentList = lookupList()

        //initialize (display) current list
        init(currentList)


        btnAdd.setOnClickListener{
            //validate data
            if (txtNewName.text.toString() == ""){
                Toast.makeText(this, "Item Name Needed", Toast.LENGTH_LONG).show()
            } else if (txtNewQty.text.toString().toIntOrNull() !is Int){
                Toast.makeText(this, "Qty must be a whole number", Toast.LENGTH_LONG).show()
            } else if (txtNewName.text.toString().length > 24){
                Toast.makeText(this, "Item Name too long (24 char max)", Toast.LENGTH_LONG).show()
            } else   {
                //add to database - current list
                val dbHandler = DBHelper(this, null, null, 1)
                val newEntry = ListEntry(txtNewName.text.toString(), txtNewQty.text.toString().toInt(), 0.0f)
                val success = dbHandler.addCurrentListEntry(newEntry)

                //refresh table
                val currentList = lookupList()
                init(currentList)

                //display success message
                txtNewName.setText("")
                txtNewQty.setText("")
                hideKeyboard()
                Toast.makeText(this, "Item Added", Toast.LENGTH_LONG).show()
            }







        }



    }

    fun hideKeyboard() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
            // TODO: handle exception
        }

    }

    //returns list of all current list entries
    fun lookupList() : MutableList<ListEntry>{
        val dbHandler = DBHelper(this, null, null, 1)
        val returnedList = dbHandler.getCurrentList()

        //strip off junk record
        returnedList.removeAt(0)
        return returnedList
    }

    //display list
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
        txtid.textSize = 10f
        txtid.width = screenWidth / 4
        //txtid.width = 100
        txtid.setPadding(3, 3, 6, 3)
        tr.addView(txtid)

        //add qty label
        txtid = TextView(this)
        txtid.text = "QTY"
        txtid.textSize = 10f
        txtid.width = screenWidth / 8
        //txtid.width = 60
        txtid.setPadding(3, 3, 0, 3)
        tr.addView(txtid)

        //add price label
        txtid = TextView(this)
        txtid.text = "Price (each)"
        txtid.textSize = 10f
        txtid.width = screenWidth / 8
        //txtid.width = 60
        //txtid.height = 60
        txtid.setPadding(3, 3, 3, 0)
        tr.addView(txtid)

        //add purchase label
        txtid = TextView(this)
        txtid.text = "Purchase"
        txtid.width = screenWidth / 5
        txtid.textSize = 10f
        txtid.setPadding(3, 3, 30, 3)
        tr.addView(txtid)

        //add Remove label
        txtid = TextView(this)
        txtid.text = "Remove"
        txtid.width = screenWidth / 5
        txtid.textSize = 10f
        txtid.setPadding(3, 3, 10, 3)
        tr.addView(txtid)

        //add row to table
        ll.addView(tr,0)
        //tHeader.addView(tr,0)
        counter++

        //dynamically create a row for each current list entry
        list.forEach{
            tr = TableRow(this)
            tr.setLayoutParams(lp)
            tr.tag = it.id.toString()

            //add id
            txtid = TextView(this)
            txtid.text = it.id.toString()
            txtid.width = 0
            txtid.textSize = 10f
            tr.addView(txtid)

            //add entry name
            txtname = TextView(this)
            txtname.text = it.entryName.toString()
            txtname.textSize = 10f
            txtname.width = screenWidth / 4
            //txtname.width = 100
            txtname.tag = "NAME" + it.id.toString()
            txtname.setPadding(3, 0, 6, 15)
            tr.addView(txtname)

            //add qty field
            txtqty = EditText(this)
            txtqty.hint = "QTY"
            txtqty.tag = "QTY" + it.id.toString()
            txtqty.setText(it.quantity.toString())
            txtqty.textSize = 10f
            txtqty.width = screenWidth / 8
            //txtqty.width = 60
            //txtqty.height = 50
            txtqty.setPadding(3, 3, 0, 3)
            tr.addView(txtqty)

            //add price field
            txtprice = EditText(this)
            txtprice.hint = "PRICE"
            txtprice.tag = "PRICE" + it.id.toString()
            txtprice.setText(it.price.toString())
            txtprice.textSize = 10f
            txtprice.setPadding(3, 3, 3, 3)
            txtprice.width = screenWidth / 8
            //txtprice.width = 60
            //txtprice.height = 50
            tr.addView(txtprice)

            //add purchase button
            btnpurchase = Button(this)
            btnpurchase.id = it.id;
            btnpurchase.text = "PURCHASE"
            btnpurchase.textSize = 10f
            btnpurchase.tag = "P" + it.id.toString();
            btnpurchase.width = screenWidth / 5
            //btnpurchase.width = 20

            //btnpurchase.height = TableRow.LayoutParams.WRAP_CONTENT
            tr.addView(btnpurchase)

            //on-click code for Purchase buttons
            val btnP = tr.findViewWithTag<Button>("P" + it.id.toString());
            val name = tr.findViewWithTag<TextView>("NAME" + it.id.toString())
            val qty = tr.findViewWithTag<EditText>("QTY" + it.id.toString())
            val price = tr.findViewWithTag<EditText>("PRICE" + it.id.toString())
            btnP.setOnClickListener(){
                val myID = btnP.id;

                //data validation
                if (qty.text.toString().toIntOrNull() !is Int){
                    Toast.makeText(this, "Qty must be a whole number", Toast.LENGTH_LONG).show()
                } else if (price.text.toString().toFloatOrNull() !is Float){
                    Toast.makeText(this, "Price must be a valid number", Toast.LENGTH_LONG).show()
                } else {
                    //if both valid, process request
                    val nameString = name.text.toString()
                    val qtyInt = qty.text.toString().toInt()
                    val priceFloat = price.text.toString().toFloat()
                    val dbHandler = DBHelper(this, null, null, 1)

                    //add to history list
                    //create new ListEntry
                    val newEntry = ListEntry(nameString, qtyInt, priceFloat)
                    val successAdd = dbHandler.addHistoryListEntry(newEntry)

                    //delete from current list
                    val successDelete = dbHandler.deleteCurrentListEntry(myID);

                    //display confirmation and remove row from displayed current list
                    val ll = findViewById<TableLayout>(R.id.t1)
                    val myRow = ll.findViewWithTag<TableRow>(myID.toString())
                    val tv = myRow.findViewWithTag<TextView>("NAME" + myID.toString())
                    Toast.makeText(this, "Item Purchased: " + tv.text.toString(), Toast.LENGTH_LONG).show()
                    ll.removeView(myRow)
                }
            }

            //add remove button
            btnremove = Button (this)
            btnremove.text = "REMOVE"
            btnremove.id = it.id
            btnremove.textSize = 10f
            btnremove.tag = "R" + it.id.toString();
            btnremove.width = screenWidth / 5
            //btnremove.width = 20
            //btnremove.height = TableRow.LayoutParams.WRAP_CONTENT
            tr.addView(btnremove)

            //on-click code for Remove buttons
            val btnR = tr.findViewWithTag<Button>("R" + it.id.toString());
            btnR.setOnClickListener(){
                val myID = btnR.id;
                val dbHandler = DBHelper(this, null, null, 1)
                val success = dbHandler.deleteCurrentListEntry(myID);
                val ll = findViewById<TableLayout>(R.id.t1)
                val myRow = ll.findViewWithTag<TableRow>(myID.toString())
                val tv = myRow.findViewWithTag<TextView>("NAME" + myID.toString())
                Toast.makeText(this, "Item Removed: " + tv.text.toString(), Toast.LENGTH_LONG).show()
                ll.removeView(myRow)
            }


            ll.addView(tr,counter)
            counter++
        } //end foreach
    }



}
