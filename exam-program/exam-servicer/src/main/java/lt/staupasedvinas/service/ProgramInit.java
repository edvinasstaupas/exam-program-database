package lt.staupasedvinas.service;

import lt.staupasedvinas.dao.DaoManager;
import lt.staupasedvinas.pojo.statistics.OverallStatistics;
import lt.staupasedvinas.pojo.statistics.PersonalStatistics;
import lt.staupasedvinas.service.exam_solving_service.ExamSolvingService;
import lt.staupasedvinas.service.question_services.QuestionAddingService;
import lt.staupasedvinas.service.question_services.QuestionEditingService;
import lt.staupasedvinas.service.statistics_service.OverallStatisticsCreatingService;
import lt.staupasedvinas.service.statistics_service.PersonalStatisticsCreatingService;
import lt.staupasedvinas.util.IDGenerator;
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
                4 see personal statistics
                5 see overall statistics
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
                case "4" -> printPersonalStatistics(name);
                case "5" -> printOverallStatistics();
                case "?" -> print(commands);
                case "0" -> exit(0);
                default -> printBadInput();
            }
        }
    }

    private void printOverallStatistics() {
        OverallStatistics overallStatistics = new OverallStatisticsCreatingService(session).init();
        System.out.println(overallStatistics.toString());
    }

    private void printPersonalStatistics(String name) {
        PersonalStatistics personalStatistics = new PersonalStatisticsCreatingService(session, name).init();
        System.out.println(personalStatistics.toString());
    }
}
