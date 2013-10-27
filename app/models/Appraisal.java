package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Page;
import com.avaje.ebean.PagingList;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import utils.Pagination;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: lala
 * Date: 10/26/13
 * Time: 11:23 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "tb_appraisal")
public class Appraisal {

    @Id
    public Integer id;

    public Date createDate, modifiedDate, appraisalDate;

    public String description;

    public String action;

    public Boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="employee_id", referencedColumnName="id")
    public Employee employee;


    public static Pagination search(String queryName, Pagination pagination) {
        pagination = pagination == null ? new Pagination() : pagination;
        ExpressionList expList = Ebean.find(Appraisal.class).fetch("employee").where();
        expList.where().eq("status",true);
        if (StringUtils.isNotEmpty(queryName)) {
            queryName = StringUtils.trimToNull(queryName);
            expList.where().ilike("employee", "%" + queryName + "%");
        }
        expList.orderBy("createDate desc");
        PagingList<Appraisal> pagingList = expList
                .findPagingList(pagination.pageSize);
        pagingList.setFetchAhead(false);
        Page page = pagingList.getPage(pagination.currentPage);
        pagination.recordList = page.getList();
        pagination.pageCount = page.getTotalPageCount();
        pagination.recordCount = page.getTotalRowCount();
        return pagination;
    }

    public static Appraisal view(Integer id) {
        if (id != null) {
            return Ebean.find(Appraisal.class, id);
        }
        return null;
    }

    public static void store(Appraisal appraisal) {
        if (appraisal.id != null && appraisal.id > 0) {
            Appraisal newAppraisal = Ebean.find(Appraisal.class, appraisal.id);
            try {
                PropertyUtils.copyProperties(newAppraisal, appraisal);
                newAppraisal.modifiedDate = new Date();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Ebean.update(newAppraisal);
        } else {
            appraisal.createDate = new Date();
            appraisal.status=true;
            Ebean.save(appraisal);
        }
    }

    public static boolean delete(Integer id) {
        Appraisal appraisal = Ebean.find(Appraisal.class, id);
        appraisal.status=false;
         Ebean.save(appraisal);
        return true;
    }


}
