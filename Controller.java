import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Controller
{
   // Class Instance Data:
   private static LinkedList<StudentEmail> studentLinkedList = new LinkedList<StudentEmail> ();
   private static int editIndex;
   private static JFrame parentJFrame;


   public static void setParent (JFrame parent)
   {
      parentJFrame = parent;
   }

   private static boolean isStudentIdInLinkedList (String idStr)
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


   public static boolean addStudent (String idStr, String nameStr)
   {
      boolean result = false; // Student not added.

      if (isStudentIdInLinkedList (idStr) == true)
      {
         JOptionPane.showMessageDialog (parentJFrame,
                              "Error: student ID is already in the database.");
      }
      else
      {
         try
         {
            StudentEmail stud = new StudentEmail (nameStr,
                                                  idStr);

            result = true; // Add successful. :)
            studentLinkedList.add (stud);
         }
         catch (StudentEmailException error)
         {
            JOptionPane.showMessageDialog (parentJFrame, error.toString ());
            // myLabel.setText (error.toString ());
         }
      }

      return result;
   }

   public static boolean deleteStudent (String idStr)
   {
      boolean result = false; // e.g. Student does note exist.

      if (studentLinkedList.size() == 0)
      {
         JOptionPane.showMessageDialog (parentJFrame,
                                        "Error: Database is empty.");
      }
      else if (isStudentIdInLinkedList (idStr) == false)
      {
         JOptionPane.showMessageDialog (parentJFrame,
                                       "Error: student ID is not in the database.");
      }
      else
      {
         for (int s = 0; s < studentLinkedList.size(); s++)
         {
            String currId = studentLinkedList.get (s).getId ();

            if (currId.compareToIgnoreCase (idStr) == 0)
            {
               studentLinkedList.remove (s);
               result = true;                // Student deleted.
               s = studentLinkedList.size(); // Exit Loop!
            }
         }
      }

      return result;
   }

   public static boolean editStudent (String idStr)
   {
      boolean result = false; // e.g. Student does note exist.

      editIndex = -1;

      if (studentLinkedList.size() == 0)
      {
         JOptionPane.showMessageDialog (parentJFrame,
                                        "Error: Database is empty.");
      }
      else if (isStudentIdInLinkedList (idStr) == false)
      {
         JOptionPane.showMessageDialog (parentJFrame,
                                        "Error: student ID is not in the database.");
      }
      else
      {
         editIndex = -1;

         for (int s = 0; s < studentLinkedList.size(); s++)
         {
            String currId = studentLinkedList.get (s).getId ();

            if (currId.compareToIgnoreCase (idStr) == 0)
            {
               editIndex = s;
               result = true;                // Student found.
               s = studentLinkedList.size(); // Exit Loop
            }
         }
      }

      return result;
   }

   public static String getEditedStudentName ()
   {
      if (editIndex >= 0)
      {
         return studentLinkedList.get (editIndex).getName ();
      }
      else
      {
         return "";
      }
   }

   public static void setEditedStudentIdAndName (String idStr, String nameStr)
   {
      if (editIndex >= 0)
      {
         studentLinkedList.get (editIndex).setName (nameStr );
         studentLinkedList.get (editIndex).setId   (idStr );
      }
   }


   public static String asString()
   {
      String outStr = "";

      for (StudentEmail stud : studentLinkedList)
      {
         outStr = outStr + stud.toString() + "\n";
      }

      return outStr;
   }



} // public class Controller
