package lteii.binarooms.utils.fileExplorer;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import lteii.binarooms.R;

public class ActFileExplorer extends AppCompatActivity {


    private File currentDir = null;
    private ListView parentList = null;
    private FileArrayAdapter adapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parentList = new ListView(this);
        parentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final FileExplorerItem fileItem = adapter.getItem(position);
                if(fileItem.getImage().equalsIgnoreCase("directory_icon") || fileItem.getImage().equalsIgnoreCase("directory_up")){
                    currentDir = new File(fileItem.getPath());
                    setupView(currentDir);
                } else {
                    final Intent intent = new Intent();
                    intent.putExtra("GetPath", currentDir.toString());
                    intent.putExtra("GetFileName", fileItem.getName());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        addContentView(parentList, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        currentDir = Environment.getExternalStorageDirectory(); //new File("/sdcard/");
        setupView(currentDir);
    }

    private void setupView(File currentDir) {
        this.setTitle("Current Dir: "+currentDir.getName());

        if (!currentDir.exists()) throw new IllegalStateException();
        if (!currentDir.isDirectory()) throw new IllegalStateException();
        final File[] fileContent = currentDir.listFiles();
        if (fileContent == null) throw new IllegalStateException();
        final List<FileExplorerItem> fileContentDir = new ArrayList<>();
        final List<FileExplorerItem> fileContentFile = new ArrayList<>();

        try {
            for(File content : fileContent) {
                final Date lastModifiedDate = new Date(content.lastModified());
                final DateFormat formater = DateFormat.getDateTimeInstance();
                final String lastModifiedDateStr = formater.format(lastModifiedDate);

                if (content.isDirectory()) {
                    final File[] fileBuffer = content.listFiles();
                    final int fileBufferLength = (fileBuffer != null) ? fileBuffer.length : 0;
                    String fileBufferLengthStr = String.valueOf(fileBufferLength) + " item";
                    if (fileBufferLength > 1) fileBufferLengthStr = fileBufferLengthStr + "s";
                    fileContentDir.add(new FileExplorerItem(content.getName(),fileBufferLengthStr,lastModifiedDateStr,content.getAbsolutePath(),"directory_icon"));
                } else {
                    fileContentFile.add(new FileExplorerItem(content.getName(),content.length() + " Byte", lastModifiedDateStr, content.getAbsolutePath(),"file_icon"));
                }
            }
        } catch(Exception e) {}

        Collections.sort(fileContentDir);
        Collections.sort(fileContentFile);
        fileContentDir.addAll(fileContentFile);
        if(!currentDir.getName().equalsIgnoreCase("sdcard")) {
            fileContentDir.add(0, new FileExplorerItem("..", "Parent Directory", "", currentDir.getParent(), "directory_up"));
        }

        adapter = new FileArrayAdapter(this, R.layout.layout_file_view, fileContentDir);
        parentList.setAdapter(adapter);
    }

}
