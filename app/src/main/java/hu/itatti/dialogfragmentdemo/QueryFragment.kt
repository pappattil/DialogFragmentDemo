package hu.itatti.dialogfragmentdemo

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import java.lang.RuntimeException

class QueryFragment:DialogFragment() {

    interface OnQueryFragmentAmswer{
        fun onPositiveSelected(text:String)
        fun onNegativeSelected()
    }

    private var onQueryFragmentAmswer: OnQueryFragmentAmswer?=null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //val message = arguments.getString(MainActivity.KEY_MSG)

        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        val inflater = LayoutInflater.from(getContext())
        val dialogLayout = inflater.inflate(R.layout.layout_dialog, null)
        val etName = dialogLayout.findViewById<EditText>(R.id.etName)
        alertDialogBuilder.setView(dialogLayout)

        alertDialogBuilder.setTitle("Please Read this TITLE!")
        alertDialogBuilder.setMessage("Please Read this MESSAGE!")


        alertDialogBuilder.setPositiveButton(
            "Okay",
            DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
                onQueryFragmentAmswer!!.onPositiveSelected((etName.text.toString()))
            })
        alertDialogBuilder.setNegativeButton(
            "Nope",
            DialogInterface.OnClickListener { dialogInterface, i ->
                onQueryFragmentAmswer!!.onNegativeSelected()
                dialogInterface.dismiss()

            })


        return alertDialogBuilder.create()


    }




    override fun onAttach(context: Context)      {
        super.onAttach(context)
        if (context is OnQueryFragmentAmswer) {
            onQueryFragmentAmswer = context
        }else{
            throw RuntimeException(
            "This Activity is not implementing the "+
            "OnMessageFragmentAnswer interface"
            )
        }
    }

}




