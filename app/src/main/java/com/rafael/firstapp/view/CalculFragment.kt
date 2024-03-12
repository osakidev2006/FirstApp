package com.rafael.firstapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rafael.firstapp.R
import com.rafael.firstapp.databinding.ActivityMainBinding
import com.rafael.firstapp.databinding.FragmentCalculBinding
import java.time.LocalDateTime

class CalculFragment : Fragment() {

    private var _binding: FragmentCalculBinding? = null
    private val binding: FragmentCalculBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnEnviar.setOnClickListener {
            var nome = binding.edtNome.editableText.toString()

            binding.tvNome.text = "Nome: " + nome

            var anoNascimento = binding.edtAnoNascimento.editableText.toString()
            val anoAtual = LocalDateTime.now().year
            var idade = anoAtual - anoNascimento.toInt()

            binding.tvIdade.text = "Idade:  ${idade}"
        }
    }
}
