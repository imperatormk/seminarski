package com.fikt.seminarski.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "tokens")
public class Token {
  @Id
  private UUID id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "userID")
  private User user;

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }
  
  @Column(name = "expiryDate")
  @NotNull(message = "Expiry date cannot be empty!")
  private Timestamp expiryDate; 
 
  public Timestamp getDate() {
	return expiryDate;
  }

  public void setDate(Timestamp expiryDate) {
    this.expiryDate = expiryDate;
  }
}