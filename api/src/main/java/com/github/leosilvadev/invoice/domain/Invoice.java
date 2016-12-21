package com.github.leosilvadev.invoice.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.function.Function;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.github.leosilvadev.issuer.domain.Issuer;

@Entity
public class Invoice {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="issuer_id")
	private Issuer issuer;
	
	@OneToMany(mappedBy="invoice", cascade=CascadeType.PERSIST)
	private Set<InvoiceProduct> invoiceProducts;

	@NotNull
	private Date emissionDate;

	@NotNull
	private BigDecimal price;
	
	public Invoice() {}
	
	public Invoice(Issuer issuer, Set<InvoiceProduct> invoiceProducts) {
		this.issuer = issuer;
		this.invoiceProducts = invoiceProducts;
		this.invoiceProducts.forEach(product -> product.setInvoice(this));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Issuer getIssuer() {
		return issuer;
	}

	public void setIssuer(Issuer issuer) {
		this.issuer = issuer;
	}

	public Set<InvoiceProduct> getInvoiceProducts() {
		return invoiceProducts;
	}

	public void setInvoiceProducts(Set<InvoiceProduct> invoiceProducts) {
		this.invoiceProducts = invoiceProducts;
	}

	public Date getEmissionDate() {
		return emissionDate;
	}

	public void setEmissionDate(Date emissionDate) {
		this.emissionDate = emissionDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Transient
	private Function<InvoiceProduct, BigDecimal> totalPrice = (product) -> {
		return product.getProduct().getPrice().multiply(new BigDecimal(product.getQuantity()));
	};
	
	@PrePersist
	public void beforeInsert() {
		this.emissionDate = new Date();
		this.price = invoiceProducts.stream().map(totalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((issuer == null) ? 0 : issuer.hashCode());
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
		Invoice other = (Invoice) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (issuer == null) {
			if (other.issuer != null)
				return false;
		} else if (!issuer.equals(other.issuer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", issuer=" + issuer + ", invoiceProducts=" + invoiceProducts + ", emissionDate="
				+ emissionDate + ", price=" + price + "]";
	}
	
}
