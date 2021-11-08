package com.springboot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.springboot.http.PaymentIntentDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@Service
public class PaymentService {

	@Value("${stripe.key.secret}")
	String secretKey;
	
	public PaymentIntent paymentIntent(PaymentIntentDTO paymentIntentDto) throws StripeException {
		Stripe.apiKey=secretKey;
		List<String> payment_method_types= new ArrayList<>();
		payment_method_types.add("card");
		Map<String, Object> params = new HashMap<>();
		params.put("amount", paymentIntentDto.getAmount());
		params.put("currency", paymentIntentDto.getCurrency());
		params.put("description", paymentIntentDto.getDescription());
		params.put("payment_method_types", payment_method_types);
		return PaymentIntent.create(params);
	}
	
	public PaymentIntent confirm(String id) throws StripeException {
		Stripe.apiKey = secretKey;
		PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
		Map<String,Object> params = new HashMap<>();
		params.put("payment_method", "pm_card_visa");
		paymentIntent.confirm(params);
		return paymentIntent;
	}
	
	public PaymentIntent cancel(String id) throws StripeException {
		Stripe.apiKey = secretKey;
		PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
		Map<String,Object> params = new HashMap<>();
		params.put("payment_method", "pm_card_visa");
		paymentIntent.cancel();
		return paymentIntent;
		
	}
	
	
}
