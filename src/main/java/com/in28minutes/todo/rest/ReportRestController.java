package com.in28minutes.todo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.model.Report;
import com.in28minutes.todo.service.ReportService;

@RestController
public class ReportRestController {
	@Autowired
	private ReportService service;
	
	private String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}
	
	@RequestMapping(value = "/api/reports", method = RequestMethod.GET)
	public List<Report> listAllReports() {
		System.out.println("hehehe");
		List<Report> reports= service.listByUser(getLoggedInUserName());
		return reports;
	}

	@RequestMapping(value = "/api/report/{id}", method = RequestMethod.GET)
	public Report getReport(@PathVariable("id") int id) {
		Report report = service.getReport(id);
		if (report.getReportedBy() != getLoggedInUserName()){
			return null;
		}
		return report;
	}
}
