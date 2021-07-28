package space.lobanov.mvvmkotlintask.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import space.lobanov.mvvmkotlintask.local_db.AppResult
import space.lobanov.mvvmkotlintask.model.Hits
import space.lobanov.mvvmkotlintask.repository.Repository2


class MyViewModel(private var repository2: Repository2) : ViewModel() {

    val hitList = MutableLiveData<List<Hits>>()


    fun getMutableLiveData() {
        viewModelScope.launch() {
            when (val result = repository2.getAllImages()) {
                is AppResult.Success -> {
                    hitList.value = result.successData!!
                }
                is AppResult.Error -> Log.d("dwd", "error")


            }


        }

    }


}


