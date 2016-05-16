package com.clay.web.servlets

import javax.servlet.http.HttpServletResponse

/**
  * Indicates that a caller requested a resource not authorized form him.
  *
  * @author Guy Raz Nir
  * @since 16/05/2016
  */
class UnauthorizedServletErrorException extends ServletErrorException(HttpServletResponse.SC_UNAUTHORIZED) {

}
