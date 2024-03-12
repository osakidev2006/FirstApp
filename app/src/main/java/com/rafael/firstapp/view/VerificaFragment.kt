package com.rafael.firstapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rafael.firstapp.R
import com.rafael.firstapp.databinding.FragmentCalculBinding
import com.rafael.firstapp.databinding.FragmentVerificaBinding


class VerificaFragment : Fragment() {

    private var _binding: FragmentVerificaBinding? = null
    private val binding: FragmentVerificaBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVerificaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {

        }
            var email = binding.tvEmail.editableText.toString()

            if(email.contains( "@") && email.substringAfter( "@").contains(".com")){
                binding.tvEmail.text = "Email: ${email}"
            } else{
                binding.tvEmail.text = "Email: Error"
            }

            var telefone = binding.tvTelefone.editableText.toString()

        if (telefone.length == 11) {
            binding.tvTelefone.text = "Telefone: ${telefone}"
        } else{
            error("Não tem 11 digitos")
        }
    }
    }
