package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import play.data.validation.Required;
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

	public String nric, nricPic;

	public String phone1, phone2, phone3;
	
	public String email;
	
	public String customerType;
	
	public String customerPayment;
	
	public String creditLimit;
	
	public String creditTerms;
	
	public String salesRep;
	
	public String driverLocation;
	
	public Date createDate, modifiedDate;

	public static Pagination search(String queryName, Pagination pagination) {
		pagination = pagination == null ? new Pagination() : pagination;
		ExpressionList expList = Ebean.find(Customer.class).where();
		if (StringUtils.isNotEmpty(queryName)) {
			queryName = StringUtils.trimToNull(queryName);
			expList.where().ilike("customerCode", "%" + queryName + "%");
		}
		expList.orderBy("createDate desc");
		PagingList<Customer> pagingList = expList
				.findPagingList(pagination.pageSize);
		pagingList.setFetchAhead(false);
		Page page = pagingList.getPage(pagination.currentPage);
		pagination.recordList = page.getList();
		pagination.pageCount = page.getTotalPageCount();
		pagination.recordCount = page.getTotalRowCount();
		return pagination;
	}
	
	public static Customer view(Integer id) {
		if (id != null) {
			return Ebean.find(Customer.class, id);
		}
		return null;
	}

	public static void store(Customer customer) {
		if (customer.id != null && customer.id > 0) {
			Customer newCustomer = Ebean.find(Customer.class, customer.id);
			try {
				PropertyUtils.copyProperties(newCustomer, customer);
				newCustomer.modifiedDate = new Date();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Ebean.update(newCustomer);
		} else {
			customer.createDate = new Date();
			Ebean.save(customer);
		}
	}

	public static boolean delete(Integer id) {
		Integer flag = Ebean.delete(Customer.class, id);
		return (flag > 0) ? true : false;
	}

}
