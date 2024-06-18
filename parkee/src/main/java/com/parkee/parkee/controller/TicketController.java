package com.parkee.parkee.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.parkee.parkee.model.Ticket;
import com.parkee.parkee.model.TicketIn;
import com.parkee.parkee.model.service.TicketRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	private TicketRepository repo;
	
	@GetMapping({"", "/"})
	public String showTicketList (Model model) {
		List<Ticket> tickets = repo.findAll();
		model.addAttribute("tickets", tickets);
		return "ticket/home";
	}
	
	
	@GetMapping("/create")
	public String showCreateTicket(Model model) {
		TicketIn ticketIns = new TicketIn();
		model.addAttribute("ticketIns", ticketIns);
		return "ticket/CreateTicket";
	}
	
    @PostMapping("/create")
    public String createTicket(@Valid @ModelAttribute("ticketIns") TicketIn ticketIns, BindingResult result, Model model) {
       
    	if (ticketIns.getsPlateNumber().isEmpty()) 	{
        	result.addError(new FieldError("ticketIns", "imageFile", "Plate number required"));
    	}
    	
    	if (result.hasErrors()) {
            return "ticket/CreateTicket";
        }
        
        Ticket ticket = new Ticket();
        
        // Generate a 6-digit random integer for iInvoice
        Random random = new Random();
        int iInvoice = 100000 + random.nextInt(900000); // Generates a number between 100000 and 999999

        // Set the generated iInvoice to the Ticket
        ticket.setiInvoice(iInvoice);
        
    	
        ticket.setiCheckin(LocalDateTime.now()); //datetime
        ticket.setsMid("10PARKEE20240610121423"); //Merchant id
        ticket.setsTid("12004201");//POS machine id
        ticket.setsPlateNumber(ticketIns.getsPlateNumber());
        ticket.setiPrice(3000);
        ticket.setiCheckout(ticket.getiCheckout());
        
        repo.save(ticket);

        return "redirect:/ticket";
    }
    
	
    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam(required = false) Integer iInvoice) {
    	   
        if (iInvoice == null) {
            return "redirect:/ticket";
        }
        
    	try {
    		Ticket tickets = repo.findByiInvoice(iInvoice);
    		
            if (tickets == null) {
                return "redirect:/ticket";
            }
            
    		model.addAttribute("tickets", tickets);
    		
    		TicketIn ticketIns = new TicketIn();
    		
    		ticketIns.setiInvoice(tickets.getiInvoice());
    		ticketIns.setiCheckin(tickets.getiCheckin());
    		ticketIns.setsMid(tickets.getsMid());
    		ticketIns.setsTid(tickets.getsTid());
    		ticketIns.setsPlateNumber(tickets.getsPlateNumber());
    		
            // Calculate duration in seconds between checkin and checkout
            LocalDateTime checkin = ticketIns.getiCheckin();
            LocalDateTime checkout = LocalDateTime.now(); // Current time as checkout
            long secondsBetween = calculateDurationInSeconds(checkin, checkout);

            // Convert seconds to hours
            double hours = secondsBetween / 3600.0; // 3600 seconds in an hour

            // Calculate total cost based on hourly rate (3000 units per hour)
            double totalCost = hours * 3000;
            
            
    		ticketIns.setiPrice((int)totalCost);
    		ticketIns.setiCheckout(LocalDateTime.now());
    		
    		model.addAttribute("ticketIns", ticketIns);
    		model.addAttribute("ticket", tickets);
    		
    	}
    	catch(Exception ex) {
    		System.out.println("Exception: " + ex.getMessage());	
    		return "redirect:/ticket";
    	}
    	return "ticket/EditProduct";
    }
    
    
    @PostMapping("/edit")
    public String deleteTicket( Model model,@RequestParam int iInvoice, @Valid @ModelAttribute TicketIn ticketIns,
    		BindingResult result) {
    	
 	   System.out.println("Receivedss iInvoice in deleteTicket: " + iInvoice);
 	   
        try {
        	Ticket tickets = repo.findByiInvoice(iInvoice);
        	 System.out.println("Received 2 iInvoice in deleteTicket: " + iInvoice);
        	        	
        	repo.deleteByiInvoice(iInvoice);
        	
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            // Handle exception or logging as needed
        }
        return "redirect:/ticket";
    }
    
    private long calculateDurationInSeconds(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start, end).getSeconds();
    }
}
