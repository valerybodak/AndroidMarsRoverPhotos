package com.marsroverphotos.entities

import com.module.domain.entities.ErrorEntity

/**
 * A generic wrapper class around data request
 */
data class Data<RequestData>(var responseType: Status, var data: RequestData? = null, var error: ErrorEntity? = null)

enum class Status { SUCCESSFUL, ERROR, LOADING }