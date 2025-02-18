package NotePackge;

public class Secured_Note extends Note{
    private String password; // will be hashed
    public Secured_Note () {
        super();
    }
    public Secured_Note (String name, String password) {
        super(name);
        this.password = Encryption.hash(password);
    }
    public void setPassword(String password) {
        this.password = Encryption.hash(password);
    }
    public String getPassword() {
        return this.password;
    }
    public Note convertToPublic() {
        Note retNote =  new Note(this.getName());
        int idx = 0;
        while(this.getImages().get(idx) != null) {
            retNote.insertImage(this.getImages().get(idx));
            idx++;
        }
        idx = 0;
        retNote.attachSketch(super.getSketch());
        return retNote;
    }
}