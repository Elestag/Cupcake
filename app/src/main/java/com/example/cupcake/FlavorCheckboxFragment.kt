package com.example.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.databinding.FragmentFlavorCheckboxBinding
import com.example.cupcake.model.OrderViewModel


class FlavorCheckboxFragment : Fragment() {
    private var binding: FragmentFlavorCheckboxBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentFlavorCheckboxBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            flavorCheckboxFragment = this@FlavorCheckboxFragment
        }
    }

    fun goToNextScreen() {

        findNavController().navigate(R.id.action_flavorCheckboxFragment_to_pickupFragment)
    }

    fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_flavorCheckboxFragment_to_startFragment)
    }

    fun onCheckBoxClicked(view: View) {
        if (view is CheckBox) {
            when (view.id) {
                R.id.vanilla -> {
                    if (view.isChecked) {
                        sharedViewModel.setFlavorOptions(getString(R.string.vanilla))
                    } else {
                        sharedViewModel.removeFlavorOptions(getString(R.string.vanilla))
                    }
                }
                R.id.chocolate -> {
                    if (view.isChecked) {
                        sharedViewModel.setFlavorOptions(getString(R.string.chocolate))

                    } else {
                        sharedViewModel.removeFlavorOptions(getString(R.string.chocolate))
                    }
                }
                R.id.red_velvet -> {
                    if (view.isChecked) {
                        sharedViewModel.setFlavorOptions(getString(R.string.red_velvet))

                    } else {
                        sharedViewModel.removeFlavorOptions(getString(R.string.red_velvet))
                    }
                }
                R.id.salted_caramel -> {
                    if (view.isChecked) {
                        sharedViewModel.setFlavorOptions(getString(R.string.salted_caramel))

                    } else {
                        sharedViewModel.removeFlavorOptions(getString(R.string.salted_caramel))
                    }
                }
                R.id.coffee -> {
                    if (view.isChecked) {
                        sharedViewModel.setFlavorOptions(getString(R.string.coffee))
                    } else {
                        sharedViewModel.removeFlavorOptions(getString(R.string.coffee))
                    }
                }
                R.id.special_flavor -> {
                    if (view.isChecked) {
                        sharedViewModel.setFlavorOptions(getString(R.string.special))
                        sharedViewModel.setIsSpecial(false)
                    } else {
                        sharedViewModel.removeFlavorOptions(getString(R.string.special))
                        sharedViewModel.setIsSpecial(true)
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}