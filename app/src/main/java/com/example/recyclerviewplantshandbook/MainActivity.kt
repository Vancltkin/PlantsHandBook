package com.example.recyclerviewplantshandbook

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerviewplantshandbook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var adapter = PlantAdapter()
    private var editLauncher: ActivityResultLauncher<Intent>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val statusBar = "PlantsHandBook"
        supportActionBar?.title = statusBar

        init()

        editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                adapter.addPlant(it.data?.getSerializableExtra("plant") as Plant)
            }
        }

        binding.apply {
            navMenu.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.c_item1 -> {
                        Toast.makeText(
                            this@MainActivity, "Обновление", Toast.LENGTH_SHORT).show()
                        drawer.closeDrawer(GravityCompat.START)
                    }
                    R.id.c_item2 -> {
                        Toast.makeText(this@MainActivity, "Зкрытие", Toast.LENGTH_SHORT).show()
                        drawer.closeDrawer(GravityCompat.START)
                    }
                    R.id.c_item3 -> {
                        Toast.makeText(this@MainActivity, "Выбран Item3", Toast.LENGTH_SHORT).show()
                    }
                }

                true
            }
        }

        binding.apply {
            bNav.selectedItemId = R.id.item1
            bNav.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.item1 -> {
                        Toast.makeText(this@MainActivity, "Загрузка закладок", Toast.LENGTH_SHORT)
                            .show()
                    }
                    R.id.item2 -> {
                        Toast.makeText(this@MainActivity, "Загрузка платежей", Toast.LENGTH_SHORT)
                            .show()
                    }
                    R.id.item3 -> {
                        Toast.makeText(this@MainActivity, "Загрузка звонков", Toast.LENGTH_SHORT)
                            .show()
                    }
                    R.id.item4 -> {
                        Toast.makeText(this@MainActivity, "Загрузка обновлений", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                true
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.sync -> {
                Toast.makeText(this, "Данные обновлены", Toast.LENGTH_SHORT).show()
            }
            R.id.save -> {
                Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT).show()
            }
            R.id.delete -> {
                Toast.makeText(this, "Данные удалены", Toast.LENGTH_SHORT).show()
            }
            R.id.search -> {
                Toast.makeText(this, "Данные обнаружены", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    private fun init() {
        binding.apply {
            rcView.layoutManager = GridLayoutManager(this@MainActivity, 3)
            rcView.adapter = adapter
            buttonAdd.setOnClickListener {
                editLauncher?.launch(Intent(this@MainActivity, EditActivity::class.java))

            }
        }
    }
}