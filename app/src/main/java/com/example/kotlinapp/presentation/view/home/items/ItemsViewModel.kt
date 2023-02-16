package com.example.kotlinapp.presentation.view.home.items


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinapp.R
import com.example.kotlinapp.domain.items.ItemsInteractor
import com.example.kotlinapp.model.ItemsModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


class ItemsViewModel @Inject constructor(
    private val itemsInteractor: ItemsInteractor
): ViewModel() {

    private val _items = MutableLiveData<List<ItemsModel>>()
    val items: LiveData<List<ItemsModel>> = _items

    private val _trigger = MutableLiveData<Flow<Unit>>()
    val trigger: LiveData<Flow<Unit>> = _trigger


//    val items = flow<Flow<List<ItemsModel>>>{ emit(itemsInteractor.showData()) }

    //    1 способ
//    val getData = flow{ emit(itemsInteractor.getData()) }

    private val _msg = MutableLiveData<Int>()
    val msg: LiveData<Int> = _msg

    private val _bundle = MutableLiveData<NavigateWithBundle?>()
    val bundle: LiveData<NavigateWithBundle?> = _bundle

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val compositeDisposable = CompositeDisposable()

    fun getData() {
        val getData = itemsInteractor.getData().subscribe({

        },{

        })
        compositeDisposable.add(getData)
        val showData = itemsInteractor.showData().subscribe({
            _items.value = it
        },{
            _error.value = "Error occurred when showing data"
        })
        compositeDisposable.add(showData)
    }

        //2 способ
//    fun getData(){
//        viewModelScope.launch {
//            _trigger.value = flow{ emit(itemsInteractor.getData()) }
//        }
//    }

    fun imageViewCLicked(){
        _msg.value = R.string.imageview_clicked
    }

    fun elementClicked(description: String, image: String){
        _bundle.value = NavigateWithBundle(description, image, destinationId = R.id.action_itemsFragment_to_detailsFragment)
    }

    fun userNavigated(){
        _bundle.value = null
    }

////    3 способ
//    suspend fun getDataSimple(){
//        itemsInteractor.getData()
//    }

    fun deleteItem(description: String){
        viewModelScope.launch {
            itemsInteractor.deleteItemByDescription(description)
        }
}
    fun onFavClicked(description: String){
        viewModelScope.launch {
            itemsInteractor.onFavClicked(description)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

data class NavigateWithBundle(
    val description: String,
    val image: String,
    val destinationId: Int
    )
