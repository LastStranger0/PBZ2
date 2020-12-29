package com.mtg.mypbz2.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mtg.mypbz2.R
import com.mtg.mypbz2.view.editActivities.AddCarActivity
import com.mtg.mypbz2.view.editActivities.AddInspectionActivity
import com.mtg.mypbz2.view.editActivities.AddPoliceActivity
import com.mtg.mypbz2.view.editActivities.SearchEditActivity
import com.mtg.mypbz2.view.mainFragments.EditFragment
import com.mtg.mypbz2.view.mainFragments.SearchFragment

class MainActivity : AppCompatActivity() {

    private var editFragment = EditFragment.newInstance()
    private var searchFragment = SearchFragment.newInstance()
    private lateinit var bottomNavigationView: BottomNavigationView


    private var bottomListener = BottomNavigationView.OnNavigationItemSelectedListener{item ->
        when(item.itemId){
            R.id.edit_bar ->{
                loadFragment(editFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.see_bar ->{
                loadFragment(searchFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener false
    }

    private fun loadFragment(fragment: Fragment) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment, fragment)
        ft.commit()
    }

    private fun setValues(){
        bottomNavigationView = findViewById(R.id.main_navigation)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setValues()
        loadFragment(editFragment)
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomListener)

    }

    fun getDialog(number: Int){
        when(number){
            10 -> {
                val intent = Intent(this, AddCarActivity::class.java)
                intent.putExtra(AddInspectionActivity.CAR_POLICE, number)
                startActivity(intent)
            }
            11 -> {
                val intent = Intent(this, AddPoliceActivity::class.java)
                startActivity(intent)
            }
            12 -> {
                val intent = Intent(this, AddInspectionActivity::class.java)
                startActivity(intent)
            }
            20 ->{
                val intent = Intent(this, SearchEditActivity::class.java)
                intent.putExtra(AddInspectionActivity.CAR_POLICE, number)
                startActivity(intent)
            }
            21 ->{
                val intent = Intent(this, SearchEditActivity::class.java)
                intent.putExtra(AddInspectionActivity.CAR_POLICE, number)
                startActivity(intent)
            }
            22 ->{
                val intent = Intent(this, SearchEditActivity::class.java)
                intent.putExtra(AddInspectionActivity.CAR_POLICE, number)
                startActivity(intent)
            }
            30 ->{
                val intent = Intent(this, SearchEditActivity::class.java)
                intent.putExtra(AddInspectionActivity.CAR_POLICE, number)
                startActivity(intent)
            }
            31 ->{
                val intent = Intent(this, SearchEditActivity::class.java)
                intent.putExtra(AddInspectionActivity.CAR_POLICE, number)
                startActivity(intent)
            }
            32 ->{
                val intent = Intent(this, SearchEditActivity::class.java)
                intent.putExtra(AddInspectionActivity.CAR_POLICE, number)
                startActivity(intent)
            }
        }
    }
}