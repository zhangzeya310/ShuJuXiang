package zzu.bs.bean;

/**
 * author John
 * date 2017/9/15
 * version v1.0
 **/

public class Admin {
    private static final long serialVersionUID = 1L;

    private Integer adminid;
    private String name;
    private String password;

    public Admin() {
    }

    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminid=" + adminid +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
