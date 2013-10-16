package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.collections.CollectionUtils;

import play.data.validation.Required;

import com.avaje.ebean.Ebean;

@Entity
@Table(name = "tb_user")
public class User{
	@Id
	public int id;
	@Required(message = "Username cannot empty")
	public String username;
	@Required(message = "Password cannot empty")
	public String password;

	public String realname;

	/* the following are service methods */
	
	/**
	 * Checking the login user login successfully or not
	 * @param user passing from submitted form
	 * @return user or null
	 */
	public static User login(User user){
		List<User> users = Ebean.find(User.class).where().eq("username", user.username).eq("password", user.password).findList();
		if(CollectionUtils.size(users) > 0){
			return users.get(0);
		}
		return null;
	}
}
