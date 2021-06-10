package org.adaschool.instrumentaltesting

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.adaschool.instrumentaltesting.databinding.FragmentLoginBinding
import org.adaschool.instrumentaltesting.viewmodel.LoginFragmentViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    val viewModel: LoginFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        viewModel.successLiveData.observe(viewLifecycleOwner, {
            hideShowProgressBar(false)
            binding.loginButton.isEnabled = true
        })
        binding.loginButton.setOnClickListener {
            if (areLoginFormInputsValid()) {
                binding.loginButton.isEnabled = false
                hideShowProgressBar(true)
                viewModel.login(binding.email.text.toString(), binding.password.text.toString())
            }
        }
    }

    private fun areLoginFormInputsValid(): Boolean {
        if (binding.email.text.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(binding.email.text.toString())
                .matches()
        ) {
            binding.email.error = getString(R.string.invalid_input)
            return false
        } else {
            binding.email.error = null
        }
        if (binding.password.text.isEmpty()

        ) {
            binding.password.error = getString(R.string.invalid_input)
            return false
        } else {
            binding.password.error = null
        }
        return true
    }

    fun hideShowProgressBar(show: Boolean) {
        activity?.runOnUiThread {
            if (show)
                binding.progressBar.visibility = View.VISIBLE
            else
                binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}