package com.example.recyclerviewplantshandbook

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerviewplantshandbook.databinding.ActivityContentBinding

class ContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val item = intent.getSerializableExtra("item") as Plant
        binding.apply {
            imPlant.setImageResource(item.imageId)
            tvTitlePlant.text = item.title
            tvContentPlant.text = item.desc
            supportActionBar?.title = "Просмотр ${item.title}"
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return true
    }
}