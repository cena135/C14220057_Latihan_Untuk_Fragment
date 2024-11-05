package com.example.latihanuntukfragment

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import fSatu

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var viewModel: SharedViewModel

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val mFragmentManager = supportFragmentManager
        val mfSatu = fSatu()

        if (mFragmentManager.findFragmentByTag(fSatu::class.java.simpleName) == null) {
            mFragmentManager
                .beginTransaction()
                .add(R.id.frameContainer, mfSatu, fSatu::class.java.simpleName)
                .commit()
        }
        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        viewModel.currentNumber.value = 99


        findViewById<Button>(R.id.btnHal1).setOnClickListener {
            viewModel.currentNumber.value = viewModel.currentNumber.value?.minus(1)

            loadFragment(fSatu())
        }

        findViewById<Button>(R.id.btnHal2).setOnClickListener {
            viewModel.currentNumber.value = viewModel.currentNumber.value?.minus(2)
            loadFragment(fDua())
        }

        findViewById<Button>(R.id.btnHal3).setOnClickListener {
            viewModel.currentNumber.value = viewModel.currentNumber.value?.minus(3)
            loadFragment(fTiga())
        }

    }
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}