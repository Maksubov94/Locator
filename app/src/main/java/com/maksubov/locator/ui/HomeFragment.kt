package com.maksubov.locator.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.maksubov.locator.FgLocationService
import com.maksubov.locator.MainActivity
import com.maksubov.locator.R
import com.maksubov.locator.databinding.HomeFragmentLayoutBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class HomeFragment: Fragment() {

    private lateinit var binding: HomeFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentLayoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.home_toolbar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.hello_toolbar -> {
                        Toast.makeText(requireContext(), "HELLO PRESSED", Toast.LENGTH_LONG).show()
                    }
                    R.id.idExit -> { stopService(context!!)}
                }
                return true
            }
        }, viewLifecycleOwner)

        // зачем мы добавили viewLifecycleOwner, для чего

    }
    fun stopService(context: Context) {
        val stopIntent = Intent(context, FgLocationService::class.java)
        context.stopService(stopIntent)
        activity?.finish()
           }


}


