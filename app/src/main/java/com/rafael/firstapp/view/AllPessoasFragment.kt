package com.rafael.firstapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafael.firstapp.R
import com.rafael.firstapp.databinding.FragmentAllPessoasBinding
import com.rafael.firstapp.view.adapter.PessoaAdapter
import com.rafael.firstapp.viewmodel.AllPessoasViewModel

class AllPessoasFragment : Fragment() {

    // Chamar a viewmodel
    private val  viewModel: AllPessoasViewModel by viewModels()

    // Chamar o adapter
    private lateinit var adapter: PessoaAdapter

    // Criar o binding
    private var _binding: FragmentAllPessoasBinding? = null

    private val binding: FragmentAllPessoasBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Configurar o binding
        _binding = FragmentAllPessoasBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Quando clicar em algum item da lista
        adapter = PessoaAdapter(viewModel.pessoalList.value){pessoa ->
          val pessoaBundle = Bundle()
            pessoaBundle.putInt("pessoaId", pessoa.id)
            arguments = pessoaBundle
            findNavController().navigate(R.id.pessoaDetailFragment, arguments)
        }

        // Configura a recycler
        val recycler = binding.rvPessoas
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        // Observa para adicionar um item na lista quando 'for adicionado
        viewModel.pessoalList.observe(viewLifecycleOwner) {
            adapter.updatePessoas(it)
        }

        // Navegar para tela de cadastro de pessoa
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.pessoaFragment)
        }


        // Carregar as pessoas cadastradas
        viewModel.loadPessoas()
    }
}
