package com.cis.android_sqlite

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val DB_NAME = "todo.db"

        Add.setOnClickListener{
            val dbHelper = DBHelper(this,DB_NAME,null,1)
            val newTask:Task= Task(editText2.text.toString())
            dbHelper.addTask(newTask)

            Toast.makeText(this,editText2.text.toString() + "add to database",
                Toast.LENGTH_SHORT).show()
        }
        Read.setOnClickListener{
            val dbHelper = DBHelper(this,DB_NAME,null,1)
            val data:Cursor? = dbHelper.getAllTask()

            data!!.moveToFirst()

           displayText.text = ""
            displayText.append(data.getString(data.getColumnIndex("taskname")))

            while (data.moveToNext()){
                displayText.append("\n")
                displayText.append(data.getString(data.getColumnIndex("id"))+" "+data.getString(data.getColumnIndex("taskname")))
            }
            data.close()
        }
        Delete.setOnClickListener {
            val input = editText2.text.toString()
            val dbHelper =DBHelper(this,DB_NAME,null,1)
            val result =dbHelper.deleteTask(input.toInt())
        }
        Edit.setOnClickListener {
            // 1,NewTask
            val input = editText2.text.toString()
            val datas = input.split(",")
            val task:Task =Task(datas[1])
            task.id = datas[0].toString().toInt()
            val dbHelper =DBHelper(this,DB_NAME,null,1)
            val result =dbHelper.updateTask(task)
        }


    }
}
