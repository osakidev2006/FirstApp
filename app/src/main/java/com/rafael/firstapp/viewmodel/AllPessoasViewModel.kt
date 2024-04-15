package com.rafael.firstapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rafael.firstapp.service.model.Pessoa
import com.rafael.firstapp.service.repository.PessoaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllPessoasViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PessoaRepository(application)

    private  val  mPessoasList = MutableLiveData<List<Pessoa>>()
    val pessoalList: LiveData<List<Pessoa>> = mPessoasList
    fun loadPessoas() {
        viewModelScope.launch ( Dispatchers.IO){
            mPessoasList.postValue(repository.getAll())
        }
    }
}