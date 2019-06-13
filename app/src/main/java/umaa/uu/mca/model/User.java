package umaa.uu.mca.model;

public class User {
    private String name;
    private String Yop;
    private String Mobile;
    private String email;

    public User() {
    }

    public User(String name, String yop, String mobile, String email) {
        this.name = name;
        Yop = yop;
        Mobile = mobile;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYop() {
        return Yop;
    }

    public void setYop(String yop) {
        Yop = yop;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
