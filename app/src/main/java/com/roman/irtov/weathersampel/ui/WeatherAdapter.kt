package com.roman.irtov.weathersampel.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roman.irtov.weathersampel.R
import com.roman.irtov.weathersampel.databinding.ItemWeatherBinding
import com.roman.irtov.weathersampel.di.module.network.WeatherImageLoader
import com.roman.irtov.weathersampel.domain.entry.Feature
import com.roman.irtov.weathersampel.ui.helper.DateTimeHelper

class WeatherAdapter(context: Context, val weatherImageLoader: WeatherImageLoader) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private var entries: List<Feature> = listOf()
    private val dateTimeHelper = DateTimeHelper()
    private val endsText = context.resources.getString(R.string.weather_ends)
    private val durationText = context.resources.getString(R.string.weather_duration)
    private val timeErrorText = context.resources.getString(R.string.time_error)
    private val eventCompletedText = context.resources.getString(R.string.event_completed)

    //For this logic it does not make sense to use logic to update cells (like - DiffUtil)
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(data: List<Feature>) {
        entries = data
        notifyDataSetChanged()
    }

    inner class WeatherViewHolder(val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setData(data: Feature) {
            data.properties?.let { property ->
                binding.itemWeatherEvent.text = property.event
                binding.itemWeatherSender.text = property.senderName
                if (property.ends > 0) {
                    val endsTime = dateTimeHelper.getDate(property.ends)
                    val duration = property.ends - property.effective
                    if (duration < 0) {
                        //It's better to use String format
                        binding.itemWeatherEndDuration.text = "$endsText $endsTime $durationText $eventCompletedText"
                    } else {
                        binding.itemWeatherEndDuration.text = "$endsText $endsTime $durationText ${dateTimeHelper.getDuration(duration)}"
                    }
                } else {
                    binding.itemWeatherEndDuration.text = timeErrorText
                }
            } ?: {
                binding.itemWeatherEvent.text = ""
                binding.itemWeatherEndDuration.text = ""
                binding.itemWeatherSender.text = ""
            }
            weatherImageLoader.loadImage(data.id, binding.itemWeatherImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = entries.size
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.setData(entries[position])
    }
}