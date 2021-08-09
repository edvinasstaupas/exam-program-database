package lt.staupasedvinas.service;

import dao.exceptions.NoDataWithSuchIDException;
import dao.exceptions.NoDataWithSuchTypeException;
import lt.staupasedvinas.dao.DaoManager;
import lt.staupasedvinas.dao.QuestionnaireDao;
import lt.staupasedvinas.pojo.Statistic;
import lt.staupasedvinas.service.exam_solving_service.ExamSolvingService;
import lt.staupasedvinas.service.question_services.QuestionAddingService;
import lt.staupasedvinas.service.question_services.QuestionEditingService;
import lt.staupasedvinas.util.IDGenerator;
import lt.staupasedvinas.util.PrintUtil;
import org.hibernate.Session;

import java.util.*;

import static java.lang.System.exit;
import static lt.staupasedvinas.util.InputUtil.*;
import static lt.staupasedvinas.util.PrintUtil.print;
import static lt.staupasedvinas.util.PrintUtil.printBadInput;

public class ProgramInit {

    private final Session session;

    public ProgramInit() {
        session = DaoManager.getSession();
        IDGenerator.initIDs(session);
    }

    public void init() {
        String commands = """
                1 start exam
                2 create new question
                3 edit questions
                4 see statistics
                5 
                6 
                7 
                8 
                ? print commands
                0 exit program""";
        print(commands);
        Scanner scanner = new Scanner(System.in);
        print("Enter your name: ");
        String name = getStringInput();
        while (true) {
            print("Enter next command: ");
            switch (getStringInput()) {
                case "1" -> new ExamSolvingService(session, name).init();
                case "2" -> new QuestionAddingService(session).init();
                case "3" -> new QuestionEditingService(session).init();
                case "4" -> printStatistic();
                //case "5" -> ();
                //case "6" -> ();
                //case "7" -> ();
                case "?" -> print(commands);
                case "0" -> exit(0);
                default -> printBadInput();
            }
        }
    }

    private void printStatistic() {
        //Statistic statistic = new Statistic();
    }
}
