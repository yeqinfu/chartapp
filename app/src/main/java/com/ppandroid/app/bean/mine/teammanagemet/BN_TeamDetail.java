package com.ppandroid.app.bean.mine.teammanagemet;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/8/25.
 */

public class BN_TeamDetail extends BaseBody {


    /**
     * message : {"employeeList":[{"id":1,"deleteStatus":false,"version":97,"createTime":null,"createBy":null,"createById":null,"lastModifyTime":"2017-08-25 10:08:15","lastModifyBy":null,"lastModifyById":null,"companyId":1,"departmentId":1,"roleId":1,"roleEnName":"administrator","roleName":"管理员","realName":"张三","mobile":"13205920973","job":"经理","headPhoto":null,"sex":1,"provinceId":null,"cityId":null,"areaId":null,"areaName":null,"password":"fd58e211b187e2eca1374da6aef7a4e43c66056365caf306bf9f5c0b696cfefd","lastLoginTime":1503626895000,"lastLoginIp":"192.168.25.134"},{"id":2,"deleteStatus":false,"version":61,"createTime":null,"createBy":null,"createById":null,"lastModifyTime":"2017-08-25 09:08:41","lastModifyBy":null,"lastModifyById":null,"companyId":1,"departmentId":1,"roleId":1,"roleEnName":"administrator","roleName":"管理员","realName":"叶钦富","mobile":"18960045254","job":"主管","headPhoto":"upload/employee/1503558156505","sex":1,"provinceId":4524130,"cityId":4524145,"areaId":4524147,"areaName":"广东省韶关市武江区","password":"fd58e211b187e2eca1374da6aef7a4e43c66056365caf306bf9f5c0b696cfefd","lastLoginTime":1503623321000,"lastLoginIp":"192.168.25.136"},{"id":3,"deleteStatus":false,"version":83,"createTime":null,"createBy":null,"createById":null,"lastModifyTime":"2017-08-23 11:56:30","lastModifyBy":null,"lastModifyById":null,"companyId":1,"departmentId":1,"roleId":1,"roleEnName":"administrator","roleName":"管理员","realName":"洪聪志","mobile":"18060031820","job":"主管","headPhoto":null,"sex":1,"provinceId":null,"cityId":null,"areaId":null,"areaName":null,"password":"fd58e211b187e2eca1374da6aef7a4e43c66056365caf306bf9f5c0b696cfefd","lastLoginTime":1503460590000,"lastLoginIp":"192.168.25.134"},{"id":5,"deleteStatus":false,"version":1,"createTime":"2017-08-22 16:31:26","createBy":null,"createById":null,"lastModifyTime":"2017-08-22 16:36:00","lastModifyBy":null,"lastModifyById":null,"companyId":1,"departmentId":1,"roleId":null,"roleEnName":"ceshi1","roleName":null,"realName":"ceshi3","mobile":"13225032331","job":null,"headPhoto":null,"sex":1,"provinceId":null,"cityId":null,"areaId":null,"areaName":null,"password":"111","lastLoginTime":null,"lastLoginIp":null}],"departmentList":[{"id":5,"name":"测试部","employeeSum":0,"sonSum":0}]}
     */

    private MessageBean message;

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public static class MessageBean {
        private List<BN_Employee> employeeList;
        private List<BN_Team> departmentList;

        public List<BN_Employee> getEmployeeList() {
            return employeeList;
        }

        public void setEmployeeList(List<BN_Employee> employeeList) {
            this.employeeList = employeeList;
        }

        public List<BN_Team> getDepartmentList() {
            return departmentList;
        }

        public void setDepartmentList(List<BN_Team> departmentList) {
            this.departmentList = departmentList;
        }




    }
}
