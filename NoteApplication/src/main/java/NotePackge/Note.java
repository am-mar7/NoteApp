package NotePackge;

import java.io.Serializable;
import java.util.LinkedList;

import FileManagement.FileManager;

public class Note implements Serializable{
    private static final long serialVersionUID = 2731284516841784590L;
    private String name;
    private LinkedList <String> textContent ;
    private LinkedList <Image> images;
    private Sketch sketch;
    public Note() {
        textContent = new LinkedList<String>();
        images = new LinkedList<Image>();
        sketch = new Sketch();
    }
    public Note(String name) {
        this.name = name;
        textContent = new LinkedList<String>();
        images = new LinkedList<Image>();
        sketch = new Sketch();
    }
    public String getName() {
        return name;
    }
    public void addContent (String Content) {
        textContent.add(Content);
    }
    public void setContent (LinkedList <String> content) {
        this.textContent = content;
    }
    public LinkedList<String> getContent (){
        return textContent;
    }
    public void insertImage (Image image) {
        images.add(image);
    }
    public LinkedList<Image> getImages(){
        return images;
    }
    public void attachSketch (Sketch sketch) {
        this.sketch = sketch;
    }
    public Sketch getSketch(){
        return sketch;
    }
    public Secured_Note convertToSecure(String password) {
        Secured_Note retNote =  new Secured_Note(this.name , password);
        int idx = 0;
        while(this.textContent.get(idx)!= null) {
            retNote.addContent(textContent.get(idx));
            idx++;
        }
        idx = 0;
        while(idx < images.size()) {
            retNote.insertImage(this.images.get(idx));
            idx++;
        }
        idx = 0;
          retNote.attachSketch(this.sketch);
          return retNote;
    }
    public String toString () {
        return this.name;
    }
}