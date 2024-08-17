package com.in28minutes.todo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.in28minutes.model.Report;
import com.in28minutes.todo.service.ReportService;

@Controller
public class ReportController {

	@Autowired
	private ReportService service;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public String showReportList(ModelMap model) {
		String user = getLoggedInUserName();
		model.addAttribute("reports", service.listByUser(user));
		return "reports";
	}

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public String showAddReportPage(ModelMap model) {
		model.addAttribute("report", new Report());
		return "reportDetail";
	}

	@RequestMapping(value = "/report", method = RequestMethod.POST)
	public String addReport(ModelMap model, @Valid Report report, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result.toString());
			return "redirect:/reports";

		}
		
		service.addReport(report.getName(), report.getDescription(), getLoggedInUserName());

		model.clear();// to prevent request parameter "name" to be passed
		System.out.println(getLoggedInUserName());

		return "redirect:/reports";
	}

	private String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}

	@RequestMapping(value = "/update-report", method = RequestMethod.GET)
	public String showUpdateReportPage(ModelMap model, @RequestParam int id) {
		model.addAttribute("report", service.getReport(id));
		return "reportDetail";
	}

	@RequestMapping(value = "/update-report", method = RequestMethod.POST)
	public String updateReport(ModelMap model, @Valid Report report,
			BindingResult result) {
		if (result.hasErrors())
			return "redirect:/reports";

		report.setReportedBy(getLoggedInUserName());
		service.updateReport(report);

		model.clear();// to prevent request parameter "name" to be passed
		return "redirect:/reports";
	}

	@RequestMapping(value = "/delete-report", method = RequestMethod.GET)
	public String deleteReport(@RequestParam int id) {
		service.deleteReport(id);

		return "redirect:/reports";
	}

}