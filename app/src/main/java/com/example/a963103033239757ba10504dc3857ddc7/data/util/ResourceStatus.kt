package com.example.a963103033239757ba10504dc3857ddc7.data.util


data class ResourceStatus<out T>(val status: Status, val data: T?, val error: String?) :
    java.io.Serializable {

    companion object {

        fun <T> loading(): ResourceStatus<T> {
            return ResourceStatus(Status.LOADING, null, null)
        }

        fun <T> success(data: T?): ResourceStatus<T> {
            return ResourceStatus(Status.SUCCESS, data, null)
        }

        fun <T> error(error: String): ResourceStatus<T> {
            return ResourceStatus(Status.ERROR, null, error)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
