package com.iav.contestdataprovider.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iav.contestdataprovider.data.model.RandomString
import com.iav.contestdataprovider.data.repo.RandomStringRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomStringViewModel @Inject constructor(
    private val repository: RandomStringRepository,
   // private val database: RandomStringDatabase
) : ViewModel() {

    private val _randomStrings  = MutableLiveData<List<RandomString>>()
    val randomStrings: LiveData<List<RandomString>> get() = _randomStrings

    private val stringsList = mutableListOf<RandomString>()

//    init {
//        _stringList.value = emptyList()
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun generateRandomString(length: Int) {
        viewModelScope.launch {
//            try {
//                val newString = repository.fetchRandomString(length)
//                val updatedList = _stringList.value.orEmpty() + newString
//                _stringList.value = updatedList
//            } catch (e: Exception) {
//                // Handle errors (e.g., show a Toast or Snackbar)
//            }
            try {
                val randomString = repository.fetchRandomString(length)
                randomString?.let {
                    //database.randomStringDao().insert(it)
                    stringsList.add(it)
                    _randomStrings.postValue(stringsList)
                  //  loadAllRandomStrings()
                }
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

//    fun deleteString(randomString: RandomString) {
//        _stringList.value = _stringList.value?.filter { it != randomString }
//    }

//    fun clearAllStrings() {
//        _randomStrings .value = emptyList()
//    }
    fun clearAllStrings() {
//        stringsList.clear()
//        _randomStrings.value = stringsList
    viewModelScope.launch {
       // database.randomStringDao().deleteAll()
        loadAllRandomStrings() // Refresh list
    }
    }

    fun deleteString(randomString: Int) {
//        stringsList.removeAt(index)
//        _randomStrings .value = stringsList
        viewModelScope.launch {
            stringsList.removeAt(randomString)
          //  database.randomStringDao().delete(stringsList as RandomString)
            loadAllRandomStrings() // Refresh list
        }
    }

    private fun loadAllRandomStrings() {
//        viewModelScope.launch {
//            _randomStrings.postValue(database.randomStringDao().getAll())
//        }
    }
}
