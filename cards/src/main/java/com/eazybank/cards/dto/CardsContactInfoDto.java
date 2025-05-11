package com.eazybank.cards.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "cards")
@Data
public class CardsContactInfoDto {
	String message;
	Map<String, String> ContactDetails;
	List<String> onCallSupport;
}
