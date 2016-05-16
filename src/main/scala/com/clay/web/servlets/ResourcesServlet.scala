package com.clay.web.servlets

import java.io._
import java.util.ResourceBundle
import javax.activation.MimetypesFileTypeMap
import javax.servlet.ServletConfig
import javax.servlet.annotation.WebServlet
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import org.springframework.util.StreamUtils


/**
  * Created by guynir on 12/05/2016.
  */
@WebServlet(name = "resourcesServlet", urlPatterns = Array("/resources/*"), loadOnStartup = 0)
class ResourcesServlet extends HttpServlet {

    /** Location where resource are available for serving. */
    var resourcePath: String = null

    /** Load MIME type conversions. */
    val mimeType = new MimetypesFileTypeMap()

    // Add custom type that is probably not yet updated within Java MIME database.
    mimeType.addMimeTypes("text/markdown md")

    /**
      * Servlet initializing callback. Extract 'resource root path' from servlet configuration.
      *
      * @param config Servlet configuration.
      */
    override def init(config: ServletConfig): Unit = {
        super.init(config)
        resourcePath = config.getServletContext.getInitParameter("resourcePath")
    }

    /**
      * Perform HTTP GET operation.
      *
      * @param req      HTTP request.
      * @param response HTTP response.
      */
    override def doGet(req: HttpServletRequest, response: HttpServletResponse): Unit = {
        // We only support HTTP/1.0 and HTTP/1.1
        if (!req.getProtocol.endsWith("1.0") && !req.getProtocol.endsWith("1.1")) {
            throw new BadRequestServletErrorException()
        }

        val file = new File(resourcePath + "/" + req.getPathInfo)
        if (file.isDirectory) {
            throw new UnauthorizedServletErrorException()
        }

        if (!file.exists()) {
            throw new NotFoundServletErrorException()
        }

        var in: InputStream = null
        try {
            in = new FileInputStream(file)
            response.setContentType(mimeType.getContentType(file))
            response.setContentLength(in.available())
            StreamUtils.copy(in, response.getOutputStream.asInstanceOf[OutputStream])
        } finally {
            if (in != null) {
                try {
                    in.close()
                } catch {
                    case ex: Exception => // Ignore
                }
            }
        }

    }
}
