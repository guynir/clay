package com.clay.web.servlets

import scala.beans.BeanProperty

/**
  * Servlet exception describing HTTP error with optional message and cause.
  *
  * @author Guy Raz Nir
  * @since 16/05/2016
  */
class ServletErrorException(@BeanProperty val statusCode: Int,
                            message: String = null,
                            cause: Throwable = null) extends RuntimeException(message, cause) {
}
