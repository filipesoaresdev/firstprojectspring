package com.firstproject.filipe.domain.enums;

public enum PaymentState {

	PENDING(1,"Pending"),
	SETTLED(2,"Settled"),
	CANCELED(3,"Cancelled");
	
	private Integer code;
	private String description;
	
	private PaymentState(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static PaymentState toEnum(Integer code) {
		
		if(code==null) {
			return null;
		}
		for(PaymentState ps : PaymentState.values()) {
			if(ps.getCode().equals(code)) {
				return ps;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID : "+ code);
	}
	
	
}
