package br.gov.frameworkdemoiselle.sample.bookmark.view;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;

import br.gov.frameworkdemoiselle.stereotype.ViewController;

@ViewController
@ViewScoped
public class BookmarkFilterMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private String description;
	private String link;
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String filter() {
		return "bookmark_list.xhtml?description=" + description + "&link="
				+ link + "&pdescription=" + description;		
	}
	
	public String filter5() {
		return "bookmark_list.xhtml?description=" + description + "&link="
				+ link + "&pdescription=" + description;		
	}	
}
