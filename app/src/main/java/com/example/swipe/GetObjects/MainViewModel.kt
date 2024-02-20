package com.example.swipe.GetObjects

import ModelServices
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _ModelState = mutableStateOf(Modelstate())
    val ModelState : State<Modelstate> = _ModelState

    init {
        fetchModels()
    }

    private fun fetchModels(){
        viewModelScope.launch {
            try {
                val response = ModelServices.ModelResponse()
                _ModelState.value =  _ModelState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )
            }catch (e:Exception){
                _ModelState.value = _ModelState.value.copy(
                    loading = false,
                    error = "Error fetching Models ${e.message}"
                )
            }
        }
    }
//
    data class Modelstate(
    val loading:Boolean = true,
    val list: ArrayList<ModelItem> = arrayListOf(),
    val error: String? = null
    )

}