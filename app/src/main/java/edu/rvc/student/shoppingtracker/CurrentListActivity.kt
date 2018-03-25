package edu.rvc.student.shoppingtracker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class CurrentListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_list)


        val txtTest = findViewById<TextView>(R.id.txtTestList)

        txtTest.text = ""

        val currentList = lookupList()

        init(currentList)

        //val adapter = ArrayAdapter<ListEntry>(this, android.R.layout.simple_list_item_1,currentList)

        var entry = ListEntry(0,"",0,0f)
        currentList.forEach{

            txtTest.text = txtTest.text.toString() + "\n" + "Item: " + it.entryName
        }


    }

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

    fun lookupList() : MutableList<ListEntry>{
        val dbHandler = DBHelper(this, null, null, 1)
        val returnedList = dbHandler.getCurrentList()

        //strip off junk record
        returnedList.removeAt(0)
        return returnedList
    }

    //display list
    fun init(list : MutableList<ListEntry>){
        //val ll = findViewById<TableLayout>(R.id.t1)
        var row : TableRow
        var lp : TableRow.LayoutParams
        var txtid : TextView
        var txtname : TextView
        var txtqty : EditText
        var txtprice : EditText
        var btnpurchase : Button
        var btnremove : Button

        list.forEach{
            lp = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
            //row.setLayoutParams(lp)
        }
    }
}
