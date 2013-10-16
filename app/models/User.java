package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.collections.CollectionUtils;

import play.data.validation.Required;
import utils.PaginationList;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Page;
import com.avaje.ebean.PagingList;

@Entity
@Table(name = "tb_user")
public class User {
	@Id
	public int id;
	@Required(message = "Username cannot be empty")
	public String username;
	@Required(message = "Password cannot be empty")
	public String password;

	public String realname;

	@Required(message = "User type cannot be empty")
	public String usertype;

	@Required(message = "Status cannot be empty")
	public Boolean status;

	public String userIp;

	public Date createDate;

	public Date lastLoginDate;

	/* the following are service methods */
	/**
	 * Checking the login user login successfully or not
	 * 
	 * @param user
	 *            passing from submitted form
	 * @return user or null
	 */
	public static User login(User user) {
		List<User> users = Ebean.find(User.class).where().eq("username", user.username).eq("password", user.password)
				.eq("status", Boolean.TRUE).findList();
		if (CollectionUtils.size(users) > 0) {
			return users.get(0);
		}
		return null;
	}

	/**
	 * @param pageSize
	 * @return
	 */
	public static PaginationList search(PaginationList paginationList) {
		paginationList = paginationList == null ? new PaginationList() : paginationList;
		PagingList<User> pagingList = Ebean.find(User.class).where().findPagingList(paginationList.pageSize);
		Page page = pagingList.getPage(paginationList.currentPage);
		paginationList.recordList = page.getList();
		paginationList.pageCount = page.getTotalPageCount();
		paginationList.recordCount = page.getTotalRowCount();
		return paginationList;
	}
}
