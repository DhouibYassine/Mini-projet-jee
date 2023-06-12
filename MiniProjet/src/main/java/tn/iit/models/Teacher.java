package tn.iit.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.*;

@Entity
@Table(name = "teacher")
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int id;
	 @OneToMany(mappedBy = "teacher" , fetch = FetchType.EAGER,cascade  = javax.persistence.CascadeType.REMOVE)
	    private List<Authorization> authorizationList;
	public List<Authorization> getAuthorizationList() {
		return authorizationList;
	}
	public void setAuthorizationList(List<Authorization> authorizationList) {
		this.authorizationList = authorizationList;
	}
	private int cin;
	private String surname;
	private int age;

	private String name;
	private int nombreHeure ;
	  
	   
	   public Teacher() {
		   nombreHeure =208;
	   };
	public void setName(String name) {
	this.name = name;
}
	public Teacher(String name) {
		this.name = name;
	}

	public Teacher(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Teacher(String name2, int cin2, String surname2, int age2) {
		// TODO Auto-generated constructor stub
		this.name=name2;
		this.cin=cin2;
		this.surname=surname2;
		this.age=age2;
		
	}
	public int getId() {
		return id;
	}
	 public int calculateAuthorizedHours() {
	        // Implement your logic here to calculate the authorized hours for the given teacher and remaining weeks
	        // You can retrieve the teacher's data from your database or any other data source
	        
	        // Placeholder code
	        int authorizedHours = 0;
	        
	        // Perform your calculations and return the authorized hours
	        Date currentDate = new Date();
	        
	        // Calculate the remaining weeks in the current year
	        Calendar calendar = new GregorianCalendar();
	        calendar.setTime(currentDate);
	        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
	        int totalWeeks = calendar.getActualMaximum(Calendar.WEEK_OF_YEAR);
	        int remainingWeeks = totalWeeks - currentWeek;
	        authorizedHours = remainingWeeks*4;
	        return authorizedHours;
	    }

	public String getName() {
		return name;
	}

	public int getNombreHeure() {
		return nombreHeure;
	}

	public void setNombreHeure(int nombreHeure) {
		this.nombreHeure = nombreHeure;
	}
	public int getCin() {
		return cin;
	}
	public void setCin(int cin) {
		this.cin = cin;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
