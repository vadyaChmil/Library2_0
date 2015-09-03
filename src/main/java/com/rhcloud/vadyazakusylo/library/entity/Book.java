package com.rhcloud.vadyazakusylo.library.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
	private int id;
	private int code;
	private String autor;
	private String title;
	private int yearEdition;
	private int pages;
	private Set<Genre> genres = new HashSet<>();

	public Book() {
	}

	public Book(int code, String autor, String title, int yearEdition, int pages) {
		this.code = code;
		this.autor = autor;
		this.title = title;
		this.yearEdition = yearEdition;
		this.pages = pages;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "code")
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Column(name = "autor")
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "year_edition")
	public int getYearEdition() {
		return yearEdition;
	}

	public void setYearEdition(int yearEdition) {
		this.yearEdition = yearEdition;
	}

	@Column(name = "pages")
	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "genre_book",
		joinColumns = @JoinColumn(name = "id_book") ,
		inverseJoinColumns = @JoinColumn(name = "id_genre") )
	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
