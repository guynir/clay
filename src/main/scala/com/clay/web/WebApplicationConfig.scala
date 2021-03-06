package com.clay.web

import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving
import org.springframework.context.annotation.aspectj.EnableSpringConfigured
import org.springframework.context.annotation.{ComponentScan, Configuration, EnableLoadTimeWeaving}
import org.springframework.stereotype.Controller

/**
  * Web application Spring configuration.<p>
  *
  * Scan all packages for `@Controller` Spring MVC controllers.
  *
  * @author Guy Raz Nir
  * @since 11/05/2016
  */
@Configuration
@ComponentScan(basePackages = Array("com.clay"),
  useDefaultFilters = false,
  includeFilters = Array(new ComponentScan.Filter(value = Array(classOf[Controller]))))
@EnableSpringConfigured
@EnableLoadTimeWeaving(aspectjWeaving = AspectJWeaving.ENABLED)
class WebApplicationConfig {
}
