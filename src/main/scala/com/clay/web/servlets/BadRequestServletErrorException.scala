package com.clay.web.servlets

import javax.servlet.http.HttpServletResponse

/**
  * This exception indicates that a client provided a bad request (malformed URL or partial request).
  *
  * @author Guy Raz Nir
  * @since 16/05/2016
  */
class BadRequestServletErrorException extends ServletErrorException(HttpServletResponse.SC_BAD_REQUEST) {

}
