package com.rafael.firstapp.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rafael.firstapp.databinding.FragmentPessoaBinding
import com.rafael.firstapp.service.model.Pessoa
import com.rafael.firstapp.viewmodel.PessoaViewModel
import java.time.LocalDateTime

class PessoaFragment : Fragment() {

    // Chamar a viewmodel
    private val viewModel: PessoaViewModel by viewModels()

    // Criar o binding
    private var _binding: FragmentPessoaBinding? = null

    private val binding: FragmentPessoaBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPessoaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Carregar a pessoa caso tenha selecionado
        arguments?.let{
            viewModel.getPessoa(it.getInt("pessoaId"))
        }

        binding.btnEnviar.setOnClickListener {
            var nome = binding.edtNome.editableText.toString()
            var anoNascimento = binding.edtAnoNascimento.editableText.toString()


            if(nome != ""  && anoNascimento != "" && binding.btnMasculino.isChecked || binding.btnFeminino.isChecked){
                binding.tvNome.text = "Nome: " + nome

                var sexo = ""

                if (binding.btnMasculino.isChecked){
                    sexo = "Masculino"
                }else{
                    sexo = "Feminino"
                }


                val anoAtual = LocalDateTime.now().year
                var idade = anoAtual - anoNascimento.toInt()

                var faixa = ""

                if (idade <= 12){
                    faixa = "Infantil"
                }else if (idade <= 17){
                    faixa = "Adolescente"
                }else if (idade <= 64){
                    faixa = "Adulto"
                }else if (idade <= 100){
                    faixa = "Idoso"
                }else{
                    faixa = "Morto"
                }

                binding.tvIdade.text = "Idade:  ${idade}"

                val pessoa = Pessoa(
                    nome = nome,
                    idade = idade,
                    sexo = sexo,
                    faixa = faixa

                )

                viewModel.pessoa.value?.let{
                    pessoa.id = it.id
                    viewModel.update(pessoa)
                } ?: run {
                    viewModel.insert(pessoa)
                }

                binding.edtNome.editableText.clear()
                binding.edtAnoNascimento.editableText.clear()
                findNavController().navigateUp()

            } else {
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_SHORT).show()
            }

        }

    viewModel.pessoa.observe(viewLifecycleOwner) {pessoa ->
        binding.edtNome.setText(pessoa.nome)
        binding.edtAnoNascimento.setText((LocalDateTime.now().year - pessoa.idade).toString())

        if(pessoa.sexo == "Masculino") {
             binding.btnMasculino.isChecked = true
        } else {
            binding.btnFeminino.isChecked = true
        }

        binding.btnDeletar.visibility = View.VISIBLE

        binding.btnDeletar.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Exclusão da Pessoa")
                .setMessage("Você realmente deseja excluir ?")
                .setPositiveButton( "Sim"){_,_ ->
                    viewModel.delete(viewModel.pessoa.value?.id?: 0)
                    findNavController().navigateUp()
                }
                .setNegativeButton("Não"){_,_ ->}
                .show()
        }
    }
    }
}
