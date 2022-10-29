package hu.bme.aut.android.wordcard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.wordcard.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            if(binding.etEmailAddressLogin.text.toString().isEmpty()) {
                binding.etEmailAddressLogin.requestFocus()
                binding.etEmailAddressLogin.error = "Please enter your email address"
            }
            else if(binding.etPasswordLogin.text.toString().isEmpty()) {
                binding.etPasswordLogin.requestFocus()
                binding.etPasswordLogin.error = "Please enter your password"
            }
            else {
                findNavController().navigate(R.id.action_loginFragment_to_collectionFragment)
            }
        }

        binding.btnSignin.setOnClickListener {
            if(binding.etEmailAddressSignin.text.toString().isEmpty()) {
                binding.etEmailAddressSignin.requestFocus()
                binding.etEmailAddressSignin.error = "Please enter your email address"
            }
            else if(binding.etPasswordFirst.text.toString().isEmpty() || binding.etPasswordSecond.text.toString().isEmpty()) {
                if(binding.etPasswordFirst.text.toString().isEmpty()) {
                    binding.etPasswordFirst.requestFocus()
                    binding.etPasswordFirst.error = "Please enter your password"
                }
                if(binding.etPasswordSecond.text.toString().isEmpty()) {
                    binding.etPasswordSecond.requestFocus()
                    binding.etPasswordSecond.error = "Please enter your password"
                }
            }
            else if(!binding.etPasswordFirst.text.toString().equals(binding.etPasswordSecond.text.toString())) {
                binding.etPasswordFirst.requestFocus()
                binding.etPasswordSecond.requestFocus()
                binding.etPasswordFirst.error = "Incorrect password"
                binding.etPasswordSecond.error = "Incorrect password"
            }
            else {
                findNavController().navigate(R.id.action_loginFragment_to_collectionFragment)
            }
        }
    }
}