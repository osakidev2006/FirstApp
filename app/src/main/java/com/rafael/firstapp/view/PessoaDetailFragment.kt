package com.rafael.firstapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rafael.firstapp.R
import com.rafael.firstapp.databinding.FragmentAllPessoasBinding
import com.rafael.firstapp.databinding.FragmentPessoaDetailBinding
import com.rafael.firstapp.viewmodel.AllPessoasViewModel
import com.rafael.firstapp.viewmodel.PessoaViewModel
import java.time.LocalDateTime

//
class PessoaDetailFragment : Fragment() {

    // Chamar viewmodel para pegar dados
    private val  viewModel: PessoaViewModel by viewModels()

    // Criar binding para pegar os elementos da tela ( CTRL + MOUSE)
    private var _binding: FragmentPessoaDetailBinding? = null


    private val binding:  FragmentPessoaDetailBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Configurar binding
        _binding = FragmentPessoaDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Chamar a função onViewCreated onde vamos implementar o codigo
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pegar ID da pessoa que foi enviado pela AllPessoasFragment
        // Setar a pessoa para ser carregado
        arguments?.let{
            viewModel.getPessoa(it.getInt("pessoaId"))
        }

        //Carregar as informações da pessoa selecionada
        viewModel.pessoa.observe(viewLifecycleOwner) { pessoa ->
            binding.tvNomeDetail.text = (pessoa.nome)
            binding.tvIdadeDetail.text = pessoa.idade.toString()
            binding.tvFaixaDetail.text = pessoa.faixa



            if (pessoa.sexo == "Masculino") {
                binding.tvSexoDetail.text = "Masculino"
            } else {
                binding.tvSexoDetail.text = "Feminino"
            }

        }

    }
}