import com.sun.tools.internal.ws.wscompile.DefaultAuthTester;

import javax.swing.*;
import java.io.Console;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: nate
 * Date: 5/4/12
 * Time: 8:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class CaseMethod {
    private static boolean[] UsedPoints;
    private static Stack Scenario;
    private static int SolutionsFound;
    private static int Branches;


    public static void main(String[] args)
    {
        Scenario = new Stack();
        long StartTime = System.currentTimeMillis();
        ArrayList<Integer> StartingList = new ArrayList<Integer>();
        StartingList.add(1);
        StartingList.add(2);
        StartingList.add(3);
        StartingList.add(4);
        StartingList.add(5);
        StartingList.add(6);
        StartingList.add(7);
        StartingList.add(8);
        StartingList.add(9);
        StartingList.add(10);
        InnerIterator(StartingList);
        long TotalTime = (System.currentTimeMillis() - StartTime);

        System.out.println("Solutions Found: " + SolutionsFound);
        System.out.println("Processing time in (ms):" + TotalTime);
        System.out.println("Branches:" + Branches);


    }

    public static void InnerIterator(List<Integer> AvailablePoints)
    {
        for (Integer point = 0; point < AvailablePoints.size(); point++)
        {
            Scenario.push(AvailablePoints.get(point));
            Branches++;
            switch (Scenario.size())
            {
                case 1:
                case 2:
                case 3:
                case 5:
                case 7:
                case 8:
                    Recurse(AvailablePoints, point); continue;

                case 4:
                    if (FailsFirstInnerTest())
                    {
                        Scenario.pop(); continue;
                    }
                    else
                    {
                        Recurse(AvailablePoints, point); continue;
                    }

                case 6:
                    if (FailsSecondInnerTest())
                    {
                        Scenario.pop(); continue;
                    }
                    else
                    {
                        Recurse(AvailablePoints, point); continue;
                    }

                case 9:
                    if (FailsOuterTest())
                    {
                        Scenario.pop(); continue;
                    }
                    else
                    {
                        Recurse(AvailablePoints, point); continue;
                    }

                case 10:
                    //Print the solution as a rough looking triangle
                    System.out.println(Scenario.elementAt(6));
                    System.out.println(Scenario.elementAt(0) + " " + Scenario.elementAt(2));
                    System.out.println(Scenario.elementAt(4) + " " + Scenario.elementAt(9) + " " + Scenario.elementAt(5));
                    System.out.println(Scenario.elementAt(7) + " " + Scenario.elementAt(3) + " " + Scenario.elementAt(1) + " "
                            + Scenario.elementAt(8));
                    System.out.println();
                    SolutionsFound++;
                    Scenario.pop(); continue;
            }
        }
    }

    private static void Recurse(List<Integer> AvailablePoints, Integer point) {
        Integer save = AvailablePoints.get(point.intValue());
        AvailablePoints.remove(point.intValue());
        InnerIterator(AvailablePoints);
        AvailablePoints.add(point.intValue(), save);
        Scenario.pop();
    }

    private static boolean FailsFirstInnerTest() {

        Integer first = (Integer)(Scenario.elementAt(0))
                + (Integer)(Scenario.elementAt(1));

        Integer second = (Integer)(Scenario.elementAt(2))
                + (Integer)(Scenario.elementAt(3));

        if (first.equals(second))
        {
            return false;
        }
        return true;

    }

    private static boolean FailsSecondInnerTest() {

        Integer first = (Integer)(Scenario.elementAt(2))
                + (Integer)(Scenario.elementAt(3));

        Integer second = (Integer)(Scenario.elementAt(4))
                + (Integer)(Scenario.elementAt(5));

        if (first.equals(second))
        {
            return false;
        }
        return true;

    }

    private static boolean FailsOuterTest() {
        Integer first = (Integer)(Scenario.elementAt(6))
                + (Integer)(Scenario.elementAt(0))
                + (Integer)(Scenario.elementAt(4))
                + (Integer)(Scenario.elementAt(7));

        Integer second = (Integer)(Scenario.elementAt(6))
                + (Integer)(Scenario.elementAt(2))
                + (Integer)(Scenario.elementAt(5))
                + (Integer)(Scenario.elementAt(8));

        Integer third = (Integer)(Scenario.elementAt(7))
                + (Integer)(Scenario.elementAt(3))
                + (Integer)(Scenario.elementAt(1))
                + (Integer)(Scenario.elementAt(8));


        if (first.equals(second) && second.equals(third))
        {
            return false;
        }
        return true;
    }


}
