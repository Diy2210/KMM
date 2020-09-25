package net.multipul.kmm.shared.model

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import net.multipul.kmm.Server
//import net.multipul.kmm.shared.repository.ServersRepository

class MainViewModel: ViewModel() {

//    lateinit var repository: ServersRepository
    lateinit var list: List<Server>
    private val _servers: MutableLiveData<List<Server>> = MutableLiveData(list)

//    init {
//        reload()
//    }
//
//    fun reload() {
//        list = repository.getAll()
//        _servers.value = list
//    }
//
//    fun getInitList(): MutableLiveData<List<Server>> {
//        _servers.value = list
//        return _servers
//    }
//
    val servers: LiveData<List<Server>> = _servers
//
//    fun delete(item: Server) {
//        try {
//            repository.delete(item.ID).also {
////                adapter.items = repository.getAll()
////                adapter.notifyDataSetChanged()
//            }
//        } catch (e: Exception) {
//        }
//    }

//    private val _counter: MutableLiveData<Int> = MutableLiveData(0)
//    val counter: LiveData<String> = _counter.map { it.toString() }
//
//    fun onCounterButtonPressed() {
//        val current = _counter.value
//        _counter.value = current + 1
//    }
}