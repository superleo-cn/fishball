package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Page;
import com.avaje.ebean.PagingList;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import play.data.validation.Required;
import utils.Pagination;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_employee")
public class Employee {
	@Id
	public Integer id;

    @Required(message = "Employee cannot be empty")
    public String employeeCode;

    public String realname;

    public String nric;

    public String address;

    public String postalCode;

    public String homeNo,mobileNo,email;

    public String emergencyContactName,emergencyContactNo;

    public String position;

    public String companyAccount;

    public Date dateHired,dateEnd;

    public Double salary,cpf,foodAllowance,transportAllowance,mobileAllowance;

	public Date createDate;

	public Date modifiedDate;


	/* the following are service methods */
	/*public static Employee login(Employee user) {
		List<Employee> users = Ebean.find(Employee.class).where()
				.eq("username", user.username).eq("password", user.password)
				.eq("status", Boolean.TRUE).findList();
		if (CollectionUtils.size(users) > 0) {
			return users.get(0);
		}
		return null;
	}
*/
	public static Pagination search(String queryName, Pagination pagination) {
		pagination = pagination == null ? new Pagination() : pagination;
		ExpressionList expList = Ebean.find(Employee.class).where();
		if (StringUtils.isNotEmpty(queryName)) {
			queryName = StringUtils.trimToNull(queryName);
			expList.where().ilike("realname", "%" + queryName + "%");
		}
		PagingList<Employee> pagingList = expList
				.findPagingList(pagination.pageSize);
		pagingList.setFetchAhead(false);
		Page page = pagingList.getPage(pagination.currentPage);
		pagination.recordList = page.getList();
		pagination.pageCount = page.getTotalPageCount();
		pagination.recordCount = page.getTotalRowCount();
		return pagination;
	}

	public static Employee view(Integer id) {
		if (id != null) {
			return Ebean.find(Employee.class, id);
		}
		return null;
	}

	public static void store(Employee employee) {

		if (employee.id != null && employee.id > 0) {
            System.out.println("UPDATE");
			Employee existingEmployee = Ebean.find(Employee.class, employee.id);
			try {
                existingEmployee.realname = employee.realname;
                existingEmployee.modifiedDate = new Date();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Ebean.update(existingEmployee);
		} else {
            System.out.println("SAVE");
			Ebean.save(employee);
		}
	}

	public static boolean delete(Integer id) {
		Integer flag = Ebean.delete(Employee.class, id);
		return (flag > 0) ? true : false;
	}
}
