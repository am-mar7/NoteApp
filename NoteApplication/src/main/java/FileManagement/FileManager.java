package FileManagement;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import NotePackge.Note;
import NotePackge.Secured_Note;
import com.dlsc.formsfx.model.structure.Field;

public abstract class FileManager{
    public static final String baselocation = "/home/ammar/Downloads/LocalDataBase/";
    public static final String AllUsersDataPath = baselocation+"AllUsersData.json";
    public static final String currentUserPath =baselocation+"currUser.json";
    public static final String currentNotePath = baselocation+"currNote.json";
    @SuppressWarnings("unchecked")
    public static LinkedList<User> UploadAllData(){
        LinkedList<User> data;
        File file = new File(AllUsersDataPath);
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            data = (LinkedList<User>) ois.readObject();
            ois.close();
            return data;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return new LinkedList<User>();
    }
    public static void addUserInSystem(User newuser) {
//		System.out.println(isUserExist(newuser));
        if(isUserExist(newuser))return;
        String location = baselocation + newuser.UserName();
        File file = new File(location);
        LinkedList <User> data = FileManager.UploadAllData();
        data.add(newuser);
        FileManager.updateAllUsersData(data);
        file.mkdir();
    }
    public static void deleteUserFromSystem(User user) {
        String location = baselocation + user.UserName();
        File file = new File(location);
        LinkedList <User> data = FileManager.UploadAllData();
        int idx = 0;
//		System.out.println("Processing");
        while(idx < data.size()) {
            if(data.get(idx).UserName().equals(user.UserName())) {
                data.remove(idx);
                break;
            }
            idx++;
        }
//		System.out.println("Done");
        FileManager.updateAllUsersData(data);
        file.delete();
    }
    public static void updateAllUsersData(LinkedList<User> newData) {
        File file =  new File(AllUsersDataPath);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(newData);
            oos.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public static boolean isUserExist (User user) {
        LinkedList <User> data = FileManager.UploadAllData();
        int idx = 0;
        while(idx < data.size()) {
            if(data.get(idx).UserName().equals(user.UserName()) && data.get(idx).Password().equals(user.Password()))return true;
            idx++;
        }
        return false;
    }
    public static boolean updateUserData(User olduser , User newuser) {
        LinkedList <User> data = FileManager.UploadAllData();
        if(! FileManager.isUserExist(olduser)) return false;
        int idx = 0;
        while(data.get(idx) != null) {
            if(data.get(idx).equals(olduser)) {
                data.set(idx, newuser);
            }
            idx++;
        }
        FileManager.updateAllUsersData(data);
        return true;
    }
    public static void addNoteINSystem(Note note, String username) {
        File file = new File(baselocation + username +'/'+ note.getName());
        try {
            ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(file));
            oos.writeObject(note);
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    public static void addNoteINSystem(Secured_Note note, String username) {
        File file = new File(baselocation + username +'/'+ note.getName());
        try {
            ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(file));
            oos.writeObject(note);
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    public static User get_CurrentUser(){
        File file = new File(currentUserPath);
        User newuser = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            newuser = (User)ois.readObject();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return newuser;
    }
    public static Secured_Note get_CurrentNote(){
        File file = new File(currentNotePath);
        Secured_Note newNote = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            newNote = (Secured_Note)ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return newNote;
    }
    public static void setCurrentNote(Secured_Note note){
        File file = new File(currentNotePath);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(note);
            System.out.println("note updated");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void setCurrentUser(User currentUser){
        File file = new File(currentUserPath);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(currentUser);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}