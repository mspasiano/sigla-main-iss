package it.cnr.contab.docamm00.client;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class TrovatoRest {
	@JsonProperty("nsrif")
	private Integer nsrif;
	@JsonProperty("titolo")
	private String titolo;
	@JsonProperty("inventore")
	private String inventore;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	*
	* @return
	* The nsrif
	*/
	@JsonProperty("nsrif")
	public Integer getNsrif() {
	return nsrif;
	}

	/**
	*
	* @param nsrif
	* The nsrif
	*/
	@JsonProperty("nsrif")
	public void setNsrif(Integer nsrif) {
	this.nsrif = nsrif;
	}

	/**
	*
	* @return
	* The titolo
	*/
	@JsonProperty("titolo")
	public String getTitolo() {
	return titolo;
	}

	/**
	*
	* @param titolo
	* The titolo
	*/
	@JsonProperty("titolo")
	public void setTitolo(String titolo) {
	this.titolo = titolo;
	}

	/**
	*
	* @return
	* The inventore
	*/
	@JsonProperty("inventore")
	public String getInventore() {
	return inventore;
	}

	/**
	*
	* @param inventore
	* The inventore
	*/
	@JsonProperty("inventore")
	public void setInventore(String inventore) {
	this.inventore = inventore;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	}

}
