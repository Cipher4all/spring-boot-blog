package com.webapp.blogDemo.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
@Data
@Table(name = "account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "email")
	private String email;
	
	@JsonIgnore
	@Column(name = "password")
    String password;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "createdAt")
	private LocalDateTime createdAt;
	
	@Column(name = "updatedAt")
	private LocalDateTime updatedAt;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Post> posts;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "account_authority",
			joinColumns = {@JoinColumn(name="account_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name="autority_name", referencedColumnName = "name")})
	Set<Authority> authority =  new HashSet<Authority>();
	
}
