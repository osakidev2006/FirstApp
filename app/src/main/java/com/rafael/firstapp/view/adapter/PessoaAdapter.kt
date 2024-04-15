package com.rafael.firstapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafael.firstapp.databinding.ListItemPessoaBinding
import com.rafael.firstapp.service.model.Pessoa

class PessoaAdapter(pessoa: List<Pessoa>?, private val clickListListener: (Pessoa) -> Unit) :
    RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder>() {
    private var pessoaList: List<Pessoa> = arrayListOf()


    class PessoaViewHolder(private val binding: ListItemPessoaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pessoa: Pessoa, clickListListener: (Pessoa) -> Unit) {
            binding.tvNome.text = pessoa.nome
            binding.tvIdade.text = pessoa.idade.toString()
            binding.tvFaixa.text = pessoa.faixa

            if (pessoa.sexo == "Masculino"){
                binding.imghomem.visibility = View.VISIBLE
                binding.imgfemale.visibility = View.GONE
            }else{
                binding.imghomem.visibility = View.GONE
                binding.imgfemale.visibility = View.VISIBLE
            }

            binding.root.setOnClickListener {
                clickListListener(pessoa)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaViewHolder {
        val listItemPessoaBinding =
            ListItemPessoaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PessoaViewHolder(listItemPessoaBinding)
    }

    override fun getItemCount(): Int {
        return pessoaList.count()
    }

    override fun onBindViewHolder(holder: PessoaViewHolder, position: Int) {
        holder.bind(pessoaList[position], clickListListener)
    }

    fun updatePessoas(list: List<Pessoa>) {
        pessoaList = list
        notifyDataSetChanged()
    }

}
