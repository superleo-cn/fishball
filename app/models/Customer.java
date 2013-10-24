package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import play.data.validation.Required;
import utils.MyBeanUtils;
import utils.Pagination;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Page;
import com.avaje.ebean.PagingList;

@Entity
@Table(name = "tb_customer")
public class Customer {
	@Id
	public Integer id;
	@Required(message = "Customer Code cannot be empty")
	public String customerCode;
	
	@Required(message = "Stauts cannot be empty")
	public Boolean status;
	
	@Required(message = "Company Name cannot be empty")
	public String companyName;

	public String companyAddress;

	public String companyPostCode;

	public String companyPhoneNo;
	
	public String companyFax;
	
	public String companyEmail;
	
	public String name;

	public String nric;

	public String nricPic;
	
	public String phone1;
	
	public String phone2;
	
	public String phone3;
	
	public String email;
	
	public String customerType;
	
	public String customerPayment;
	
	public String creditLimit;
	
	public String creditTerms;
	
	public String salesRep;
	
	public String driverAlocation;
	
	public Date createDate;

	public Date modifiedDate;
	
	
	public static Pagination search(String queryName, Pagination pagination) {
		pagination = pagination == null ? new Pagination() : pagination;
		ExpressionList expList = Ebean.find(Customer.class).where();
		if (StringUtils.isNotEmpty(queryName)) {
			queryName = StringUtils.trimToNull(queryName);
			expList.where().ilike("customerCode", "%" + queryName + "%");
		}
		PagingList<Customer> pagingList = expList
				.findPagingList(pagination.pageSize);
		pagingList.setFetchAhead(false);
		Page page = pagingList.getPage(pagination.currentPage);
		pagination.recordList = page.getList();
		pagination.pageCount = page.getTotalPageCount();
		pagination.recordCount = page.getTotalRowCount();
		return pagination;
	}

}
