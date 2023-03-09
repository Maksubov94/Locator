package com.maksubov.locator

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.maksubov.locator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

  //  private  val logger = Logger(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.container)
        setupActionBarWithNavController(navController)
        val colorDrawable = ColorDrawable(getColor(R.color.blue))
        supportActionBar?.setBackgroundDrawable(colorDrawable)
        askPermissions()
        startForegroundService(Intent(this, FgLocationService::class.java))


//        stopService(Intent(this,FgLocationService::class.java))

        // а так можно?
      //  startService(Intent(this,FgLocationService::class.java))
    }

    private fun askPermissions() {
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if(it.getOrDefault(android.Manifest.permission.ACCESS_FINE_LOCATION, false) &&
                    it.getOrDefault(android.Manifest.permission.ACCESS_COARSE_LOCATION, false)&&
                it.getOrDefault(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION,false)){
                LocationSource.startListening(this)
            }
        }.launch(arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
        ))
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()

    }



    override fun onDestroy() {
//        try{
//
//        } catch (e: Exception){
//            logger.log(e.stackTraceToString())
//        }
        super.onDestroy()
    }
}