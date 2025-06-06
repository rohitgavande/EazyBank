package com.eazybank.accounts.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "accounts")
@Data
public class AccountsContactInfoDto {
	String message;
	Map<String, String> ContactDetails;
	List<String> onCallSupport;
}
