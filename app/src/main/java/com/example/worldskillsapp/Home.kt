package com.example.worldskillsapp

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView

class Home : AppCompatActivity() {
    private lateinit var carrossel: ViewPager
    private var currentPage = 0
    private lateinit var drawerLayout: DrawerLayout
    private val handler = Handler()
    private val updateInterval = 3000L // Intervalo de 3 segundos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        window.statusBarColor = Color.parseColor("#3b2a93")
        supportActionBar?.hide()

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val menu: ImageView = findViewById(R.id.menu_icon)

        menu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawer(GravityCompat.START)

            when (menuItem.itemId) {
                R.id.Galeria -> {
                    // Open Gallery Activity
                    val intent = Intent(this, GalleryActivit::class.java)
                    startActivity(intent)
                }
                R.id.computacaoEmNuvem -> {
                    // Open Cloud Computing Competitors Activity
                    val intent = Intent(this, CloudComputingActivity::class.java)
                    startActivity(intent)
                }
                R.id.DesenvolvimentoDeApps -> {
                    // Open App Development Competitors Activity
                    val intent = Intent(this, AppDevelopmentActivity::class.java)
                    startActivity(intent)
                }
                R.id.desenvolvimentoMecanicoEmCad -> {
                    // Open Mechanical CAD Competitors Activity
                    val intent = Intent(this, MechanicalCadActivity::class.java)
                    startActivity(intent)
                }
                R.id.sistemasDeRefrigeração -> {
                    // Open Refrigeration Systems Competitors Activity
                    val intent = Intent(this, RefrigerationSystemsActivity::class.java)
                    startActivity(intent)
                }
                R.id.tecnologiaAutomotiva -> {
                    // Open Automotive Technology Competitors Activity
                    val intent = Intent(this, AutomotiveTechnologyActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }

        val button = findViewById<Button>(R.id.my_button)
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://worldskills.org/what/competitions/"))
            startActivity(intent)
        }

        setupCarrossel()
        startAutoScroll()
    }

    private fun setupCarrossel() {
        carrossel = findViewById(R.id.carrossel)
        val images = listOf(R.drawable.ws1, R.drawable.ws2, R.drawable.ws3, R.drawable.ws4, R.drawable.ws5)
        carrossel.adapter = CarouselAdapter(images)
    }

    private fun startAutoScroll() {
        val update = object : Runnable {
            override fun run() {
                if (carrossel.adapter?.count ?: 1 > 0) {
                    if (currentPage == (carrossel.adapter?.count ?: 1) - 1) {
                        currentPage = 0
                    } else {
                        currentPage++
                    }
                    carrossel.setCurrentItem(currentPage, true)
                    handler.postDelayed(this, updateInterval)
                }
            }
        }
        handler.postDelayed(update, updateInterval)
    }
}
