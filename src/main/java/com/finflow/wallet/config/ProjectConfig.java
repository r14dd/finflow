package com.finflow.wallet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/* When using stereotype annotations, consider two steps. First, use the stereotype
annotation (@Component) to annotate the class for which you want Spring to add a bean to its
context. Second, use the @ComponentScan annotation to tell Spring where to look for classes
annotated with stereotype annotations. Chapter 2.2.2 */

@Configuration // to annotate this is a configuration class.

/* Using the basePackages attribute of the
   annotation, we tell Spring where to look
   for classes annotated with stereotype
   annotations.
*/
@ComponentScan(basePackages = "com.finflow.wallet.model")
public class ProjectConfig {


}
