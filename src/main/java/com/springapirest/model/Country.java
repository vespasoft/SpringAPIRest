package com.springapirest.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

/**
 * Created by luis vespa on 27/06/18.
 */
@Entity
@Table(name = "country")
@EntityListeners(AuditingEntityListener.class)
public class Country {
    @Id
    private int id;

    @NotBlank
    private String iso;
    
    @NotBlank
    private String name;

    @NotBlank
    private String nicename;
    
    private String iso3;
    
    private int numcode;
    
    private int phonecode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNicename() {
		return nicename;
	}

	public void setNicename(String nicename) {
		this.nicename = nicename;
	}

	public String getIso3() {
		return iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}

	public int getNumcode() {
		return numcode;
	}

	public void setNumcode(int numcode) {
		this.numcode = numcode;
	}

	public int getPhonecode() {
		return phonecode;
	}

	public void setPhonecode(int phonecode) {
		this.phonecode = phonecode;
	}	

}