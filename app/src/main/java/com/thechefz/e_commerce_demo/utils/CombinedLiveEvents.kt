package com.thechefz.e_commerce_demo.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import android.app.Activity
import androidx.fragment.app.FragmentActivity



class CombinedLiveEvents<T> : MutableLiveData<T>() {
    private val error: SingleLiveEvent<OurException> = ErrorLiveData()
    private val loading: SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun setLoading(boolean: Boolean){
        loading.value = boolean
    }

    public fun isLoading():Boolean{
        return loading.value ?: false
    }

    fun setError(OurException: OurException?){
        error.value = OurException
        loading.value = false
    }

    override fun setValue(t: T?) {
        super.setValue(t)
        loading.value = false
    }
    fun observe(
        owner: LifecycleOwner, successObserver: Observer<T>,
        activity: FragmentActivity? = null,
        loadingObserver: Observer<Boolean>? = null,
        commonErrorObserver: Observer<OurException>,
        httpErrorConsumer: Observer<OurException>? = null,
        networkErrorConsumer: Observer<OurException>? = null,
        unExpectedErrorConsumer: Observer<OurException>? = null,
        serverDownErrorConsumer: Observer<OurException>? = null,
        timeOutErrorConsumer: Observer<OurException>? = null,
        unAuthorizedErrorConsumer: Observer<OurException>? = null
    ) {
        super.observe(owner, successObserver)
        //Unauthorized consumer
        val actualUnAuth = if(activity != null && unAuthorizedErrorConsumer == null){
            getDefaultUnAuthorizedConsumer(activity)
        } else unAuthorizedErrorConsumer

        //Network consumer
        val actualNetwork = if (activity != null && networkErrorConsumer == null)
            getDefaultNetworkConsumer(activity)
        else networkErrorConsumer

        loadingObserver?.let { loading.observe(owner, it) }
        (error as ErrorLiveData).observe(
            owner, commonErrorObserver, httpErrorConsumer, actualNetwork, unExpectedErrorConsumer,
            serverDownErrorConsumer, timeOutErrorConsumer, actualUnAuth
        )
    }
    private fun getDefaultNetworkConsumer(activity: FragmentActivity?)= Observer<OurException>{
        //activity?.showInternetError()
    }

    private fun getDefaultUnAuthorizedConsumer(activity: Activity?)
            = Observer<OurException>{
        //activity?.goToLogin()
    }

    class ErrorLiveData : SingleLiveEvent<OurException>() {
        private var ownerRef: LifecycleOwner? = null
        private var httpErrorConsumer: Observer<OurException>? = null
        private var networkErrorConsumer: Observer<OurException>? = null
        private var unExpectedErrorConsumer: Observer<OurException>? = null
        private var commonErrorConsumer: Observer<OurException>? = null

        private var serverDownErrorConsumer: Observer<OurException>? = null
        private var timeOutErrorConsumer: Observer<OurException>? = null
        private var unAuthorizedErrorConsumer: Observer<OurException>? = null

        override fun setValue(value: OurException?) {
            ownerRef?.also {
                removeObservers(it)
                value?.let { vale -> addProperObserver(vale) }
                super.setValue(value)
            }

        }

        override fun postValue(value: OurException) {
            ownerRef?.also {
                removeObservers(it)
                addProperObserver(value)
                super.postValue(value)
            }

        }

        private fun addProperObserver(value: OurException) {
            ownerRef?.let { owner ->
                when (value.getExceptionType()) {
                    OurException.ExceptionType.NETWORK -> networkErrorConsumer?.let {
                        observe(
                            owner,
                            it
                        )
                    }
                        ?: commonErrorConsumer?.let { observe(owner, it) }
                    OurException.ExceptionType.HTTP -> httpErrorConsumer?.let {
                        observe(
                            owner,
                            it
                        )
                    }
                        ?: commonErrorConsumer?.let { observe(owner, it) }
                    OurException.ExceptionType.UNKNOWN -> unExpectedErrorConsumer?.let {
                        observe(
                            owner,
                            it
                        )
                    }
                        ?: commonErrorConsumer?.let { observe(owner, it) }

                    OurException.ExceptionType.SERVER_DOWN -> serverDownErrorConsumer?.let {
                        observe(
                            owner,
                            it
                        )
                    }
                        ?: commonErrorConsumer?.let { observe(owner, it) }

                    OurException.ExceptionType.TIME_OUT -> timeOutErrorConsumer?.let {
                        observe(
                            owner,
                            it
                        )
                    }
                        ?: commonErrorConsumer?.let { observe(owner, it) }

                    OurException.ExceptionType.UNAUTHORIZED -> unAuthorizedErrorConsumer?.let {
                        observe(
                            owner,
                            it
                        )
                    }
                        ?: commonErrorConsumer?.let { observe(owner, it) }

                    else -> {
                    }
                }
            }
        }


        fun observe(
            owner: LifecycleOwner, commonErrorConsumer: Observer<OurException>,
            httpErrorConsumer: Observer<OurException>? = null,
            networkErrorConsumer: Observer<OurException>? = null,
            unExpectedErrorConsumer: Observer<OurException>? = null,

            serverDownErrorConsumer: Observer<OurException>? = null,
            timeOutErrorConsumer: Observer<OurException>? = null,
            unAuthorizedErrorConsumer: Observer<OurException>? = null
        ) {
            super.observe(owner, commonErrorConsumer)
            this.ownerRef = owner
            this.commonErrorConsumer = commonErrorConsumer
            this.httpErrorConsumer = httpErrorConsumer
            this.networkErrorConsumer = networkErrorConsumer
            this.unExpectedErrorConsumer = unExpectedErrorConsumer
            this.serverDownErrorConsumer = serverDownErrorConsumer
            this.timeOutErrorConsumer = timeOutErrorConsumer
            this.unAuthorizedErrorConsumer = unAuthorizedErrorConsumer
        }
    }
}