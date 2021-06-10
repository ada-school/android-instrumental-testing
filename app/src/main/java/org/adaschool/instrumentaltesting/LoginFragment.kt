package org.adaschool.instrumentaltesting

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.adaschool.instrumentaltesting.databinding.FragmentFirstBinding
import org.adaschool.instrumentaltesting.viewmodel.LoginFragmentViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val viewModel: LoginFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.loginButton.setOnClickListener {
            validateFormFields()
        }
    }

    private fun validateFormFields() {
        if (binding.email.text.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(binding.email.text.toString())
                .matches()
        ) {
            binding.email.error = getString(R.string.invalid_input)
        } else {
            binding.email.error = null
        }
        if (binding.password.text.isEmpty()

        ) {
            binding.password.error = getString(R.string.invalid_input)
        } else {
            binding.password.error = null
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}