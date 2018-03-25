package org.smart4j.chapter2.controller;

import org.smart4j.chapter2.model.Customer;
import org.smart4j.chapter2.service.CustomerService;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;

import java.util.List;
import java.util.Map;

/**
 * Created by shucheng on 2018/3/25.
 * 处理客户管理相关请求
 */
@Controller
public class CustomerController {

    @Inject
    private CustomerService customerService;

    /**
     * 进入 客户列表 界面
     * @param param
     * @return
     */
    @Action("get:/customer")
    public View index(Param param) {
        List<Customer> customerList = customerService.getCustomerList();
        return new View("customer.jsp").addModel("custmoerList", customerList);
    }

    /**
     * 显示客户基本信息
     * @param param
     * @return
     */
    @Action("get:/customer_show")
    public View show(Param param) {
        long id = param.getLong("id");
        Customer customer = customerService.getCustomer(id);
        return new View("customer_show.jsp").addModel("customer", customer);
    }

    /**
     * 进入 创建客户 界面
     * @param param
     * @return
     */
    @Action("get:/customer_create")
    public View create(Param param) {
        return new View("customer_create.jsp");
    }

    /**
     * 处理 创建客户 请求
     * @param param
     * @return
     */
    public Data createSubmit(Param param) {
        Map<String, Object> fieldMap = param.getMap();
        boolean result = customerService.createCustomer(fieldMap);
        return new Data(result);
    }

    /**
     * 进入 编辑客户 界面
     * @param param
     * @return
     */
    @Action("get:/customer_edit")
    public View edit(Param param) {
        long id = param.getLong("id");
        Customer customer = customerService.getCustomer(id);
        return new View("customer_edit.jsp").addModel("customer", customer);
    }

    /**
     * 处理 编辑客户 请求
     * @param param
     * @return
     */
    @Action("put:/customer_edit")
    public Data editSubmit(Param param) {
        long id = param.getLong("id");
        Map<String, Object> fieldMap = param.getMap();
        boolean result = customerService.updateCustomer(id, fieldMap);
        return new Data(result);
    }

    /**
     * 处理删除客户请求
     * @param param
     * @return
     */
    @Action("delete:/customer_edit")
    public Data delete(Param param) {
        long id = param.getLong("id");
        boolean result = customerService.deleteCustomer(id);
        return new Data(result);
    }
}
