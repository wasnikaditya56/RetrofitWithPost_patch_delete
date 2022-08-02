package com.adityawasnik.retrofitwithpost_patch_delete

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivityViewModel: ViewModel() {


    lateinit var recyclerListData: MutableLiveData<UserList>

    init {
        recyclerListData = MutableLiveData()
    }

    fun getUserListObserverable(): MutableLiveData<UserList>{
        return recyclerListData
    }

    fun getUserList() {
       val retroInstance =  RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getUsersList()
        call.enqueue(object : Callback<UserList>{

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                recyclerListData.postValue(null)
            }

            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                if(response.isSuccessful){
                    recyclerListData.postValue(response.body())
                }
            }
        })
    }

    //searchUser
    fun searchUser(searchText:String) {
        val retroInstance =  RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.searchUsers(searchText)
        call.enqueue(object : Callback<UserList>{

            override fun onFailure(call: Call<UserList>, t: Throwable) {
                recyclerListData.postValue(null)
            }

            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                if(response.isSuccessful){
                    recyclerListData.postValue(response.body())
                }
            }
        })
    }


}