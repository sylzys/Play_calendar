package controllers;

import play.*;
import play.data.binding.As;
import play.mvc.*;

import java.util.*;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.joda.time.Period;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;

import models.*;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class Application extends Controller {

	public static void index() {
		Table<String, String, String> my_rdv_list = TreeBasedTable.create();
		int counter = 1;
		 List<Calendar_> cal = Calendar_.find("order by date asc").fetch();
				 for (Calendar_ calendrier : cal) {
				 System.out.println(calendrier.date + " à "+ calendrier.heure+" -> "+calendrier.type+": "+calendrier.description);
				 //filling all my appointement for the period
				 my_rdv_list.put(calendrier.heure+"/"+calendrier.type, calendrier.date.toString(), calendrier.description);
				 counter++;
				 }
	
		MutableDateTime dt = new MutableDateTime(); // current time
		int month = dt.getMonthOfYear(); // gets the current month
		int i = 0;
		
		Table<String, Integer, Map<String, String>> my_month = TreeBasedTable.create();
		List<Table<String, Integer, Map<String, String>>> listeMonths = Lists.newArrayList();
		int current_month = dt.getMonthOfYear();
		dt.setDayOfMonth(1);
		String current_date = String.valueOf(dt.getYear())+'-'+ dt.monthOfYear().getAsText()+'-'+dt.dayOfMonth().getAsText();
		String new_date = current_date;
		String change_date = current_date;
		int new_month = current_month;
		int change_month = current_month;

		while (new_month >= current_month && new_month <= current_month + 6) {
			if (change_month != new_month) {
				listeMonths.add(my_month);
				my_month = TreeBasedTable.create();
				change_month = new_month;
			}
			
			String full_month = (String.valueOf(dt.getMonthOfYear()).length() < 2) ? "0"+String.valueOf(dt.getMonthOfYear()) : String.valueOf(dt.getMonthOfYear());
			String full_day = (dt.dayOfMonth().getAsText().length() < 2) ? "0"+dt.dayOfMonth().getAsText() : dt.dayOfMonth().getAsText();		
			my_month.put(dt.dayOfMonth().getAsText()+"/"+dt.dayOfWeek().getAsText()
					.substring(0, 1).toUpperCase()+"/"+
					dt.monthOfYear().getAsText()+"/"+
					String.valueOf(dt.getYear()), 1, my_rdv_list.column(String.valueOf(dt.getYear())+'-'+full_month+'-'+full_day));
			dt.addDays(1);
			new_month = dt.getMonthOfYear();
			
		}

		for (Table<String, Integer, Map<String,String>> t : listeMonths) {
			for (String key : t.rowKeySet()) {
				System.out.println("KEYYY:"+key + "->" + t.row(key).get(1));
			}
		}
		render(listeMonths);
	}

	public static void get_appointement(@As("yyyy-MM-dd") Date date) {//doit formater la date
	
		List<Calendar_> calendars = Calendar_.find("date=?", date).fetch();
				
				render(calendars);
	}
	
	public static void add_appointement(@As("MM-dd-yyyy") Date date, String description, String type, String hour, String min){
		Calendar_ cal = new Calendar_();
		cal.date = date;
		cal.description = description;
		cal.type = type;
		cal.heure=hour+':'+min;
		cal.save();
		renderText(description);
	}
}