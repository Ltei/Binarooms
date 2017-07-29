package lteii.binarooms.utils.fileExplorer;

import android.support.annotation.NonNull;

public class FileExplorerItem implements Comparable<FileExplorerItem> {


    private final String name;
    private final String data;
    private final String date;
    private final String path;
    private final String image;

    public FileExplorerItem(String name, String data, String date, String path, String image) {
        this.name = name;
        this.data = data;
        this.date = date;
        this.path = path;
        this.image = image;
    }


    public String getName() {return name;}
    public String getData() {return data;}
    public String getDate() {return date;}
    public String getPath() {return path;}
    public String getImage() {return image;}

    @Override
    public int compareTo(@NonNull FileExplorerItem o) {
        if (name == null) throw new IllegalStateException();
        return name.toLowerCase().compareTo(o.name.toLowerCase());
    }


}
