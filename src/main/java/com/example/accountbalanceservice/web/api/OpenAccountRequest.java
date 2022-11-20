package com.example.accountbalanceservice.web.api;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OpenAccountRequest {
    private long userId;
    private String currency;
}
