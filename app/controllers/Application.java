package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.joda.time.Period;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;

import models.*;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class Application extends Controller {

	public static void index() {
//		Utilisateur user = new Utilisateur();
//		user.login="Toto";
//		user.password="lol";
//		user.save();
		
		//='select * from utilisateur where password="lol"'
//		List<Utilisateur> users = Utilisateur.find("password=?", "lol").fetch();//Utilisateur.findAll();
//		for (Utilisateur utilisateur : users) {
//			System.out.println(utilisateur.login + ' '+ utilisateur.password);
//		}
		MutableDateTime dt = new MutableDateTime();  // current time
		int month = dt.getMonthOfYear();     // gets the current month
		int i = 0;
		// alternative way to get value
		//String monthStr = dt.gemonth().getAsText();  // gets the month name
		//System.out.println("month :" + dt.monthOfYear().getAsText());
		List<String> days = Lists.newArrayList();
		List <String> day = Lists.newArrayList();
		Table<Integer, Integer, String> my_month = TreeBasedTable.create();
		int current_month = dt.getMonthOfYear();
		int new_month = current_month;
		int counter = 1;
		System.out.println("current:"+current_month);
		while (new_month >= current_month && new_month <= current_month + 6)
		{
			//System.out.println (dt.dayOfWeek().getAsText().substring(0,1).toUpperCase() + " "+ dt.dayOfMonth().getAsText());	
			//days.add(dt.dayOfMonth().getAsText());
			//day.add(dt.dayOfWeek().getAsText().substring(0,1).toUpperCase());
			my_month.put(counter, 1 , dt.dayOfMonth().getAsText());
			my_month.put(counter, 2 , dt.dayOfWeek().getAsText().substring(0,1).toUpperCase());
			my_month.put(counter, 3 , dt.monthOfYear().getAsText());
			dt.addDays(1);
			new_month = dt.getMonthOfYear();
			counter++;
			System.out.println("current:"+new_month);
		}
		//final List<String> firstValues = Lists.newArrayList();
//		
		render(my_month);

}

}