package hh.ohjelmistoprojekti.kysely;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/*")
      .allowedOrigins("http://localhost:3000/")
      .allowedMethods("GET", "POST", "PUT", "DELETE")
      .allowedHeaders("")
    	.maxAge(3600);
  }
}
