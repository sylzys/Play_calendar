package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.*;

import play.db.jpa.Model;
@Entity
public class Calendar_ extends Model { //etend Model de play
	@Temporal(TemporalType.DATE)
	public Date date;
	public String type; 
	public String description; 
	public String color; 
	public String heure;
	
}