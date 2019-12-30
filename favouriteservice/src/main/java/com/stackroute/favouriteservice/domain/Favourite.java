package com.stackroute.favouriteservice.domain;

import javax.persistence.*;

@Entity
@Table(name="fooddb")
public class Favourite {

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ndbno;

	@Column(name = "itemName")
	private String name;

	@Column(name = "user_id")
	private String userId;

	@Column(name= "foodGroup")
	private String foodGroup;

	public String getFoodGroup() {
		return foodGroup;
	}

	public void setFoodGroup(String foodGroup) {
		this.foodGroup = foodGroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNdbno() {
		return ndbno;
	}

	public void setNdbno(Integer ndbno) {
		this.ndbno = ndbno;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Favourite(){}

	public Favourite(String userId, String itemName, Integer id, String foodGroup) {
		super();
		this.name = itemName;
		this.ndbno = id;
		this.userId = userId;
		this.foodGroup = foodGroup;
	}

}
