package com.in28minutes.todo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.in28minutes.model.Report;

@Service
public class ReportService {
	private static List<Report> reports = new ArrayList<Report>();
	private static int count = 0;

	public List<Report> listByUser(String reportedBy) {
		List<Report> data = new ArrayList<Report>();
		for (Report item : reports) {
			if (item.getReportedBy().equals(reportedBy)) {
				data.add(item);
				System.out.println(item.getCreatedAt());
			}	
		}
		return data;
	}
	
	public Report getReport(int id) {
		for (Report item: reports) {
			if (item.getId() == id)
				return item;
		}
		return null;
	}
	
	public void updateReport(Report report) {
		for (Report item : reports) {
			if (item.getId() == report.getId()) {
				item.update(report.getName(), report.getDescription(), report.getReportedBy());
				break;
			}
		}
	}

	public void addReport(String name, String description, String reportedBy) {
		reports.add(new Report(++count, name, description, reportedBy));
		System.out.println("Add Report");
	}

	public void deleteReport(int id) {
		Iterator<Report> iterator = reports.iterator();
		while (iterator.hasNext()) {
			Report report = iterator.next();
			if (report.getId() == id) {
				iterator.remove();
			}
		}
	}
}