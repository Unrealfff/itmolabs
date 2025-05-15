package org.unrealfff.data;

import org.unrealfff.data.unit.Request;
import org.unrealfff.data.unit.Response;
import org.unrealfff.data.unit.Route;
import org.unrealfff.io.Router;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class FormatWorker{
    /**
     * formatworker handles file input/output
     */
    String fileName;
    Router router;
    private String initTime;
    public FormatWorker(String fileName, Router router) {
        this.fileName = fileName;
        this.router = router;
    }
    public Response save(LinkedHashSet<Route> collection) {
        Iterator<Route> iter = collection.iterator();
        try{
            FileWriter fileWriter = new FileWriter(this.fileName);
            while(iter.hasNext()) {
                Route i = iter.next();
                //System.out.print(i.toString() + "\n");
                fileWriter.write(i + "\n");
            }
            fileWriter.close();
            return new Response("saved collection to " + this.fileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new Response("unable to save collection");
    }
    public Response executeScript(String filename) {
        File f = new File(filename);
        if(f.exists() && !f.isDirectory()) {
            String strValues = "";
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filename))) {
                int data;
                while ((data = bis.read()) != -1) {
                    strValues += (char) data;
                }
                //System.out.println(strValues);
                router.chmod(strValues);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new Response("");
        }
        else{
            return new Response("script " + filename + " does not exists");
        }
    }
    public LinkedHashSet<Route> getFromFile() {
        File f = new File(this.fileName);
        /*if(!f.exists() || f.isDirectory()) {
            FileNotFoundException e =  new FileNotFoundException("no such file");
            e.printStackTrace();
        }*/
        if(f.exists() && !f.isDirectory()) {
            LinkedHashSet<Route> ans = new LinkedHashSet<>();
            String strValues = "";
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f))) {
                int data;
                this.initTime = Files.readAttributes(f.toPath(), BasicFileAttributes.class).creationTime().toString();
                while ((data = bis.read()) != -1) {
                    strValues += (char) data;
                }
                if (!strValues.isEmpty()) {
                    try {
                        String[] splitData = strValues.split("\n");
                        for(int i = 0; i < splitData.length; i++) {
                            ans.add(Route.valueOf(splitData[i]));
                        }
                    }
                    catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ans;
        }
        else{
            this.initTime = LocalDate.now().toString();
            return new LinkedHashSet<>();
        }
    }

    public String getCreationTime() {
        return this.initTime;
    }
}
