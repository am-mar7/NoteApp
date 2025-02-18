package FileManagement;
import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;

import NotePackge.Note;
import NotePackge.Secured_Note;

public class Folder implements Serializable{
    private static final long serialVersionUID = 1356537549738799942L;
    private String baselocation = "/home/ammar/Downloads/LocalDataBase/";
    private LinkedList <Note> AllNotes;
    private LinkedList <Secured_Note> AllSecNotes;
    private String name ;
    int x = 0;
    public Folder(String name) {
        AllNotes = new LinkedList<Note>();
        this.name = name;
    }
    public void add (Note note) {
        AllNotes.add(note);
        FileManager.addNoteINSystem(note, this.name);
        LinkedList<User> data = FileManager.UploadAllData();
        int idx = 0;
        while(idx < data.size()) {
            if(data.get(idx).UserName().equals(this.name)) {
                data.get(idx).setFolder(this);
                System.out.println("Folder added");
            }
            idx++;
        }
        FileManager.updateAllUsersData(data);
    }
    public void remove (Note note) {
        AllNotes.remove(note);
        File file = new File("/home/ammar/Downloads/LocalDataBase/"+this.name+'/'+note.getName());
        LinkedList<User> data = FileManager.UploadAllData();
        int idx = 0;
        while(idx < data.size()) {
            if(data.get(idx).UserName().equals(this.name)) {
                data.get(idx).setFolder(this);
            }
            idx++;
        }
        FileManager.updateAllUsersData(data);
        file.delete();
    }
    public void remove (int idx) {
        System.out.println("remove "+idx);
        this.remove(AllNotes.get(idx));
    }
    public void update (Note note , int index) {
        this.remove(index);
        this.add(note);
    }
    public LinkedList<Note> getAllNotes(){
        return AllNotes;
    }
    public void DisplayAllNotes (){
        int idx = 0;
        while(idx < AllNotes.size()) {
            System.out.println(AllNotes.get(idx).getName());
            idx++;
        }
    }
}