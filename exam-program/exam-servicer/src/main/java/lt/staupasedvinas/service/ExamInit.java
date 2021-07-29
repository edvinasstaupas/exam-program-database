package lt.staupasedvinas.service;

import dao.exceptions.NoDataWithSuchIDException;
import dao.exceptions.NoDataWithSuchTypeException;
import lt.staupasedvinas.dao.DaoManager;
import lt.staupasedvinas.dao.QuestionnaireDao;
import lt.staupasedvinas.service.exam_solving_service.ExamSolvingService;
import lt.staupasedvinas.util.IDGenerator;
import lt.staupasedvinas.util.PrintUtil;
import org.hibernate.Session;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.exit;

public class ExamInit {

    private final Scanner scanner;
    private final Session session;

    public ExamInit() {
        scanner = new Scanner(System.in);
        session = DaoManager.getSession();
        IDGenerator.initIDs(session);
    }

    public void init() {
        print("Enter your name: ");
        String name = scanner.next();
        String commands = """
                1 start exam
                2 
                3 
                4 
                5 
                6 
                7 
                8 
                ? print commands
                0 exit program""";
        print(commands);
        while (true) {
            print("Enter next command: ");
            switch (getStringInput()) {
                case "1" -> solveExam(name);
                //case "2" -> ();
                //case "3" -> ();
                //case "4" -> ();
                //case "5" -> ();
                //case "6" -> ();
                //case "7" -> ();
                case "?" -> print(commands);
                case "0" -> exit(0);
                default -> printBadInput();
            }
        }
    }

    private void solveExam(String name) {
        Long id = null;
        while (id == null) {
            print("Would you like to see what kind of questionnaire types our program has? Yes/no");
            String typeInput = getStringInput();
            if (typeInput.toLowerCase(Locale.ROOT).equals("yes")) {
                getAllTypes();
                print("What type of questionnaire would you like to solve? Please enter type name: ");
                String type = getStringInput();
                try {
                    id = new QuestionnaireDao(session).getByType(type);
                } catch (NoDataWithSuchTypeException e) {
                    //e.printStackTrace();
                    print(e.getMessage());
                    id = null;
                }
            } else if (typeInput.toLowerCase(Locale.ROOT).equals("no")) {
                print("Then please enter questionnaire id: ");
                id = getLongInput();
            } else {
                printBadInput();
            }
            if (id != null) {
                ExamSolvingService examSolvingService = new ExamSolvingService(session, name, id);
                try {
                    examSolvingService.startExam();
                } catch (NoDataWithSuchIDException e) {
                    //e.printStackTrace();
                    print(e.getMessage());
                    id = null;
                }
            }
        }
    }

    private void getAllTypes() {
        List<String> types = new QuestionnaireDao(session).loadAllDistinctTypes();
        print("Found types: ");
        types.forEach(this::print);
    }

    private Long getLongInput() {
        Long longInput = null;
        boolean badOrNoInput = true;
        while (badOrNoInput) {
            try {
                longInput = scanner.nextLong();
                badOrNoInput = false;
            } catch (InputMismatchException e) {
                printBadInput();
            }
        }
        return longInput;
    }

    private String getStringInput() {
        return scanner.next();
    }

    private void print(String string) {
        PrintUtil.print(string);
    }

    private void printBadInput() {
        PrintUtil.printBadInput();
    }
}
