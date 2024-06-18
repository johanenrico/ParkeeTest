package com.parkee.parkee.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;

public class TicketIn {

	private int iInvoice;
	private String sTid;
	private String sMid;
	
    @NotBlank(message = "License plate number is required")
    @Size(min = 1, max = 10, message = "License plate number must be between 1 and 10 characters")
    private String sPlateNumber;
    
    
	@NotEmpty(message = "Date time checkin in is required")
	private LocalDateTime iCheckin;
	private LocalDateTime iCheckout;
	
	@Min(0)
	private int iPrice;
	
	
	public LocalDateTime getiCheckout() {
		return iCheckout;
	}

	public void setiCheckout(LocalDateTime iCheckout) {
		this.iCheckout = iCheckout;
	}

	public LocalDateTime getiCheckin() {
		return iCheckin;
	}

	public void setiCheckin(LocalDateTime iCheckin) {
		this.iCheckin = iCheckin;
	}

	public int getiInvoice() {
		return iInvoice;
	}

	public void setiInvoice(int iInvoice) {
		this.iInvoice = iInvoice;
	}

	public String getsTid() {
		return sTid;
	}

	public void setsTid(String sTid) {
		this.sTid = sTid;
	}

	public String getsMid() {
		return sMid;
	}

	public void setsMid(String sMid) {
		this.sMid = sMid;
	}

	public String getsPlateNumber() {
		return sPlateNumber;
	}

	public void setsPlateNumber(String sPlateNumber) {
		this.sPlateNumber = sPlateNumber;
	}

	public int getiPrice() {
		return iPrice;
	}

	public void setiPrice(int iPrice) {
		this.iPrice = iPrice;
	}
	
}
