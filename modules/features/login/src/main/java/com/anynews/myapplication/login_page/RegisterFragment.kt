package com.anynews.myapplication.login_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anynews.myapplication.login_page.databinding.FragmentRegisterBinding
import com.anynews.myapplication.navigations.NavGraph
import com.anynews.myapplication.navigations.Navigations
import com.google.android.material.snackbar.Snackbar

class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding

    lateinit var navController: Navigations

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        navController = requireActivity() as Navigations
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(MESSAGE)?.let {
            Snackbar.make(view, it, Snackbar.LENGTH_LONG).show()
        }
        binding.nextBtn.setOnClickListener {
            navController.navigate(NavGraph.MAIN)
        }
        binding.backBtn.setOnClickListener {
            navController.popBackStack()
        }
    }

    companion object {
        const val MESSAGE = "MESSAGE"
    }
}