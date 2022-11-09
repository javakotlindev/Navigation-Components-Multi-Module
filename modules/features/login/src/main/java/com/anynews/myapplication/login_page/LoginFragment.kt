package com.anynews.myapplication.login_page

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.anynews.myapplication.login_page.databinding.FragmentLoginBinding
import com.anynews.myapplication.navigations.NavGraph
import com.anynews.myapplication.navigations.Navigations

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var navController: Navigations

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navController = requireActivity() as Navigations
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextBtn.setOnClickListener {
            navController.navigate(NavGraph.REGISTER, bundleOf(MESSAGE to REGISTER_PAGE))
        }
    }

    companion object {
        const val MESSAGE = "MESSAGE"
        const val REGISTER_PAGE = "Экран Регистрации"
    }
}
