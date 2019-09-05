/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

import data7.Exporter;
import data7.ResourcesPath;
import data7.model.Data7;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

/**
 *
 * @author aayush.garg
 */
public class D7toX_InteractWithData7 {

    private Scanner scanner;

    public D7toX_InteractWithData7() {
        try {
            scanner = new Scanner(System.in);
            InteractWithUser();
        } catch (Exception ex) {
            System.out.println("Exception at D7toX_InteractWithData7.D7toX_InteractWithData7" + ex.toString());
        }
    }

    private void InteractWithUser() {
        try {
            System.out.println("Please enter the absolute path of the object file (e.g. C:\\FolderA\\Folder1\\Data7ObjectName.obj): ");
            String objPath = scanner.nextLine();
            File objFile = new File(objPath);
            System.out.println("Reading object file: " + objFile);
            if (objFile.exists()) {
                InteractWithD7(objFile);
            } else {
                System.out.println("File not found. Please check the path...");
            }
        } catch (Exception ex) {
            System.out.println("Exception at D7toX_InteractWithData7.InteractWithUser" + ex.toString());
        }
    }

    private void InteractWithD7(File objFile) {
        try {
            FileInputStream fileIn = new FileInputStream(objFile);
            ObjectInputStream read = new ObjectInputStream(fileIn);
            Data7 d7Data = (Data7) read.readObject();

            ResourcesPath path = new ResourcesPath(objFile.getParent());
            Exporter exporter = new Exporter(path);
            exporter.exportDatasetToXML(d7Data);
            read.close();
            fileIn.close();
            System.out.println("XML file saved at " + path.getXmlPath());

        } catch (Exception ex) {
            System.out.println("Exception at D7toX_InteractWithData7.InteractWithD7" + ex.toString());
        }
    }
}
