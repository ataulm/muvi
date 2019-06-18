package com.muvi.actor_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muvi.actor_detail_domain.ActorDetail
import com.muvi.actor_detail_domain.GetActorDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class ActorDetailViewModel(
        private val actorId: String,
        private val getActorDetail: GetActorDetailUseCase
) : ViewModel() {

    fun foo() {

    }

    private val _actorDetail = MutableLiveData<ActorDetail>()
    val actorDetail: LiveData<ActorDetail> = _actorDetail

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getActorDetail(actorId)
            Log.d("!!", "vm actor detail: $result")
            withContext(Dispatchers.Main) {
                _actorDetail.value = result
            }
        }
    }
}
