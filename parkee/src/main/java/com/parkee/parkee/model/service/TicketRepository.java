package com.parkee.parkee.model.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parkee.parkee.model.Ticket;

import jakarta.transaction.Transactional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	Ticket findByiInvoice(int iInvoice);
	
	@Transactional
	Ticket deleteByiInvoice(int iInvoice);
}
