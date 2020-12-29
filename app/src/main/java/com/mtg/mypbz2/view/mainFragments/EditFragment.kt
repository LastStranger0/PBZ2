package com.mtg.mypbz2.view.mainFragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.mtg.mypbz2.R
import com.mtg.mypbz2.view.editActivities.ChooseDialog


class EditFragment : Fragment() {
    private lateinit var addButton: Button
    private lateinit var editButton: Button
    private lateinit var removeButton: Button
    private lateinit var mainView: View
    private lateinit var mainContext: Context

    private fun setValues(){
        addButton = mainView.findViewById(R.id.add_button)
        editButton = mainView.findViewById(R.id.edit_button)
        removeButton = mainView.findViewById(R.id.remove_button)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mainView = inflater.inflate(R.layout.fragment_edit, container, false)
        setValues()
        addButton.setOnClickListener {
            chooseButton(1)
        }
        removeButton.setOnClickListener {
            chooseButton(3)
        }
        editButton.setOnClickListener {
            chooseButton(2)
        }
        return mainView
    }

    private fun chooseButton(button: Int){
        val args = Bundle()
        val customDialog = ChooseDialog()
        args.putInt("button", button)
        customDialog.arguments = args
        activity?.let { customDialog.show(it.supportFragmentManager, "custom") }
    }




    companion object {
        fun newInstance(): EditFragment {
            return EditFragment()
        }
    }
}