package com.example.whetherapp.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.whetherapp.R
import com.example.whetherapp.databinding.FragmentWhetherBinding
import com.example.whetherapp.viewmodel.WeatherViewModel

class WhetherFragment : Fragment() {
    private lateinit var binding: FragmentWhetherBinding
    private lateinit var viewModel: WeatherViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWhetherBinding.bind(inflater.inflate(R.layout.fragment_whether, null))
        initview()
        setUplistners()
        return binding.root
    }
    private fun initview(){
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

    }

    private fun setUplistners() {
        binding.buttonGetWeather.setOnClickListener {
            val cityName = binding.editTextCityName.text.toString()
            val apiKey = "40846529c2fe3640cc17279a757ac537"

            observeData(cityName, apiKey)

        }
    }

    private fun observeData(cityName: String, apiKey: String) {
        viewModel.getWeather(cityName, apiKey).observe(viewLifecycleOwner) { weatherResponse ->
            val temperature = weatherResponse?.main?.temp.toString()
            val description = weatherResponse?.weather?.get(0)?.description
            val weatherInfo = "Temperature: $temperature°C\nDescription: $description"
            binding.textViewWeatherInfo.text = weatherInfo
        }
    }
}
