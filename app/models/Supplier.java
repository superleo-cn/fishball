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
@Table(name = "tb_supplier")
public class Supplier {
	@Id
	public Integer id;
	@Required(message = "Supplier Code cannot be empty")
	public String supplierCode;
	
	@Required(message = "Stauts cannot be empty")
	public Boolean status;
	
	@Required(message = "Company Account cannot be empty")
	public String companyAccount;
	
	@Required(message = "Company Name cannot be empty")
	public String companyName;

	public String companyAddress;

	public String companyPostCode;

	public String companyPhoneNo;
	
	public String companyFax;
	
	public String companyEmail;

	public String name;
	
	public String phone1, phone2, phone3;
	
	public String email;
	
	public Date createDate, modifiedDate;

	
	public static Pagination search(String queryName, Pagination pagination) {
		pagination = pagination == null ? new Pagination() : pagination;
		ExpressionList expList = Ebean.find(Supplier.class).where();
		if (StringUtils.isNotEmpty(queryName)) {
			queryName = StringUtils.trimToNull(queryName);
			expList.where().ilike("supplierCode", "%" + queryName + "%");
		}
		expList.orderBy("createDate desc");
		PagingList<Supplier> pagingList = expList
				.findPagingList(pagination.pageSize);
		pagingList.setFetchAhead(false);
		Page page = pagingList.getPage(pagination.currentPage);
		pagination.recordList = page.getList();
		pagination.pageCount = page.getTotalPageCount();
		pagination.recordCount = page.getTotalRowCount();
		return pagination;
	}
	
	public static Supplier view(Integer id) {
		if (id != null) {
			return Ebean.find(Supplier.class, id);
		}
		return null;
	}

	public static void store(Supplier supplier) {
		if (supplier.id != null && supplier.id > 0) {
			Supplier newSupplier = Ebean.find(Supplier.class, supplier.id);
			try {
				PropertyUtils.copyProperties(newSupplier, supplier);
				newSupplier.modifiedDate = new Date();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Ebean.update(newSupplier);
		} else {
			supplier.createDate = new Date();
			Ebean.save(supplier);
		}
	}

	public static boolean delete(Integer id) {
		Integer flag = Ebean.delete(Supplier.class, id);
		return (flag > 0) ? true : false;
	}

}
