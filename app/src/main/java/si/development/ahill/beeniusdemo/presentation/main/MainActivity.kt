package si.development.ahill.beeniusdemo.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import si.development.ahill.beeniusdemo.R
import si.development.ahill.beeniusdemo.data.database.BeeniusDemoDatabase

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.navigationHostFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        BeeniusDemoDatabase.destroyInstance()
    }

    override fun onSupportNavigateUp() =
        navController.navigateUp() || super.onSupportNavigateUp()
}
