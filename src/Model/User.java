package Model;

public class User {
    private  int id;
    private String name;
    private String email;
    private String password;
    private String cin;

    public User() {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cin = cin;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getCin(){return cin;}
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setCin(String cin){this.cin = cin;}
    @Override
    public String toString() {
        return "Client [name=" + name + ", email=" + email + "Cin= "+ cin + "]";
    }

}

