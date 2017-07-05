/*

Design data structures and algorithms for in-memory file system
Explain the data structures and algorithms that you would use to design an in-memory file system.
 Illustrate with an example in the code logic where possible.

 */
package miscellaneous;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 24/06/17.
 */


abstract class Entry {

    private String name;
    private Directory parent;
    private long createdAt;
    private long lastUpdatedAt;
    private long lastAccessedAt;

    public Entry(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
        long currentTime = System.currentTimeMillis();
        this.createdAt = currentTime;
        this.lastAccessedAt = currentTime;
        this.lastUpdatedAt = currentTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Directory getParent() {
        return parent;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(long lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public long getLastAccessedAt() {
        return lastAccessedAt;
    }

    public void setLastAccessedAt(long lastAccessedAt) {
        this.lastAccessedAt = lastAccessedAt;
    }
}

class Directory extends Entry {

    List<Directory> directoryList;
    List<File> fileList;
    int size;

    public Directory(String name, Directory parent) {
        super(name, parent);
        directoryList = new ArrayList<>();
        fileList = new ArrayList<>();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public int getEntireCount() {
        int count =0;
        count+= fileList.size();
        for (Directory directory : directoryList) {
            count++;
            count += directory.fileList.size();
        }

        return count;
    }

    public void delete() {
        Directory parent = this.getParent();
        if(null!=parent) {
            parent.directoryList.remove(this);
        }
    }

    public void setDirectoryList(List<Directory> directoryList) {
        this.directoryList = directoryList;
        size+= directoryList.size();
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
        size = fileList.size();
    }
}

class File extends Entry {

    private String content;
    int size;

    public File(String name, Directory parent,int size) {
        super(name, parent);
        this.size = size;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSize() {
        return size;
    }

    public void delete() {
        Directory parent = this.getParent();
        if(null!=parent) {
            parent.fileList.remove(this);
        }
    }

}

public class InMemoryFileSystem {

    public static void main(String[] args) {

        Directory root = new Directory("root",null);


    }


}

/*

A file system, in its most simplistic version, consists of Files and Directories.
 Each Directory contains a set of Files and Directories. Since Files and Directories share so many characteristics,
 we’ve implemented them such that they inherit from the same class, Entry.

 */