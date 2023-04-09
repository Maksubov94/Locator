package com.maksubov.locator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.maksubov.locator.LocationHandler
import com.maksubov.locator.databinding.StatisticsBinding
import com.maksubov.locator.entity.LocationEntity
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject

class StatisticFragment: Fragment() {

    private lateinit var binding: StatisticsBinding

    private val viewModel: StatisticViewModel by inject()

    private val locationHandler by lazy { LocationHandler() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe()
    }

    private fun subscribe() {
        lifecycleScope.launchWhenCreated {
            viewModel.dataFlow.collectLatest {
                handleLocation(it)
            }
        }
    }

    private fun handleLocation(data: List<LocationEntity>) {
        val handledInfo = locationHandler.handleData(data)
        binding.apply {
            tvStepsToday.text = handledInfo.distance.toString()
            tvInformation.text = handledInfo.time
        }
    }


}