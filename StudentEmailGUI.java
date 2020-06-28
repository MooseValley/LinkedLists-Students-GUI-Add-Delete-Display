/*
      Author: Moose OMalley
 Source File: StudentEmailGUI.java
 Description: Part of the "Java - LinkedLists - Students GUI - Add, Delete, Display"
              video tutorials.  The latest source code.

The Students GUI Linked List tutorial series:
* Java - LinkedLists - Students GUI - Add, Delete, Display - Part 01
https://youtu.be/0KdG9DZgi6I
* Java - LinkedLists - Students GUI - Add, Delete, Display - Part 02 - Edit
https://youtu.be/LXMg1E6gJJ0
* Java - LinkedLists - Students GUI - Add, Delete, Display - Part 03 - NetBeans
https://youtu.be/xrNSa0nXDLE

Java Source code is in my GitHub:
* https://github.com/MooseValley/LinkedLists-Students-GUI-Add-Delete-Display

See my other Java videos:
* Mike's Java Software Development Tutorial Videos:
http://tinyurl.com/MikesJavaVideos

*/

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class StudentEmailGUI extends JFrame
{
   // Constants:

   // GUI Componet:
   JTextArea  studentTextArea   = new JTextArea ();

   JLabel     idLabel           = new JLabel     ("ID: ");
   JTextField idTextField       = new JTextField (10);
   JLabel     nameLabel         = new JLabel     ("Name: ");
   JTextField nameTextField     = new JTextField (10);

   JButton    testDataButton    = new JButton ("Test Data");
   JButton    addButton         = new JButton ("Add");
   JButton    deleteButton      = new JButton ("Delete");
   JButton    editButton        = new JButton ("Edit");
   JButton    editSaveButton    = new JButton ("Save");
   JButton    displayAllButton  = new JButton ("Display All");
   JButton    exitButton        = new JButton ("Exit");


   // Class Instance Data:
   private LinkedList<StudentEmail> studentLinkedList = new LinkedList<StudentEmail> ();
   private int editIndex;


   public StudentEmailGUI ()
   {
      JPanel flow1Panel = new JPanel (new FlowLayout (FlowLayout.CENTER));
      JPanel flow2Panel = new JPanel (new FlowLayout (FlowLayout.CENTER));
      JPanel gridPanel  = new JPanel (new GridLayout (2, 1));

      studentTextArea.setEditable (false);

      flow1Panel.add (idLabel);
      flow1Panel.add (idTextField);
      flow1Panel.add (nameLabel);
      flow1Panel.add (nameTextField);

      flow2Panel.add (testDataButton);
      flow2Panel.add (addButton);
      flow2Panel.add (editButton);
      flow2Panel.add (editSaveButton);
      flow2Panel.add (deleteButton);
      flow2Panel.add (displayAllButton);
      flow2Panel.add (exitButton);

      gridPanel.add (flow1Panel);
      gridPanel.add (flow2Panel);

      editSaveButton.setEnabled (false);

      add (studentTextArea, BorderLayout.CENTER);
      add (gridPanel,       BorderLayout.SOUTH);


      addButton.addActionListener        (event -> addStudent ());
      displayAllButton.addActionListener (event -> displayAll ());
      editButton.addActionListener       (event -> editStudent ());
      editSaveButton.addActionListener   (event -> editSaveStudent ());
      exitButton.addActionListener       (event -> exitApplication ());
      deleteButton.addActionListener     (event -> deleteStudent ());
      testDataButton.addActionListener   (event -> addTestData ());

      setTitle ("Student Linked List - v0.02");
   }

   private boolean isStudentIdInLinkedList (String idStr)
   {
      boolean inList = false;

      for (StudentEmail stud : studentLinkedList)
      {
         if (stud.getId ().compareToIgnoreCase (idStr) == 0)
         {
            inList = true;
         }
      }

      return inList;
   }

   private void addStudent ()
   {
      if (isStudentIdInLinkedList (idTextField.getText()) == true)
      {
         JOptionPane.showMessageDialog (StudentEmailGUI.this,
                              "Error: student ID is already in the database.");
      }
      else
      {
         try
         {
            StudentEmail stud = new StudentEmail (nameTextField.getText(),
                                                  idTextField.getText());

            studentLinkedList.add (stud);

            displayAll ();

            nameTextField.setText("");
            idTextField.setText("");


         }
         catch (StudentEmailException error)
         {
            JOptionPane.showMessageDialog (StudentEmailGUI.this, error.toString ());
            // myLabel.setText (error.toString ());


         }

      }
   }

   private void deleteStudent ()
   {
      if (studentLinkedList.size() == 0)
      {
         JOptionPane.showMessageDialog (StudentEmailGUI.this,
                                        "Error: Database is empty.");
      }
      else if (isStudentIdInLinkedList (idTextField.getText()) == false)
      {
         JOptionPane.showMessageDialog (StudentEmailGUI.this,
                                       "Error: student ID is not in the database.");
      }
      else
      {
         for (int s = 0; s < studentLinkedList.size(); s++)
         {
            String currId = studentLinkedList.get (s).getId ();

            if (currId.compareToIgnoreCase (idTextField.getText()) == 0)
            {
               studentLinkedList.remove (s);
            }
         }

         displayAll ();

         nameTextField.setText("");
         idTextField.setText("");
      }
   }

   private void editStudent ()
   {
      if (studentLinkedList.size() == 0)
      {
         JOptionPane.showMessageDialog (StudentEmailGUI.this,
                                        "Error: Database is empty.");
      }
      else if (isStudentIdInLinkedList (idTextField.getText()) == false)
      {
         JOptionPane.showMessageDialog (StudentEmailGUI.this,
                                        "Error: student ID is not in the database.");
      }
      else
      {
         editIndex = -1;

         for (int s = 0; s < studentLinkedList.size(); s++)
         {
            String currId = studentLinkedList.get (s).getId ();

            if (currId.compareToIgnoreCase (idTextField.getText()) == 0)
            {
               editIndex = s;
               s = studentLinkedList.size(); // Exit Loop
            }
         }

         // index cannot be less than 0, because we checked if the Stud Id was in
         // the linked list before we looped above.
         if (editIndex >= 0)
         {
            editSaveButton.setEnabled   (true);

            editButton.setEnabled       (false);
            testDataButton.setEnabled   (false);
            addButton.setEnabled        (false);
            deleteButton.setEnabled     (false);
            displayAllButton.setEnabled (false);
            exitButton.setEnabled       (false);

            nameTextField.setText (studentLinkedList.get (editIndex).getName () );
            //idTextField.setText   (studentLinkedList.get (editIndex).getId () );
         }


      }

   }

   private void editSaveStudent ()
   {
      // This code will preserve the changes the user made to the student
      // they were editing - and save them back into the Linked List.

      studentLinkedList.get (editIndex).setName (nameTextField.getText() );
      studentLinkedList.get (editIndex).setId   (idTextField.getText() );

      displayAll ();

      nameTextField.setText ("");
      idTextField.setText   ("");

      editSaveButton.setEnabled   (false);

      editButton.setEnabled       (true);
      testDataButton.setEnabled   (true);
      addButton.setEnabled        (true);
      deleteButton.setEnabled     (true);
      displayAllButton.setEnabled (true);
      exitButton.setEnabled       (true);
   }

   private void addTestData ()
   {
      nameTextField.setText ("Moose");
      idTextField.setText   ("s123");
      addStudent ();

      nameTextField.setText ("Frankie");
      idTextField.setText   ("s111");
      addStudent ();

      nameTextField.setText ("Bella");
      idTextField.setText   ("s789");
      addStudent ();
   }

   private void displayAll ()
   {
      studentTextArea.setText ("");

      for (StudentEmail stud : studentLinkedList)
      {
         studentTextArea.append (stud + "\n");
      }
   }

   private void exitApplication ()
   {
      System.exit (0); // All is OK.
   }

   public static void main (String[] args)
   {
      StudentEmailGUI app = new StudentEmailGUI ();
      app.setVisible  (true);
      app.setSize     (500, 310);
      app.setLocation (200, 100);
      app.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
   }
} // public class StudentEmailGUI