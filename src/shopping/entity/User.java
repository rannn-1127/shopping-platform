package shopping.entity;

public class User {
    private String id;
    private String name;
    private String password;
    private String role;
    private char sex;
    private String city;

    public User() {
    }
    public User(String name,String password,String role){
        this.name=name;
        this.password=password;
        this.role=role;
    }

    public User(String id, String name, String password, String role, char sex, String city) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.sex = sex;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String toString() {
        return "User{id = " + id + ", name = " + name + ", password = " + password + ", role = " + role + ", sex = " + sex + ", city = " + city + "}";
    }
}
