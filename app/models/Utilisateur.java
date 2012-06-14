package models;

import play.db.jpa.Model;
import javax.persistence.Entity;

@Entity //indique a java que cette classe pourra etre bindée avec une base de données

public class Utilisateur extends Model { //etend Model de play
	public String login;
	public String password; //decommenter db=meme dans conf/application.conf  
////	//visualiser : localhost:9000/@db jdbc url: jdbc:h2:mem:play -> connect
	
}
