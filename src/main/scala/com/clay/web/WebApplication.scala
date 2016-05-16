package com.clay.web

import javax.servlet.{ServletContext, ServletRegistration}

import com.clay.web.servlets.ResourcesServlet
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

/**
  * Created by guynir on 11/05/16.
  */
class WebApplication extends WebApplicationInitializer {

  /** Class logger. */
  var logger : Logger = LoggerFactory.getLogger(classOf[WebApplication])

  /**
    *
    * Callback during web application startup. Does nothing at the momonet.
    *
    * @param context Servlet context.
    */
  override def onStartup(context: ServletContext): Unit = {
    logger.info("Application startup.")

    // Create the 'root' Spring application context
    val rootContext = new AnnotationConfigWebApplicationContext()
    rootContext.register(classOf[RootConfiguration])

    // Manage the lifecycle of the root application context
    context.addListener(new ContextLoaderListener(rootContext))

    // Create the dispatcher servlet's Spring application context
    val dispatcherContext = new AnnotationConfigWebApplicationContext()
    dispatcherContext.register(classOf[WebApplicationConfig])

    // Register and map the dispatcher servlet
    val dispatcher : ServletRegistration.Dynamic = context.addServlet("dispatcher", new DispatcherServlet(dispatcherContext))
    //dispatcher.setLoadOnStartup(1)
    dispatcher.addMapping("/")

    context.setInitParameter("resourcePath", "d:/clay")
  }

}
