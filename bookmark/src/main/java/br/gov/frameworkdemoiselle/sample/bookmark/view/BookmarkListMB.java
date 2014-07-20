package br.gov.frameworkdemoiselle.sample.bookmark.view;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.sample.bookmark.business.BookmarkBC;
import br.gov.frameworkdemoiselle.sample.bookmark.domain.Bookmark;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.gov.frameworkdemoiselle.util.Parameter;

@ViewController
@NextView("/bookmark_edit.xhtml")
@PreviousView("/bookmark_list.xhtml")
public class BookmarkListMB extends AbstractListPageBean<Bookmark, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private BookmarkBC bc;

	@Inject
	private Logger logger;

	private String description;
	private String link;
	
	@Inject
	private Parameter<String> pdescription;
	
	// f:metadata f:viewParam
	private String vdescription;

	@Override
	protected List<Bookmark> handleResultList() {
		Bookmark bookmark = new Bookmark();
		bookmark.setDescription(description);
		bookmark.setLink(link);
		return this.bc.filter(bookmark);
	}

	public String filter2() {
		this.description = pdescription.getValue();
		
		getResultList();

		return "bookmark_list.xhtml";		
	}
	
	public String filter3(String description, String link) {
		this.description = description;
		this.link = link;

		getResultList();

		return "bookmark_list.xhtml";
	}
	
	public String filter4() {
		this.description = vdescription;
		
		getResultList();

		return "bookmark_list.xhtml";		
	}	

	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter
				.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);

			if (delete) {
				bc.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

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

	public String getVdescription() {
		return vdescription;
	}

	public void setVdescription(String vdescription) {
		this.vdescription = vdescription;
	}
}
