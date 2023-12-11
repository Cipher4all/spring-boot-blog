package com.webapp.blogDemo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "authority")
public class Authority {
	
	@Id
	@Column(length = 16)
	private String name;
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this  == obj) {
			return true;
		}
		
		if(obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		Authority authority1 = (Authority) obj;
		
		return name.equals(authority1.name);
	}
	
	@Override
	public int hashCode() {
		
		return name.hashCode();
	}

}
