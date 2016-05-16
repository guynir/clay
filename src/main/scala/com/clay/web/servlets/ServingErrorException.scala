package com.clay.web.servlets

/**
  * Created by guynir on 15/05/16.
  */
class ServingErrorException(val statusCode : Int, message : String = null) extends RuntimeException(message) {

}
