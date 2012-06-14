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
		Table<String, Integer, String> my_month = TreeBasedTable.create();
		List<Table> listeMonths = Lists.newLinkedList();
		int current_month = dt.getMonthOfYear();
		int new_month = current_month;
		int change_month = current_month;
		int counter = 1;
		System.out.println("current:"+current_month);
		while (new_month >= current_month && new_month <= current_month + 6)
		{
			if (change_month != new_month){
				System.out.println("change month");
				listeMonths.add(my_month);
				for (final String key: my_month.rowKeySet()) {
					  System.out.println("just filled: "+ my_month.row(key).get(2) + " "+ my_month.row(key).get(3));
					}
				my_month.clear();
				change_month = new_month;
			}
			my_month.put(dt.dayOfMonth().getAsText(), 1 , dt.dayOfMonth().getAsText());
			my_month.put(dt.dayOfMonth().getAsText(), 2 , dt.dayOfWeek().getAsText().substring(0,1).toUpperCase());
			my_month.put(dt.dayOfMonth().getAsText(), 3 , dt.monthOfYear().getAsText());
			dt.addDays(1);
			new_month = dt.getMonthOfYear();
			counter++;
			System.out.println("current:"+new_month);
		}
		//final List<String> firstValues = Lists.newArrayList();
		System.out.println(listeMonths.size());
		for (Table<String, Integer, String> t : listeMonths) {
			for (final String key: t.rowKeySet()) {
				  System.out.println(key + "->" +t.row(key).get(2) + " "+ t.row(key).get(3));
				}
		}
		render(my_month);

}

}