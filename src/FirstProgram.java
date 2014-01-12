import com.sun.tools.internal.ws.wscompile.DefaultAuthTester;

import java.io.Console;
import java.util.Arrays;
import java.util.Stack;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: nate
 * Date: 5/4/12
 * Time: 8:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class FirstProgram {
    private static boolean[] UsedPoints;
    private static Stack Scenario;
    private static int SoultionsFound;
    private static int Branches;


    public static void main(String[] args)
    {
        UsedPoints = new boolean[11];
        Arrays.fill(UsedPoints, false);

        Scenario = new Stack();
        long StartTime = System.currentTimeMillis();
        InnerIterator();
        long TotalTime = (System.currentTimeMillis() - StartTime);

        System.out.println("Solutions Found: " + SoultionsFound);
        System.out.println("Processing time in (ms):" + TotalTime);
        System.out.println("Branches:" + Branches);


    }

    public static void InnerIterator()
    {
        for (Integer point = 1; point < 11; point++)
        {
            if (Scenario.size() == 6)
            {
                TestInnerScenario();
                break;
            }

            if (UsedPoints[point])
                continue;

            UsedPoints[point] = true;
            Scenario.push(point);
            Branches++;
            InnerIterator();
            Scenario.pop();
            UsedPoints[point] = false;
        }
    }

    private static void TestInnerScenario() {

        Integer first = (Integer)(Scenario.elementAt(0))
                + (Integer)(Scenario.elementAt(5));

        Integer second = (Integer)(Scenario.elementAt(1))
                + (Integer)(Scenario.elementAt(4));

        Integer third = (Integer)(Scenario.elementAt(2))
                + (Integer)(Scenario.elementAt(3));

        if (first.equals(second) && second.equals(third))
        {
            OuterIterator();
        }
    }

    private static void OuterIterator() {
        for (Integer point = 1; point < 11; point++)
        {
            if (Scenario.size() == 10)
            {
                TestOuterScenario();
                break;
            }

            if (UsedPoints[point])
                continue;

            UsedPoints[point] = true;
            Scenario.push(point);
            OuterIterator();
            Scenario.pop();
            UsedPoints[point] = false;
        }
    }

    private static void TestOuterScenario() {
        Integer first = (Integer)(Scenario.elementAt(6))
                + (Integer)(Scenario.elementAt(0))
                + (Integer)(Scenario.elementAt(2))
                + (Integer)(Scenario.elementAt(7));

        Integer second = (Integer)(Scenario.elementAt(6))
                + (Integer)(Scenario.elementAt(1))
                + (Integer)(Scenario.elementAt(3))
                + (Integer)(Scenario.elementAt(8));

        Integer third = (Integer)(Scenario.elementAt(7))
                + (Integer)(Scenario.elementAt(4))
                + (Integer)(Scenario.elementAt(5))
                + (Integer)(Scenario.elementAt(8));

        if (first.equals(second) && second.equals(third))
        {
            //Print the solution as a rough looking triangle
            System.out.println(Scenario.elementAt(6));
            System.out.println(Scenario.elementAt(0) + " " + Scenario.elementAt(1));
            System.out.println(Scenario.elementAt(2) + " " + Scenario.elementAt(9) + " " + Scenario.elementAt(3));
            System.out.println(Scenario.elementAt(7) + " " + Scenario.elementAt(4) + " " + Scenario.elementAt(5) + " "
                    + Scenario.elementAt(8));
            System.out.println();
            SoultionsFound++;
        }
    }


}
