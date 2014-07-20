package br.gov.frameworkdemoiselle.sample.bookmark.persistence;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;

import br.gov.frameworkdemoiselle.sample.bookmark.domain.Bookmark;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

@PersistenceController
public class BookmarkDAO extends JPACrud<Bookmark, Long> {
	
	private static final long serialVersionUID = 1L;
	
	public List<Bookmark> filter(Bookmark bookmark) {
					
		String jpql = "select b from Bookmark b where 1=1";
		
		if (!StringUtils.isEmpty(bookmark.getDescription())) {
			jpql += " AND b.description like :description";
		}
		if (!StringUtils.isEmpty(bookmark.getLink())) {
			jpql += " AND b.link like :link";
		}		
		
		TypedQuery<Bookmark> query = getEntityManager().createQuery(jpql, Bookmark.class);		
		
		if (!StringUtils.isEmpty(bookmark.getDescription())) {
			query.setParameter("description", "%" + bookmark.getDescription() + "%");
		}
		if (!StringUtils.isEmpty(bookmark.getLink())) {
			query.setParameter("link", "%" + bookmark.getLink() + "%");
		}		
		
		return query.getResultList();
	}
	
}
