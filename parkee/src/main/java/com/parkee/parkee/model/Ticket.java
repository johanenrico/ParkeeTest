package com.parkee.parkee.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "price_table")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "i_Invoice")
	private int iInvoice;
	
	private String sTid;
	private String sMid;
	private String sPlateNumber;
	private LocalDateTime iCheckin;
	private LocalDateTime iCheckout;
	private int iPrice;
	
	
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

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getiCheckin() {
		return iCheckin;
	}
	public void setiCheckin(LocalDateTime iCheckin) {
		this.iCheckin = iCheckin;
	}
	public LocalDateTime getiCheckout() {
		return iCheckout;
	}
	public void setiCheckout(LocalDateTime iCheckout) {
		this.iCheckout = iCheckout;
	}
	public int getiPrice() {
		return iPrice;
	}
	public void setiPrice(int iPrice) {
		this.iPrice = iPrice;
	}

}
