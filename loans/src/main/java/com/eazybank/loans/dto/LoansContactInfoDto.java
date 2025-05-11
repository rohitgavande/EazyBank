package com.eazybank.loans.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "loans")
@Data
public class LoansContactInfoDto {
	String message;
	Map<String, String> ContactDetails;
	List<String> onCallSupport;
}
