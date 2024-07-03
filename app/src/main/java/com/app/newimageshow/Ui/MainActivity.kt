package com.app.newimageshow.Ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.app.newimageshow.R
import com.app.newimageshow.ViewModel.CatImageViewModel
import com.app.newimageshow.databinding.ActivityMainBinding
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CatImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.fetchCatImage()

        observeViewModel()
        setupListeners()

    }

    private fun observeViewModel() {
        viewModel.catImage.observe(this, Observer { imageUrl ->
            if (imageUrl.isNotBlank()) {
                showLoading(false)
                loadImage(imageUrl)
            } else {
                showLoading(false)
                // Handle error case
                binding.imageView.setImageResource(R.drawable.hbfnnf)
            }
        })
    }

    private fun setupListeners() {
        binding.btnFetchImage.setOnClickListener {
            showLoading(true)
            viewModel.fetchCatImage()
        }
    }

    private fun loadImage(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .centerCrop()
            .into(binding.imageView)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) android.view.View.VISIBLE else android.view.View.GONE
    }
}