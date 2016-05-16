package com.clay.web.servlets

import javax.servlet.http.HttpServletResponse

/**
  * Servlet exception indicating a requested resource was not found.
  *
  * @author Guy Raz Nir
  * @since 16/05/2016
  */
class NotFoundServletErrorException extends ServletErrorException(HttpServletResponse.SC_NOT_FOUND, "Resource not found") {
}
