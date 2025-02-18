package FileManagement;


import java.io.Serializable;

import NotePackge.Encryption;

public class User implements Serializable{
    private static final long serialVersionUID = -2013019463154592396L;
    private String uname ;
    private String password;
    private Folder ufolder;
    public User(String name, String password) {
        this.password = Encryption.hash(password);
        this.uname = name;
        ufolder = new Folder(name);
    }
    public void setUserName(String name) {
        this.uname = name;
    }
    public void setpassword(String password) {
        this.password = Encryption.hash(password);
    }
    public String UserName () {
        return uname;
    }
    public String Password () {
        return password;
    }
    public void setFolder (Folder folder) {
        this.ufolder  =folder;
    }
    public Folder getFolder() {
        return ufolder;
    }
}