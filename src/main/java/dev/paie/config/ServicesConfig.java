package dev.paie.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("dev.paie.service")
@ComponentScan("dev.paie.util")
public class ServicesConfig {
}
