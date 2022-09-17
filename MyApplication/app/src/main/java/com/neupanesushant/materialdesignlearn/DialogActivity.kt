package com.neupanesushant.materialdesignlearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.neupanesushant.materialdesignlearn.databinding.ActivityDialogBinding

class DialogActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShowAlertDialog.setOnClickListener {
            showAlertDialog()
        }

        binding.btnShowSimpleDialog.setOnClickListener {
            showSimpleDialog()
        }
        binding.btnShowConfirmationDialog.setOnClickListener {
            showConfirmationDialog()
        }
    }

    fun showAlertDialog(){
        MaterialAlertDialogBuilder(this, R.style.MaterialDialogs)
            .setTitle("Title")
            .setMessage("This is the message to be displayed")
            .setNeutralButton("Cancle"){dialog, which ->
                dialog.cancel()
            }
            .setNegativeButton("Decline"){dialog, which ->
                Toast.makeText(this, "The action is declined", Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Accept"){dialog, which ->
                Toast.makeText(this, "The action is accepted", Toast.LENGTH_SHORT).show()
            }
            .show()
    }

    fun showConfirmationDialog(){
        val items = arrayOf("Item 1 ", "Item 2", "Item 3", "Item 4", "Item 5")
        var checkedItem = -1
        MaterialAlertDialogBuilder(this, R.style.MaterialDialogs)
            .setTitle("Title")
            .setNeutralButton("Cancle"){dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton("Submit"){dialog, which ->
                Toast.makeText(this, "The selected item is : " + items.get(checkedItem), Toast.LENGTH_SHORT).show()
            }
            .setSingleChoiceItems(items, checkedItem){dialog, which ->
                checkedItem = which
            }
            .show()

        //for multiple create array of checked items and perform actions accordingly
    }

    fun showSimpleDialog(){

        val items = arrayOf("Item 1", "Item 2", "Item 3")
        MaterialAlertDialogBuilder(this, R.style.MaterialDialogs)
            .setTitle("Title")
            .setItems(items){dialog, position ->
                val current = items.get(position)
                Toast.makeText(this, "Clicked ${items.get(position)}", Toast.LENGTH_SHORT).show()
            }
            .show()
    }
}
