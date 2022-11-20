package com.example.accountbalanceservice.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.currency")
@NoArgsConstructor
@Setter
@Getter
public class CommonCurrencyProperties {
    private String commonCurrency;
}
