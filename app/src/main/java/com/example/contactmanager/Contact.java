package com.example.contactmanager;
// class contact to represent the local database columns
public class Contact {
    private String id ;
    private String first_name;
    private String last_name;
    private String number;
    //private String user_id ;

    public Contact(String id , String first_name, String last_name,
                   String number ) {
        this.id = id ;
        this.first_name = first_name;
        this.last_name = last_name;
        this.number = number;
       // this.user_id = user_id ;
    }

    @Override
    //pour formatter le contenu de chaque objet
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", number='" + number + '\'' +
               // ", user_id='" + user_id + '\'' +
                '}';
    }

    // generated getters and setters
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public String getFirst_name() {return first_name;}
    public void setFirst_name(String first_name) {this.first_name = first_name;}
    public String getLast_name() {return last_name;}
    public void setLast_name(String last_name) {this.last_name = last_name;}
    public String getNumber() {return number;}
    public void setNumber(String number) {this.number = number;}
   // public String getUser_id() {return user_id;}
    // public void setUser_id(String user_id) {this.user_id = user_id;}
}
